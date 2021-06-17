package ch.hevs.smartphone.applications.weather.classInfo;

/**
 * Class used to create system object from the response of the weather API
 *
 * @author Bourquin Jonathan
 */

public class SystemInfo {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Int
    private int type;
    private int id;

    // String
    private String country;

    // Long
    private long sunrise;
    private long sunset;


    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     * @param type
     * @param id
     * @param country
     * @param sunrise
     * @param sunset
     */
    public SystemInfo(int type, int id, String country, long sunrise, long sunset) {
        this.type = type;
        this.id = id;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************

    public String getCountry() { return country; }
}
