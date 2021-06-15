package ch.hevs.smartphone.applications.weather.classInfo;

/**
 * @author Bourquin Jonathan
 */

public class WeatherMaster {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    private Object[] weatherContent;
    private WeatherInfo[] weather;

    // Class
    private CloudsInfo clouds;
    private CoordinateInfos coord;
    private MainInfo main;
    private SystemInfo sys;
    private WindInfo wind;

    // String
    private String base;
    private String name;

    // Int
    private int cod;
    private int timezone;
    private int visibility;

    // Long
    private long dt;
    private long id;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     * @param base
     * @param clouds
     * @param cod
     * @param coord
     * @param dt
     * @param id
     * @param main
     * @param name
     * @param timezone
     * @param visibility
     * @param weather
     * @param sys
     * @param wind
     */
    public WeatherMaster(String base, CloudsInfo clouds, int cod, CoordinateInfos coord,
                         long dt, long id, MainInfo main, String name, int timezone,
                         int visibility, WeatherInfo[] weather, SystemInfo sys, WindInfo wind) {
        this.base = base;
        this.clouds = clouds;
        this.cod = cod;
        this.coord = coord;
        this.dt = dt;
        this.id = id;
        this.main = main;
        this.name = name;
        this.timezone = timezone;
        this.visibility = visibility;
        this.weather = weather;
        this.sys = sys;
        this.wind = wind;
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public String getBase() {
        return base;
    }

    public CloudsInfo getClouds() {
        return clouds;
    }

    public int getCod() {
        return cod;
    }

    public CoordinateInfos getCoord() {
        return coord;
    }

    public long getDt() {
        return dt;
    }

    public long getId() {
        return id;
    }

    public MainInfo getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public int getTimezone() {
        return timezone;
    }

    public int getVisibility() {
        return visibility;
    }

    public WeatherInfo[] getWeather() {
        return weather;
    }

    public SystemInfo getSys() {
        return sys;
    }

    public WindInfo getWind() {
        return wind;
    }
}
