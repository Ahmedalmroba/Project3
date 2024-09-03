package com.example.project3.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeDTO {

    @NotEmpty(message = "Username cannot be empty")
    @Size(min=4,max = 10)

    private String username;

    @NotEmpty(message = "Password cannot be empty")

    private String password;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min=4,max = 10)

    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must have a valid format")
    @Size(min=7,max = 50,
            message = "Email must have between 7 to 50 letters")
    private String email;

    @NotEmpty(message = "Position cannot be empty")
    private String position;

    @Positive(message = "Salary cannot be a zero or a negative number")
    private double salary;
}
