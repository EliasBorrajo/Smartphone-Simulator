package ch.hevs.smartphone.applications.gallery;

import java.io.Serial;
import java.io.Serializable;

public class Photo implements Serializable {

    @Serial
    private static final long serialVersionUID = 8730285243432450765L;

    public String path;
    public String name;

    public Photo(String path, String name){
        this.path = path;
        this.name = name;
    }

    public Photo(){}

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
