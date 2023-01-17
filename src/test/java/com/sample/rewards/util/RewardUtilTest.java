package com.sample.rewards.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardUtilTest {

    @Test
    public void calculatePointsTest(){
       var points = RewardsUtil.calculatePoints(BigDecimal.valueOf(120.0));
        assertEquals(90,points);
    }

    @Test
    public void calculatePointsTestBetween50And100(){
        var points = RewardsUtil.calculatePoints(BigDecimal.valueOf(90.0));
        assertEquals( 40,points);
    }
    @Test
    public void calculatePointsTestAbove100(){
        var points = RewardsUtil.calculatePoints(BigDecimal.valueOf(110.0));
        assertEquals( 70,points);
    }
    @Test
    public void calculatePointsTestBelow50(){
        var points = RewardsUtil.calculatePoints(BigDecimal.valueOf(30.0));
        assertEquals(0,points);
    }
    @Test
    public void calculatePointsTestZero(){
        var points = RewardsUtil.calculatePoints(BigDecimal.valueOf(0.0));
        assertEquals(0,points);
    }
    @Test
    public void calculatePointsTestNegative(){
        var points = RewardsUtil.calculatePoints(BigDecimal.valueOf(-120.0));
        assertEquals( 0,points);
    }
    @Test
    public void calculatePointsTestDecimal(){
        var points = RewardsUtil.calculatePoints(BigDecimal.valueOf(120.25));
        assertEquals(91,points);
    }
    @Test
    public void calculatePointsTestNull(){
        var points = RewardsUtil.calculatePoints(null);
        assertEquals(0,points);
    }
}
