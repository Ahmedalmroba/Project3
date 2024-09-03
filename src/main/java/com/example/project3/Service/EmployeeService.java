package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
  private final EmployeeRepository employeeRepository;
  private final AuthRepository authRepository;


    public List<User> getAllEmployees() {
        List<User> AllEmployees = new ArrayList<>();
        for (User user : authRepository.findAll()) {
            if (user.getRole().equals("EMPLOYEE")) {
                AllEmployees.add(user);
            }
        }
        return AllEmployees;
    }
    public void registerEmployee(EmployeeDTO employeeDTO) {

        String hash= new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        User user = new User();
        user.setRole("EMPLOYEE");
        user.setUsername(employeeDTO.getUsername());
        user.setPassword(hash);
        user.setEmail(employeeDTO.getEmail());
        user.setName(employeeDTO.getName());
        authRepository.save(user);

        Employee employee = new Employee();
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        employee.setUser(user);
        employeeRepository.save(employee);
    }
    public void updateEmployee(Integer employeeId, EmployeeDTO employeeDTO) {
        Employee employee=employeeRepository.findEmployeeById(employeeId);
        User user=employee.getUser();
        String hash= new BCryptPasswordEncoder().encode(employeeDTO.getPassword());

        user.setPassword(hash);
        user.setEmail(employeeDTO.getEmail());
        employee.setSalary(employeeDTO.getSalary());
        user.setUsername(employeeDTO.getUsername());
        employee.setPosition(employeeDTO.getPosition());
        user.setName(employeeDTO.getName());

        employeeRepository.save(employee);
        authRepository.save(user);
    }

    public void deleteEmployee(int userId , int employeeId) {
        User user = authRepository.findUserById(userId);
        Employee employee = employeeRepository.findEmployeeById(employeeId);

        if(employee == null){
            throw new ApiException("customer not found");
        }

        if(user.getId()!=employee.getId()) {
            throw new ApiException("employee  not found");
        }

        employee.setUser(null);
        authRepository.delete(user);
}}
