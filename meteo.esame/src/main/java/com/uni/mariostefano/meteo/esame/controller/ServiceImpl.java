package com.uni.mariostefano.meteo.esame.controller;


import java.io.BufferedReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.uni.mariostefano.meteo.esame.model.City;
import com.uni.mariostefano.meteo.esame.model.forecasts;


/** Questa classe è l'implementazione dell'interfaccia Service.
 * Contiene i metodi che vengono utilizzati dal controller.
 * @author Stefano Bandello
 */

@Service
public class ServiceImpl implements com.uni.mariostefano.meteo.esame.controller.Service {	
	
	/**
	 * api_key è la key necessaria per ottenere informazioni.
	 */
	private String api_key = "67e3d274028b01442141ddff3600d722";
		
	/**
	 * Questo metodo prendere da forecasts le previsioni meteo di una città.
	 * @param city è il nome della città di cui si vuole conoscere le previsioni meteo.
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
	 * Questo metodo utilizza getCityW per  prendere le previsioni sulla pressione e umidità e tempo della città richiesta.
	 * @param  il nome della città che si vuole conoscere la pressione.
	 * @return restituisce il JSONArray contente la pressione, umidità e tempo  con la relativa data e ora.
	 */

	public JSONArray getfromApi(String name) {	
	
		JSONObject object = getCityW(name);
		JSONArray toGive = new JSONArray();		
		ArrayList forecastsArray = (ArrayList)object.get("list");
		LinkedHashMap weather=new LinkedHashMap<>();		
		LinkedHashMap support =new LinkedHashMap<>();
			double pressure;
			double humidity;
			double temp;
			String data;
			
			for (int i = 0; i<forecastsArray.size(); i++) {
										
				support =(LinkedHashMap) forecastsArray.get(i);
				weather=(LinkedHashMap) support.get("main");
				pressure = (int) weather.get("pressure");
				humidity=(int) weather.get("humidity");
				temp=(double) weather.get("temp");
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
	 * Questo metodo utilizza getCityW per  selezionare le previsioni meteo ristrette.
	 * @param name è il nome della città di cui si vogliono conoscere le previsioni ristrette.
	 */
	public City getCityWeatherRistrictfromApi(String name) {
		
		JSONObject object = getCityW(name);
		City city = new City(name);
		city = getCityInfofromApi(name);
		
		ArrayList  forecastsArray = (ArrayList )object.get("list");
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
				weather.setTemp((double) main.get("temp"));				
				vector.add(weather);				
			}
	
		} catch(Exception e) {
			e.printStackTrace();
		}
				
		city.setVector(vector);
		return city;

	}
			
	/**.
	 * Si occupa della lettura dello storico della città passata in ingresso. 
	 * @param  cityName name è il nome della città di cui si vuole leggere lo storico.
	 * @return il JSONArray che contiene tutte le informazioni ristrette
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
				obj.put(ok3[0],Double.valueOf(ok3[1]));
				
		}
			else if(ok3[0].equals("data")) {	
				obj.put(ok3[0],(ok3[1]));
				
			}
			
			else if(ok3[0].equals("humidity")){							
				obj.put(ok3[0],Double.valueOf(ok3[1]));
				
		}
			else if (ok3[0].equals("pressure")){								
				obj.put(ok3[0],Double.valueOf(ok3[1]));				
		}
			
			arr.add(obj);
		}
		return arr;			
	}
				
	
	/**Questo metodo richiama getCityWeatherRistrictfromApi(String name) e serve per salvare su file le previsioni meteo 	 
	 * @param cityName è il nome della città
	 * @return una stringa contenente il path del file salvato.
	 */
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

/** Questo metodo richiama getCityWeatherRistrictfromApi(String name) e serve per salvare le previsioni meteo ogni ora.
 * @param  cityName è il nome della città
 * @return una stringa contenente il path del file salvato.
 */
public String saveEveryHour (String cityName) {
	
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
				e.printStackTrace();
			}	    	
	    }
	}, 0, 5, TimeUnit.HOURS);	

	return "Il file è stato salvato in " + path;
	
}
	
/** Questo metodo serve per ottenere le informazioni sulla città da OpenWeather. Viene richiamato da
* getCityWeatherRistrictfromApi(String name).
* @param  cityName nome della città.
* @return un oggetto di tipo città popolato delle informazioni sulla città.
*/
	public City getCityInfofromApi(String cityName) {
		
		JSONObject object = getCityW(cityName);
		
		City city = new City(cityName);
		
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



			
			
			
			

 









			

	



