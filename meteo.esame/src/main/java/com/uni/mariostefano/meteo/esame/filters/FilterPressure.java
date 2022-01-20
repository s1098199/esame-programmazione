package com.uni.mariostefano.meteo.esame.filters;


	import java.util.ArrayList;
	import java.util.Iterator;

	import org.json.JSONArray;
	import org.json.JSONObject;

	import com.uni.mariostefano.meteo.esame.exception.WrongValue;

	/**
	 * Questa classe implementa FilterStats e contiene i metodi per il filtraggio rispetto alla pressione.
	 * @author Stefano Bandello
	 * @author Mario De Berardinis 
	 */

	public class FilterPressure implements FilterStats{
		
		Statistics statistic = new Statistics();
		
		/**
		 * Questo metodo calcola la media, la varianza, la massima e la minima pressione di un giorno delle città passate in ingresso e filtra rispetto al value.
		 * Restituisce un JSONArray contenente JSONObject che rappresentano le città e le relative statistiche sulla pressione. 
		 * A seconda di value c'è un JSONObject che rappresenta la città con max/min valore di pressione
		 * @param cities rappresenta le città con cui si vuole fare la statistica e il filtraggio
		 * @param value rappresenta il valore con cui si vuole fare il filtraggio.
		 * @return JSONArray come descritto sopra. 
		 * @throws WrongValueException se viene inserita una stringa errata.
		 * 
		 */
		
		public JSONArray oneDay (ArrayList<String> cities, String value) throws WrongValue {
			
			JSONArray array = new JSONArray();
			
			ArrayList<JSONObject> average = new ArrayList<JSONObject>();
			ArrayList<JSONObject> averagePressure = new ArrayList<JSONObject>();
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
				JSONObject pressuredata = object.getJSONObject("Pressure Data");
				averagePressure.add(pressuredata);
				
				double max_pressure = pressuredata.getDouble("Max Pressure");
				double min_pressure = pressuredata.getDouble("Min Pressure");
				double pressure_average = pressuredata.getDouble("Pressure average");
				double pressure_variance = pressuredata.getDouble("Pressure variance");
				
				JSONObject obj = new JSONObject();
				obj.put("cityName",cities.get(i));
				obj.put("Pressure",pressuredata);
				objects.add(obj);
				array.put(obj);
				
				if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
					
						if(max_pressure>request1max) {
							request1max = max_pressure;
						}
						if(min_pressure>request2max) {
							request2max = min_pressure;
						}
						if(pressure_average>request3max) {
							request3max = pressure_average;
						}
						if(pressure_variance>=request4max) {
							request4max = pressure_variance;
						}
						
						i++;
						
					
				}
				else if(value.equals("min") || value.equals("MIN") || value.equals("Min")) {
					
					if(max_pressure<request1max) {
						request1max = max_pressure;
					}
					if(min_pressure<request2max) {
						request2max = min_pressure;
					}
					if(pressure_average<request3max) {
						request3max = pressure_average;
					}
					if(pressure_variance<request4max) {
						request4max = pressure_variance;
					}
					
					i++;
				}
				else throw new WrongValue (value+" è una stringa errata! Devi inserire una stringa tra max/MAX/Max oppure min/MIN/Min");
					
			}
			
			JSONObject object = new JSONObject();
			
			if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
				object.put("max max pressure", request1max);
				object.put("max min pressure", request2max);
				object.put("max pressure average", request3max);
				object.put("max variance average", request4max);
			}
			else { 
				object.put("min max pressure", request1min);
				object.put("min min pressure", request2min);
				object.put("min pressure average", request3min);
				object.put("min variance average", request4min);
			}
			
			
			array.put(object);
			
			return array;
		}
		
		
		/**
		 * Questo metodo calcola la media, la varianza, la massima e la minima pressione su sette giorni delle città passate in ingresso e filtra rispetto al value.
		 * Restituisce un JSONArray contenente JSONObject che rappresentano le città e le relative statistiche sulla pressione. 
		 * A seconda di value c'è un JSONObject che rappresenta la città con max/min valore di pressione
		 * @param cities rappresenta le città con cui si vuole fare la statistica e il filtraggio
		 * @param value rappresenta il valore con cui si vuole fare il filtraggio.
		 * @return JSONArray come descritto sopra. 
		 * @throws WrongValueException se viene inserita una stringa errata.
		 * 
		 */
		
		public JSONArray sevenDay (ArrayList<String> cities, String value) throws WrongValue {
			
			JSONArray array = new JSONArray();
			
			ArrayList<JSONObject> average = new ArrayList<JSONObject>();
			ArrayList<JSONObject> averagePressure = new ArrayList<JSONObject>();
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
				JSONObject pressuredata = object.getJSONObject("Pressure Data");
				averagePressure.add(pressuredata);
				
				double max_pressure = pressuredata.getDouble("Max pressure");
				double min_pressure = pressuredata.getDouble("Min Pressure");
				double pressure_average = pressuredata.getDouble("Pressure average");
				double pressure_variance = pressuredata.getDouble("Pressure variance");
				
				JSONObject obj = new JSONObject();
				obj.put("cityName",cities.get(i));
				obj.put("Pressure",pressuredata);
				objects.add(obj);
				array.put(obj);
				
				if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
					
					if(max_pressure>request1max) {
						request1max = max_pressure;
					}
					if(min_pressure>request2max) {
						request2max = min_pressure;
					}
					if(pressure_average>request3max) {
						request3max = pressure_average;
					}
					if(pressure_variance>request4max) {
						request4max = pressure_variance;
					}
					
					i++;
					
				}
				else if(value.equals("min") || value.equals("MIN") || value.equals("Min")) {
					
					if(max_pressure<request1max) {
						request1max = max_pressure;
					}
					if(min_pressure<request2max) {
						request2max = min_pressure;
					}
					if(pressure_average<request3max) {
						request3max = pressure_average;
					}
					if(pressure_variance<request4max) {
						request4max = pressure_variance;
					}
					
					i++;
				}
				else throw new WrongValue (value+" è una stringa errata! Devi inserire una stringa tra max/MAX/Max oppure min/MIN/Min");
					
			}
			
			JSONObject object = new JSONObject();
			
			if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
				object.put("max max pressure", request1max);
				object.put("max min pressure", request2max);
				object.put("max pressure average", request3max);
				object.put("max variance average", request4max);
			}
			else { 
				object.put("min max pressure", request1min);
				object.put("min min pressure", request2min);
				object.put("min pressure average", request3min);
				object.put("min variance average", request4min);
			}
			
			
			array.put(object);
			
			
			
			
			return array;
			
			
		}
		
		
	}

