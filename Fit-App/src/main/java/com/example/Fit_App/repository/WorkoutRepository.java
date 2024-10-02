package com.example.Fit_App.repository;

import com.example.Fit_App.domain.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUser_EmailAndUser_Password(String email, String password);
    List<Workout> findByUser_Email(String email);
}
