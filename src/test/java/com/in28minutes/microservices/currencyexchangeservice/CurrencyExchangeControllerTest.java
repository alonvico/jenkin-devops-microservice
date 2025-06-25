package com.in28minutes.microservices.currencyexchangeservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CurrencyExchangeController.class)
public class CurrencyExchangeControllerTest {
    @Autowired
    private MockMvc mvc;

    @Mock
    private CurrencyExchangeRepository repository;

    @Mock
    private InstanceInformationService instanceInformationService;

    @Test
    public void imHealthy() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void retrieveExchangeValue() throws Exception {
        Mockito.when(repository.findByFromAndTo("USD", "MXN")).thenReturn(Optional.of(new CurrencyExchange(Long.getLong("1"), "USD", "MXN", BigDecimal.valueOf(80.00))));
        mvc.perform(MockMvcRequestBuilders.get("/currency-exchange/from/USD/to/MXN")).andExpect(status().isOk());
    }
}