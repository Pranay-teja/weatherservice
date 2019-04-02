package com.example.weatherapp.controller;

import com.example.weatherapp.model.Dates;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.service.WeatherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "weather/historical")
public class GetHistorical {

    private WeatherService weatherService;

    @Autowired
    public GetHistorical(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @ApiOperation(value = "Provides List of Dates present in DB")
    @GetMapping(path = "/")
    public ResponseEntity<List<Dates>> getListOfDates(){
        return new ResponseEntity<List<Dates>>(weatherService.getDates().stream().map(Dates::new).collect(Collectors.toList()), HttpStatus.OK);
    }


    @ApiOperation(value = "Provides weather on a particular date")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Weather> getWeatherOnDate(@PathVariable("id") String id){
        Weather weather =  weatherService.getWeather(id);
        if(Optional.ofNullable(weather).isPresent())
            return new ResponseEntity<Weather>(weatherService.getWeather(id),HttpStatus.OK);
        else
            return new ResponseEntity("HTTP Error code 404",HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Can insert a new weather entry into DB")
    @PostMapping(path = "/", headers="Accept=application/json")
    public ResponseEntity<Dates> addWeatherEntry(@RequestBody Weather weather){
        System.out.println("in here post ");
        if(weatherService.addWeather(weather)){
            return new ResponseEntity(new Dates(weather.getDate()),HttpStatus.CREATED);
        }
        return new ResponseEntity("Date already exists",HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ApiOperation(value = "Remove a weather entry in the DB")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteWeatherEntry(@PathVariable("id") String id){
        if(weatherService.removeEntry(id)){
            return new ResponseEntity("Record Deleted",HttpStatus.OK);
        }
        return new ResponseEntity("check date",HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
