package com.uni.mariostefano.meteo.esame.exception;

    /**
     * Questa classe contiene il metodo che segnala l'eccezione causata da una stringa vuota
     * @author Stefano Bandello
     * @author Mario De Berardinis
     *
     */

public class EmptyString extends Exception{
	
    String mex;
	
	 /**
	  * Questo è il costruttore
	  * @param mex indica il messaggio di errore
	  */
	 
      public EmptyString (String mex) {
		
    	this.mex=mex;

    }
		
	 /**
	  * Restituisce un messaggio di errore tramite il costruttore quando viene inserita ,in modo non conforme, una stringa vuota
	  * @return String contenente il messaggio d'errore
	  */
	 
      public String getMex() {
	    return mex;
		
		}
		
}