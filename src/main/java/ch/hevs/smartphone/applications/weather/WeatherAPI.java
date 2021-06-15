package ch.hevs.smartphone.applications.weather;

// Les deux imports de google sont nécessaires pour la methode "jsonToMap"

import ch.hevs.smartphone.applications.weather.classInfo.main;
import ch.hevs.smartphone.applications.weather.classInfo.weather;
import ch.hevs.smartphone.applications.weather.classInfo.WeatherMaster;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.google.gson.reflect.*;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



/**
 * @author Borrajo Elias, Milena Lonfat
 * Tutoriel trouvé sur : https://stackoverflow.com/questions/58759133/openweathermap-api-java
 */
public class WeatherAPI {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Attributs nécessaire pour récuperer les infos de l'API
    private static final String API_KEY = "ec290e8fe580091860106fddb502ce81";   // Est la clé lié à mon compte au fournisseur de l'API
    private static final String UNITS = "&units=metric";                      // Permet d'avoir des unitées METRICS, donc les temperatures en °C
    private String urlLocation = "Sion";                                        // La ville sera par défaut "Sion"
    private boolean isConnected;

    // Attributs a retourner au système pour afficher dans le GUI
    private String nomVille;
    private double temperature;
    private double tempMax;
    private double tempMin;
    private double tempRessenti;
    private int humidity;
    private double windSpeed;
    private String description;

// @TODO : POUR RECUPERER LE TABLEAU, RECUPERER NORMAL 1 FOIS DEJA; PUIS RöCUPERER DE CE RESULTAT LA STRING

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
   /* public static void main(String[] args)
    {
        WeatherAPI weather = new WeatherAPI();

    }*/

    public WeatherAPI() {
        getAPIDetails();
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    /**
     * Convert JSON into a MAP
     * ON donne en entrée le fichier JSON que l'on veut,
     * et en sortie, on crée une HasMap qui nous liée une information à une string.
     * Ex : "location" string est lié à "Bern" object
     *
     * @param str
     * @return @TODO contrôler : retourne une map des informations dont on a besoin
     */
    private static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(
                str,
                new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );
        return map;
    }

    private weather jsonMappingForWeatherInfo(String str) {
        Gson gson = new GsonBuilder().create();
        weather weather;
        weather = gson.fromJson(str, weather.class);
        return weather;
    }

    protected void getAPIDetails() {
        // Se connecter à l'API
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q="
                + urlLocation
                + UNITS
                + "&appid="
                + API_KEY;


        isConnected = false;

        /**
         * Tentative de récuperer les informations de l'API
         */
        try {
            URL url = new URL(urlString);
            System.out.println(url);

            WeatherMaster weatherMaster;
            weatherMaster = urlReader3(url);
            System.out.println(weatherMaster);

            String weatherInfos = weatherMaster.getWeather()[0].getIcon();
            System.out.println(weatherInfos);

            String urlPicture = "http://openweathermap.org/img/wn/"
                    + weatherInfos
                    + "@2x.png";

            System.out.println(urlPicture);


            // Attribue les informations récuperés à mes variables
            setNomVille(weatherMaster.getName());
            setTemperature(weatherMaster.getMain().getTemp());
            setTempMax(weatherMaster.getMain().getTemp_max());
            setTempMin(weatherMaster.getMain().getTemp_min());
            setTempRessenti(weatherMaster.getMain().getFeels_like());
            setHumidity(weatherMaster.getMain().getHumidity());
            //setWeatherIcon((Icon) weather.get("icon"));
            //setDescription(weatherMap.get("description").toString());
            setWindSpeed(weatherMaster.getWind().getSpeed());

            System.out.println(getNomVille());
            System.out.println(getTempMax());
            System.out.println(getDescription());
            System.out.println(getHumidity());
            isConnected = true;

        } catch (IOException e) {
            isConnected = false;
            System.out.println("Erreur dans WEATHER_API : methode = getAPIDetails");
            JOptionPane.showMessageDialog(null, "Ville non valide" +
                    "\n Ville par défault initialisée : Sion");
            setUrlLocation("Sion");
            e.printStackTrace();
        }
    }

    private WeatherMaster urlReader(URL url) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        WeatherMaster weatherMaster = mapper.readValue(url, WeatherMaster.class);

        //Map<String, Object> map = mapper.readValue(url, Map.class);

        return weatherMaster;
    }

    private String urlReader2(URL url) throws IOException {
        URLConnection connection = url.openConnection();
        StringBuilder tmpResult = new StringBuilder();
        String result;

        // Lecture du flux de l'API, la lecture additionne la STRING resultat de la lecture à la suite.
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;                            // Permet de contenir la ligne que nous lisons actuellement

        while ((line = br.readLine()) != null) {
            tmpResult.append(line);      // Ajoute au resultat la ligne en cours de lecture
        }
        br.close();

        result = tmpResult.toString();
        System.out.println(result);

        return result;
    }

    private WeatherMaster urlReader3(URL url) throws IOException {
        Gson gson = new GsonBuilder().create();

        WeatherMaster weatherMaster = gson.fromJson(urlReader2(url), WeatherMaster.class);

        return weatherMaster;
    }


    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************

    public static String getApiKey() {
        return API_KEY;
    }

    public static String getUNITS() {
        return UNITS;
    }

    public String getUrlLocation() {
        return urlLocation;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public String getNomVille() {
        return nomVille;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempRessenti() {
        return tempRessenti;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getDescription() {
        return description;
    }

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************

    public void setUrlLocation(String urlLocation) {
        this.urlLocation = urlLocation;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempRessenti(double tempRessenti) {
        this.tempRessenti = tempRessenti;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
