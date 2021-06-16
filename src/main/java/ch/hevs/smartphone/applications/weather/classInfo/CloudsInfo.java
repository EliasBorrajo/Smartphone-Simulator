package ch.hevs.smartphone.applications.weather.classInfo;

/**
 * Class used to create cloud object from the response of the weather API
 *
 * @author Bourquin Jonathan
 */

public class CloudsInfo {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Int
    private int all;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     * @param all
     * */
    public CloudsInfo(int all) {
        this.all = all;
    }
}
