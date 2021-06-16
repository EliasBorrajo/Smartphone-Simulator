package ch.hevs.smartphone.applications.weather.classInfo;

/**
 * @author Bourquin Jonathan
 */

public class WindInfo {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Double
    private double speed;

    // Int
    private int deg;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     * @param speed
     * @param deg
     */
    public WindInfo(double speed, int deg) {
        this.speed = speed;
        this.deg = deg;
    }

    //*****************************************************************************
    // M E T H O D S
    //*****************************************************************************
    @Override
    public String toString() {
        return "wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public double getSpeed() {
        return speed;
    }

    public int getDeg() {
        return deg;
    }
}
