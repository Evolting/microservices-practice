package dev.evolting.quizservice.services.impl;

import dev.evolting.quizservice.dtos.QuestionDTO;
import dev.evolting.quizservice.entities.Quiz;
import dev.evolting.quizservice.entities.Response;
import dev.evolting.quizservice.feign.QuestionInterface;
import dev.evolting.quizservice.repositories.QuizRepository;
import dev.evolting.quizservice.services.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionInterface questionInterface;

    public QuizServiceImpl(
            QuizRepository quizRepository,
            QuestionInterface questionInterface) {
        this.quizRepository = quizRepository;
        this.questionInterface = questionInterface;
    }

    @Override
    public ResponseEntity<List<Quiz>> getAllQuiz() {
        return new ResponseEntity<>(quizRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Quiz> getQuizById(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (!quiz.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(quiz.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> addQuiz(String category, Integer numQ, String title) {
        List<Integer> questionIds = questionInterface.getQuestionsforQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Quiz Added Successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<QuestionDTO>> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        List<QuestionDTO> questionDTOS = questionInterface.getQuestionsByIds(questionIds).getBody();

        return new ResponseEntity<>(questionDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> calculateResult(List<Response> responses) {
        int rightAnswers = 0;
        rightAnswers = questionInterface.getScore(responses).getBody();
        return new ResponseEntity<>(rightAnswers, HttpStatus.OK);
    }
}
