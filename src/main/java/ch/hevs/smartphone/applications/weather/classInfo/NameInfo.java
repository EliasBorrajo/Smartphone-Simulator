package ch.hevs.smartphone.applications.weather.classInfo;

public class NameInfo {
    private String name;

    public NameInfo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NameInfo{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
}
