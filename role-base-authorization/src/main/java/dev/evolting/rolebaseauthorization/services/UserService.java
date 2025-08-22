package dev.evolting.rolebaseauthorization.services;

import dev.evolting.rolebaseauthorization.entities.Role;
import dev.evolting.rolebaseauthorization.entities.User;
import dev.evolting.rolebaseauthorization.repositories.RoleRepository;
import dev.evolting.rolebaseauthorization.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public String register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(roleRepository.findById(1).get());
        userRepository.save(user);
        return "Register done";
    }
}
