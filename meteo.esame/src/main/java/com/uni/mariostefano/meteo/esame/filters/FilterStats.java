package com.uni.mariostefano.meteo.esame.filters;


	import java.util.ArrayList;

	import org.json.JSONArray;

	import com.uni.mariostefano.meteo.esame.exception.WrongValue;

	/**
	 * Questa è l'interfaccia contenente i metodi per il filtraggio di value. 
	 * @author Stefano Bandello
	 */

	public interface FilterStats {
		
		public abstract JSONArray oneDay(ArrayList<String> cities, String value) throws WrongValue;
		public abstract JSONArray sevenDay(ArrayList<String> cities, String value) throws WrongValue;

	}

