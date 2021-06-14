package ch.hevs.smartphone.applications.weather.classInfo;

public class cod {
    private int cod;

    public cod(int cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        return "cod{" +
                "cod=" + cod +
                '}';
    }

    public int getCod() {
        return cod;
    }
}
