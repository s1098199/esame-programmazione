package com.uni.mariostefano.meteo.esame.controller;


import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//import org.json.JSONArray;
//import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import com.uni.mariostefano.meteo.esame.model.City;

import org.json.simple.*;
import org.springframework.stereotype.Service;

import com.uni.mariostefano.meteo.esame.exception.ExceptionCity;
import com.uni.mariostefano.meteo.esame.exception.EmptyString;
import com.uni.mariostefano.meteo.esame.exception.WrongPeriod;
import com.uni.mariostefano.meteo.esame.exception.WrongValue;
import com.uni.mariostefano.meteo.esame.model.*;
//import com.project.WeatherApp.utils.error.ErrorCalculator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/** Questa classe è l'implementazione dell'interfaccia Service.
 * Contiene i metodi che vengono utilizzati dal controller.
 * @author Stefano Bandello
 * @author Mario De Benardinis
 */

@Service

public class ServiceImpl implements com.uni.mariostefano.meteo.esame.controller.Service {
	
	
	/**
	 * api_key è la key necessaria per ottenere informazioni.
	 */
	private String api_key = "666efac3e1caf3f728f8c5860edeb469";
	
	
	/**
	 * Questo metodo prendere da forecasts le previsioni meteo di una città.
	 * @param è il nome della città di cui si vuole conoscere le previsioni meteo.
	 * @return un JSONObject contenente le previsioni meteo complete.
	 */
	public JSONObject getCityWeather(String city) {
		
		JSONObject obj;
		String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid="+api_key;
		
		RestTemplate rt = new RestTemplate();
		
		obj = new JSONObject(rt.getForObject(url, String.class));
		
		return obj;
		
	
	try{
	      BufferedWriter out = new BufferedWriter (new FileWriter(api_key));
	      out.write(obj.toJSONString());
	      out.close();
	}
	catch(IOException e){
	          System.out.println("error");
	          System.out.println(e);
	}


	}
}


