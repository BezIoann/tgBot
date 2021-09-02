package com.example.tgBot.city;

import com.example.tgBot.entity.CityEntity;
import com.example.tgBot.model.City;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class CityApi {
    public static String getDecription(String message, CityEntity city) throws IOException {
        URL url = new URL("http://localhost:8080/cities?cityname=" + message);

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        city.setDescription(object.getString("description"));
        return "Description: " + city.getDescription();
    }
}
