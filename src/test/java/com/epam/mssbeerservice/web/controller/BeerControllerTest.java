package com.epam.mssbeerservice.web.controller;

import com.epam.mssbeerservice.web.model.BeerDto;
import com.epam.mssbeerservice.web.model.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {
    @Autowired
    MockMvc mockMvc;
    
    private final String API_URL_V1="/api/v1/beer/";

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get(API_URL_V1 + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = BeerDto.builder().beerName("Gyumri")
                .price(new BigDecimal(2.65))
                .upc(25123123L)
                .beerStyle(BeerStyleEnum.ALE).build();
        String jsonBeer = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBeer))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto beerDto = BeerDto.builder().beerName("Gyumri")
                .price(new BigDecimal(2.65))
                .upc(25123123L)
                .beerStyle(BeerStyleEnum.ALE).build();
        String jsonBeer = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put(API_URL_V1 + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBeer))
                .andExpect(status().isNoContent());
    }
    
    @Test
    void deleteById() throws Exception{
        mockMvc.perform(delete(API_URL_V1+UUID.randomUUID()))
                .andExpect(status().isNoContent());
    }
}