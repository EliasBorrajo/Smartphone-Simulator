package ch.hevs.smartphone.applications.weather.classInfo;

public class weather {
    private int id;
    private String main;
    private String description;
    private String iconCode;

    public weather(int id, String main, String description, String iconCode) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.iconCode = iconCode;
    }

    /*@Override
    public String toString() {
        return "weather{" +
                "id=" + id +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", iconCode='" + iconCode + '\'' +
                '}';
    }*/

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIconCode() {
        return iconCode;
    }
}
