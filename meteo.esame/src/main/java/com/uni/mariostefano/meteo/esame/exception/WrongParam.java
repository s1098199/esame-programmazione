package com.uni.mariostefano.meteo.esame.exception;

    /**
     * Questa classe contiene il metodo che segnala l'eccezione nel caso di una stringa non ammessa per param
     * @author Stefano Bandello
     *
     */

public class WrongParam extends Exception {

	String mex;
	
	 /**
	  * Questo è il costruttore
	  * @param mex rappresenta il messaggio di errore
	  */
	
	  public WrongParam(String mex) {
		
		this.mex = mex;
		
	}
	
	 /**
	  * Restituisce un messaggio di errore tramite il costruttore quando viene inserita una stringa non ammessa per param
	  * @return String contenente il messaggio d'errore
	  */
	  
	  public String getMex() {
		return mex;
		
	    }
	  
}
