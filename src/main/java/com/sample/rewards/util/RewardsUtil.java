package com.sample.rewards.util;

import java.math.BigDecimal;

public class RewardsUtil {

    /**
     * A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every
     * dollar spent over $50 in each transaction
     * (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
     * @param transactionAmount
     * @return
     */
    public static long calculatePoints(BigDecimal transactionAmount){
        if(transactionAmount == null)
            return 0;
        Double amount = transactionAmount.doubleValue();
        Double amountAboveHundred = (amount > 100) ? (amount - 100) : 0.0;
        Double amountBelowHundred = (amount > 50) ?(amount - amountAboveHundred ) - 50 :0.0;
        return Math.round((amountAboveHundred * 2) + (amountBelowHundred * 1));
    }
}
