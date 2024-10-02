package com.example.Fit_App.service;

import com.example.Fit_App.domain.dto.WorkoutDTOForm;
import com.example.Fit_App.domain.dto.WorkoutDTOView;
import com.example.Fit_App.domain.entity.Workout;

import java.util.List;

public interface WorkoutService {

    WorkoutDTOView register(WorkoutDTOForm dtoForm);

    List<WorkoutDTOView> findAllWorkoutsByUserEmail(String email);

    WorkoutDTOView addWorkoutToUser(String email, WorkoutDTOForm dtoForm);
}

