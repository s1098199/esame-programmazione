package com.uni.mariostefano.meteo.esame.controller;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.uni.mariostefano.meteo.esame.filters.Statistics;
import com.uni.mariostefano.meteo.esame.controller.*;


/** Questa classe gestisce  chiamate al server 
 * @author Stefano Bandello
 */

@RestController
public class Controller {	
	@Autowired
	Statistics s;
	@Autowired
	ServiceImpl si;

	/**
	 * Rotta di tipo GET che serve per prendere le previsioni ristrette
	 * @param cityName
	 * @return previsione ristrette sulla città richiesta
	 */
@GetMapping (value="/General")
public ResponseEntity<Object> getCityWeatherRistrictfromApi (@RequestParam String cityName) {
	return new ResponseEntity<> (si.getCityWeatherRistrictfromApi(cityName), HttpStatus.OK);
}

/**
 * Rotta di tipo GET che prende il nome e id della città
 * @param cityName
 * @return un oggetto di tipo città popolato delle informazioni sulla città.
 */
@GetMapping (value="/info")
public ResponseEntity<Object> getCityInfofromApi (@RequestParam String cityName) {
	return new ResponseEntity<> (si.getCityInfofromApi(cityName), HttpStatus.OK);
}
/**
 * Rotta di tipo GET che salva ogni ora le previsioni sulla visibilità della città inserita dall'utente.
 * 
 * @param cityName
 * @return il path dove viene salvato il file.
 * @throws IOException se si verificano errori di output su file.
 */
@GetMapping (value="/saveEveryHour")
public ResponseEntity<Object> saveEveryHour (@RequestParam String cityName)throws IOException {
	return new ResponseEntity<> (si.saveEveryHour(cityName).toString(), HttpStatus.OK);
}

/**
 * Rotta di tipo GET prende le informazioni meteo di una città
 * @param cityName
 * @return un JSONObject contenente le previsioni meteo complete.
 */
@GetMapping (value="/city")
public ResponseEntity<Object> getCityW (@RequestParam String cityName) {
	return new ResponseEntity<> (si.getCityW(cityName), HttpStatus.OK);
}

/**
 * Rotta di tipo GET prende delle previsioni sulla città richiesta
 * @param cityName il file salvato
 * @return restituisce il JSONArray contente la pressione, umidità  tempo  con la relativa data e ora.
 */
@GetMapping (value="/Api")
public ResponseEntity<Object> getfromApi (@RequestParam String cityName) {
	return new ResponseEntity<> (si.getfromApi(cityName), HttpStatus.OK);
}

/**
 * Rotta di tipo GET salva le inforazioni della città
 * @param cityName
 * @return  file salvato
 * @throws IOException se si verificano errori di output su file.
 */
@GetMapping (value="/save")
public ResponseEntity<Object> save (@RequestParam String cityName) throws IOException{
	return new ResponseEntity<> (si.save(cityName).toString(), HttpStatus.OK);
}

/**
 * Rotta di GET che legge le informazioni salvate
 * @param cityName
 * @return il JSONArray che contiene tutte le informazioni ristrette
 * @throws IOException se si verificano errori di output su file.
 */
@GetMapping (value="/readHistory")
public ResponseEntity<Object> readHistory (@RequestParam String cityName) throws IOException  {
	return new ResponseEntity<> (si.readHistory(cityName ), HttpStatus.OK);
}
	
/**
 * Rotta di tipo GET serve per calcolare le medie giornaliere.
 * @param cityName
 * @return JSONObject contenente il nome della città e le relative  medie
 * @throws IOException
 */
@GetMapping (value="/todayAverage")
public ResponseEntity<Object> todayAverage (@RequestParam String cityName) throws IOException  {
	 return new ResponseEntity<> (s.todayAverage(cityName), HttpStatus.OK);
}

}

