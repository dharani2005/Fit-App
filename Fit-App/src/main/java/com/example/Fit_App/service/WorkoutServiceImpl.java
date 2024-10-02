package com.example.Fit_App.service;

import com.example.Fit_App.domain.dto.UserDTOForm;
import com.example.Fit_App.domain.dto.UserDTOView;
import com.example.Fit_App.domain.dto.WorkoutDTOForm;
import com.example.Fit_App.domain.dto.WorkoutDTOView;
import com.example.Fit_App.domain.entity.User;
import com.example.Fit_App.domain.entity.Workout;
import com.example.Fit_App.repository.UserRepository;
import com.example.Fit_App.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService{

    private final UserRepository userRepository;
    private final WorkoutRepository workoutRepository;
    @Autowired
    public WorkoutServiceImpl(UserRepository userRepository, WorkoutRepository workoutRepository) {
        this.userRepository = userRepository;
        this.workoutRepository = workoutRepository;
    }

    @Override
    public WorkoutDTOView register(WorkoutDTOForm dtoForm) {
        //check the parameters are null
        if(dtoForm == null) throw new IllegalArgumentException("dtoForm cannot be null");
        //check if user already exists in database, otherwise create new workout
        Optional<User> optionalUser = userRepository.findByEmail(dtoForm.getUser().getEmail());
       User user;
       if(optionalUser.isPresent()) {
           user = optionalUser.get();
       }else{
         user =  User.builder().email(dtoForm.getUser().getEmail())
                   .password(dtoForm.getUser().getPassword())
                   .name(dtoForm.getUser().getName())
                   .build();
        user =  userRepository.save(user);
       }
       //create a new workout entity using the dto and builder
       Workout workout = Workout.builder().exercise(dtoForm.getExercise())
                .reps(dtoForm.getReps())
                .sets(dtoForm.getSets())
                .weight(dtoForm.getWeight())
                .date(dtoForm.getDate())
                .user(user).build();
       //save the created workout to database
      Workout savedWorkout =  workoutRepository.save(workout);

        //convert the saved entity to userDtoView using builder
       UserDTOView userDTOView = UserDTOView.builder()
                .email(savedWorkout.getUser().getEmail())
                .name(savedWorkout.getUser().getName())
                .build();

       //return the workoutDTOView using builder
        return WorkoutDTOView.builder()
                .exercise(savedWorkout.getExercise())
                .reps(savedWorkout.getReps())
                .sets(savedWorkout.getSets())
                .weight(savedWorkout.getWeight())
                .date(savedWorkout.getDate())
                .user(userDTOView)
                .build();
    }

    public WorkoutDTOView toWorkoutView(Workout workout) {
        return  WorkoutDTOView.builder().exercise(workout.getExercise())
                .sets(workout.getSets())
                .reps(workout.getReps())
                .weight(workout.getWeight())
                .date(workout.getDate())
                .build();

    }

    @Override
    public List<WorkoutDTOView> findAllWorkoutsByUserEmail(String email) {
        List<Workout> workouts = workoutRepository.findByUser_Email(email);
        //using lambda expression
        //return workouts.stream().map(workout -> toWorkoutView(workout)).collect(Collectors.toList());
        //using method reference
        return workouts.stream().map(this::toWorkoutView).collect(Collectors.toList());
    }

    @Override
    public WorkoutDTOView addWorkoutToUser(String email, WorkoutDTOForm dtoForm) {
       User user = userRepository.findByEmail(email)
               .orElseThrow(()-> new IllegalArgumentException("User not found with this email"));
        //update the workoutDTOForm with the retrieved user
        dtoForm.setUser(UserDTOForm.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .build());
       return register(dtoForm);
    }

}

