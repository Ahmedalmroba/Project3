package com.example.project3.Controller;

import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.User;
import com.example.project3.Repository.CustomerRepository;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

    @GetMapping("/get-all-customers")
    public ResponseEntity getAllCustomers(){
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }
    @PostMapping("/register-customer")
    public ResponseEntity registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        customerService.registerCustomer(customerDTO);
        return ResponseEntity.status(200).body("Customer registered successfully");
    }

    @PutMapping("/update-my-info")
    public ResponseEntity updateMyInfo(@AuthenticationPrincipal User user, @RequestBody@Valid CustomerDTO customer) {
        customerService.updateCustomer(user.getId(),customer);
        return ResponseEntity.status(200).body("Customer updated successfully");
    }


    @DeleteMapping("/delete-customer/{customerId}")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user , @PathVariable int customerId){

        customerService.deleteCustomer(user.getId(),customerId);
        return ResponseEntity.status(200).body("Customer Deleted");
    }
}
