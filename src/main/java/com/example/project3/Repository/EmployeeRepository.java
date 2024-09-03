package com.example.project3.Repository;

import com.example.project3.Model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee findEmployeeById(Integer id);
}
