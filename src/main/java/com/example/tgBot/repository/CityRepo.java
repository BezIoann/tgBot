package com.example.tgBot.repository;

import com.example.tgBot.entity.CityEntity;
import org.springframework.data.repository.CrudRepository;

public interface CityRepo extends CrudRepository<CityEntity, Long> {
    CityEntity findByCityname(String cityname);
    void deleteByCityname(String cityname);
}
