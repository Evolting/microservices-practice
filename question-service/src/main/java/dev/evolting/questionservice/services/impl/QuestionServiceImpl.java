package dev.evolting.questionservice.services.impl;

import dev.evolting.questionservice.dtos.QuestionDTO;
import dev.evolting.questionservice.entities.Question;
import dev.evolting.questionservice.entities.Response;
import dev.evolting.questionservice.repositories.QuestionRepository;
import dev.evolting.questionservice.services.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public ResponseEntity<List<Question>> getAllQuestion() {
        try{
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionRepository.findQuestionsByCategory(category), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> addQuestion(Question question) {
        try{
            Boolean isAdded = questionRepository.save(question) != null;
            return isAdded ?
                    new ResponseEntity<>("Question Added Successfully", HttpStatus.CREATED)
                    : new ResponseEntity<>("Question Not Added", HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Integer>> getQuestionsforQuiz(String category, Integer numQ) {
        List<Integer> questionIds = questionRepository.findRandomQuestionsByCategory(category, numQ);
        return new ResponseEntity<>(questionIds, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<QuestionDTO>> getQuestionsByIds(List<Integer> questionIds) {
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        List<Question> questions = questionRepository.findAllById(questionIds);
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setId(question.getId());
            questionDTO.setQuestionTitle(question.getQuestionTitle());
            questionDTO.setOption1(question.getOption1());
            questionDTO.setOption2(question.getOption2());
            questionDTO.setOption3(question.getOption3());
            questionDTO.setOption4(question.getOption4());
            questionDTOS.add(questionDTO);
        }
        return new ResponseEntity<>(questionDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int rightAnswers = 0;
        for (Response response : responses) {
            Question question = questionRepository.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                rightAnswers++;
            }
        }
        return new ResponseEntity<>(rightAnswers, HttpStatus.OK);
    }
}
