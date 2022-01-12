package com.uni.mariostefano.meteo.esame.controller;


	import org.json.simple.*;
	import org.json.simple.JSONArray;
	import org.json.simple.JSONObject;
	import org.json.simple.parser.JSONParser;
	import org.json.simple.parser.ParseException;
	
	import java.util.*;	
	import com.uni.mariostefano.meteo.esame.model.*;






	/** Questa classe contiene il metodo per la scrittura di una città in un JSONObject.
	 *  @author Stefano Bandello
	 *  @author Mario De Berardinis
	 */
	public class ToJSON {
		City city = new City();
		

		
		/**
		 * Questo metodo restituisce il JSONObject corrispondente all'oggetto City passato in ingresso.
		 * 
		 * @param city città che si vuole come un JSONObject.
		 * @return il JSONObject che rappresenta la città.
		 */
		public JSONObject ToJSON(City city) {
			
			
			JSONObject object = new JSONObject();
			
			object.put("name", city.getName());
			object.put("Nation", city.getNation());
			object.put("id", city.getId());

			
			
			JSONArray arr = new JSONArray();
			
			for(int i=0; i<(city.getVector()).size(); i++) {
				
				JSONObject forecasts = new JSONObject();
				forecasts.put("data", (city.getVector()).get(i).getData());
				forecasts.put("humidity", (city.getVector()).get(i).gethumidity());
				forecasts.put("temp", (city.getVector()).get(i).getTemp());
				//forecasts.put("temp_min",(city.getVector()).get(i).getTemp_min());
				forecasts.put("pressure", (city.getVector()).get(i).getpressure());
    			arr.add(i, forecasts);
			}
			
			
			object.put("Weather", arr);
			
			return object;
		}
		
		
	}


