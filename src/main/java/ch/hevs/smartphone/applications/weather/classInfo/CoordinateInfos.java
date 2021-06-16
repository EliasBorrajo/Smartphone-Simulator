package ch.hevs.smartphone.applications.weather.classInfo;

/**
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

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }
}
