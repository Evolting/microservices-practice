package dev.evolting.rolebaseauthorization.services;

import dev.evolting.rolebaseauthorization.entities.MyUserDetails;
import dev.evolting.rolebaseauthorization.entities.User;
import dev.evolting.rolebaseauthorization.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return new MyUserDetails(user);
    }
}
