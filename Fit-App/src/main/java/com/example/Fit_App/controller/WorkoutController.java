package com.example.Fit_App.controller;

import com.example.Fit_App.domain.dto.WorkoutDTOForm;
import com.example.Fit_App.domain.dto.WorkoutDTOView;
import com.example.Fit_App.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/workout")
public class WorkoutController {
    private final WorkoutService workoutService;
   @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }
@PostMapping("/register")
    private ResponseEntity<WorkoutDTOView> doCreateWorkout(@RequestBody WorkoutDTOForm dtoForm) {
      WorkoutDTOView responseBody = workoutService.register(dtoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
    @GetMapping("/user-workouts")
    private ResponseEntity<List<WorkoutDTOView>> getAllWorkoutsByUserEmail(@RequestParam String email) {
     List<WorkoutDTOView> responseBody =  workoutService.findAllWorkoutsByUserEmail(email);
       return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
@PostMapping("/add-user")
private ResponseEntity<WorkoutDTOView> addWorkoutToUser(@RequestParam String email, @RequestBody WorkoutDTOForm dtoForm) {
    WorkoutDTOView responseBody  = workoutService.addWorkoutToUser(email,dtoForm);
       return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
}
}
