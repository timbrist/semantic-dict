package fi.coin.semanticdict.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import fi.coin.semanticdict.dto.*;
import org.json.JSONObject;
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


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import fi.coin.semanticdict.entity.Role;
import fi.coin.semanticdict.entity.User;
import fi.coin.semanticdict.repository.RoleRepository;
import fi.coin.semanticdict.repository.UserRepository;

import org.springframework.web.client.RestTemplate;
import fi.coin.semanticdict.service.ImageTextService;
import fi.coin.semanticdict.service.DertService;
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

        // ====We will return imageDto as JSON===
        ImageDto imageDto = new ImageDto();


        try {
            // ==== Convert File into bytes[] =====
            byte[] imageData = file.getBytes();

            // ====FIRST: Get Image descriptions ======
            ImageTextService imageTextService = new ImageTextService();
            String description = "";
            JSONObject imageText = imageTextService.query(imageData);
            description = imageText.getString("generated_text");

            // =====Second: Get the object coordinates from dert service;
            DertService dertService = new DertService();
            JSONObject jsonObject  = dertService.query(imageData);
            List<ObjectCoorDto> objectCoorList =new ArrayList<>();

            Iterator<String> keys = jsonObject.keys();
            while(keys.hasNext()) {
                String key = keys.next();
                if (jsonObject.get(key) instanceof JSONObject) {
                    // do something with jsonObject here
                    ObjectCoorDto objectCoorDto = new ObjectCoorDto();
                    objectCoorDto.setScore(jsonObject.getDouble("score"));
                    objectCoorDto.setLabel(jsonObject.getString("label"));
                    JSONObject boxInfo = jsonObject.getJSONObject("box");
                    Box box = new Box();
                    box.xmin = boxInfo.getInt("xmin");
                    box.ymin = boxInfo.getInt("ymin");
                    box.xmax = boxInfo.getInt("xmax");
                    box.ymax = boxInfo.getInt("ymax");
                    objectCoorDto.setBox(box);
                    objectCoorList.add(objectCoorDto);
                }
            }
            imageDto.setImageName(file.getOriginalFilename());
            imageDto.setImageDescription(description);
            imageDto.setImageItems(objectCoorList);


        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Check for the image file.");
        }

        // Return a response, e.g., a confirmation message
        return imageDto;
    }

//    @GetMapping("/search")
//    public


}