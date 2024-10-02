package com.example.Fit_App.service;

import com.example.Fit_App.domain.dto.UserDTOForm;
import com.example.Fit_App.domain.dto.UserDTOView;
import com.example.Fit_App.domain.entity.User;
import com.example.Fit_App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;

@Service
public class UserServiceImpl implements UserService{
     private final UserRepository userRepository;
    private final CustomPasswordEncoder customPasswordEncoder;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CustomPasswordEncoder customPasswordEncoder) {
        this.userRepository = userRepository;
        this.customPasswordEncoder = customPasswordEncoder;
    }

    @Override
    public UserDTOView register(UserDTOForm dtoForm) {
        //check parameters are null
        if(dtoForm == null) throw new IllegalArgumentException("UserDTOForm cannot be null");
        //check user already exists in the database
        boolean emailExists = userRepository.existsByEmail(dtoForm.getEmail());
        if(emailExists) {
            throw new IllegalArgumentException("Email already exists");
        }
        //convert userDTOForm to userEntity
        User user = User.builder()
                    .email(dtoForm.getEmail())
                    .password(dtoForm.getPassword())
                    .name(dtoForm.getName())
                    .build();
        //save the user to the dateBase
       User savedUser = userRepository.save(user);
       //return the userDTOView
       return UserDTOView.builder()
               .email(savedUser.getEmail())
               .name(savedUser.getName())
               .build();
    }

    @Override
    public UserDTOView login(String email, String password) {
        //check the parameters are null
       if(email == null || password == null) throw new IllegalArgumentException("Email and password cannot be null");

       //check if the user with email and password exits in the database and get
        if(userRepository.findByEmailAndPassword(email, password).isPresent()) {

            //return the userDTOView from the user entity
           User user = userRepository.findByEmailAndPassword(email, password).get();
           return UserDTOView.builder()
                   .email(user.getEmail())
                   .name(user.getName())
                   .build();
        }
        return null;
    }

    @Override
    public UserDTOView authenticate(UserDTOForm dtoForm) {
        //check parameter is null
        if(dtoForm == null) throw new IllegalArgumentException("UserDTOForm cannot be null");
       //find the user by email
        User user =  userRepository.findByEmail(dtoForm.getEmail())
               .orElseThrow(()-> new IllegalArgumentException("Invalid email"));
       //check if the password matches
        //TODO:
        if(customPasswordEncoder.matches(dtoForm.getPassword(), user.getPassword())) {
            //return UserDTOView
            return UserDTOView.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .build();

        }
         throw new IllegalArgumentException("Invalid authentication");
    }

}
