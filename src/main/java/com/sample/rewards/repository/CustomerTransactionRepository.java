package com.sample.rewards.repository;

import com.sample.rewards.model.CustomerTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerTransactionRepository extends CrudRepository<CustomerTransaction, Long> {

    public List<CustomerTransaction> getCustomerTransactionByCustomerId(String customerId);
}
