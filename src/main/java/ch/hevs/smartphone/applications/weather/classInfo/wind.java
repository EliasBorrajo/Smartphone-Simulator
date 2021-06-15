package ch.hevs.smartphone.applications.weather.classInfo;

public class wind {
    private double speed;
    private int deg;

    public wind(double speed, int deg) {
        this.speed = speed;
        this.deg = deg;
    }

    @Override
    public String toString() {
        return "wind{" +
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
