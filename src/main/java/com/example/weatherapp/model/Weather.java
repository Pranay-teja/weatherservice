package com.example.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Weather {
    @Id
    @Column(name = "WDate")
    @JsonProperty("DATE")
    private String Date;

    @NotNull
    @JsonProperty("TMAX")
    private String TMAX;

    @NotNull
    @JsonProperty("TMIN")
    private String TMIN;

    @JsonProperty("DATE")
    public String getDate() {
        return Date;
    }

    @JsonProperty("DATE")
    public void setDate(String date) {
        Date = date;
    }

    @JsonProperty("TMAX")
    public String getTMAX() {
        return TMAX;
    }
    @JsonProperty("TMAX")
    public void setTMAX(String TMAX) {
        this.TMAX = TMAX;
    }
    @JsonProperty("TMIN")
    public String getTMIN() {
        return TMIN;
    }
    @JsonProperty("TMIN")
    public void setTMIN(String TMIN) {
        this.TMIN = TMIN;
    }
}
