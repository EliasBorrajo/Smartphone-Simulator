package ch.hevs.smartphone;

import ch.hevs.smartphone.applications.weather.WeatherAPI;
import org.junit.jupiter.api.Test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Tests pour l'application weather
 * Liste des tests :
 * - récupération correcte des données
 * - réaction en cas de perte de connexion internet --> régler timeout super court
 * - URL corrompue --> API_KEY corrompue
 *
 */

public class TestJUnit5Weather {

    @Test
    public void testCorruptedAPIkey() throws IOException {
        String apiKey = "ec290e8fe580091860106fddb502ce81"; // Est la clé lié à mon compte au fournisseur de l'API
        String units   = "&units=metric";                    // Permet d'avoir des unitées METRICS, donc les temperatures en °C
        String urlLocation = "Sion";

        String urlString = "http://api.openweathermap.org/data/2.5/weather?q="
                + urlLocation
                + units
                +"&appid="
                + apiKey;

        StringBuilder result = new StringBuilder();
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();

        System.out.println(connection.getHeaderFields());

        //BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        Map<String, List<String>> myConnection = connection.getHeaderFields();

        myConnection.forEach((k, v)-> System.out.println(k + " - " + v));

    }




}
