package com.uni.mariostefano.meteo.esame.controller;

import org.json.simple.JSONObject;

public interface Service {

public abstract JS0NObject toJSON(City city);
public abstract JSONObject getJSONForecast(String city);
public abstract City getForecast (JS0NObject forecast);
public abstract void saveToFile(JSONObject obj);

 

}
