package com.epam.mssbeerservice.web.mappers;

import com.epam.mssbeerservice.domain.Beer;
import com.epam.mssbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDto beerDto);
    BeerDto beerToBeerDto(Beer beer);
}
