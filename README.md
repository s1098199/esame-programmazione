<h1 align="center"> esame-programmazione </h1>

##METEO

L'applicazione serve per fornire le informazioni della citt� richiesta, generando anche statistiche

## **SCALETTA CONTENUTI**

* [Introduzione](#Introduzione)
* [Installazione](#Installazione)
* [Configurazione](#Configurazione)
* [Rotte](#rotte)
* [Come pu� l'utente effettuare richieste?](#richieste)
* [Documentazione](#Documentazione)
* [Autore](#Autore)

<a name="Introduzione"></a>
## Introduzione

Il programma Meteo, consiste nel ricevere informazioni sulla pressione, humidit�, tempo e data della citt� richiesta, con la stastica del tempo massimo e tempo minimo

<a name="Installazione"></a>
## Installazione 

Il programma � installabile con il seguente link:
```
https://github.com/s1098199/esame-programmazione
```

<a name="Configurazione"></a>
##Configurazione

Per usare il programma, viene utilizzato l'apikey, ma per semplificare la procedure � gi� inserito nel programma, quindinono bisogna andarla a richidere da Openweather.

<a name="rotte"></a>
##Rotte

Per usare le rotte tramite postman si utilizza il seguente indirizzo:

```
localhost:8080
```

le rotte principali sono:

"/General"       : ritorna previsione ristrette sulla citt� richiesta

"/info"          : ritorna un oggetto di tipo citt� popolato delle  informazioni sulla citt�.

"/saveEveryHour" : ritorna il path dove viene salvato il file.

"/city"          : ritorna un JSONObject contenente le previsioni meteo complete.

"/Api"           :restituisce il JSONArray contente la pressione, umidit�  tempo  con la relativa data e ora.

"/save"          : ritorna un file salvato.

"/readHistory"   : ritorna il JSONArray che contiene tutte le informazioni ristrette.

"/todayAverage"  : ritorna un JSONObject contenente il nome della citt� e le relative  medie.

<a name="richieste"></a>
##Come pu� l'utente effettuare richieste?

Basta avviare il programma come applicazione SpringBoot, assicurarsi di avere Postaman e mandarli le rotte aggiungendo la citt�, dove si vuole vedere le informazioni sul meteo

```
localhost:8080\General?cityName=
```
```
localhost:8080\info?cityName=
```
```
localhost:8080\saveEveryHour?cityName=
```
```
localhost:8080\city?cityName=
```
```
localhost:8080\Api?cityName=
```
```
localhost:8080\save?cityName=
```
```
localhost:8080\readHistory?cityName=
```
```
localhost:8080\todayAverage?cityName=
```
<a name="Documentazione"></a>
<a name="Autore"></a>
<a name="intro"></a>

