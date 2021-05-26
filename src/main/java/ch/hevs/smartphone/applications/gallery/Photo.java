package ch.hevs.smartphone.applications.gallery;

import java.io.Serial;
import java.io.Serializable;

public class Photo implements Serializable {

    @Serial
    private static final long serialVersionUID = 8730285243432450765L;

    public String path;
    public Photo(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
