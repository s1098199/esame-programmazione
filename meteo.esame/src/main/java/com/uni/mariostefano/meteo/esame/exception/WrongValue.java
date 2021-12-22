package com.uni.mariostefano.meteo.esame.exception;


/**
     * Questa classe  contiene il metodo che segnala l'eccezione rappresentata da una stringa non ammessa per il value
     * @author Stefano Bandello
     * @author Mario De Berardinis
     *
     */

public class WrongValue extends Exception{

	String mex;
	
	 /**
	  * Questo è il costruttore
	  * @param mex rappresenta il messaggio di errore
	  */
	 
	  public WrongValue(String mex) {
		
		this.mex = mex;
		
	}
	
	 /**
	  * Restituisce un messaggio di errore tramite il costruttore quando viene inserita una stringa non concessa per il value
	  * @return String contenente il messaggio d'errore 
	  */
	
	  public String getMex() {
		return mex;
		
	    }
	
}