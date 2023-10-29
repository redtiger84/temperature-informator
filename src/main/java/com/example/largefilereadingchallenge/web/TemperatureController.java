package com.example.largefilereadingchallenge.web;

import com.example.largefilereadingchallenge.data.Temperature;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperatures")
public class TemperatureController {

    @GetMapping("/city")
    public List<String> city(
        @RequestParam(value = "term", required = false) String term,
        @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        List<String> allCities = List.of("Berlin", "Ateny", "Rzym", "Włochy");

        if (term != null && !term.isEmpty()) {
            return allCities.stream()
                .filter(city -> city.toLowerCase().contains(term.toLowerCase()))
                .limit(limit)
                .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @GetMapping("/{city}")
    public List<Temperature> getTemperature(@PathVariable String city) {
        Map<String, List<Temperature>> result = new HashMap<>();
        result.put("Berlin",
            List.of(new Temperature(2021, 12.1), new Temperature(2022, 13.1), new Temperature(2023, 13.1)));
        result.put("Ateny", List.of(new Temperature(2022, 11.1)));
        result.put("Rzym", List.of(new Temperature(2023, 14.1)));
        result.put("Włochy", List.of(new Temperature(2024, 15.0)));

        return result.get(city);
    }
}