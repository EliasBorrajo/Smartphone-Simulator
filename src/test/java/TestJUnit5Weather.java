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

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Bourquin Jonathan
 * Testing for weather app:
 * - Corruption of the API_KEY
 * - Correct gathering of the data --> URL corruption
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

    /**
     * Testing API_KEY corruption --> checking if returning 401 code (unauthorized request)
     * @throws IOException
     */
    @Test
    public void testCorruptedAPIKey() throws IOException {
        String apiKey = " ";                // simulate corrupted API_KEY
        String units   = "&units=metric";
        String urlLocation = "Sion";
        String unauthorizedRequestCode = "401";  // error code when URL connection is unauthorized

        String urlString = "http://api.openweathermap.org/data/2.5/weather?q="
                + urlLocation
                + units
                +"&appid="
                + apiKey;

        StringBuilder result = new StringBuilder();
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();

        // System.out.println(connection.getHeaderFields());
        Map<String, List<String>> myConnection = connection.getHeaderFields();

        // myConnection.forEach((k, v)-> System.out.println(k + " - " + v));
        List<String> httpCodeList = myConnection.get(null);
        // System.out.println(httpCodeList.toString());
        String tmpCode = String.join(" ", httpCodeList);
        // System.out.println(tmpCode);
        String finalErrorCode = tmpCode.substring(9,12);
        // System.out.println(finalErrorCode);

        assertEquals(finalErrorCode, unauthorizedRequestCode);
    }

}

