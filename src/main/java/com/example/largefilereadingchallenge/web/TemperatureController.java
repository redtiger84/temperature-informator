package com.example.largefilereadingchallenge.web;

import com.example.largefilereadingchallenge.exception.DataFileException;
import com.example.largefilereadingchallenge.services.TemperatureService;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperatures")
@AllArgsConstructor
public class TemperatureController {

    private final TemperatureService temperatureService;

    @GetMapping("/city")
    public Set<String> city(
        @RequestParam(value = "term", required = false) String term,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) throws DataFileException {
        Set<String> allCities = temperatureService.extractCities();

        if (term != null && !term.isEmpty()) {
            return allCities.stream()
                .filter(city -> city.toLowerCase().contains(term.toLowerCase()))
                .limit(limit)
                .collect(Collectors.toSet());
        } else {
            return allCities.stream().limit(limit).collect(Collectors.toSet());
        }
    }

    @GetMapping("/{city}")
    public Map<Integer, Double> getTemperature(@PathVariable String city) throws DataFileException {
        return temperatureService.readCSVFile(city);
    }
}