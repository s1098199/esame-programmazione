package com.uni.mariostefano.meteo.esame.filters;


import org.json.simple.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uni.mariostefano.meteo.esame.model.City;
import com.uni.mariostefano.meteo.esame.controller.*;	




	/** Questa classe contiene i metodi necessari alle statistiche 
	 * @author Stefano Bandello
	 */

@Service
	public class Statistics  {

	
	ServiceImpl servizio ;

	@Autowired
	public Statistics() {
			servizio =  new ServiceImpl();
		}
		
		
	
		/**
		 * Questo metodo serve per calcolare le medie giornaliere.
		 * @param cityName è il nome della città su cui si vogliono fare statistiche.
		 * @return JSONObject contenente il nome della città e le relative  medie
		 */
public JSONObject todayAverage(String cityName) {
	         
	 JSONObject object = new JSONObject();	        
	      City city = new City(cityName);
	        city = servizio.getCityWeatherRistrictfromApi(cityName);
	        double temp_max_ave = 0;
	        double temp_min_ave = 10000000;
	        double temp_ave = 0;
	        
	        int i=0;
	        
	        String date = "";
	        date += (city.getVector().get(0).getData()).charAt(8);
	        date += (city.getVector().get(0).getData()).charAt(9);
	    
	        String effectiveDate = date;
	       
	        
	        while(date.equals(effectiveDate)) {
	            temp_ave += city.getVector().get(i).getTemp();
	           if(city.getVector().get(i).getTemp()<temp_min_ave) temp_min_ave = city.getVector().get(i).getTemp();
	           if(city.getVector().get(i).getTemp()>temp_max_ave) temp_max_ave = city.getVector().get(i).getTemp();
         
	            i++;
	            effectiveDate = "";
	            effectiveDate += (city.getVector().get(i).getData()).charAt(8);
	            effectiveDate += (city.getVector().get(i).getData()).charAt(9);
	        }
	        	        
	        temp_ave = temp_ave/i;

	        effectiveDate = date;
	        i=0;	      	     
	    	        
	        	        
	       object.put("CityName", cityName);
	       object.put("Temp_Max Average", temp_max_ave);
	       object.put("Temp_Min Average", temp_min_ave);
	       object.put("Temp Average", temp_ave);
	        return object;	        
	    }
		

		/**
		 * Questo metodo serve per calcolare le medie su sette giorni.
		 * @param cityName è il nome della città su cui si vogliono fare statistiche.
		 * @return JSONObject contenente il nome della città e le relative  medie
		 */
	    public JSONObject sevenDayAverage(String cityName) {
	                 
	        City city = new City(cityName);
	        city = servizio.getCityWeatherRistrictfromApi(cityName);
	        
	        double temp_max_ave = 0;
	        double temp_min_ave = 0;
	        double feels_like_ave = 0;       
	        
	        int i=0;
	        	            
	        temp_max_ave = temp_max_ave/i;
	        temp_min_ave = temp_min_ave/i;
	        feels_like_ave = feels_like_ave/i;
	        	        	     	        		        
	        JSONObject object = new JSONObject();
	       	        	      	        
	        object.put("CityName", cityName);
	        object.put("Temp_Max Average", temp_max_ave);
	        object.put("Temp_Min Average", temp_min_ave);
	        object.put("Feels_like Average", feels_like_ave);
	       
	        return object;
	        	     		
}
}
