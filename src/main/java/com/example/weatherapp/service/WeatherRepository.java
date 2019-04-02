package com.example.weatherapp.service;

import com.example.weatherapp.model.Weather;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.xml.bind.ValidationEvent;
import java.util.List;

public interface WeatherRepository extends CrudRepository<Weather,String> {

    public static final String GET_DATES = "select WDate from WEATHER";

    @Query(value = GET_DATES, nativeQuery = true)
    public List<String> getAllDates();

    public static final String FETCH_FUTURE = "SELECT * FROM WEATHER where WDate >= :date ORDER BY WDate limit 7";
    @Query(value = FETCH_FUTURE,nativeQuery = true)
    public List<Weather> getFuture(@Param("date") String date);


}
