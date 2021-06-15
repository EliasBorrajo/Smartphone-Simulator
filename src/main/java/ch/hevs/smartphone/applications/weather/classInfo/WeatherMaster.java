package ch.hevs.smartphone.applications.weather.classInfo;

public class WeatherMaster {
    private Object[] weatherContent;

    private String base;
    private CloudsInfo clouds;
    private int cod;
    private CoordinateInfos coord;
    private long dt;
    private long id;
    private MainInfo main;
    private String name;
    private int timezone;
    private int visibility;
    private WeatherInfo[] weather;
    private SystemInfo sys;
    private WindInfo wind;


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
