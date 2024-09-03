package com.example.project3.Controller;

import com.example.project3.Api.ApiException;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.User;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/get-all")
    public ResponseEntity getAllEmployee(){
        return ResponseEntity.status(200).body(employeeService.getAllEmployees());

    }
    @PostMapping("/register-employee")
    public ResponseEntity registerEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.registerEmployee(employeeDTO);
        return ResponseEntity.status(200).body("Employee registered successfully");
    }

    @PutMapping("/update-employee")
    public ResponseEntity updateMyInfo(@AuthenticationPrincipal User user, @RequestBody@Valid EmployeeDTO employeeInfo) {
        employeeService.updateEmployee(user.getId(), employeeInfo);
        return ResponseEntity.status(200).body(new ApiException("Employee updated successfully"));

}
    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal User user , @PathVariable int id){
        employeeService.deleteEmployee(user.getId(),id);
        return ResponseEntity.status(200).body("Employee Deleted");
    }
}
