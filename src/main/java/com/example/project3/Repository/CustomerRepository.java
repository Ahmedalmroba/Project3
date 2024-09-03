package com.example.project3.Repository;

import com.example.project3.Model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    Customer findCustomerById(Integer id);
}
