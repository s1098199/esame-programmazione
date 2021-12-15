package com.uni.mariostefano.meteo.esame.model;


	/**
	 * Classe che rappresenta le previsioni del meteo restrette
	 */
	
public class forecasts {
		private String main;
		private String description;
		private double temp_max;
		private double temp_min;
		private double Perceived_temperature;
		private String data;
		
		//costruttore
		
		public forecasts() {
			this.main = null;
			this.description = null;
			this.temp_max = 0;
			this.temp_min = 0;
			this.Perceived_temperature = 0;
			this.data = null;
		}
		
		/**costruttore
		 * @param main        Informaizoni generali sul meteo
		 * @param description Descrizione del meteo
		 */
		
		public forecasts (String main, String description) {
			super();
			this.main = main;
			this.description = description;
		}
		
		/** Costruttore 
	     * @param main            Informaizone generale sul meteo.
	     * @param description     Descrizione del meteo.
	     * @param temp_max        Temperatura massima
	     * @param temp_min        Temperatura minima
	     */
		public forecasts (String main, String description, double temp_max, double temp_min) {
			super();
			this.main = main;
			this.description = description;
			this.temp_max = temp_max;
			this.temp_min = temp_min;
		}

		
		/** Costruttore
	     * @param main                        Indicazione generale sul meteo.
	     * @param description                 Descrizione del meteo.
	     * @param temp_max                    Temperatura massima
	     * @param temp_min                    Temperatura minima
	     * @param Perceived_temperature       Temperatura percepita
	     * @param data                        Giorno e ora a cui si riferisce la previsione
	     */
		public forecasts (String main, String description, double temp_max, double temp_min, 
				String data) {
			super();
			this.main = main;
			this.description = description;			
			this.temp_max = temp_max;
			this.temp_min = temp_min;
			this.Perceived_temperature = Perceived_temperature;
			this.data = data;
		}
		
		/**
		 * Metodo che restituisce  le infomrazioni principali sul meteo
		 * @return main
		 */

		public String getMain() {
			return main;
		}
		
		/**
		 * Metodo che setta il main
		 * @param  String main
		 */

		public void setMain(String main) {
			this.main = main;
		}
		
		/**
		 * Metodo che restituisce la descrizione del meteo
		 * @return description
		 */

		public String getDescription() {
			return description;
		}
		
		/**
		 * Metodo che setta la descrizione del meteo
		 * @param  String description
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		
		/**
		 * Metodo che restituisce la temperatura massima
		 * @return  temp_max
		 */

		public double getTemp_max() {
			return temp_max;
		}
		
		/**
		 * Metodo che setta la temperatura massima
		 * @param  double temp_max
		 */

		public void setTemp_max(double temp_max) {
			this.temp_max = temp_max;
		}
		
		/**
		 * Metodo che restituisce la temperatura minima
		 * @return temp_ min
		 */

		public double getTemp_min() {
			return temp_min;
		}
		
		/**
		 * Metodo che setta la temperatura minima
		 * @param  double temp_min
		 */

		public void setTemp_min(double temp_min) {
			this.temp_min = temp_min;
		}
		
		/**
		 * Metodo che restituisce la temperatura percepita
		 * @return Perceived_temperature
		 */

		public double getPerceived_temperature() {
			return Perceived_temperature;
		}
		
		/**
		 * Metodo che setta la temperatura percepita
		 * @param  double perceived_temperature
		 */

		public void setPerceived_temperature(double perceived_temperature) {
			Perceived_temperature = perceived_temperature;
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
			return  "main=" + main + ", description=" + description + "data=" + data +", temp_max="
					+ temp_max + ", temp_min=" + temp_min + ", Perceived_temperature=" + Perceived_temperature + "";
		}
	

}
