package com.example.tgBot.model;
import com.example.tgBot.entity.CityEntity;

public class City {

    private String description;

    public String getDescription() {
        return description;
    }

    public static City toModel(CityEntity entity) {
        City model = new City();
        model.setDescription(entity.getDescription());
        return model;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
