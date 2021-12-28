package com.uni.mariostefano.meteo.esame.model;

import java.util.Vector;


//La seguenta classe descrive la creazione delle città
	public class City {
		
		private String name;
		private String Nation;
		private long id;
		private Coordinates coordinates;
		private Vector<forecasts> vector = new Vector<forecasts>();	
		
		public City() {
			super();
		}
		

	/**Costruttore dell'Id della città
	* @param id        ID della città
	*/
		public City(long id) {
			this.id=id;
			this.name=null;
			this.coordinates=null;
			this.Nation=null;
		}
		/** Costruttore del nome della città
	     * @param name      nome della città
	     */	
		public City(String name) {
			this.id=0;
			this.name=name;
			this.coordinates=null;
			this.Nation=null;
		}
		
		/**Costruttore delle  coordinate della città	
		 * @param coordinates coordinate della città
		 */
		public City(Coordinates coordinates) {
			this.id=0;
			this.name=null;
			this.coordinates=coordinates;
			this.Nation=null;		
		}
		
		/**Costruttore	
		 * @param name     nome della città
		 * @param Nation nazione dela città
		 */
		public City(String name, String Nation) {
			this.id=0;
			this.name=name;
			this.coordinates=null;
			this.Nation=Nation;
		}
		
		/**Costruttore completo
		 * @param name         nome della città
		 * @param nation       nazione della città
		 * @param id           id della città
		 * @param coordinates  coordinate della città
		 * @param vector       vettore delle previsioni ristrette
		*/
	
	
	public City(long id, String name, Coordinates coordinates, String Nation, double 
			pressure, float humidity, int feels_like) {
		this.id = id;
		this.name = name;
		this.coordinates = coordinates;
		this.Nation = Nation;
			// TODO Auto-generated constructor stub
		}
	/**
	 * Metodo che restituisce il nome	
	 * @return name
	 */
		public String getName() {
			return name;
		}
		
		/**
		 * Metodo che setta il nome
		 * @param  String name
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * Metodo che restituisce la Nazione
		 * @return Nation
		 */
		public String getNation() {
			return Nation;
		}
		
		/**
		 * Metodo che setta la Nazione
		 * @param String Nation
		 */
		public void setNation(String Nation) {
			this.Nation = Nation;
		}
		
		/**
		 * Metodo che setta l'Id
		 * @param long id
		 */
		 	public void setId(long id) {
			this.id = id;
		}
		/**
		 * Metodo che restitutisce l'Id
		 * @return Id
		 */
		 public long getId() {
		 return id;
		}
		 /**
		  * Metodo che restituisce le Coordinate
		  * @return coordinates
		  */
		public Coordinates getCoordinates() {
			return coordinates;
		}
		
		/**
		 * Metodo che setta le Coordinate
		 * @param coordinates
		 */
		public void setCoordinates(Coordinates coordinates) {
			this.coordinates = coordinates;
		}
		
		/**
		 * Metodo che restituisce il vettore delle previsioni della città
		 * @return vector
		 */
		public Vector<forecasts> getVector() {
			return vector;
		}
		
		/**
		 * Metodo che setta il vettore delle previsioni della città
		 * @param vector di forecasts
		 */
		public void setVector(Vector<forecasts> vector) {
			this.vector = vector;
		}
		
		/**
		 * Override del metodo toString
		 * @return String che rappresenta la città
		 */
		
		@Override
		public String toString() {
		 return "id=" + id + ", name=" + name + ", coordinates=" + coordinates + ", Nation=" + Nation
					+ ", forecastsArray=" + toString() + ""; 
		}
				
}