package com.example.Fit_App.domain.dto;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class WorkoutDTOView {
    private String exercise;
    private int reps;
    private int sets;
    private Float weight;
    private LocalDate date;
    private UserDTOView user;
}
