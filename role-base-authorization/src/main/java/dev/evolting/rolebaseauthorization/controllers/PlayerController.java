package dev.evolting.rolebaseauthorization.controllers;

import dev.evolting.rolebaseauthorization.entities.Club;
import dev.evolting.rolebaseauthorization.entities.Player;
import dev.evolting.rolebaseauthorization.services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/get-all-player")
    public ResponseEntity<?> getAllPlayer() {
        return playerService.getAllPlayer();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create-player")
    public ResponseEntity<?> createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update-player-club")
    public ResponseEntity<?> updatePlayerClub(@RequestBody Player player) {
        return playerService.updatePlayerClub(player);
    }
}
