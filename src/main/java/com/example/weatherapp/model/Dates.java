package com.example.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dates {

    @JsonProperty("DATE")
    private String DATE;

    public Dates(String date){
        this.DATE = date;
    }

    @JsonProperty("DATE")
    public String getDate() {
        return DATE;
    }

    @JsonProperty("DATE")
    public void setDate(String date) {
        this.DATE = date;
    }
}
