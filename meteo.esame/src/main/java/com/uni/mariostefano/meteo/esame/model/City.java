package com.uni.mariostefano.meteo.esame.model;

import java.util.Vector;


//La seguenta classe descrive la creazione delle città
	public class City {
		
		private String name;
		private String Nation;
		private long id;
		
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
			
			this.Nation=null;
		}
		/** Costruttore del nome della città
	     * @param name      nome della città
	     */	
		public City(String name) {
			this.id=0;
			this.name=name;
			
			this.Nation=null;
		}
		

		
		/**Costruttore	
		 * @param name     nome della città
		 * @param Nation nazione dela città
		 */
		public City(String name, String Nation) {
			this.id=0;
			this.name=name;
			
			this.Nation=Nation;
		}
		
		/**Costruttore completo
		 * @param name         nome della città
		 * @param nation       nazione della città
		 * @param id           id della città
		 * @param coordinates  coordinate della città
		 * @param vector       vettore delle previsioni ristrette
		*/
	
	
	public City(long id, String name,  String Nation, double 
			pressure, float humidity, int feels_like) {
		this.id = id;
		this.name = name;
		
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
		
		
		/** Metodo che scrive il vettore come una stringa.
	     * @return String toReturn che rappresenta le previsioni meteo.
	     */
		public String toStringVector() {
			String toReturn="";
			for (int i=0; i<vector.size(); i++)
				toReturn += vector.get(i).toString();
			return toReturn;
		}

		/* * Override del metodo toString
		 * @return String che rappresenta la città
		 */
		
		@Override
		public String toString() {
		 return "id=" + id + "name=" + name + " Nation=" + Nation
					+ ", forecastsArray=" + toStringVector(); 
		}

		/**
		 * Override del metodo equals.
		 * @param oggetto City da confrontare.
		 * @return true o false a seconda che i due oggetti siano uguali.
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			City other = (City) obj;
			
			if (Nation == null) {
				if (other.Nation != null)
					return false;
			} else if (!Nation.equals(other.Nation))
				return false;
			if (id != other.id)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		
		
				
}