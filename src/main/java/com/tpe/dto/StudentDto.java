package com.tpe.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    @Size(min = 2, max = 25 , message = "Name '${validatedValue}' must be beetwen {min} and {max}")
    @NotBlank(message = "Name can not be space")
    private String name;


    @Size(min = 2, max = 25 , message = "Lastname '${validatedValue}' must be beetwen {min} and {max}")
    @NotBlank(message = "LastName can not be space")
    private String lastname;


    private Integer grade;


    @Email(message = "Please provide valid email")
    private String email;


    private String phoneNumber;



}
