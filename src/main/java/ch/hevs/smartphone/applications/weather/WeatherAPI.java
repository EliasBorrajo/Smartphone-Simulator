package ch.hevs.smartphone.applications.weather;

// Les deux imports de google sont nécessaires pour la methode "jsonToMap"

import ch.hevs.smartphone.applications.weather.classInfo.WeatherMaster;
import com.google.gson.*;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Borrajo Elias, Milena Lonfat, Bourquin Jonathan
 * This class retrieves GSON info from an API (openweathermap)
 */
public class WeatherAPI {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Attributes needed to retrieve API info
    private static final String API_KEY = "ec290e8fe580091860106fddb502ce81";   // Key linked to my API provider account
    private static final String UNITS = "&units=metric";                        // Allows to have METRICS units, therefore temperatures in ° C
    private String urlLocation = "Sion";                                        // The city will be by default "Sion"
    private boolean isConnected;

    // Attributes to return to the system to display in the GUI
    private String cityName;
    private double temperature;
    private double maxTemp;
    private double minTemp;
    private double feltTemp;
    private int humidity;
    private double windSpeed;
    private String description;
    private String urlPicture;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    public WeatherAPI() {
        getAPIDetails();
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    protected void getAPIDetails() {
        // Se connecter à l'API
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q="
                + urlLocation
                + UNITS
                + "&appid="
                + API_KEY;

        isConnected = false;

        /**
         * Attempting to retrieve information from the API
         */
        try {
            URL url = new URL(urlString);
            System.out.println(url);

            WeatherMaster weatherMaster;
            weatherMaster = urlReader(url);
            System.out.println(weatherMaster);

            String weatherInfos = weatherMaster.getWeather()[0].getIcon();
            System.out.println(weatherInfos);

            urlPicture = "http://openweathermap.org/img/wn/"
                    + weatherInfos
                    + "@2x.png";

            System.out.println(urlPicture);

            // Assigns the information retrieved to my variables
            setCityName(weatherMaster.getName());
            setTemperature(weatherMaster.getMain().getTemp());
            setMaxTemp(weatherMaster.getMain().getTemp_max());
            setMinTemp(weatherMaster.getMain().getTemp_min());
            setFeltTemp(weatherMaster.getMain().getFeels_like());
            setHumidity(weatherMaster.getMain().getHumidity());
            setWindSpeed(weatherMaster.getWind().getSpeed());

            System.out.println(getCityName());
            System.out.println(getMaxTemp());
            System.out.println(getDescription());
            System.out.println(getHumidity());
            isConnected = true;

        } catch (IOException e) {
            isConnected = false;
            System.out.println("Erreur in WEATHER_API : method = getAPIDetails");
            JOptionPane.showMessageDialog(null, "Invalid city" +
                    "\n Try again or check your connection");
            setUrlLocation("Sion");
            System.out.println("Is connected resultat:" + isConnected);
            e.printStackTrace();
        }
    }

    private WeatherMaster urlReader(URL url) throws IOException {
        URLConnection connection = url.openConnection();
        StringBuilder tmpResult = new StringBuilder();
        String result;

        // Reading the stream from the API, reading adds the STRING result of reading afterwards.
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;                            // Allows to contain the line we are currently reading

        while ((line = br.readLine()) != null) {
            tmpResult.append(line);      // Add to the result the line being read
        }
        br.close();

        result = tmpResult.toString();
        System.out.println(result);

        Gson gson = new GsonBuilder().create();
        WeatherMaster weatherMaster = gson.fromJson(result, WeatherMaster.class);
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

    public String getCityName() {
        return cityName;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getFeltTemp() {
        return feltTemp;
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

    public String getUrlPicture() {
        return urlPicture;
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

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public void setFeltTemp(double feltTemp) {
        this.feltTemp = feltTemp;
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

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }
}
