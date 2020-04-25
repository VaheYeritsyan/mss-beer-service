package com.epam.mssbeerservice.web.controller;

import com.epam.mssbeerservice.services.BeerService;
import com.epam.mssbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeerById(@NotNull @PathVariable("beerId")UUID id){
        return new ResponseEntity<>(beerService.getBeerById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity saveNewBeer(@Validated @RequestBody BeerDto beerDto){
        return new ResponseEntity(beerService.saveNewBeer(beerDto),HttpStatus.CREATED);
    }

    @PutMapping({"/{beerId}"})
    public ResponseEntity updateBeerById(@NotNull @PathVariable("beerId") UUID id,
                                         @Validated @RequestBody BeerDto beerDto){
        return new ResponseEntity(beerService.updateBeerById(id,beerDto),
                HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{beerId}"})
    public ResponseEntity deleteBeer(@NotNull @PathVariable("beerId") UUID uuid){
        beerService.deleteBeerById(uuid);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
