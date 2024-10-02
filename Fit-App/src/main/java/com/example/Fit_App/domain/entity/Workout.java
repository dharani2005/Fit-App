package com.example.Fit_App.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String exercise;
    private int sets;
    private int reps;
    @Column(nullable = false)
    private Float weight;
    @Column(nullable = false)
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;

}
