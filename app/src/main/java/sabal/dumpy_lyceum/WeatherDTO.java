package sabal.dumpy_lyceum;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sergey on 02.03.2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDTO {

    private Main main;

    public WeatherDTO() {
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Main {
        private String temp;
        private String pressure;
        private String humidity;

        public Main() {
        }


        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }
    }


}
