package ch.hevs.smartphone.applications.weather;

// Les deux imports de google sont nécessaires pour la methode "jsonToMap"
import com.google.gson.*;
import com.google.gson.reflect.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


public class WeatherAPI
{

    //*****************************************************************************
    // A T T R I B U  T S
    //*****************************************************************************
    // Attributs nécessaire pour récuperer les infos de l'API
    private static final String API_KEY = "ec290e8fe580091860106fddb502ce81";
    private String location;

    // Attributs a retourner au système pour afficher dans le GUI
    // @TODO NON STRING MAIS OBJECTS POUR ETRE PAREIL QUE LES MAPS ??
    private String nomVille;
    private String temperature;
    private String tempMax;
    private String tempMin;
    private String tempRessenti;
    private String description;
    private String humidite ;       // en %
    private String windSpeed;       // metres/sec
    private String nuage;           // % de nuages
    private String pluie1h;         // Volume de pluie la dernière heure


    private Icon   weatherIcon;     // "weather" --> icon




    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************


    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************


    /**
     * Convert JSON into a MAP
     * ON donne en entrée le fichier JSON que l'on veut,
     * et en sortie, on crée une HasMap qui nous liée une information à une string.
     * Ex : "location" string est lié à "Bern" object
     * @param str
     * @return
     */
    private static Map <String, Object> jsonToMap(String str)
    {
        Map <String, Object> map = new Gson().fromJson(
                str,
                new TypeToken<HashMap <String, Object>>() {}.getType()
        );
        return map;
    }

    private void getAPIDetails(String cityName)
    {
        // Les paramètres nécessaires pour se connecter à l'API
        //String API_KEY   = "ec290e8fe580091860106fddb502ce81";                  // Est la clé lié à mon compte au fournisseur de l'API
        String LOCATON   = "Sion";                                              // La ville que je shouaite reçevoir les informations
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q="  //
                + LOCATON + "&appid=" + API_KEY;


        try
        {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            // Lecture du flux de l'API, la lecture additionne la STRING resultat de la lecture à la suite.
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;                            // Permet de contenir la ligne que nous lisons actuellement
            while ((line = br.readLine()) != null)
            {
                result.append(line);      // Ajoute au resultat la ligne en cours de lecture
            }
            br.close();
            System.out.println(result);

            Map<String, Object> resultMap = jsonToMap(result.toString());
            System.out.println(result.toString());
            System.out.println("API infos are on : "+connection.getURL());
            System.out.println();

            // Crée les différents MAP à partir de la MAP resultat.
            //Map<String, Object> weatherMap= jsonToMap(resultMap.get("weather").toString());
            Map<String, Object> mainMap   = jsonToMap(resultMap.get("main").toString());
            Map<String, Object> windMap   = jsonToMap(resultMap.get("wind").toString());
            Map<String, Object> sysMap    = jsonToMap(resultMap.get("sys").toString());

            // Affiche les resultats voulus de chaque MAP
            System.out.println("Current temperature : "+ mainMap.get("temp"));
            System.out.println("Current humidity : "+ mainMap.get("humidity"));

            System.out.println("Wind Speeds : "+ windMap.get("speed"));
            System.out.println("Wind Angle : "+ windMap.get("deg"));

            System.out.println("Country is : " + sysMap.get("country"));

        }
        catch (IOException e)
        {
            System.out.println("Erreur dans WEATHER_API : methode = getAPIDetails");
            e.printStackTrace();
        }

    }


}
