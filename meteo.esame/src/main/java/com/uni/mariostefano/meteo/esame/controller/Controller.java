package com.uni.mariostefano.meteo.esame.controller;

import java.io.IOException;
import java.util.ArrayList;

//import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uni.mariostefano.meteo.esame.exception.EmptyString;
import com.uni.mariostefano.meteo.esame.exception.ExceptionCity;
import com.uni.mariostefano.meteo.esame.exception.WrongPeriod;
import com.uni.mariostefano.meteo.esame.exception.WrongValue;

//import java.util.ArrayList;
//import org.json.JSONArray;

//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import com.uni.mariostefano.meteo.esame.controller.Service;

@RestController

public class Controller {
	
	@Autowired
	ServiceImpl si;
	//Service service;
	//Statistics statistic = new Statistics();
	
/**
 * Rotta di tipo GET che mostra le informazioni attuali sulla pressione e umidità le previsioni 
 * per i 7 giorni successivi della città richiesta dall'utente.
 * 
 * @param cityName rappresenta la città di cui si richiedono le previsioni sulla pressione e umidità.
 * @return le previsioni meteo sulla pressione e umidità della città richiesta e le informazioni generali sulla città.
 */


@GetMapping (value="/General")
public ResponseEntity<Object> getfromApi (@RequestParam String cityName) {
	return new ResponseEntity<> (si.getCityWeatherRistrictfromApi(cityName).toString(), HttpStatus.OK);
}


/**
 * Rotta di tipo GET che salva ogni ora le previsioni sulla visibilità della città inserita dall'utente.
 * 
 * @param cityName rappresenta la città della quale si richiede di salvare il report.
 * @return il path dove viene salvato il file.
 * @throws IOException se si verificano errori di output su file.
 */

@GetMapping(value="/readHistoryError")
public ResponseEntity<Object> readHistoryError(@RequestParam String cityName) throws IOException {
	
	//String path = si.saveEveryHour(cityName);
	
	try {
		return new ResponseEntity<> (si.readHistory(cityName), HttpStatus.OK);
	} catch (/*EmptyString | ExceptionCity | WrongPeriod | WrongValue |*/ IOException e) {
		// TODO Auto-generated catch block
		return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
	}
}
}
