package com.example.largefilereadingchallenge.services;

import com.example.largefilereadingchallenge.exception.DataFileException;
import com.example.largefilereadingchallenge.repository.TemperatureReader;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TemperatureService {

    private final TemperatureReader temperatureReader;

    @Cacheable(value = "cities")
    public Set<String> extractCities() throws DataFileException {
        return temperatureReader.extractCities();
    }

    @CacheEvict(value = "cities", allEntries = true)
    @Scheduled(fixedRate = 300000)
    public void clearCitiesCache() {
    }


    public Map<Integer, Double> readCSVFile(String city) throws DataFileException {
        return temperatureReader.readCSVFile(city);
    }
}

