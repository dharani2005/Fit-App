package com.example.Fit_App.service;

import com.example.Fit_App.domain.dto.UserDTOForm;
import com.example.Fit_App.domain.dto.UserDTOView;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDTOView register (UserDTOForm dtoForm);
    UserDTOView login (String email, String password);
    UserDTOView authenticate (UserDTOForm dtoForm);


}
