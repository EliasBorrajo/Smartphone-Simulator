package ch.hevs.smartphone.applications.weather.classInfo;

import java.util.Arrays;

public class WeatherMaster {
    private Object[] weatherContent;

    private base base;
    private clouds clouds;
    private cod cod;
    private coord coord;
    private dt dt;
    private id id;
    private main main;
    private name name;
    private timezone timezone;
    private visibility visibility;
    private weather weather;
    private sys sys;
    private wind wind;


    public WeatherMaster(base base, clouds clouds, cod cod, coord coord, dt dt, id id, main main, name name, timezone timezone, visibility visibility, weather weather, sys sys, wind wind) {
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

    @Override
    public String toString() {
        return "WeatherMaster{" +
                "weatherContent=" + Arrays.toString(weatherContent) +
                ", base=" + base +
                ", clouds=" + clouds +
                ", cod=" + cod +
                ", coord=" + coord +
                ", dt=" + dt +
                ", id=" + id +
                ", main=" + main +
                ", name=" + name +
                ", timezone=" + timezone +
                ", visibility=" + visibility +
                ", weather=" + weather +
                ", sys=" + sys +
                ", wind=" + wind +
                '}';
    }

    public Object[] getWeatherContent() {
        return weatherContent;
    }
}
