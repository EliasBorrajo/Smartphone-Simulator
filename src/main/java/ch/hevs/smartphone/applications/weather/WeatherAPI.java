package ch.hevs.smartphone.applications.weather;

// Les deux imports de google sont nécessaires pour la methode "jsonToMap"

import ch.hevs.smartphone.applications.weather.classInfo.WeatherInfo;
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
    private ArrayList<String> weatherDataFlow;
    private boolean isConnected;

    // Attributs a retourner au système pour afficher dans le GUI
    // @TODO : NON STRING MAIS OBJECTS POUR ETRE PAREIL QUE LES MAPS ??
    private String nomVille;        // Pourra être changé
    private String temperature;
    private String tempMax;
    private String tempMin;
    private String tempRessenti;
    private String description;
    private String humidite;        // en %
    private String windSpeed;       // metres/sec
    private String nuage;           // % de nuages
    private String pluie1h;         // Volume de pluie la dernière heure

    private Icon weatherIcon;       // "weather" --> icon

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

    private WeatherInfo jsonMappingForWeatherInfo(String str) {
        Gson gson = new GsonBuilder().create();
        WeatherInfo weatherInfo;
        weatherInfo = gson.fromJson(str, WeatherInfo.class);
        return weatherInfo;
    }

    /*private WindInfo jsonMappingForWindInfo(String str) {

    }*/

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
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();


            /*weatherDataFlow = new ArrayList<>();
            String line;
            int cptScan = 0;
            URLConnection connection = url.openConnection();
            connection.connect();

            InputStream inStream =  connection.getInputStream();
            Scanner in = new Scanner(inStream);
            while(in.hasNextLine()){
                line = in.nextLine();
                weatherDataFlow.add(cptScan, line);
            }*/

            WeatherMaster weatherMaster;
            weatherMaster = urlReader3(url);
            System.out.println(weatherMaster);

            //Map<String, Object> map = urlReader(url);




            // Lecture du flux de l'API, la lecture additionne la STRING resultat de la lecture à la suite.
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;                            // Permet de contenir la ligne que nous lisons actuellement
            //Scanner sc = new Scanner(line);
            //int cptScan = 0;
            while ((line = br.readLine()) != null) {
                result.append(line);      // Ajoute au resultat la ligne en cours de lecture
            }
            br.close();


            /*System.out.println(weatherDataFlow.size());
            System.out.println(weatherDataFlow.toString());

            Map<String, Object> resultMap = jsonToMap(result.toString());
            resultMap.forEach((k, v)-> System.out.println(k + " - " + v));
            System.out.println("Resultat : " + result);
            System.out.println("API infos are on : " + connection.getURL());
            System.out.println(resultMap.get("weather").toString());
            System.out.println(resultMap.get("main").toString());

            Map<String, Object> mainMap = jsonToMap(resultMap.get("main").toString());
            Map<String, Object> windMap = jsonToMap(resultMap.get("wind").toString());*/
            //String json = "{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"}";
            /*WeatherInfo weather = jsonMappingForWeatherInfo();
            System.out.println(weather.toString());

            String weatherInfos = weather.getIcon();
            System.out.println(weatherInfos);

            String urlPicture = "http://openweathermap.org/img/wn/"
                    + weatherInfos
                    + "@2x.png";

            System.out.println(urlPicture);*/


            // Attribue les informations récuperés à mes variables
            /*setNomVille(resultMap.get("name").toString());

            setTemperature(mainMap.get("temp").toString());
            setTempMax(mainMap.get("temp_max").toString());
            setTempMin(mainMap.get("temp_min").toString());
            setTempRessenti(mainMap.get("feels_like").toString());
            setHumidite(mainMap.get("humidity").toString());
            //setWeatherIcon((Icon) weather.get("icon"));

            //setDescription(weatherMap.get("description").toString());

            setWindSpeed(windMap.get("speed").toString());

            System.out.println(getNomVille());
            System.out.println(getTempMax());
            System.out.println(getDescription());
            System.out.println(getHumidite());*/
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
        String result = tmpResult.toString();

        // Lecture du flux de l'API, la lecture additionne la STRING resultat de la lecture à la suite.
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;                            // Permet de contenir la ligne que nous lisons actuellement

        while ((line = br.readLine()) != null) {
            result.append(line);      // Ajoute au resultat la ligne en cours de lecture
        }

        br.close();
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
    public String getNomVille() {
        return nomVille;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getTempMax() {
        return tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public String getTempRessenti() {
        return tempRessenti;
    }

    public String getDescription() {
        return description;
    }

    public String getHumidite() {
        return humidite;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getNuage() {
        return nuage;
    }

    public String getPluie1h() {
        return pluie1h;
    }

    public Icon getWeatherIcon() {
        return weatherIcon;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public String getUrlLocation() {
        return urlLocation;
    }

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************
    public void setUrlLocation(String urlLocation) {
        this.urlLocation = urlLocation;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempRessenti(String tempRessenti) {
        this.tempRessenti = tempRessenti;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHumidite(String humidite) {
        this.humidite = humidite;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setNuage(String nuage) {
        this.nuage = nuage;
    }

    public void setPluie1h(String pluie1h) {
        this.pluie1h = pluie1h;
    }

    public void setWeatherIcon(Icon weatherIcon) {
        this.weatherIcon = weatherIcon;
    }
}
