package sabal.dumpy_lyceum;

/**
 * Created by Sergey on 02.03.2017.
 */

public class GetDTO {

    double temperature;
    String direction;
    long unixtime;
    double weather;
    String title;
    double humidity;
    GetDTO (String title, double humidity, long unixtime, double weather){
        this.title = title;
        this.humidity = humidity;
        this.unixtime = unixtime;
        this.weather = weather;
    }
}
