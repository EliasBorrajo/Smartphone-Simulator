package ch.hevs.smartphone;

import ch.hevs.smartphone.applications.weather.WeatherAPI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing for weather app:
 * - Corruption of the API_KEY
 * - correct gathering of the data
 *
 * (Tests pour l'application weather
 * Liste des tests :
 * - récupération correcte des données
 * - réaction en cas de perte de connexion internet --> régler timeout super court
 * - URL corrompue --> API_KEY corrompue)
 *
 */

public class TestJUnit5Weather {

    /**
     * Testing success of the HTTP request --> correct data gathering ?
     * @throws IOException
     */
    @Test
    public void testSuccessfulHTTPRequest() throws IOException {
        String apiKey = "ec290e8fe580091860106fddb502ce81";
        String units   = "&units=metric";
        String urlLocation = "Sion";
        String successRequestCode = "200";

        String urlString = "http://api.openweathermap.org/data/2.5/weather?q="
                + urlLocation
                + units
                +"&appid="
                + apiKey;

        StringBuilder result = new StringBuilder();
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();

        // System.out.println(connection.getHeaderFields());

        //BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        Map<String, List<String>> myConnection = connection.getHeaderFields();

        // myConnection.forEach((k, v)-> System.out.println(k + " - " + v));
        List<String> httpCodeList = myConnection.get(null);
        // System.out.println(httpCodeList.toString());
        String tmpCode = String.join(" ", httpCodeList);
        // System.out.println(tmpCode);
        String finalErrorCode = tmpCode.substring(9,12);
        // System.out.println(finalErrorCode);

        assertEquals(finalErrorCode, successRequestCode);
    }

    @Test
    public void testCorruptedAPIKey() {
        String apiKey = "euhje3434jhkhu334353535";               // corrupted API key with empty string
        String units   = "&units=metric";
        String urlLocation = "Sion";
        //String successRequestCode = "200";

        String urlString = "http://api.openweathermap.org/data/2.5/weather?q="
                + urlLocation
                + units
                +"&appid="
                + apiKey;

        StringBuilder result = new StringBuilder();
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection connection = null;
        try {
            connection = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println(connection.getHeaderFields());

        //BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        Map<String, List<String>> myConnection = connection.getHeaderFields();

        // myConnection.forEach((k, v)-> System.out.println(k + " - " + v));
        List<String> httpCodeList = myConnection.get(null);
        System.out.println(httpCodeList.toString());
        String tmpCode = String.join(" ", httpCodeList);
        // System.out.println(tmpCode);
        String finalErrorCode = tmpCode.substring(9,12);
        // System.out.println(finalErrorCode);

        //assertEquals(finalErrorCode, successRequestCode);
    }




}
