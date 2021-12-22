package com.uni.mariostefano.meteo.esame.model;

public class Coordinates {
	
	private double latitude;
	private double longitude;
	
	/**
	 * costruttore
	 * @param latitude   indica la latitudine
	 * @param longitude  indica la longitudine
	 */
	
	public Coordinates(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = latitude;
	}

	/**
	 * metodo che restituisce la latitudine
	 * @return latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * metodo che setta la latitudine
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * metodo che restituisce la longitudine
	 * @return longitude 
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 *metodo che setta la longitudine 
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Override del metodo toString(),
	 * @return String che rappresenta le coordinate.
	 */
	@Override
	public String toString() {
		return "[latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
