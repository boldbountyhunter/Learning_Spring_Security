package com.example.springSecurity.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional<User> user = userRepository.findByUsername(username);
         user.orElseThrow(()-> new UsernameNotFoundException("User doesn't exist"));
         return user.map(MyUserDetails::new).get();
    }

    String addUser(User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12)));
        user.setActive(true);
        userRepository.save(user);
        return "saved";
    }

    List<User> getUser() {
        return userRepository.findAll();
    }

}
