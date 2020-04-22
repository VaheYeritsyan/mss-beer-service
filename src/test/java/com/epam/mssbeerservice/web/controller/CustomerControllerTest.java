package com.epam.mssbeerservice.web.controller;

import com.epam.mssbeerservice.web.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private String url = "/api/v1/customer/";

    @Test
    void handleGet() throws Exception {
        mockMvc.perform(get(url + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void handlePost() throws Exception {
        Customer customer = Customer.builder().build();
        String customerJson = objectMapper.writeValueAsString(customer);

        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
                .andExpect(status().isCreated());
    }

    @Test
    void handlePut() throws Exception {
        Customer customer = Customer.builder().build();
        String customerJson = objectMapper.writeValueAsString(customer);

        mockMvc.perform(put(url + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
                .andExpect(status().isNoContent());
    }

    @Test
    void handleDelete() throws Exception{
        mockMvc.perform(delete(url+UUID.randomUUID()))
                .andExpect(status().isNoContent());
    }
}