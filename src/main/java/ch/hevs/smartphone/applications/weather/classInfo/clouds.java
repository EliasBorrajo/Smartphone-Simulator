package ch.hevs.smartphone.applications.weather.classInfo;

public class clouds {
    private int all;

    public clouds(int all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "clouds{" +
                "all=" + all +
                '}';
    }

    public int getAll() {
        return all;
    }
}
