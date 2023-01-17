package com.sample.rewards.controller;

import com.sample.rewards.service.RewardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RewardController.class)
public class RewardControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private RewardService rewardService;
    @Test
    public void postCustomerTransactionAPI() throws Exception {
        String content = "{\n" +
                "  \"customerId\":\"1234\",\n" +
                "  \"amount\":102.00,\n" +
                "  \"transactionDate\": \"2022-01-17T00:53:30\"\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders
                        .post("/createTransaction")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void getCustomerSummeryAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/getCustomerSummery/1234")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getCustomerAPI404() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/test/1234")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
