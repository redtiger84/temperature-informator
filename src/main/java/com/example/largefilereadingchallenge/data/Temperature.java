package com.example.largefilereadingchallenge.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Temperature {

    private String city;
    private double averageTemperature;
}
