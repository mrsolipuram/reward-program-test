package com.sample.rewards.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CustomerRewards {
    private String customerId;
    private Long totalPoints;
    private Map<String, Long> pointsSummery;

}
