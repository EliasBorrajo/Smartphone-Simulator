package ch.hevs.smartphone.applications.weather.classInfo;

public class id {
    private long id;

    public id(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id{" +
                "id=" + id +
                '}';
    }

    public long getId() {
        return id;
    }
}
