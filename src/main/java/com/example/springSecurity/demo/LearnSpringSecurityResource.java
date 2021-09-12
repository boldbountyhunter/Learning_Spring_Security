package com.example.springSecurity.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LearnSpringSecurityResource {

    @Autowired
    MyUserDetailService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtTokenUtil;

    @GetMapping(value = "/user/hello")
    public String sayHello() {
        return "Hello Saurav";
    }

    @GetMapping(value = "/user/hi")
    public String sayHi() {
        return "Hi Saurav";
    }

    @GetMapping(value = "/anyone")
    public String sayHey() {
        return "Heyy Saurav";
    }

    @GetMapping(value = "/admin/{name}")
    public String sayHeyWithName(@PathVariable String name) {
        return "Heyy "+name;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JWTResponse> getAuthToken(@RequestBody JWTRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JWTResponse(jwt));
    }

    @PostMapping(value="/signup")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        return new ResponseEntity<String>(userDetailsService.addUser(user),HttpStatus.OK);
    }

    @GetMapping(value="/admin/users")
    public ResponseEntity<List<User>> getUser(){
        return new ResponseEntity<List<User>>(userDetailsService.getUser(),HttpStatus.OK);
    }
}
