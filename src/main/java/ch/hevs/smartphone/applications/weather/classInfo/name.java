package ch.hevs.smartphone.applications.weather.classInfo;

public class name {
    private String name;

    public name(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
}
