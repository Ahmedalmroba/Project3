package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public List<User> getAllCustomers() {
        List<User> Customers = new ArrayList<>();
        for(User user : authRepository.findAll()){
            if(user.getRole().equals("CUSTOMER")){
                Customers.add(user);
            }
        }
        return Customers;
    }
    public void registerCustomer(CustomerDTO customerDTO) {
        String hash= new BCryptPasswordEncoder().encode(customerDTO.getPassword());//Hash password

        User user = new User();
        user.setUsername(customerDTO.getUsername());
        user.setPassword(hash);
        user.setRole("CUSTOMER");
        user.setEmail(customerDTO.getEmail());
        user.setName(customerDTO.getName());
        authRepository.save(user);

        Customer customer = new Customer();
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setUser(user);
        customerRepository.save(customer);
    }
    public void updateCustomer(Integer customerId,CustomerDTO customerDTO) {
        Customer Customer = customerRepository.findCustomerById(customerId);
        User User = Customer.getUser();

        String hash= new BCryptPasswordEncoder().encode(customerDTO.getPassword());//Hash password

        User.setEmail(customerDTO.getEmail());
        User.setPassword(hash);
        User.setName(customerDTO.getName());
        Customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customerRepository.save(Customer);
        authRepository.save(User);
    }
    public void deleteCustomer(int userId , int customerId) {
        User user = authRepository.findUserById(userId);
        Customer customer = customerRepository.findCustomerById(customerId);

        if(customer == null){
            throw new ApiException("customer not found");
        }

        if(user.getId()!=customer.getId()) {
            throw new ApiException("customer not match");
        }

        customer.setUser(null);
        authRepository.delete(user);
    }


}
