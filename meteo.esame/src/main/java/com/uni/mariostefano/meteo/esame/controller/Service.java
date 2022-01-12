package com.uni.mariostefano.meteo.esame.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.*;

//import org.json.JSONArray;
import com.uni.mariostefano.meteo.esame.exception.EmptyString;
import com.uni.mariostefano.meteo.esame.exception.ExceptionCity;
import com.uni.mariostefano.meteo.esame.exception.WrongPeriod;
import com.uni.mariostefano.meteo.esame.exception.WrongValue;
import com.uni.mariostefano.meteo.esame.model.City;

/** Questa classe è l'interfaccia di ServiceImpl e contiene i metodi richiamati dal Controller.
 * @author Stefano Bandello
 * @author Mario De Benardinis 
 
 */
public interface Service {
	
	public abstract JSONObject getCityW(String city);
	public abstract City getCityInfofromApi(String name);
	public abstract JSONArray getfromApi(String city);
	
	public abstract City getCityWeatherRistrictfromApi(String city);
	public abstract String save(String city) throws IOException;
	public String saveEveryHour(String cityName);
	//public abstract ArrayList<ArrayList> readHistoryError(ArrayList<String> cities,int error,String value,int period) throws EmptyString , ExceptionCity , WrongPeriod , WrongValue ,  IOException;
	

}
	