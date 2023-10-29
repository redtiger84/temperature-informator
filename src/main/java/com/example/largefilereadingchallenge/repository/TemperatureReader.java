package com.example.largefilereadingchallenge.repository;

import com.example.largefilereadingchallenge.exception.DataFileException;
import java.util.Map;
import java.util.Set;

public interface TemperatureReader {

    Set<String> extractCities() throws DataFileException;

    Map<Integer, Double> readCSVFile(String city) throws DataFileException;
}
