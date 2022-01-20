package com.uni.mariostefano.meteo.esame.filters;


	import java.util.ArrayList;

	import org.json.JSONArray;

	import com.uni.mariostefano.meteo.esame.exception.WrongParam;
	import com.uni.mariostefano.meteo.esame.exception.WrongPeriod;
	import com.uni.mariostefano.meteo.esame.exception.WrongValue;

	/** Questa classe contiene i metodi necessari al filtraggio.
	 * @author Stefano Bandello
	 * @author Mario De Berardinis
	 */

	public class Filter {
		
		private ArrayList<String> cities = new ArrayList<String>();
		private String param;
		private String value;
		private int period;
		
	   /**
	    *  Questo è il costruttore della classe.
	    * @param cities è un ArrayList di Stringhe contenenti i nomi delle città che si vogliono filtrare.
	    * @param param parametro per cui si vuole effettuare il filtraggio.
	    * @param value valore max o min di param.
	    * @param period periodo in cui si vuole fare la statistica.
	    */
		
		public Filter(ArrayList<String> cities, String param, String value, int period) {
			super();
			this.cities = cities;
			this.param =  param;
			this.value =  value;
			this.period = period;
		}

		/**
		 * Questo metodo filtra il periodo e il parametro. Richiama altri metodi per filtrare il value.
		 * @return JSONArray contenente le città filtrate e le statistiche ottenute.
		 * @throws WrongPeriod se il numero inserito non è 1 o 7.
		 * @throws WrongValue se è stato inserita una stringa errata per value.
		 * @throws WrongParam se è stato inserita una stringa errata per param.
		 */
		
		public JSONArray analyze() throws WrongPeriod, WrongValue, WrongParam {
			
			JSONArray array = new JSONArray ();
			
			if(period==1) {
				if(param.equals("temp_max")) {
					FilterTempMax filter = new FilterTempMax();
					array = filter.oneDay(cities, value);
				} 
				
				else if (param.equals("temp_min")) {
					FilterTempMin filter = new FilterTempMin();
					array = filter.oneDay(cities, value);
				}
				else if (param.equals("feels_like")) {
					FilterFeelsLike filter = new FilterFeelsLike();
					array = filter.oneDay(cities, value);
				}
				else if (param.equals("pressure")) {
					FilterPressure filter = new FilterPressure();
					array = filter.oneDay(cities, value);
				}
				else if (param.equals("humidity")) {
					FilterHumidity filter = new FilterHumidity();
					array = filter.oneDay(cities, value);
				}
				else  throw new WrongParam (param+ " non è una stringa ammessa.Inserisci una stringa tra temperature,temp_min,temp_max,feels_like, pressure e humidity");   
							
			}
			
			else if(period==7) {
				if(param.equals("temp_max")) {
					FilterTempMax filter = new FilterTempMax();
					array = filter.sevenDay(cities, value);
				}
				
				else if (param.equals("temp_min")) {
					FilterTempMin filter = new FilterTempMin();
					array = filter.sevenDay(cities, value);
				}
				else if (param.equals("feels_like")) {
					FilterFeelsLike filter = new FilterFeelsLike();
					array = filter.sevenDay(cities, value);
				}
				else if (param.equals("pressure")) {
					FilterPressure filter = new FilterPressure();
					array = filter.sevenDay(cities, value);
				}
				else if (param.equals("humidity")) {
					FilterHumidity filter = new FilterHumidity();
					array = filter.sevenDay(cities, value);	
				}
				else  throw new WrongParam (param+ " non è una stringa ammessa.Inserisci una stringa tra temperature,temp_min,temp_max,feels_like, pressure e humidity");
							
			}
			else  throw new WrongPeriod (period + " non è un numero ammesso. Inserisci un numero che sia o 1 o 7");
			
			return array;
		}
		
		
		
		
		
		
	}

