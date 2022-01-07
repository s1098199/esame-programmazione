package com.uni.mariostefano.meteo.esame.controller;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

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

	public JSONArray getPressurefromApi(String name) {
		
		JSONObject object = getCityW(name);
		JSONArray toGive = new JSONArray();
			
			JSONArray forecastsArray = getJSONArray("main");
			JSONObject support= null;
			int pressure;
			String data;
			
			for (int i = 0; i<forecastsArray.size(); i++) {
				
			
				String s = JSONObject.toJSONString(support);
				//support = forecastsArray.getJSONObject(i);
				pressure = (int) support.get("pressurre");
				data = (String) support.get("dt_txt");
				JSONObject toReturn = new JSONObject();
				toReturn.put("Pressure", pressure);
				toReturn.put("Data", data);
				toGive.add(toReturn);
			}
			return toGive;
			
				
		}
			 
	
			/**
			 * Questo metodo utilizza getCityW per  prendere le previsioni sulla umidità della città richiesta.
			 * @param  il nome della città che si vuole conoscere l' umidità.
			 * @return restituisce il JSONArray contente l' umidità con la relativa data e ora.
			 */

			public JSONArray getHumidityfromApi(String name) {
				
				JSONObject object = getCityW(name);
				JSONArray toGive = new JSONArray();
					
					JSONArray forecastsArray = getJSONArray("main");
					JSONObject support = null;
					int humidity;
					String data;
					
					for (int i = 0; i<forecastsArray.size(); i++) {
						
						String s = JSONObject.toJSONString(support);
					//	support = JSONObject.toJSONString(i);
						humidity = (int) support.get("humidity");
						data = (String) support.get("dt_txt");
						JSONObject toReturn = new JSONObject();
						toReturn.put("Humidity", humidity);
						toReturn.put("Data", data);
						toGive.add(toReturn);
						
					}
					return toGive;
		
	
		
	}
	private JSONArray getJSONArray(String string) {
				// TODO Auto-generated method stub
				return null;
			}


	/**
	 * Questo metodo utilizza getCityW per  selezionare le previsioni meteo ristrette (umidità, pressione, temperatura
	 *  ).
	 * @param name è il nome della città di cui si vogliono conoscere le previsioni ristrette.
	 */
	public void getCityWrRistrictfromApi(String name) {
		
		JSONObject object = getCityW(name);
		
		City city = new City(name);
		
		city = getCityInfoApi(name);
		
		
		
		JSONArray forecastsArray = getJSONArray("main");
		JSONObject counter;
		
		Vector<forecasts> vector = new Vector<forecasts>(forecastsArray.size());
		
		
	/*	try {
			
			
			for (int i = 0; i<forecastsArray.size(); i++) {
				
				forecasts weather = new forecasts();
				counter = ( forecastsArray).getJSONObject(i);				
				forecasts.setData(counter.toJSONString("dt_txt"));
				JSONArray arrayW = counter.getJSONArray("weather");
				JSONObject objectW = arrayW.getJSONObject(0);
				JSONObject objectW2 = counter.getJSONObject("");
				forecasts.setTemp_max(objectW2.getDouble("temp_max"));
				forecasts.setTemp_min(objectW2.getDouble("temp_min"));
				forecasts.setfeels_like(objectW2.getdouble("feels_like"));
				forecasts.setpressure(objectW2.getDouble("pressure"));
				forecasts.sethumidity(object.getdouble("humidity"));
				vector.add(weather); 
		
			}
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		city.setVector(vector);
		
		return city;
*/
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
	
	public JSONArray readHistory1(String name, boolean flag) throws IOException {
		
		String path = "";
		
		if(flag) {
			path = System.getProperty("user.dir") + "/error/" + name +".txt";
		}
		else path = System.getProperty("user.dir") + "/pressure/" + name +".txt";
		
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
	 */
	
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
			
	}
	
	/**
	 * Questo metodo serve per raccogliere le informazioni dallo storico di ogni città passata in ingresso 
	 * e richiama altri metodi che servono per leggere lo storico stesso e metodi per calcolare statistiche e filtrarle.
	 * 
	 * @param cities contiene i nomi di tutte le città su cui si vogliono fare statistiche sulla soglia di
	 *        errore e applicare i filtri.
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
	public ArrayList<JSONObject> readHistoryError(ArrayList<String> cities,int error,String value,int period) 
			throws EmptyString , ExceptionCity , WrongPeriod , WrongValue ,  IOException {
		
			for(int i=0; i<cities.size(); i++) {
				if(cities.get(i).isEmpty())
					throw new EmptyString("Hai dimenticato di inserire la città...");
				else if(!(cities.get(i).equals("capri") || cities.get(i).equals("chieti") || cities.get(i).equals("civitella del tronco") || cities.get(i).equals("tortoreto") || cities.get(i).equals("Sant'egidio alla vibrata")))
					throw new ExceptionCity ("Città non trovata nello storico");
			}
		
			if(period<1 || period>7)
				throw new WrongPeriod(period + " non è un numero ammesso. Devi inserire un numero compreso tra "
						+ "1 e 7 inclusi.");
			
			if(!(value.equals("$gt") || value.equals("$lt") || value.equals("=")))
				throw new WrongValue(value+ " non è una stringa ammessa. "
						+ "Devi inserire una stringa tra \"$gt\", \"$lt\" e \"=\"");
		
			Iterator<String> it = cities.iterator();
			
			ArrayList<JSONArray> PressureArray = new ArrayList<JSONArray>();
			ArrayList<JSONArray> HumidityArray = new ArrayList<JSONArray>();
					
					
					JSONObject forecasts = new JSONObject();
					forecasts = array.getJSONObject(it);
					
					JSONArray arr = new JSONArray();
					arr = ( forecasts).getJSONArray("forecasts");
					
					
					for(int j=0; j<arr.size();j++) {
						
						JSONObject all = new JSONObject();
						all = arr.getJSONObject(j);
						
						
					
	}					

 }


	@Override
	public City getCityInfoApi(String city) {
         // TODO Auto-generated method stub
		return null;
	}


	@Override
	public City getCityWeatherRistrictfromApi(String city) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String save(String city) throws IOException {
		// TODO Auto-generated method stub
	return null;
	}


	@Override
	public String saveEveryHour(String cityName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<JSONArray> readHumidityHistory(ArrayList<String> cities, String period)
			throws EmptyString, ExceptionCity, WrongPeriod, WrongValue, IOException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<JSONArray> readPressureHistory(ArrayList<String> cities, String period)
			throws EmptyString, ExceptionCity, WrongPeriod, WrongValue, IOException {
		// TODO Auto-generated method stub
		return null;
	}



			
}
	



