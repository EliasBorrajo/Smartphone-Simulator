package ch.hevs.smartphone.applications.weather.classInfo;

/**
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
    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }
}
