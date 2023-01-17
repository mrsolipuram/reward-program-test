package com.sample.rewards.service;

import com.sample.rewards.model.CustomerRewards;
import com.sample.rewards.model.CustomerTransaction;
import com.sample.rewards.repository.CustomerTransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RewardServiceTest {

    @Autowired
    RewardService rewardService;
    @Autowired
    CustomerTransactionRepository customerTransactionRepository;
    @BeforeEach
    private void loadCustomerData(){
        CustomerTransaction customerTransaction = new CustomerTransaction();
        customerTransaction.setCustomerId("1234");
        customerTransaction.setTransactionDate(LocalDateTime.now());
        customerTransaction.setAmount(BigDecimal.valueOf(120.0));
        rewardService.createTransaction(customerTransaction);
    }
    @AfterEach
    private void clearCustomerData(){
        customerTransactionRepository.deleteAll();
    }
    @Test
    public void getCustomerTransactionHistoryTest(){
        assertNotNull(rewardService.getCustomerHistory("1234"));
    }

    @Test
    public void getCustomerSummeryTest(){
        CustomerTransaction customerTransaction = new CustomerTransaction();
        customerTransaction.setCustomerId("1234");
        customerTransaction.setAmount(BigDecimal.valueOf(120.0));
        customerTransaction.setTransactionDate(LocalDateTime.now());
        customerTransaction = rewardService.createTransaction(customerTransaction);
        assertNotNull(customerTransaction.getId());
        CustomerRewards customerRewards = rewardService.getCustomerSummery("1234");
        assertEquals(180,customerRewards.getTotalPoints());
    }
    @Test
    public void getCustomerSummeryTestNull(){
        CustomerRewards customerRewards = rewardService.getCustomerSummery(null);
        assertEquals(0,customerRewards.getTotalPoints());
    }
}
