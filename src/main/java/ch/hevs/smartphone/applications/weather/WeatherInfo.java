package ch.hevs.smartphone.applications.weather;

public class WeatherInfo {
    private int id;
    private String main;
    private String description;
    private String cloud;
    private String iconcode;

    public WeatherInfo(int id, String main, String description, String cloud, String iconcode) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.cloud = cloud;
        this.iconcode = iconcode;
    }
}
