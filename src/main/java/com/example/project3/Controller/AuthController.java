package com.example.project3.Controller;

import com.example.project3.Model.User;
import com.example.project3.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
   private final AuthService authSrrvice;
    @GetMapping("/getallusers")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(authSrrvice.getAllUsers());
    }

}
