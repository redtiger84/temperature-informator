package com.example.largefilereadingchallenge.services;

import com.example.largefilereadingchallenge.exception.DataFileException;
import com.example.largefilereadingchallenge.repository.TemperatureReader;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TemperatureService {

    private final TemperatureReader temperatureReader;

    public Set<String> extractCities() throws DataFileException {
        return temperatureReader.extractCities();
    }


    public Map<Integer, Double> readCSVFile(String city) throws DataFileException {
        return temperatureReader.readCSVFile(city);
    }
}

