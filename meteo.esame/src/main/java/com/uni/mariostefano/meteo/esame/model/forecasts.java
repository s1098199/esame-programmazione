package com.uni.mariostefano.meteo.esame.model;

	/**
	 * Classe che rappresenta le previsioni del meteo restrette
	 */
	
public class forecasts{
		private double feels_like;
		private int pressure;
		private double humidity;
		private String data;
		private double temp;
		private double temp_max;
		private double temp_min;
		
		
		//costruttore
		
		public forecasts() {
			this.feels_like = 0;
			this.data = null;
		}
		
		/**costruttore
		 * @param feels_like       Informazioni riguardante la temperature percepita
		 * @param pressure                  Informazioni sulla pressione
		 */
		
		public forecasts (double feels_like, int pressure) {
			super();
			this.feels_like = feels_like;
			this.pressure = pressure;
		}
		
		
		/** Costruttore 
	     * @param feels_like              Informazione attuali sulla temperatura
	     * @param pressure                Informazioni sulla pressione
	     * @param humidity                Infomrazioni sull'umidità
	     */
		
		public forecasts (double feels_like, int pressure, double humidity) {
			super();
			this.feels_like = feels_like;
			this.pressure = pressure;
			this.humidity = humidity;

		}

		
		/** Costruttore
	     * @param feels_like                  Informazioni attuali sulla temperatura
	     * @param pressure                    Infomrazioni sulla pressione
	     * @param humidity                    Informazioni sull'umidità
	     * @param data                        Giorno e ora a cui si riferisce la previsione
	     * @param temp_max                    Informazioni sulla temperatura massima
	     * @param temp_min                    Infomrazioni sulla temperatura minima
	     */
		public forecasts ( int pressure, double humidity, String data, double temp) {
			super();
			this.feels_like = feels_like;
			this.pressure = pressure;			
			this.humidity = humidity;
			this.data = data;
			this.temp = temp;
			this.temp_min = temp_min;
			
		}
		
		/**
		 * Metodo che restituisce la temperatura attuale
		 * @return
		 */
		public double getTemp() {
			return temp;
		}
		
		/**
		 * Metodo che setta la temperatura
		 * @param temperature
		 */

		public void setTemp(double temp) {
			this.temp = temp;
		}

		/**
		 * Metodo che restituisce la temperatura massima
		 * @return temp_max
		 */
		public Double getTemp_max() {
			return temp_max;
		}
		
		/**
		 * Metodo che setta la temp_max
		 * @param temp_max
		 */

		public void setTemp_max(double temp_max) {
			this.temp_max = temp_max;
		}
		
		/**
		 * Metodo che restituisce la temperatura minima
		 * @return temp_min
		 */

		public double getTemp_min() {
			return temp_min;
		}
		
		/**
		 * Metodo che setta la temperatura minima
		 * @param temp_min
		 */

		public void setTemp_min(double temp_min) {
			this.temp_min = temp_min;
		}

		/**
		 * Metodo che restituisce  le informazioni riguardanti la temperatura percepita
		 * @return feels_like
		 */

		public double feels_like() {
			return feels_like;
		}
		
		/**
		 * Metodo che setta il feels_like
		 * @param  double feels_like
		 */

		public void setfeels_like(double feels_like) {
			this.feels_like = feels_like;
		}
		
		/**
		 * Metodo che restituisce le informazioni riguardanti la pressione
		 * @return pressure
		 */

		public int getpressure() {
			return pressure;
		}
		
		/**
		 * Metodo che setta le informazioni riguardanti la pressione
		 * @param  double pressure
		 */
		public void setpressure(int pressure) {
			this.pressure = pressure;
		}
		
		/**
		 * Metodo che restituisce le informazioni riguardanti l'umidità
		 * @return  humidity
		 */

		public double gethumidity() {
			return humidity;
		}
		
		/**
		 * Metodo che setta le informazioni riguardanti l'umidità
		 * @param  double humidity
		 */

		public void sethumidity(double humidity) {
			this.humidity = humidity;
		}
		
		
		/**
		 * Metodo che restituisce la data (giorno ed ora)
		 * @return data
		 */

		public String getData() {
			return data;
		}
		
		/**
		 * Metodo che setta la data (giorno ed ora)
		 * @param  String data
		 */

		public void setData(String data) {
			this.data = data;
		}
		
		/**
		 * Override del metodo toString.
		 * @return String che rappresenta il meteo.
		 */
		public String toString() {
			return  "  pressure=" + pressure + "data=" + data +", humidity="
					+ humidity + ",temp=" + temp + "temp_max="+ temp_max + "temp_min="+ temp_min + "feels_like=" + feels_like ;
		}


}
