package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = " accountNumber can not be null")
    @Column(columnDefinition = "varchar(20) not null unique  ")
    @Pattern(regexp = "^\\d{21}$")
    private String accountNumber;

    @NotEmpty(message = " balance can not be null")
    private double balance;

    @NotEmpty(message = " isActive can not be null")
    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    private boolean isActive ;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

}
