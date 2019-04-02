package com.example.weatherapp.service;

import com.example.weatherapp.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    private WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository){
        this.weatherRepository = weatherRepository;
    }

    public List<String> getDates(){
        return weatherRepository.getAllDates();
    }

    public Weather getWeather(String WDate){
        return weatherRepository.findOne(WDate);
    }

    public boolean addWeather(Weather weather){

        if(weatherRepository.exists(weather.getDate())){
           removeEntry(weather.getDate());
        }
        System.out.println(weather.getDate());

        weatherRepository.save(weather);
        return true;

    }

    public boolean removeEntry(String date){
        if(!weatherRepository.exists(date)){
            return false;
        }
        weatherRepository.delete(date);
        return true;
    }

    public List<Weather> forecastWeather(String date){
        if(weatherRepository.exists(date)){
            return weatherRepository.getFuture(date);
        }
        String givenYear = date.substring(0,4);
        date = "2016"+date.substring(4);
        System.out.println(date);
        List<Weather> future = weatherRepository.getFuture(date);
        for(Weather weather:future){
            weather.setDate(givenYear+ weather.getDate().substring(4));
        }
        return future;

    }
}
