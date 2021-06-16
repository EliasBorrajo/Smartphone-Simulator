package ch.hevs.smartphone.applications.weather.classInfo;

/**
 * Class used to create weather object from the response of the weather API
 *
 * @author Bourquin Jonathan
 */

public class WeatherInfo {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Int
    private int id;

    // String
    private String main;
    private String description;
    private String icon;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************

    /**
     * Constructor
     * @param id
     * @param main
     * @param description
     * @param icon
     */
    public WeatherInfo(int id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public String getMain() {
        return main;
    }

    public String getIcon() {
        return icon;
    }
}
