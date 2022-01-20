package com.uni.mariostefano.meteo.esame.filters;


	import java.util.ArrayList;
	import java.util.Iterator;

	import org.json.JSONArray;
	import org.json.JSONObject;

	import com.uni.mariostefano.meteo.esame.exception.WrongValue;

	/**
	 * Questa classe implementa FilterStats e contiene i metodi per il filtraggio rispetto alla temperatura massima.
	 * @author Stefano Bandello
	 * @author Mario De Berardinis 
	 */


	public class FilterTempMax implements FilterStats {
		
		Statistics statistic = new Statistics();
		
		/**
		 * Questo metodo calcola la media della temperatura massima di un giorno delle città passate in ingresso e
		 * filtra rispetto al value.
		 * Restituisce un JSONArray contenente JSONObject che rappresentano le città e le relative temperature massime. 
		 * A seconda di value c'è un JSONObject che rappresenta la città con max/min valore di temperatura massima.
		 * 
		 * @param cities rappresenta le città con cui si vuole fare la statistica e il filtraggio
		 * @param value rappresenta il valore con cui si vuole fare il filtraggio.
		 * @return JSONArray come descritto sopra. 
		 * @throws WrongValueException se viene inserita una stringa non ammessa, cioè una stringa che non sia max,MAX,Max o 
		 *         min, MIN, Min.
		 */
		
		public JSONArray oneDay (ArrayList<String> cities, String value) throws WrongValue {
			
			JSONArray array = new JSONArray();
			
			ArrayList<JSONObject> average = new ArrayList<JSONObject>();
			ArrayList<Double> averageTempMax = new ArrayList<Double>();
			ArrayList<JSONObject> objects = new ArrayList<JSONObject>();
			ArrayList<String> names = new ArrayList<String>();
			
			Iterator<String> it = cities.iterator();
			
			double request1 = 0;
			double request2 = 1000;

			int i = 0;
			
			while(it.hasNext()) {
				JSONObject object = new JSONObject();
				object = statistic.todayAverage(it.next());
				average.add(object);
				double ave = object.getDouble("Temp_Max Average");
				averageTempMax.add(ave);
				
				JSONObject obj = new JSONObject();
				obj.put("cityName:",cities.get(i));
				obj.put("temp_max_average:",ave);
				objects.add(obj);
				array.put(obj);
				
				if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
					
					if(ave>request1) {
						request1 = ave;
						names = new ArrayList<String>();
						names.add(cities.get(i));
					}
					else if(ave==request1) {
						names.add(cities.get(i));
					}
					i++;
					
				}
				else if(value.equals("min") || value.equals("MIN") || value.equals("Min")) {
					
					if(ave<request2) {
						request2 = ave;
						names = new ArrayList<String>();
						names.add(cities.get(i));
					}
					else if(ave==request2) {
						names.add(cities.get(i));
					}
					i++;
				}
				else throw new WrongValue (value+" è una stringa errata! Devi inserire una stringa tra max/MAX/Max oppure min/MIN/Min");
					
			}
			
			JSONObject object = new JSONObject();
			
			if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
				object.put("City with max average", names);
				object.put("max average", request1);
			}
			else { 
				object.put("City with min average", names);
				object.put("min average", request2);
			}
			
			
			array.put(object);
		
			
			return array;
			
		}
		
		/**
		 * Questo metodo calcola la media della temperatura massima su sette giorni delle città passate in ingresso e
		 * filtra rispetto al value.
		 * Restituisce un JSONArray contenente JSONObject che rappresentano le città e le relative temperature massime. 
		 * A seconda di value c'è un JSONObject che rappresenta la città con max/min valore di temperatura massima.
		 * 
		 * @param cities rappresenta le città con cui si vuole fare la statistica e il filtraggio
		 * @param value rappresenta il valore con cui si vuole fare il filtraggio.
		 * @return JSONArray come descritto sopra. 
		 * @throws WrongValueException se viene inserita una stringa non ammessa, cioè una stringa che non sia max,MAX,Max o 
		 *         min, MIN, Min.
		 */
		
		public JSONArray sevenDay (ArrayList<String> cities, String value) throws WrongValue {
			
			JSONArray array = new JSONArray();
			
			ArrayList<JSONObject> average = new ArrayList<JSONObject>();
			ArrayList<Double> averageTempMax = new ArrayList<Double>();
			ArrayList<JSONObject> objects = new ArrayList<JSONObject>();
			ArrayList<String> names = new ArrayList<String>();
			
			Iterator<String> it = cities.iterator();
			
			double request1 = 0;
			double request2 = 1000;
			
			int i = 0;
			
			while(it.hasNext()) {
				JSONObject object = new JSONObject();
				object = statistic.sevenDayAverage(it.next());
				average.add(object);
				double ave = object.getDouble("Temp_Max Average");
				averageTempMax.add(ave);
				
				JSONObject obj = new JSONObject();
				obj.put("cityName:",cities.get(i));
				obj.put("temp_max_average:",ave);
				objects.add(obj);
				array.put(obj);
				
				if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
					
					if(ave>request1) {
						request1 = ave;
						names = new ArrayList<String>();
						names.add(cities.get(i));
					}
					else if(ave==request1) {
						names.add(cities.get(i));
					}
					i++;
					
				}
				else if(value.equals("min") || value.equals("MIN") || value.equals("Min")) {
					
					if(ave<request2) {
						request2 = ave;
						names = new ArrayList<String>();
						names.add(cities.get(i));
					}
					else if(ave==request2) {
						names.add(cities.get(i));
					}
					i++;
				}
				else throw new WrongValue (value+" è una stringa errata! Devi inserire una stringa tra max/MAX/Max oppure min/MIN/Min");
					
			}
			
			JSONObject object = new JSONObject();
			
			if(value.equals("max") || value.equals("MAX") || value.equals("Max")) {
				object.put("City with max average", names);
				object.put("max average", request1);
			}
			else { 
				object.put("City with min average", names);
				object.put("min average", request2);
			}
			
			
			array.put(object);
			
			return array;
			
			
		}
		
		
		
	}

