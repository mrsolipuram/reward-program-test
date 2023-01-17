package com.sample.rewards.service;

import com.sample.rewards.model.CustomerRewards;
import com.sample.rewards.model.CustomerTransaction;
import com.sample.rewards.repository.CustomerTransactionRepository;
import com.sample.rewards.util.RewardsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class RewardService {

    @Autowired
    private CustomerTransactionRepository customerTransactionRepository;

    /**
     *
     * @param customerId
     * @return
     */
    public List<CustomerTransaction> getCustomerHistory(String customerId){
        return customerTransactionRepository.getCustomerTransactionByCustomerId(customerId);
    }

    /**
     *
     * @param customerTransaction
     * @return
     */
    public CustomerTransaction createTransaction(CustomerTransaction customerTransaction) {
        customerTransaction.setCreateDate(LocalDateTime.now());
        customerTransaction.setPoints(RewardsUtil.calculatePoints(customerTransaction.getAmount()));
        log.info("Transaction created for customer {} amount {} point {}",customerTransaction.getCustomerId(),customerTransaction.getAmount(),customerTransaction.getPoints());
        return customerTransactionRepository.save(customerTransaction);
    }

    /**
     *
     * @param customerId
     * @return
     */
    public CustomerRewards getCustomerSummery(String customerId) {
        List<CustomerTransaction> customerTransactionList = customerTransactionRepository.getCustomerTransactionByCustomerId(customerId);
        var totalPoints = customerTransactionList.stream().mapToLong(CustomerTransaction::getPoints).sum();
        CustomerRewards customerRewards = new CustomerRewards();
        customerRewards.setCustomerId(customerId);
        customerRewards.setTotalPoints(totalPoints);
        //logic to calculate points at yyyy-mm level
        var map = customerTransactionList.stream().collect(
                Collectors.groupingBy(customerTransaction -> customerTransaction.getTransactionDate().getYear()+"-"+customerTransaction.getTransactionDate().getMonthValue(),
                        Collectors.summingLong(customerTransaction -> customerTransaction.getPoints())));
        customerRewards.setPointsSummery(map);
        return customerRewards;
    }
}
