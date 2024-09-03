package com.example.project3.Repository;

import com.example.project3.Model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    Account findAccountById(Integer Id);
    List<Account> findAccountByCustomerId(Integer customerId);
}
