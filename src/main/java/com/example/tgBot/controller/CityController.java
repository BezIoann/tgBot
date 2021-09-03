package com.example.tgBot.controller;

import com.example.tgBot.entity.CityEntity;
import com.example.tgBot.exception.CityAlreadyExistException;
import com.example.tgBot.exception.CityNotFoundException;
import com.example.tgBot.model.City;
import com.example.tgBot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity addCity(@RequestBody CityEntity city) {
        try {
            cityService.addCity(city);
            return ResponseEntity.ok("City was saved!");
        } catch (CityAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong!");
        }
    }


    @GetMapping
    public ResponseEntity getOneCity(@RequestParam String cityname) {
        try {
            return ResponseEntity.ok(cityService.getOne(cityname));
        } catch (CityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong!");
        }
    }
    @Transactional
    @DeleteMapping("/{cityname}")
    public ResponseEntity deleteCity(@PathVariable String cityname) {
        try {
            return ResponseEntity.ok(cityService.delete(cityname.trim()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong!");
        }
    }

    @PutMapping("/update/{cityname}")
    public ResponseEntity updateCity(@RequestBody CityEntity city, @PathVariable String cityname) {
        try {
            return ResponseEntity.ok(cityService.update(cityname, city));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong!");
        }
    }
}
