package ch.hevs.smartphone.applications.weather.classInfo;

public class BaseInfo {
    private String base;

    public BaseInfo(String base) {
        this.base = base;
    }

    @Override
    public String toString() {
        return "BaseInfo{" +
                "base='" + base + '\'' +
                '}';
    }

    public String getBase() {
        return base;
    }
}
