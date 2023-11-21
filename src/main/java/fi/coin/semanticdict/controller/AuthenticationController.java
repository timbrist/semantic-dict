package fi.coin.semanticdict.controller;

import java.util.Collections;

import fi.coin.semanticdict.dto.ImageDto;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import fi.coin.semanticdict.dto.SignUpDto;
import fi.coin.semanticdict.dto.LoginDto;
import fi.coin.semanticdict.dto.ResultDto;
import fi.coin.semanticdict.entity.Role;
import fi.coin.semanticdict.entity.User;
import fi.coin.semanticdict.repository.RoleRepository;
import fi.coin.semanticdict.repository.UserRepository;

import org.springframework.web.client.RestTemplate;
import fi.coin.semanticdict.service.ImageTextService;
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

    @Autowired
    private RestTemplate restTemplate;

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

    @PostMapping("/image")
    public ImageDto handleFileUpload(@RequestParam("image") MultipartFile file) {
        // Implement your logic to handle the file, like saving it to disk or a database
        System.out.println(file.getOriginalFilename());
        ImageDto imageDto = new ImageDto();
        ImageTextService imageTextService = new ImageTextService();
        String description = "";
        try {
            byte[] imageData = file.getBytes();
            JSONObject imageText = imageTextService.query(imageData);
            description = imageText.getString("generated_text");
        }catch (Exception e) {
        e.printStackTrace();
        return null; // Or handle the exception as appropriate
        }
        imageDto.setImageName(file.getOriginalFilename());
        imageDto.setImageDescription(description);

        // Return a response, e.g., a confirmation message
        return imageDto;
    }


}