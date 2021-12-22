package com.uni.mariostefano.meteo.esame.exception;

    /**
     * Questa classe contiene il metodo che segnala l'eccezione causata dall'errato inserimento del nome della città 
     * @author Stefano Bandello
     * @author Mario De Berardinis
     *
     */

public class ExceptionCity extends Exception{

	String mex;
	
	 /**
	  * Questo è il costruttore
	  * @param mex indica il messaggio di errore
	  */
	
	  public ExceptionCity(String mex) {
		super();
		this.mex = mex;
	
    } 
	
	 /**
	  * Restituisce un messaggio di errore tramite il costruttore quando all'inserimento del nome della città non vi è alcun risultato
	  * @return String contenente il messaggio d'errore
	  */
	
	  public String getMex() {
	 	return mex;
	
	    }
	  
}