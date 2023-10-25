package com.example.largefilereadingchallenge.web;

import com.example.largefilereadingchallenge.data.Temperature;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/temperatures")
public class TemperatureController {

    @GetMapping("/autocomplete")
    @ResponseBody
    public List<String> autocomplete() {
        return List.of("Suggestion1", "Suggestion2", "Suggestion3");
    }
}


/*
* [
  {
	"year": "2021",
	"averageTemperature": 12.1
  },
  {
	"year": "2022",
	"averageTemperature": 11.1
  },
  {
	"year": "2023",
	"averageTemperature": 14.1
  }
]
* */