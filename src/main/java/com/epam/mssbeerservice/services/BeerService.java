package com.epam.mssbeerservice.services;

import com.epam.mssbeerservice.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface BeerService {
    BeerDto getBeerById(UUID id);
    BeerDto saveNewBeer(BeerDto beerDto);
    BeerDto updateBeerById(UUID id,BeerDto beerDto);
    void deleteBeerById(UUID id);
}
