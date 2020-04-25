package com.epam.mssbeerservice.services;

import com.epam.mssbeerservice.domain.Beer;
import com.epam.mssbeerservice.repositories.BeerRepository;
import com.epam.mssbeerservice.web.controller.NotFoundException;
import com.epam.mssbeerservice.web.mappers.BeerMapper;
import com.epam.mssbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getBeerById(UUID id) {
        return beerMapper.beerToBeerDto(beerRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository
                .save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeerById(UUID id, BeerDto beerDto) {
        Beer foundBeer = beerRepository.findById(id).orElseThrow(NotFoundException::new);
        foundBeer.setBeerName(beerDto.getBeerName());
        foundBeer.setBeerStyle(beerDto.getBeerStyle().name());
        foundBeer.setPrice(beerDto.getPrice());
        foundBeer.setUpc(beerDto.getUpc());
        return beerMapper.beerToBeerDto(beerRepository.save(foundBeer));
    }

    @Override
    public void deleteBeerById(UUID id) {
        beerRepository.deleteById(id);
    }
}
