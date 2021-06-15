package ch.hevs.smartphone.applications.weather.classInfo;

import java.util.Arrays;

public class WeatherMaster {
    private Object[] weatherContent;

    private String base;
    private clouds clouds;
    private int cod;
    private coord coord;
    private long dt;
    private long id;
    private main main;
    private String name;
    private int timezone;
    private int visibility;
    private weather[] weather;
    private sys sys;
    private wind wind;


    public WeatherMaster(String base, clouds clouds, int cod, coord coord,
                         long dt, long id, main main, String name, int timezone,
                         int visibility, weather[] weather, sys sys, wind wind) {
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

    public ch.hevs.smartphone.applications.weather.classInfo.clouds getClouds() {
        return clouds;
    }

    public int getCod() {
        return cod;
    }

    public ch.hevs.smartphone.applications.weather.classInfo.coord getCoord() {
        return coord;
    }

    public long getDt() {
        return dt;
    }

    public long getId() {
        return id;
    }

    public main getMain() {
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

    public weather[] getWeather() {
        return weather;
    }

    public sys getSys() {
        return sys;
    }

    public wind getWind() {
        return wind;
    }
}
