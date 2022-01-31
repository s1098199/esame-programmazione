package com.uni.mariostefano.meteo.esame.filters;


	import java.util.ArrayList;
	import java.util.Iterator;

	import org.json.JSONArray;
	import org.json.JSONObject;

	import com.uni.mariostefano.meteo.esame.exception.WrongValue;

	/**
	 * Questa classe implementa FilterStats e contiene i metodi per il filtraggio rispetto all'umidità.
	 * @author Stefano Bandello
	 */

	public class FilterHumidity implements FilterStats{
		
		Statistics statistic = new Statistics();
		
		/**
		 * Questo metodo calcola la media, la varianza, la massima e la minima umidità di un giorno delle città passate in ingresso e filtra rispetto al value.
		 * Restituisce un JSONArray contenente JSONObject che rappresentano le città e le relative statistiche sull'umidità. 
		 * A seconda di value c'è un JSONObject che rappresenta la città con max/min valore di umidità
		 * @param cities rappresenta le città con cui si vuole fare la statistica e il filtraggio
		 * @param value rappresenta il valore con cui si vuole fare il filtraggio.
		 * @return JSONArray come descritto sopra. 
		 * @throws WrongValueException se viene inserita una stringa errata.
		 * 
		 */
		
		public JSONArray oneDay (ArrayList<String> cities, String value) throws WrongValue {
			
			JSONArray array = new JSONArray();
			
			ArrayList<JSONObject> average = new ArrayList<JSONObject>();
			ArrayList<JSONObject> averageHumidity = new ArrayList<JSONObject>();
			ArrayList<JSONObject> objects = new ArrayList<JSONObject>();
			
			Iterator<String> it = cities.iterator();
			
			int i = 0;
			
			double request1max = 0;
			double request1min = 100000;
			double request2max = 0;
			double request2min = 100000;
			double request3max = 0;
			double request3min = 100000;
			double request4max = 0;
			double request4min = 100000;
			
			while(it.hasNext()) {
				JSONObject object = new JSONObject();
     			object = statistic.todayAverage(it.next());
				average.add(object);
				JSONObject humiditydata = object.getJSONObject("Humidity Data");
				averageHumidity.add(humiditydata);
				
				double max_humidity = humiditydata.getDouble("Max Humidity");
				double min_humidity = humiditydata.getDouble("Min Humidity");
				double humidity_average = humiditydata.getDouble("Humidity average");
				double humidity_variance = humiditydata.getDouble("Humidity variance");
				
				JSONObject obj = new JSONObject();
				obj.put("cityName",cities.get(i));
				obj.put("Humidity",humiditydata);
				objects.add(obj);
				array.put(obj);
				
				if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
					
						if(max_humidity>request1max) {
							request1max = max_humidity;
						}
						if(min_humidity>request2max) {
							request2max = min_humidity;
						}
						if(humidity_average>request3max) {
							request3max = humidity_average;
						}
						if(humidity_variance>=request4max) {
							request4max = humidity_variance;
						}
						
						i++;
						
					
				}
				else if(value.equals("min") || value.equals("MIN") || value.equals("Min")) {
					
					if(max_humidity<request1max) {
						request1max = max_humidity;
					}
					if(min_humidity<request2max) {
						request2max = min_humidity;
					}
					if(humidity_average<request3max) {
						request3max = humidity_average;
					}
					if(humidity_variance<request4max) {
						request4max = humidity_variance;
					}
					
					i++;
				}
				else throw new WrongValue (value+" è una stringa errata! Devi inserire una stringa tra max/MAX/Max oppure min/MIN/Min");
					
			}
			
			JSONObject object = new JSONObject();
			
			if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
				object.put("max max humidity", request1max);
				object.put("max min humidity", request2max);
				object.put("max humidity average", request3max);
				object.put("max variance average", request4max);
			}
			else { 
				object.put("min max humidity", request1min);
				object.put("min min humidity", request2min);
				object.put("min humidity average", request3min);
				object.put("min variance average", request4min);
			}
			
			
			array.put(object);
			
			return array;
		}
		
		
		/**
		 * Questo metodo calcola la media, la varianza, la massima e la minima umidità su sette giorni delle città passate in ingresso e filtra rispetto al value.
		 * Restituisce un JSONArray contenente JSONObject che rappresentano le città e le relative statistiche sull'umidità. 
		 * A seconda di value c'è un JSONObject che rappresenta la città con max/min valore di umidità
		 * @param cities rappresenta le città con cui si vuole fare la statistica e il filtraggio
		 * @param value rappresenta il valore con cui si vuole fare il filtraggio.
		 * @return JSONArray come descritto sopra. 
		 * @throws WrongValueException se viene inserita una stringa errata.
		 * 
		 */
		
		public JSONArray sevenDay (ArrayList<String> cities, String value) throws WrongValue {
			
			JSONArray array = new JSONArray();
			
			ArrayList<JSONObject> average = new ArrayList<JSONObject>();
			ArrayList<JSONObject> averageHumidity = new ArrayList<JSONObject>();
			ArrayList<JSONObject> objects = new ArrayList<JSONObject>();
			
			Iterator<String> it = cities.iterator();
			
			int i = 0;
			
			double request1max = 0;
			double request1min = 100000;
			double request2max = 0;
			double request2min = 100000;
			double request3max = 0;
			double request3min = 100000;
			double request4max = 0;
			double request4min = 100000;
			
			while(it.hasNext()) {
				JSONObject object = new JSONObject();
				object = statistic.sevenDayAverage(it.next());
				average.add(object);
				JSONObject humiditydata = object.getJSONObject("Humidity Data");
				averageHumidity.add(humiditydata);
				
				double max_humidity = humiditydata.getDouble("Max Humidity");
				double min_humidity = humiditydata.getDouble("Min Humidity");
				double humidity_average = humiditydata.getDouble("Humidity average");
				double humidity_variance = humiditydata.getDouble("Humidity variance");
				
				JSONObject obj = new JSONObject();
				obj.put("cityName",cities.get(i));
				obj.put("Humidity",humiditydata);
				objects.add(obj);
				array.put(obj);
				
				if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
					
					if(max_humidity>request1max) {
						request1max = max_humidity;
					}
					if(min_humidity>request2max) {
						request2max = min_humidity;
					}
					if(humidity_average>request3max) {
						request3max = humidity_average;
					}
					if(humidity_variance>request4max) {
						request4max = humidity_variance;
					}
					
					i++;
					
				}
				else if(value.equals("min") || value.equals("MIN") || value.equals("Min")) {
					
					if(max_humidity<request1max) {
						request1max = max_humidity;
					}
					if(min_humidity<request2max) {
						request2max = min_humidity;
					}
					if(humidity_average<request3max) {
						request3max = humidity_average;
					}
					if(humidity_variance<request4max) {
						request4max = humidity_variance;
					}
					
					i++;
				}
				else throw new WrongValue (value+" è una stringa errata! Devi inserire una stringa tra max/MAX/Max oppure min/MIN/Min");
					
			}
			
			JSONObject object = new JSONObject();
			
			if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
				object.put("max max humidity", request1max);
				object.put("max min humidity", request2max);
				object.put("max humidity average", request3max);
				object.put("max variance average", request4max);
			}
			else { 
				object.put("min max humidity", request1min);
				object.put("min min humidity", request2min);
				object.put("min humidity average", request3min);
				object.put("min variance average", request4min);
			}
			
			
			array.put(object);
			
			
			
			
			return array;
			
			
		}
		
		
	}

