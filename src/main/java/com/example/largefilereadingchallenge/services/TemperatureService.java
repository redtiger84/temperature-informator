package com.example.largefilereadingchallenge.services;

import com.example.largefilereadingchallenge.data.Temperature;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class TemperatureService {

    private final Map<String, List<Temperature>> temperatures = new HashMap<>();

    public void loadTemperaturesFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            String city = parts[0];
            int year = Integer.parseInt(parts[1]);
            double temperature = Double.parseDouble(parts[2]);

            Temperature temperatureEntry = new Temperature(year, temperature);
            List<Temperature> cityTemperatures = temperatures.getOrDefault(city, new ArrayList<>());
            cityTemperatures.add(temperatureEntry);
            temperatures.put(city, cityTemperatures);
        }
        reader.close();
    }

    public List<Temperature> getTemperaturesForCity(String city) {
        return temperatures.get(city);
    }
}

