package com.uni.mariostefano.meteo.esame.model;

import java.util.Vector;


//La seguenta classe descrive la creazione delle città
	public class City {
		
		private String name;
		private String Nation;
		private long id;
		private Coordinates coordinates;
		private Vector<forecasts> vector = new Vector<forecasts>();
		

	//costruttore dell'Id della città	
		public City(long id) {
			this.id=id;
			this.name=null;
			this.coordinates=null;
			this.Nation=null;
		}
		//costruttore del nome della città	
		public City(String name) {
			this.id=0;
			this.name=name;
			this.coordinates=null;
			this.Nation=null;
		}
		
		//costruttore delle coordinate della città	
		public City(Coordinates coordinates) {
			this.id=0;
			this.name=null;
			this.coordinates=coordinates;
			this.Nation=null;		
		}
		
		//costruttore del nome della città	
		//costruttore dello Stato della città	
		public City(String name, String Nation) {
			this.id=0;
			this.name=name;
			this.coordinates=null;
			this.Nation=Nation;
		}
		
		//costruttore completo
		//costruttore del nome della città
		//costruttore della nazione della città
		//costruttore dell'id della città
		//costruttore delle coordinate della città
		//costruttore deò vettore delle previsioni ristrette
		
		public City(long id, String name, Coordinates coordinates, String Nation) {
			this.id = id;
			this.name = name;
			this.coordinates = coordinates;
			this.Nation = Nation;
			this.vector = vector;
		}
	
	//metodo che restituisce il nome	
		public String getName() {
			return name;
		}
		
		//metodo che setta il nome
		public void setName(String name) {
			this.name = name;
		}
		
		//metodo che restituisce la nazione
		public String getNation() {
			return Nation;
		}
		
		//metodo che setta la nazione
		public void setNation(String Nation) {
			this.Nation = Nation;
		}
		
		//metodo che restituisce l'Id
		public long getId() {
			return id;
		}
}