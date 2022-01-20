package com.uni.mariostefano.meteo.esame.filters;


	import org.json.JSONObject;

	import com.uni.mariostefano.meteo.esame.model.City;
	import com.uni.mariostefano.meteo.esame.controller.ServiceImpl;



	/** Questa classe contiene i metodi necessari alle statistiche ed estende la classe ErrorCalculator.
	 * @author Federica Parlapiano
	 * @author Francesca Palazzetti 
	 */


	public class Statistics  {
		
		ServiceImpl service = new ServiceImpl();
		
		/**
		 * Questo metodo serve per calcolare le medie giornaliere.
		 * @param name è il nome della città su cui si vogliono fare statistiche.
		 * @return JSONObject contenente il nome della città e le relative  medie
		 */

	    public JSONObject todayAverage(String name) {
	         
	        
	        City city = new City(name);
	        city = service.getCityWeatherRistrictfromApi(name);
	        
	        double temp_max_ave = 0;
	        double temp_min_ave = 0;
	        double feels_like_ave = 0;
	        
	        double variance = 0;
	        
	        int i=0;
	        
	        String date = "";
	        date += (city.getVector().get(0).getData()).charAt(8);
	        date += (city.getVector().get(0).getData()).charAt(9);
	    
	        String effectiveDate = date;
	        
	       
	        
	        while(date.equals(effectiveDate)) {
	            temp_max_ave += city.getVector().get(i).getTemp_max();
	            temp_min_ave += city.getVector().get(i).getTemp_min();
	            feels_like_ave += city.getVector().get(i).getfeels_like();	           
	            i++;
	            effectiveDate = "";
	            effectiveDate += (city.getVector().get(i).getData()).charAt(8);
	            effectiveDate += (city.getVector().get(i).getData()).charAt(9);
	        }
	        
	        
	        temp_max_ave = temp_max_ave/i;
	        temp_min_ave = temp_min_ave/i;
	       	feels_like_ave = feels_like_ave/i;
	      
	        
	       	effectiveDate = date;
	        i=0;	        	     
	    
	        
	        variance /= i;
	        
	        JSONObject object = new JSONObject();
	      //  JSONObject visibility_data = new JSONObject();
	        
	        
	        object.put("CityName", name);
	        object.put("Temp_Max Average", temp_max_ave);
	        object.put("Temp_Min Average", temp_min_ave);
	        object.put("Feels_like Average", feels_like_ave);
	    //    object.put("Visibility Data", visibility_data);
	        
	        return object;
	        
	    }
		

		/**
		 * Questo metodo serve per calcolare le medie su sette giorni.
		 * @param name è il nome della città su cui si vogliono fare statistiche.
		 * @return JSONObject contenente il nome della città e le relative  medie
		 */
	    public JSONObject sevenDayAverage(String name) {
	         
	        
	        City city = new City(name);
	        city = service.getCityWeatherRistrictfromApi(name);
	        
	        double temp_max_ave = 0;
	        double temp_min_ave = 0;
	        double feels_like_ave = 0;
	       
	        double variance = 0;
	        
	        int i=0;
	        

	            
	        temp_max_ave = temp_max_ave/i;
	        temp_min_ave = temp_min_ave/i;
	        feels_like_ave = feels_like_ave/i;
	        
	        
	      
	        
	        variance /=i;
	        
	        JSONObject object = new JSONObject();
	       // JSONObject visibility_data = new JSONObject();
	        	      
	        
	        object.put("CityName", name);
	        object.put("Temp_Max Average", temp_max_ave);
	        object.put("Temp_Min Average", temp_min_ave);
	        object.put("Feels_like Average", feels_like_ave);
	       // object.put("Visibility Data", visibility_data);

	        return object;
	        
	    }
}
