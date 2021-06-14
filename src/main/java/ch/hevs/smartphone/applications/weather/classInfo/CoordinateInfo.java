package ch.hevs.smartphone.applications.weather.classInfo;

public class CoordinateInfo {
    private double lon;
    private double lat;

    public CoordinateInfo(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "CoordinateInfo{" +
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
