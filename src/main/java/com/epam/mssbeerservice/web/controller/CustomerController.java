package com.epam.mssbeerservice.web.controller;

import com.epam.mssbeerservice.web.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> handleGet(@PathVariable("customerId")UUID id){
        return new ResponseEntity<>(Customer.builder().fullName("Vahe Yeritsyan")
                .build(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity handlePost(@RequestBody Customer customer){
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity handlePut(@PathVariable("customerId")UUID id,
                                    @RequestBody Customer customer){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity handleDelete(@PathVariable("customerId")UUID id){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
