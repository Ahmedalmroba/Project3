package com.example.project3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Size(min = 4, max = 10)
        @NotEmpty(message = "user name can not be null")
        @Column(columnDefinition = "varchar(20) not null")
        private String username;
        @Positive(message = "Enter valid value for rating")
        @Size(min = 6)
        @Column(columnDefinition = "varchar(500) not null ")
        private String password;
        @NotEmpty(message = " name can not be null")
        @Size(min = 2, max = 20)
        private String name;
        @Email
        @NotEmpty(message = " email can not be null")
        private String email;
        @Column(columnDefinition = "VARCHAR(30) NOT NULL")
        @NotEmpty(message = " role can not be null")
        @Pattern(regexp = "(CUSTOMER|EMPLOYEE|ADMIN)$")
        private String role;


        @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
        @PrimaryKeyJoinColumn
        private Customer customer;

        @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
        @PrimaryKeyJoinColumn
        private Employee employee;



        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.singleton(new SimpleGrantedAuthority(this.role));
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
}
