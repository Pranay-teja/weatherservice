package com.example.weatherapp.controller;

import com.example.weatherapp.model.Weather;
import com.example.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/weather/forecast")
public class Forecast {

    private WeatherService weatherService;

    @Autowired
    public Forecast(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Weather>> getForcast(@PathVariable("id") String id){
        return new ResponseEntity<List<Weather>>(weatherService.forecastWeather(id), HttpStatus.OK);
    }
}
