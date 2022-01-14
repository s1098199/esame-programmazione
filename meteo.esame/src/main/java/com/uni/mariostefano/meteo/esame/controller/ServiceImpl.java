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
		
		City city = new City(name);
		
		city = getCityInfofromApi(name);
		
		
		
		ArrayList forecastsArray = (ArrayList)object.get("list");
		LinkedHashMap counter;
		
		Vector<forecasts> vector = new Vector<forecasts>(forecastsArray.size());
		
		
		try {
			
			
			for (int i = 0; i<forecastsArray.size(); i++) {
				
				forecasts weather = new forecasts();
				counter = (LinkedHashMap) forecastsArray.get(i);				
				weather.setData(counter.get("dt_txt").toString());
				LinkedHashMap main=new LinkedHashMap<>();
				main=(LinkedHashMap) counter.get("main");
				weather.setpressure((int) main.get("pressure"));
				weather.sethumidity( (int) main.get("humidity"));
				//weather.setTemp((int) main.get("temp"));
				//weather.setTemp_max( (double) main.get("temp_max"));
				//weather.setTemp_min( (double) main.get("temp_min"));
				weather.setfeels_like( (double) main.get("feels_like"));
				vector.add(weather); 
		
			}
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		city.setVector(vector);
		
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
	
	public JSONArray readHistory(String cityName, boolean flag ) throws IOException {
		
		String path = "";
		
		    
		    	path = System.getProperty("user.dir") +"\\"+ cityName + ".txt";
/*String path = "";
		
		if(flag) {
			path = System.getProperty("user.dir") + "/error/" + name +".txt";
		}
		else path = System.getProperty("user.dir") + "/Capri/" + name +"HourlyReport.txt";	        
		  /*else if (flag){ 
	 	path = System.getProperty("user.dir") + "\\humidity\\" + name +".txt";
			}
		else if (flag) {
			path = System.getProperty("user.dir") + "\\temp\\" + name +".txt";
			}
		else if (flag) {
			path = System.getProperty("user.dir") + "\\error\\" + name +".txt";
		}*/
		
		
		String everything;
			
		BufferedReader br = new BufferedReader(new FileReader(path));
		StringBuilder sb;
		
			try {
				sb = new StringBuilder();
			    String line = br.readLine();

			    while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
			    }
			   // System.out.println(sb);
			    everything = sb.toString();
			} finally {
			    br.close();
			}
				
		
			JSONArray array = new JSONArray();
			array.add(everything);
	
			return array;
			
	}
	
public String save(String cityName) throws IOException {
	
	
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	String today = date.format(new Date());


	String nameFile=cityName +".txt";
	String path=System.getProperty("user.dir")+nameFile;
	City city = getCityWeatherRistrictfromApi(cityName);        
    
	JSONObject obj = new JSONObject();
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
		write.print("\n");
		write.append(obj.toString());
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
	/**
	 * Questo metodo serve per raccogliere le informazioni dallo storico di ogni città passata in ingresso 
	 * e richiama altri metodi che servono per leggere lo storico stesso e metodi per calcolare statistiche e filtrarle.
	 * 
.
	 * @throws EmptyString se almeno uno dei nomi inseriti è uguale alla stringa vuota.
	 * @throws ExceptionCity se l'utente ha inserito una città di cui non è presente lo storico. Le stringhe ammesse
	 *         sono: "chieuti","tortoreto","capri","civitella del tronto","sant'egidio alla vibrata".
	 * @throws WrongPeriod se l'utente ha inserito un numero che non è compreso tra 1 e 7 (inclusi).
	 * @throws WrongValue se l'utente ha inserito una stringa non ammessa per il value.
	 * @throws IOException se si verificano problemi nella lettura del file.
	 */
	public ArrayList<ArrayList> readHistoryError(String cityName) 
			throws EmptyString , ExceptionCity , WrongPeriod , WrongValue ,  IOException {
			
	
		//	Iterator<String> it = cities.iterator();
			
			ArrayList<ArrayList> All= new ArrayList<ArrayList>();
			ArrayList<JSONArray> PressureArray = new ArrayList<JSONArray>();
			ArrayList<JSONArray> HumidityArray = new ArrayList<JSONArray>();
			ArrayList<JSONArray> TempArray = new ArrayList<JSONArray>();
			//return null;
            
			//while(it.hasNext()) {
				
				JSONArray array = new JSONArray();
			
				
				
				array = readHistory(cityName, false);
				//String everything=(String) array.get(0);
				//everything.split(ok);
				/*Scanner input=new Scanner(everything);
				input.next();
				input.next();
				while(!(input.next()=="\n"))
				if(input.next().equals("temp"))
				{
					input.next();
					double temp=Double.valueOf(input.next());
					
					
				}*/
				
				JSONArray pressureinfo = new JSONArray();
				JSONArray humidityinfo = new JSONArray();
				JSONArray Tempinfo = new JSONArray();
				
				for(int i=0; i<array.size(); i++) {
					/*
					 *{ [{
					 * 			weather:.......
					 * }]}
					 */
					JSONArray pressureday = new JSONArray();
					JSONArray humidityday = new JSONArray();
					JSONArray tempday = new JSONArray();
					
					JSONObject weather = new JSONObject();
					weather.put("weather",array.get(0)); 
					
					JSONArray arr = new JSONArray();
				//	arr = (JSONArray)weather.get("weather");
					arr.add(weather.get("weather"));
					
					
					for(int j=0; j<arr.size();j++) {
						
						JSONObject pressure = new JSONObject();
						JSONObject humidity = new JSONObject();
						JSONObject temp = new JSONObject();
						JSONObject all = new JSONObject();
						all.put("all",arr.get(j));
						
						
						pressure.put("pressure", all.get("pressure"));
						humidity.put("humidity", all.get("humidity"));
						temp.put("humidity", all.get("temp"));
						pressure.put("data", all.get("data"));
						humidity.put("data", all.get("data"));
						temp.put("data", all.get("data"));

						pressureday.add(pressure);
						humidityday.add(humidity);
						tempday.add(temp);
						
					
					}
					
					pressureinfo.add(pressureday);
					humidityinfo.add(humidityday);
					pressureinfo.add(tempday);
					
					
				}
				
				PressureArray.add(pressureinfo);
				HumidityArray.add(humidityinfo);
				TempArray.add(Tempinfo);
				
	//}
All.add(PressureArray);
All.add(HumidityArray);
All.add(TempArray);
return All;
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


			
			
			
			

 









			

	



