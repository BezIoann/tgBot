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
            throw new CityAlreadyExistException("city already exists!");
        }
        return cityRepo.save(city);
    }

    public CityEntity getOne(String cityname) throws CityNotFoundException {
        CityEntity city = cityRepo.findByCityname(cityname);
        if (city == null) {
            throw  new CityNotFoundException("City not founded");
        }
        return city;
    }

    public String delete(String cityname) {
        cityRepo.deleteByCityname(cityname);
        return cityname;
    }


    public CityEntity update(String cityname, CityEntity UpdatedCity) {
        CityEntity city = cityRepo.findByCityname(cityname);
        city.setDescription(UpdatedCity.getDescription());
        city.setCityname(UpdatedCity.getCityname());
        return cityRepo.save(city);
    }
}
