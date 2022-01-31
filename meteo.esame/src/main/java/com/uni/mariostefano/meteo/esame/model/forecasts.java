package com.uni.mariostefano.meteo.esame.model;

	/**
	 * Classe che rappresenta le previsioni del meteo restrette
	 * @author Stefano Bandello
	 */
	
public class forecasts{
		private int pressure;
		private double humidity;
		private String data;
		private double Temp;
		
		//costruttore		
		public forecasts() {
			this.data = null;
		}
		
		/**costruttore
		 * @param pressure                  Informazioni sulla pressione
		 */		
		public forecasts ( int pressure) {
			super();
			this.pressure = pressure;
		}
		
		/** Costruttore a
	     * @param pressure                Informazioni sulla pressione
	     * @param humidity                Infomrazioni sull'umidità
	     */		
		public forecasts ( int pressure, double humidity) {
			super();
			this.pressure = pressure;
			this.humidity = humidity;

		}
		
		/** Costruttore
	     * @param pressure                    Informazioni sulla pressione
	     * @param humidity                    Informazioni sull'umidità
	     * @param data                        Giorno e ora a cui si riferisce la previsione	  
	     * @param Temp                        Informazioni sulla temperatura
	     */
		public forecasts ( int pressure, double humidity, String data, double Temp) {
			super();
		
			this.pressure = pressure;			
			this.humidity = humidity;
			this.data = data;
			this.Temp = Temp;					
		}
		
		/**
		 * Metodo che restituisce la temperatura attuale
		 * @return
		 */
		public double getTemp() {
			return Temp;
		}
		
		/**
		 * Metodo che setta la temperatura
		 * @param temperature
		 */

		public void setTemp(double Temp) {
			this.Temp = Temp;
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
					+ humidity + ",temp=" + Temp ;
		}


}
