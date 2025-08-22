package dev.evolting.rolebaseauthorization.services;

import dev.evolting.rolebaseauthorization.dtos.ClubDTO;
import dev.evolting.rolebaseauthorization.dtos.PlayerDTO;
import dev.evolting.rolebaseauthorization.entities.Club;
import dev.evolting.rolebaseauthorization.entities.Player;
import dev.evolting.rolebaseauthorization.repositories.ClubRepository;
import dev.evolting.rolebaseauthorization.repositories.PlayerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;
    private ClubRepository clubRepository;

    public PlayerService(PlayerRepository playerRepository, ClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
    }


    public ResponseEntity<?> getAllPlayer() {
        List<PlayerDTO> playerDTOS = playerRepository.findAll().stream()
                .map(player -> new PlayerDTO(
                        player.getId(),
                        player.getName(),
                        player.getAge(),
                        player.getPosition(),
                        player.getNumber(),
                        (player.getClub() != null) ? player.getClub().getName() : ""
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(playerDTOS);
    }

    public ResponseEntity<?> createPlayer(Player player) {
        return ResponseEntity.ok(playerRepository.save(player));
    }

    public ResponseEntity<?> updatePlayerClub(Player player) {
        // Validate club exists
        Club club = clubRepository.findById(player.getClub().getId()).get();
        player.setClub(club);
        Player savedPlayer = playerRepository.save(player);
        // Map to DTO to avoid proxy issues
        ClubDTO clubDTO = new ClubDTO(club.getId(), club.getName(), club.getDescription());
        PlayerDTO playerDTO = new PlayerDTO(
                savedPlayer.getId(),
                savedPlayer.getName(),
                savedPlayer.getAge(),
                savedPlayer.getPosition(),
                savedPlayer.getNumber(),
                clubDTO.getName()
        );
        return ResponseEntity.ok(playerDTO);
    }
}
