package ch.hevs.smartphone.applications.weather.classInfo;

/**
 * Class used to create coordinate object from the response of the weather API
 *
 * @author Bourquin Jonathan
 */

public class CoordinateInfos {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Double
    private double lon;
    private double lat;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     * @param lon
     * @param lat
     */
    public CoordinateInfos(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }
}
