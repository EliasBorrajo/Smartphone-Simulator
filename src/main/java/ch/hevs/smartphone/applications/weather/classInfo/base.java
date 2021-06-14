package ch.hevs.smartphone.applications.weather.classInfo;

public class base {
    private String base;

    public base(String base) {
        this.base = base;
    }

    @Override
    public String toString() {
        return "base{" +
                "base='" + base + '\'' +
                '}';
    }

    public String getBase() {
        return base;
    }
}
