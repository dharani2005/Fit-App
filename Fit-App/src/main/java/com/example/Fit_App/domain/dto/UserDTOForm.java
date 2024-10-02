package com.example.Fit_App.domain.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTOForm {
    private String email;
    private String password;
    private String name;
}
