package dev.evolting.rolebaseauthorization.services;

import dev.evolting.rolebaseauthorization.dtos.ClubDTO;
import dev.evolting.rolebaseauthorization.entities.Club;
import dev.evolting.rolebaseauthorization.repositories.ClubRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubService {
    private ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public ResponseEntity<?> getAllClub() {
        List<ClubDTO> clubDTOs = clubRepository.findAll().stream()
                .map(club -> new ClubDTO(club.getId(), club.getName(), club.getDescription()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(clubDTOs);
    }

    public ResponseEntity<?> createClub(Club club) {
        if (club.getPlayers() != null) {
            club.getPlayers().forEach(player -> player.setClub(club));
        }
        Club savedClub = clubRepository.save(club);
        ClubDTO clubDTO = new ClubDTO(savedClub.getId(), savedClub.getName(), savedClub.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(clubDTO);
    }
}
