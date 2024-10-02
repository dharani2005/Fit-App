package com.example.Fit_App.domain.dto;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class WorkoutDTOForm {
    private String exercise;
    private int reps;
    private int sets;
    private Float weight;
    private LocalDate date;
    private UserDTOForm user;

}
