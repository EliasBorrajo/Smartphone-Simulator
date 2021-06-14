package ch.hevs.smartphone.applications.weather.classInfo;

public class WindInfo {
    private double speed;
    private int deg;

    public WindInfo(double speed, int deg) {
        this.speed = speed;
        this.deg = deg;
    }

    @Override
    public String toString() {
        return "WindInfo{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }

    public double getSpeed() {
        return speed;
    }

    public int getDeg() {
        return deg;
    }
}
