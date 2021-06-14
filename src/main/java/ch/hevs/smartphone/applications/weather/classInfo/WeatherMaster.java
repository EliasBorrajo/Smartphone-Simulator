package ch.hevs.smartphone.applications.weather.classInfo;

import java.util.Arrays;

public class WeatherMaster {
    private Object[] weatherContent;
    private MainInfo mainInfo;
    private NameInfo nameInfo;
    private WeatherInfo weatherInfo;
    private SystInfo systInfo;
    private WindInfo windInfo;

    public WeatherMaster(MainInfo mainInfo, NameInfo nameInfo, WeatherInfo weatherInfo, SystInfo systInfo, WindInfo windInfo) {
        this.mainInfo = mainInfo;
        this.nameInfo = nameInfo;
        this.weatherInfo = weatherInfo;
        this.systInfo = systInfo;
        this.windInfo = windInfo;
    }

    public WeatherMaster() {
    }

    @Override
    public String toString() {
        return "WeatherMaster{" +
                "weatherContent=" + Arrays.toString(weatherContent) +
                ", mainInfo=" + mainInfo +
                ", nameInfo=" + nameInfo +
                ", weatherInfo=" + weatherInfo +
                ", systInfo=" + systInfo +
                ", windInfo=" + windInfo +
                '}';
    }

    public Object[] getWeatherContent() {
        return weatherContent;
    }
}
