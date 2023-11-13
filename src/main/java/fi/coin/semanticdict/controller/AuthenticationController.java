package fi.coin.semanticdict.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.coin.semanticdict.dto.SignUpDto;
import fi.coin.semanticdict.dto.LoginDto;
import fi.coin.semanticdict.dto.ResultDto;
import fi.coin.semanticdict.entity.Role;
import fi.coin.semanticdict.entity.User;
import fi.coin.semanticdict.repository.RoleRepository;
import fi.coin.semanticdict.repository.UserRepository;
@RestController
@RequestMapping("/api")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResultDto authenticateUser(@RequestBody LoginDto loginDto) {
        ResultDto resultDto = new ResultDto();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        resultDto.setResult(HttpStatus.OK.toString());
        resultDto.setContext("User login successfully!...");
        return resultDto;
    }


    @PostMapping("/signup")
    public ResultDto registerUser(@RequestBody SignUpDto signUpDto){
        ResultDto resultDto = new ResultDto();
        // checking for username exists in a database
        if(userRepository.existsByUserName(signUpDto.getUsername())){
            resultDto.setResult(HttpStatus.BAD_REQUEST.toString());
            resultDto.setContext("Username is already exist!");
            return resultDto;
        }
        // checking for email exists in a database
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            resultDto.setResult(HttpStatus.BAD_REQUEST.toString());
            resultDto.setContext("Email is already exist!");
            return resultDto;
        }
        // creating user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUserName(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
        resultDto.setResult(HttpStatus.OK.toString());
        resultDto.setContext("success");
        return resultDto;

    }
}