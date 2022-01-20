package com.uni.mariostefano.meteo.esame.controller;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
//import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uni.mariostefano.meteo.esame.exception.EmptyString;
import com.uni.mariostefano.meteo.esame.exception.ExceptionCity;
import com.uni.mariostefano.meteo.esame.exception.WrongPeriod;
import com.uni.mariostefano.meteo.esame.exception.WrongValue;
import com.uni.mariostefano.meteo.esame.model.City;

import com.uni.mariostefano.meteo.esame.model.forecasts;


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
	private String api_key = "67e3d274028b01442141ddff3600d722";
	
	
	/**
	 * Questo metodo prendere da forecasts le previsioni meteo di una città.
	 * @param è il nome della città di cui si vuole conoscere le previsioni meteo.
	 * @return un JSONObject contenente le previsioni meteo complete.
	 */
	public JSONObject getCityW(String city) {
		
		JSONObject obj;
		String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid="+api_key;
		
		RestTemplate rt = new RestTemplate();
		
		obj = new JSONObject (rt.getForObject(url, JSONObject.class));
		
		return obj;
	}
	

	/**
	 * Questo metodo utilizza getCityW per  prendere le previsioni sulla pressione della città richiesta.
	 * @param  il nome della città che si vuole conoscere la pressione.
	 * @return restituisce il JSONArray contente la pressione con la relativa data e ora.
	 */

	public JSONArray getfromApi(String name) {
		
		JSONObject object = getCityW(name);
		JSONArray toGive = new JSONArray();
			
		JSONArray forecastsArray = (JSONArray)object.get("list");
		LinkedHashMap weather=new LinkedHashMap<>();
		
			JSONObject support;
			double pressure;
			double humidity;
			double temp;
			int i =0;
			String data;
			
			while(!forecastsArray.isEmpty()) {
				
			
			
				support =(JSONObject) forecastsArray.get(i);
				weather=(LinkedHashMap) support.get("main");
				pressure = (int) weather.get("pressure");
				humidity=(int) weather.get("humidity");
				temp=(int) weather.get("temp");
				data = (String) support.get("dt_txt");
				JSONObject toReturn = new JSONObject();
				toReturn.put("Pressure", pressure);
				toReturn.put("temp", temp);
				toReturn.put("Humidity", humidity);
				toReturn.put("Data", data);
				toGive.add(toReturn);
				forecastsArray.remove(i);
				i++;
			}
			return toGive;
			
				
		}
			 
	
			
	


	/**
	 * Questo metodo utilizza getCityW per  selezionare le previsioni meteo ristrette (umidità, pressione, temperatura max, min, percepita
	 *  ).
	 * @param name è il nome della città di cui si vogliono conoscere le previsioni ristrette.
	 */
	public City getCityWeatherRistrictfromApi(String name) {
		
		JSONObject object = getCityW(name);
//		JSONObject obj=new JSONObject();
		City city = new City(name);
//		JSONArray arr = new JSONArray();
		city = getCityInfofromApi(name);
		
		
		
		ArrayList forecastsArray = (ArrayList)object.get("list");
		LinkedHashMap counter;
		
		Vector<forecasts> vector = new Vector<forecasts>(forecastsArray.size());
		
		
		try {
			
			
			for (int i = 0; i<forecastsArray.size(); i++) {
//				obj = new JSONObject();
				forecasts weather = new forecasts();
				counter = (LinkedHashMap) forecastsArray.get(i);				
				weather.setData(counter.get("dt_txt").toString());
				LinkedHashMap main=new LinkedHashMap<>();
				main=(LinkedHashMap) counter.get("main");
				weather.setpressure((int) main.get("pressure"));
				weather.sethumidity( (int) main.get("humidity"));
				weather.setTemp((double) main.get("temp"));				
				vector.add(weather); 
//				obj.put("weather",weather);
		
			}
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		city.setVector(vector);
//		arr.add(obj);
		return city;

	}
	

	
	
	/**
	 * Questo metodo viene richiamato da readHistoryError e da readPressurreHistory.
	 * Si occupa della lettura dello storico della città passata in ingresso. A seconda che il flag sia true o false, il 
	 * metodo andrà a leggere lo storico 
	 * 
	 * @param name è il nome della città di cui si vuole leggere lo storico.
	 * @param flag indica quale storico andare a leggere.
	 * @return il JSONArray che contiene tutte le informazioni sulla Pressione.
	 * @throws IOException se si verificano errori di input da file.
	 */
	
	public JSONArray readHistory(String cityName) throws IOException {
		
		String path = "";
		
		    
		    	path = System.getProperty("user.dir") +"\\"+ cityName + ".txt";		
	
		BufferedReader br = new BufferedReader(new FileReader(path));
		
		JSONArray arr = new JSONArray();
		JSONObject obj=new JSONObject();
		String ok2;
		String[] ok3;
		
		for( int i=0; i<160; i++) {
			String ok = br.readLine();
			ok3 = ok.split(":");
			obj=new JSONObject();
			
			if( ok3[0].equals("temp")){
		
    			System.out.println(ok);
				obj.put(ok3[0],Double.valueOf(ok3[1]));
				
		}
			else if(ok3[0].equals("data")) {	
				System.out.println(ok);
				obj.put(ok3[0],(ok3[1]));
				
			}
			
			else if(ok3[0].equals("humidity")){
				System.out.println(ok);								
				obj.put(ok3[0],Double.valueOf(ok3[1]));
				
		}
			else if (ok3[0].equals("pressure")){
				System.out.println(ok);								
				obj.put(ok3[0],Double.valueOf(ok3[1]));
				
		}
			
			arr.add(obj);
		}
		
		return arr;	
		
	}
				
	
public String save(String cityName) throws IOException {
	
	
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	String today = date.format(new Date());


	String nameFile=cityName +".txt";
	String path=System.getProperty("user.dir")+nameFile;
	City city = getCityWeatherRistrictfromApi(cityName);        
    
	JSONObject obj = new JSONObject();
	JSONArray arr=new JSONArray();
	ToJSON tojson = new ToJSON();
    
	obj = tojson.ToJSON(city);
	
	
	try{

		File file_out=new File(nameFile);
		PrintWriter write;

		if(file_out.exists())
		{
			FileOutputStream existing_file= new FileOutputStream(nameFile,true);
			 write= new PrintWriter(existing_file);
		}
			

		
		else 
		{
			file_out.createNewFile();
			write= new PrintWriter(file_out);

		}
		arr=(JSONArray) obj.get("Weather");
		for(int i=0; i<arr.size(); i++)
		{
			if (i!=0)write.print("\n");
			
			write.append("temp:"+((JSONObject) arr.get(i)).get("temp").toString()+"\n");
			write.append("data:"+((JSONObject) arr.get(i)).get("data").toString()+"\n");
			write.append("humidity:"+((JSONObject) arr.get(i)).get("humidity").toString()+"\n");
			write.append("pressure:"+((JSONObject) arr.get(i)).get("pressure").toString());
		}
		
		write.close();

	}catch (IOException e) {System.out.println(e);};


	return nameFile;
        
	
	
        
	}


public String saveEveryHour(String cityName) {
	
	String path = System.getProperty("user.dir") + "\\" + cityName ;
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	String today = date.format(new Date());
	File file = new File(cityName );
	
	ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	scheduler.scheduleAtFixedRate(new Runnable() {
	    @Override
	    public void run() {
	    	
	    	
	    	try {
				save(cityName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	}, 0, 5, TimeUnit.HOURS);
	
	
	return "Il file è stato salvato in " + path;
	
}
	
	public City getCityInfofromApi(String name) {
		
		JSONObject object = getCityW(name);
		
		City city = new City(name);
		
		try {
			
			LinkedHashMap cityObj =(LinkedHashMap) object.get("city");
			String country = (String) cityObj.get("country");
			int id = (int) cityObj.get("id");

			city.setNation(country);
			city.setId(id);
		;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return city;
	}
	
	
}



			
			
			
			

 









			

	



