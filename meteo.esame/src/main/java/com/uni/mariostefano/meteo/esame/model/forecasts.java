package com.uni.mariostefano.meteo.esame.model;

	/**
	 * Classe che rappresenta le previsioni del meteo restrette
	 */
	
public class forecasts{
		private double current_temperature;
		private double pressure;
		private double humidity;
		private String data;
		private double temp_max;
		private double temp_min;
		
		
		//costruttore
		
		public forecasts() {
			this.current_temperature = 0;
			this.data = null;
		}
		
		/**costruttore
		 * @param current_temperature       Informazioni riguardante la temperature attuale
		 * @param pressure                  Informazioni sulla pressione
		 */
		
		public forecasts (double current_temperature, double pressure) {
			super();
			this.current_temperature = current_temperature;
			this.pressure = pressure;
		}
		
		/** Costruttore 
	     * @param current_temperature     Informazione attuali sulla temperatura
	     * @param pressure                Informazioni sulla pressione
	     * @param humidity                Infomrazioni sull'umidità
	     */
		public forecasts (double current_temperature, double pressure, double humidity) {
			super();
			this.current_temperature = current_temperature;
			this.pressure = pressure;
			this.humidity = humidity;

		}

		
		/** Costruttore
	     * @param current_temperature         Informazioni attuali sulla temperatura
	     * @param pressure                    Infomrazioni sulla pressione
	     * @param humidity                    Informazioni sull'umidità
	     * @param data                        Giorno e ora a cui si riferisce la previsione
	     * @param temp_max                    Informazioni sulla temperatura massima
	     * @param temp_min                    Infomrazioni sulla temperatura minima
	     */
		public forecasts (double current_temperature, double pressure, double humidity, String data,
				double temp_max,double temp_min) {
			super();
			this.current_temperature = current_temperature;
			this.pressure = pressure;			
			this.humidity = humidity;
			this.data = data;
			this.temp_max = temp_max;
			this.temp_min = temp_min;
			
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
		 * Metodo che restituisce  le informazioni riguardanti la temperatura attuale
		 * @return current_temperature
		 */

		public double getcurrent_temperature() {
			return current_temperature;
		}
		
		/**
		 * Metodo che setta il current_temperature
		 * @param  double current_temperature
		 */

		public void setcurrent_temperature(double current_temperature) {
			this.current_temperature = current_temperature;
		}
		
		/**
		 * Metodo che restituisce le informazioni riguardanti la pressione
		 * @return pressure
		 */

		public double getpressure() {
			return pressure;
		}
		
		/**
		 * Metodo che setta le informazioni riguardanti la pressione
		 * @param  double pressure
		 */
		public void setpressure(double pressure) {
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
		@Override
		public String toString() {
			return  "current_temperature=" + current_temperature + ", pressure=" + pressure + "data=" + data +", humidity="
					+ humidity ;
		}


}
