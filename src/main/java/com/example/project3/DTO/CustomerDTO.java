package com.example.project3.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDTO {
    @NotEmpty(message = "Username cannot be empty")
    @Size(min=4,max = 10)

    private String username;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min=4,max = 10)
    private String name;
   @Email
    @NotEmpty(message = " email can not be null")
    private String email;
    @NotEmpty(message = " phoneNumber can not be null")
    private String phoneNumber;

}
