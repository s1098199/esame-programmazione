package com.uni.mariostefano.meteo.esame.controller;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.uni.mariostefano.meteo.esame.exception.EmptyString;
import com.uni.mariostefano.meteo.esame.exception.ExceptionCity;



	/** Questa classe testa i metodi di ServiceImpl.
	 * @author Stefano Bandello
	 * @author Mario De Berardinis
	 */
	class ServiceImplTest {

		private ServiceImpl service;
	    private ArrayList<String> cities;
	    
		 /**
	     * Inizializza i componenti necessari a testare i metodi.
	     * @throws java.lang.Exception
	     */
		@BeforeEach
		void setUp() throws Exception {
			service = new ServiceImpl();
			cities = new ArrayList<String>();
			}

		/**
	     * Serve per distruggere ciò che è stato inizializzato dal metodo setUp.
	     * throws java.lang.Exception
	     */
		@AfterEach
		void tearDown() throws Exception {
		}
		/**
		 * Questo Test verifica se viene generata correttamente l'eccezione CityNotFound.
		 */
	/*    @Test
	    @DisplayName("Corretta generazione dell'eccezione ExceptionCity.")
	    void readHistory1() {
			
	    	cities.add("Tortoreto");
	        cities.add("Capri");
	    	
	       ExceptionCity e = assertThrows(ExceptionCity.class, () -> {service.readHistory(cities,1,"max",1);});
	    
	        assertEquals("Città non trovata nello storico", e.getMex());
	        
	    }
	    
	    /**
		 * Questo Test verifica se viene generata correttamente l'eccezione EmptyString.
		 */
		/*@Test
	    @DisplayName("Corretta generazione dell'eccezione EmptyString.")
	    void readHistory2() {
		
	    	cities.add("Chieuti");
	        cities.add("");
	    	
	        EmptyString e = assertThrows(EmptyString.class, () -> {service.readHistory(cities,1,"max",1);});
	        
	        assertEquals("Hai dimenticato di inserire la città...", e.getMex());
	        
	    }
		*/
		
	}


