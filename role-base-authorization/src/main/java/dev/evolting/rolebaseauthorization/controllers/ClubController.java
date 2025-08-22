package dev.evolting.rolebaseauthorization.controllers;

import dev.evolting.rolebaseauthorization.entities.Club;
import dev.evolting.rolebaseauthorization.services.ClubService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/club")
public class ClubController {
    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/get-all-club")
    public ResponseEntity<?> getAllClub() {
        return clubService.getAllClub();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create-club")
    public ResponseEntity<?> createClub(@RequestBody Club club) {
        return clubService.createClub(club);
    }
}
