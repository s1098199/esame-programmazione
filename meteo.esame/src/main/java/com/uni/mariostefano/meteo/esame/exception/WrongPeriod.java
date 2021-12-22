package com.uni.mariostefano.meteo.esame.exception;

    /**
     * Questa classe contiene il metodo che segnala l'eccezione causata da un numero non concesso del periodo
     * @author Stefano Bandello
     * @author Mario De Berardinis
     *
     */

public class WrongPeriod extends Exception{

	String mex;
	
	 /**
	  * Questo è il costruttore
	  * @param mex rappresenta il messaggio di errore
	  */
	
	  public WrongPeriod(String mex) {
		
		this.mex = mex;
		
	}
	
	 /**
	  * Restituisce un messaggio di errore tramite il costruttore quando viene inserito un numero non concesso 
	  * @return String contenente il messaggio d'errore 
	  */
	
	  public String getMex() {
		return mex;
	
	    }
	
}
