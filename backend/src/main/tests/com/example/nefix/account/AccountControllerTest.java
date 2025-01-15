package com.example.nefix.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccountController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private AccountService accountService;
    @Autowired
    private ObjectMapper objectMapper;
    private Account account;
    private AccountSubscriptionRequestDto accountSubscriptionRequestDto;
    private BlockedAccountRequestDto blockedAccountRequestDto;
    private ReferralDiscountRequestDto referralDiscountRequestDto;

    @BeforeEach
    public void init()
    {
        account = Account.builder().email("email").password("password").build();
        accountSubscriptionRequestDto = new AccountSubscriptionRequestDto(LocalDate.now().minusDays(1),
                LocalDate.now().plusMonths(1) );

    }

    @Test
    void AccountControllerTest_addSubscription_returnCreated() throws Exception {
        // Mock the service call to add a subscription
        given(accountService.addSubscription(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(), ArgumentMatchers.any()))
                .willReturn(accountSubscriptionRequestDto);

        // Perform the POST request to the controller and check if the response status is Created
        mockMvc.perform(post("/account/{accountId}/subscription/{subscriptionId}", 1L, 1L) // replace with actual URL
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(accountSubscriptionRequestDto)))
                .andExpect(status().isCreated());  // Expecting a 201 Created response
    }

    @Test
    void AccountControllerTest_addSubscription_returnCreated() throws Exception
    {
        given(accountService.addSubscription(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(), ArgumentMatchers.any())).willReturn(accountSubscriptionRequestDto);

    }
}

