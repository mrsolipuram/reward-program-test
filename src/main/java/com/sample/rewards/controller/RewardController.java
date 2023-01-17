package com.sample.rewards.controller;

import com.sample.rewards.model.CustomerRewards;
import com.sample.rewards.model.CustomerTransaction;
import com.sample.rewards.service.RewardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RewardController {

    @Autowired
    private RewardService rewardService;

    /**
     *
     * @param customerId
     * @return
     */
    @GetMapping("/getCustomerSummery/{customerId}")
    public CustomerRewards getCustomerSummery(@PathVariable("customerId") String customerId){
        return rewardService.getCustomerSummery(customerId);
    }

    /**
     *
     * @param customerId
     * @return
     */
    @GetMapping("/history/{customerId}")
    public List<CustomerTransaction> getCustomerHistory(@PathVariable("customerId") String customerId){
        return rewardService.getCustomerHistory(customerId);
    }

    /**
     *
     * @param customerTransaction
     * @return
     */
    @PostMapping("/createTransaction")
    public ResponseEntity<CustomerTransaction> createTransaction(@Valid @RequestBody CustomerTransaction customerTransaction){
        return ResponseEntity.ok(rewardService.createTransaction(customerTransaction));
    }
}
