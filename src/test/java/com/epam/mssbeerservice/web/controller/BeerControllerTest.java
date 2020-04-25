package com.epam.mssbeerservice.web.controller;

import com.epam.mssbeerservice.bootstrap.BeerLoader;
import com.epam.mssbeerservice.services.BeerService;
import com.epam.mssbeerservice.web.model.BeerDto;
import com.epam.mssbeerservice.web.model.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {
    @Autowired
    MockMvc mockMvc;


    @MockBean
    BeerService beerService;
    
    private final String API_URL_V1="/api/v1/beer/";

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        given(beerService.getBeerById(any())).willReturn(getValidBeerDto());

        mockMvc.perform(get(API_URL_V1 + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());
        BeerDto beerDto = getValidBeerDto();
        String jsonBeer = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBeer))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        given(beerService.updateBeerById(any(),any())).willReturn(getValidBeerDto());
        BeerDto beerDto = getValidBeerDto();
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

    private BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("2.99"))
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }
}