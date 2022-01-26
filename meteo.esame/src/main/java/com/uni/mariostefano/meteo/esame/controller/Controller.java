package com.uni.mariostefano.meteo.esame.controller;

import java.io.IOException;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.Ansi8BitColor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.uni.mariostefano.meteo.esame.exception.EmptyString;
import com.uni.mariostefano.meteo.esame.exception.ExceptionCity;
import com.uni.mariostefano.meteo.esame.exception.WrongPeriod;
import com.uni.mariostefano.meteo.esame.exception.WrongValue;
import com.uni.mariostefano.meteo.esame.filters.Statistics;
//- @org.springframework.beans.factory.annotation.Autowired(required=true)
@RestController

public class Controller {
	
	@Autowired
	Statistics s ;
	@Autowired
	ServiceImpl si;
	
/**
 * Rotta di tipo GET che mostra le informazioni attuali sulla pressione e umidità le previsioni 
 * per i 7 giorni successivi della città richiesta dall'utente.
 * 
 * @param cityName rappresenta la città di cui si richiedono le previsioni sulla pressione e umidità.
 * @return le previsioni meteo sulla pressione e umidità della città richiesta e le informazioni generali sulla città.
 */


@GetMapping (value="/General")
public ResponseEntity<Object> getfromApi (@RequestParam String cityName) {
	return new ResponseEntity<> (si.getCityWeatherRistrictfromApi(cityName), HttpStatus.OK);
}

@GetMapping (value="/saveEveryHour")
public ResponseEntity<Object> saveEveryHour (@RequestParam String cityName) {
	return new ResponseEntity<> (si.saveEveryHour(cityName).toString(), HttpStatus.OK);
}

@GetMapping (value="/save")
public ResponseEntity<Object> save (@RequestParam String cityName) throws IOException{
	return new ResponseEntity<> (si.save(cityName).toString(), HttpStatus.OK);
}

@GetMapping (value="/readHistory")
public ResponseEntity<Object> readHistory (@RequestParam String cityName) throws IOException  {
	return new ResponseEntity<> (si.readHistory(cityName ), HttpStatus.OK);
}
@GetMapping (value="/sevenDay")
public ResponseEntity<Object> sevenDay (@RequestParam String cityName) throws WrongValue  {
	return new ResponseEntity<> (s.todayAverage(cityName) ,  HttpStatus.OK);
}
}
