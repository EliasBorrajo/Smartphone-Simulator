package ch.hevs.smartphone.applications.weather.classInfo;

public class coord {
    private double lon;
    private double lat;

    public coord(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "coord{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }
}
