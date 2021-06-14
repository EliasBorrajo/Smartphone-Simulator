package ch.hevs.smartphone.applications.weather.classInfo;

public class timezone {
    private int timezone;

    public timezone(int timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return "timezone{" +
                "timezone=" + timezone +
                '}';
    }

    public int getTimezone() {
        return timezone;
    }
}
