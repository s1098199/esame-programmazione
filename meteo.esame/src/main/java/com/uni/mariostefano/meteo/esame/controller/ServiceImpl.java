package com.uni.mariostefano.meteo.esame.controller;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
			
			JSONArray forecastsArray = getJSONArray("list");
		LinkedHashMap weather=new LinkedHashMap<>();
		//main=(LinkedHashMap) object.get("main");
		//JSONObject obj2=new JSONObject();
			
			JSONObject support= null;
			int pressure;
			int humidity;
			String data;
			
			for (int i = 0; i<forecastsArray.size(); i++) {
				
			
				//String s = JSONObject.toJSONString(support);
				support =(JSONObject) forecastsArray.get(i);
				weather=(LinkedHashMap) support.get("main");
				pressure = (int) weather.get("pressure");
				humidity=(int) weather.get("humidity");
				data = (String) support.get("dt_txt");
				JSONObject toReturn = new JSONObject();
				toReturn.put("Pressure", pressure);//SchedulerExecutor
				toReturn.put("Humidity", humidity);
				toReturn.put("Data", data);
				toGive.add(toReturn);
			}
			return toGive;
			
				
		}
			 
	
			/**
			 * Questo metodo utilizza getCityW per  prendere le previsioni sulla umidità della città richiesta.
			 * @param  il nome della città che si vuole conoscere l' umidità.
			 * @return restituisce il JSONArray contente l' umidità con la relativa data e ora.
			 

			public JSONArray getHumidityfromApi(String name) {
				
				JSONObject object = getCityW(name);
				JSONArray toGive = new JSONArray();
					
					JSONArray forecastsArray = getJSONArray("list");
					JSONObject support = null;
					int humidity;
					String data;
					
					for (int i = 0; i<forecastsArray.size(); i++) {
						
						//String s = JSONObject.toJSONString(support);
					support =(JSONObject) object.get("main");
						humidity = (int) support.get("humidity");
						data = (String) support.get("dt_txt");
						JSONObject toReturn = new JSONObject();
						toReturn.put("Humidity", humidity);
						toReturn.put("Data", data);
						toGive.add(toReturn);
						
					}
					return toGive;
		
	
		
	}*/
	private JSONArray getJSONArray(String string) {
				// TODO Auto-generated method stub
				return null;
			}


	/**
	 * Questo metodo utilizza getCityW per  selezionare le previsioni meteo ristrette (umidità, pressione, temperatura
	 *  ).
	 * @param name è il nome della città di cui si vogliono conoscere le previsioni ristrette.
	 */
	public City getCityWeatherRistrictfromApi(String name) {
		
		JSONObject object = getCityW(name);
		
		City city = new City(name);
		
		city = getCityInfofromApi(name);
		
		
		
		JSONArray forecastsArray = getJSONArray("main");
		JSONObject counter;
		
		Vector<forecasts> vector = new Vector<forecasts>(forecastsArray.size());
		
		
		try {
			
			
			for (int i = 0; i<forecastsArray.size(); i++) {
				
				forecasts weather = new forecasts();
				counter = (JSONObject) forecastsArray.get(i);				
				weather.setData(counter.get("dt_txt").toString());
				//JSONArray arrayW =(JSONA counter.get("weather");
				//JSONObject objectW = arrayW.getJSONObject(0);
				//JSONObject objectW2 = counter.getJSONObject("");
				//forecasts.setTemp_max(objectW2.getDouble("temp_max"));
				//forecasts.setTemp_min(objectW2.getDouble("temp_min"));
				//forecasts.setfeels_like(objectW2.getdouble("feels_like"));
				LinkedHashMap main=new LinkedHashMap<>();
				main=(LinkedHashMap) counter.get("main");
				weather.setpressure((double) main.get("pressure"));
				weather.sethumidity( (double) main.get("humidity"));
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
	
	public JSONArray readHistory(String name, boolean flag) throws IOException {
		
		String path = "";
		
		if(flag) 
			
		
	 path = System.getProperty("user.dir") + "/pressure/" + name +".txt";
		else path = System.getProperty("user.dir") + "/humidity/" + name +".txt";
		
		String everything;
			
		BufferedReader br = new BufferedReader(new FileReader(path));
		
			try {
			    StringBuilder sb = new StringBuilder();
			    String line = br.readLine();

			    while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
			    }
			    everything = sb.toString();
			} finally {
			    br.close();
			}
				
		
			JSONArray array = new JSONArray ();
	
			return array;
			
	}
	
	/**
	 * Questo metodo viene richiamato da readHistoryError e da readHumidityHistory.
	 * Si occupa della lettura dello storico della città passata in ingresso. A seconda che il flag sia true o false, il 
	 * metodo andrà a leggere lo storico  
	 * 
	 * @param name è il nome della città di cui si vuole leggere lo storico.
	 * @param flag indica quale storico andare a leggere.
	 * @return il JSONArray che contiene tutte le informazioni sulla umidità.
	 * @throws IOException se si verificano errori di input da file.
	 
	
	public JSONArray readHistory2(String name, boolean flag) throws IOException {
		
		String path = "";
		
		if(flag) {
			path = System.getProperty("user.dir") + "/error/" + name +".txt";
		}
		else path = System.getProperty("user.dir") + "/humidity/" + name +".txt";
		
		String everything;
			
		BufferedReader br = new BufferedReader(new FileReader(path));
		
			try {
			    StringBuilder sb = new StringBuilder();
			    String line = br.readLine();

			    while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
			    }
			    everything = sb.toString();
			} finally {
			    br.close();
			}
				
		
			JSONArray array = new JSONArray();
	
			return array;
			
	}*/
public String save(String cityName) throws IOException {
        
		City city = getCityWeatherRistrictfromApi(cityName);        
        
		JSONObject obj = new JSONObject();
		ToJSON tojson = new ToJSON();
        
		obj = tojson.ToJSON(city);
        
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String today = date.format(new Date());
        
		String nomeFile = cityName+"_"+today;
		
		String path = System.getProperty("user.dir")+nomeFile+".txt";
        
		try{
			
			PrintWriter file_output = new PrintWriter(new BufferedWriter(new FileWriter(path)));
			
			
			file_output.println(obj.toString());
			file_output.close();
			
		}
		
		catch (Exception e) {
			System.err.println("Error: " + e);
		}
        
		return path;
        
	}


public String saveEveryHour(String cityName) {
	
	String path = System.getProperty("user.dir") + "/" + cityName + "HourlyReport.txt";
	
	File file = new File(path);
	
	ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	scheduler.scheduleAtFixedRate(new Runnable() {
	    @Override
	    public void run() {
	    	
	    	/*JSONArray visibility = new JSONArray();
	    	visibility = getVisibilityfromApi(cityName);
	    	
	    	JSONObject actualvisibility = new JSONObject();
	    	actualvisibility = visibility.getJSONObject(0);

	    			try{
	    			    if(!file.exists()) {
	    			        file.createNewFile();
	    			    }

	    			    FileWriter fileWriter = new FileWriter(file, true);
	    				
	    				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	    			    bufferedWriter.write(actualvisibility.toString());
	    			    bufferedWriter.write("\n");
	    			    
	    			    bufferedWriter.close();
	    			    
	    			} catch(IOException e) {
	    			    System.out.println(e);
	    			}*/
	    	try {
				save(cityName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	}, 0, 7, TimeUnit.HOURS);
	
	
	return "Il file è stato salvato in " + path;
	
}
	/**
	 * Questo metodo serve per raccogliere le informazioni dallo storico di ogni città passata in ingresso 
	 * e richiama altri metodi che servono per leggere lo storico stesso e metodi per calcolare statistiche e filtrarle.
	 * 
	 * @param cities contiene i nomi di tutte le città su cui si vogliono  applicare i filtri.
	 * @param error è l'intero che rappresenta la soglia con cui si vuole filtrare.
	 * @param value esprime il filtro che si vuole applicare, cioè se si vuole sapere quali città hanno un errore maggiore
	 *        o minore dell'intero error che è stato inserito. Le stringhe ammesse sono: "$lt", "$gt" e "=".
	 * @param period rappresenta i giorni di predizione (da 1 a 7 inclusi).
	 * @return restituisce l'ArrayList di JSONObject filtrati secondo i filtri indicati.
	 * @throws EmptyString se almeno uno dei nomi inseriti è uguale alla stringa vuota.
	 * @throws ExceptionCity se l'utente ha inserito una città di cui non è presente lo storico. Le stringhe ammesse
	 *         sono: "chieuti","tortoreto","capri","civitella del tronto","sant'egidio alla vibrata".
	 * @throws WrongPeriod se l'utente ha inserito un numero che non è compreso tra 1 e 7 (inclusi).
	 * @throws WrongValue se l'utente ha inserito una stringa non ammessa per il value.
	 * @throws IOException se si verificano problemi nella lettura del file.
	 */
	public ArrayList<ArrayList> readHistoryError(ArrayList<String> cities,int error,String value,int period) 
			throws EmptyString , ExceptionCity , WrongPeriod , WrongValue ,  IOException {
		
			for(int i=0; i<cities.size(); i++) {
				if(cities.get(i).isEmpty())
					throw new EmptyString("Hai dimenticato di inserire la città...");
				else if(!(cities.get(i).equals("Capri") || cities.get(i).equals("chieuti") || cities.get(i).equals("civitella del tronco") || cities.get(i).equals("tortoreto") || cities.get(i).equals("Sant'egidio alla vibrata")))
					throw new ExceptionCity ("Città non trovata nello storico");
			}
		
			if(period<1 || period>7)
				throw new WrongPeriod(period + " non è un numero ammesso. Devi inserire un numero compreso tra "
						+ "1 e 7 inclusi.");
			
			if(!(value.equals("$gt") || value.equals("$lt") || value.equals("=")))
				throw new WrongValue(value+ " non è una stringa ammessa. "
						+ "Devi inserire una stringa tra \"$gt\", \"$lt\" e \"=\"");
		
			Iterator<String> it = cities.iterator();
			
			ArrayList<ArrayList> All= new ArrayList<ArrayList>();
			ArrayList<JSONArray> PressureArray = new ArrayList<JSONArray>();
			ArrayList<JSONArray> HumidityArray = new ArrayList<JSONArray>();
			//return null;
while(it.hasNext()) {
				
				JSONArray array = new JSONArray();
				array = readHistory(it.next(),true);
				JSONArray pressureinfo = new JSONArray();
				JSONArray humidityinfo = new JSONArray();
				
				for(int i=0; i<array.size(); i++) {
					
					JSONArray pressureday = new JSONArray();
					JSONArray humidityday = new JSONArray();
					
					JSONObject weather = new JSONObject();
					weather = (JSONObject)array.get(i);
					
					JSONArray arr = new JSONArray();
					arr = (JSONArray)weather.get("Weather");
					
					
					for(int j=0; j<arr.size();j++) {
						
						JSONObject pressure = new JSONObject();
						JSONObject humidity = new JSONObject();
						JSONObject all = new JSONObject();
						all = (JSONObject)arr.get(j);
						
						
						pressure.put("pressure", all.get("pressure"));
						humidity.put("humidity", all.get("humidity"));
						pressure.put("data", all.get("data"));
						humidity.put("data", all.get("data"));

						pressureday.add(pressure);
						humidityday.add(humidity);
						
					
					}
					
					pressureinfo.add(pressureday);
					humidityinfo.add(humidityday);
					
					
				}
				
				PressureArray.add(pressureinfo);
				HumidityArray.add(humidityinfo);
				
			}
All.add(PressureArray);
All.add(HumidityArray);
return All;
			
					/*JSONObject forecasts = new JSONObject();
					
					forecasts = Array.getJSONObject(it);
					
					JSONArray arr = new JSONArray();
					arr =  ((JSONArray) forecasts).get("forecasts");
					
					
					for(int j=0; j<arr.size();j++) {
						
						JSONObject all = new JSONObject();
						all = (JSONObject)arr.get(j);*/
						
						
					
	}
public City getCityInfofromApi(String name) {
		
		JSONObject object = getCityW(name);
		
		City city = new City(name);
		
		try {
			
			JSONObject cityObj =(JSONObject) object.get("city");
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


			
			
			
			

 









			

	



