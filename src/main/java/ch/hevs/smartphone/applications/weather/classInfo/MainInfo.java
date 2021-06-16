package ch.hevs.smartphone.applications.weather.classInfo;

/**
 * @author Bourquin Jonathan
 */

public class MainInfo {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // Double
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;

    // Int
    private int pressure;
    private int humidity;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************

    /**
     * Constructor
     * @param temp
     * @param feels_like
     * @param temp_min
     * @param temp_max
     * @param pressure
     * @param humidity
     */
    public MainInfo(double temp, double feels_like, double temp_min, double temp_max, int pressure, int humidity) {
        this.temp = temp;
        this.feels_like = feels_like;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public double getTemp() {
        return temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }
}
