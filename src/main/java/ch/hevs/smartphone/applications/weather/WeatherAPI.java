package ch.hevs.smartphone.applications.weather;

import ch.hevs.smartphone.applications.weather.classInfo.WeatherMaster;
import ch.hevs.smartphone.errors.BusinessException;
import ch.hevs.smartphone.errors.ErrorCode;
import com.google.gson.*;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * This class retrieves GSON infos from an API (openweathermap)
 *
 * @author Borrajo Elias, Milena Lonfat, Bourquin Jonathan
 */
public class WeatherAPI {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Attributes needed to retrieve API info
    // String
    private static final String API_KEY = "ec290e8fe580091860106fddb502ce81";   // Key linked to my API provider account
    private static final String UNITS = "&units=metric";                        // Allows to have METRICS units, therefore temperatures in ° C
    private String urlLocation = "Sion";                                        // The city will be by default "Sion"

    // Boolean
    private boolean isConnected;

    // Attributes to return to the system to display in the GUI
    // Double
    private double windSpeed;
    private double temperature;
    private double maxTemp;
    private double minTemp;
    private double feltTemp;

    // Int
    private int humidity;

    // String
    private String cityName;
    private String description;
    private String urlPicture;
    private String country;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     */
    public WeatherAPI() {
        getAPIDetails();
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    /**
     * Method to get the API details
     */
    protected void getAPIDetails() {
        // Se connecter à l'API
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q="
                + urlLocation
                + UNITS
                + "&appid="
                + API_KEY;

        isConnected = false;

        // Attempting to retrieve information from the API
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
            setCountry(weatherMaster.getSys().getCountry());


            System.out.println(getCityName());
            System.out.println(getMaxTemp());
            System.out.println(getDescription());
            System.out.println(getHumidity());
            isConnected = true;

        }
        // Gestion of the global error,
        // 1) if URL is wrong
        // 2) if there is no internet connection
        // 3) if the city name is not found or valid
        catch (IOException e)
        {
            isConnected = false;
            System.out.println("Erreur in WEATHER_API : method = getAPIDetails");
            JOptionPane.showMessageDialog(null, "Invalid city" +
                    "\n Try again or check your connection");
            setUrlLocation("Sion");

            // Error Code for testing with JUNIT5
            try
            {
                throw new BusinessException("Erreur in WEATHER_API", ErrorCode.WRONG_URL_ERROR);
            } catch (BusinessException businessException)
            {
                businessException.printStackTrace();
            }
            System.out.println("Is connected resultat:" + isConnected);
            e.printStackTrace();
        }
    }

    /**
     * Creation of objects based on the API response
     *
     * @param url
     * @return a weatherMaster object
     * @throws IOException
     */
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
        WeatherMaster weatherMaster = gson.fromJson(result, WeatherMaster.class);  // Dé-serialization of the JSON object RESULT
        return weatherMaster;
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
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

    public int getHumidity() {
        return humidity;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public String getCountry() { return country; }

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************
    public void setUrlLocation(String urlLocation) {
        this.urlLocation = urlLocation;
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

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public void setCountry(String country) { this.country = country; }
}
