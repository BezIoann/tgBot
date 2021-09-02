package com.example.tgBot.service;

import com.example.tgBot.entity.CityEntity;
import com.example.tgBot.exception.CityAlreadyExistException;
import com.example.tgBot.exception.CityNotFoundException;
import com.example.tgBot.model.City;
import com.example.tgBot.repository.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepo cityRepo;

    public CityEntity addCity(CityEntity city) throws CityAlreadyExistException {
        if (cityRepo.findByCityname(city.getCityname()) != null) {
            throw new CityAlreadyExistException("name was already added");
        }
        return cityRepo.save(city);
    }

    public City getOne(String cityname) throws CityNotFoundException {
        CityEntity city = cityRepo.findByCityname(cityname);
        if (city == null) {
            throw  new CityNotFoundException("City not founded");
        }
        return City.toModel(city);
    }

    public int delete(String cityname) {
        cityRepo.deleteByCityname(cityname);
        return 1;
    }


    public CityEntity updateDescription(String cityname, City description) {
        CityEntity city = cityRepo.findByCityname(cityname);
        city.setDescription(description.getDescription());
        return cityRepo.save(city);

    }
}
