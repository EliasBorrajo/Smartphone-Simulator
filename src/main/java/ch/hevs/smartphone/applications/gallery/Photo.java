package ch.hevs.smartphone.applications.gallery;

import java.io.Serial;
import java.io.Serializable;

/**
 * Attributes the strings that will be saved in the JSON for each photo
 *
 * @author Lonfat Milena
 */
public class Photo implements Serializable {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    @Serial
    private static final long serialVersionUID = 8730285243432450765L;

    // String
    public String path;
    public String name;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     *
     * @param path
     * @param name
     */
    public Photo(String path, String name) {
        this.path = path;
        this.name = name;
    }

    /**
     * Empty constructor
     */
    public Photo() {

    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    @Override
    public String toString() {
        return "Photo{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    //*****************************************************************************
    // S E T T E R S
    //*****************************************************************************
    public void setName(String name) {
        this.name = name;
    }
}
