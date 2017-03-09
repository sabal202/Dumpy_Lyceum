package sabal.dumpy_lyceum;

/**
 * Created by Valentun on 06.03.2017.
 */

public class Constants {

    public static class URL {
         public static final String HOST = "http://lyceum.nstu.ru/";
         public static final String WEATHER_HOST = "http://api.openweathermap.org/data/2.5/";
         public static final String NEWS = HOST + "novosti?format=json";
         public static final String CURRENT_WEATHER = WEATHER_HOST + "weather?appid=" + API_KEY + "&units=metric" + "&q=" + CITY_NAME;
    }

    private static final String API_KEY = "f9aa0a5ed0ae92439b4f09ad124206c1";
    private static final String CITY_NAME = "Novosibirsk"; //временно
}
