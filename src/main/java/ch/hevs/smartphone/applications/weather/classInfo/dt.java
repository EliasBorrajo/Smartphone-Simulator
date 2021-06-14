package ch.hevs.smartphone.applications.weather.classInfo;

public class dt {
    private long dt;

    public dt(long dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "dt{" +
                "dt=" + dt +
                '}';
    }

    public long getDt() {
        return dt;
    }
}
