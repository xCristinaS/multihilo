package c.examen2t.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristina on 03/03/2016.
 */

public class Respuesta {
    private Coord coord;
    private Rain rain;
    private List<Weather> weather = new ArrayList<Weather>();
    private String base;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Integer dt;
    private Sys sys;
    private Integer id;
    private String name;
    private Integer cod;

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    /**
     *
     * @return
     * The coord
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     *
     * @param coord
     * The coord
     */
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    /**
     *
     * @return
     * The weather
     */
    public List<Weather> getWeather() {
        return weather;
    }

    /**
     *
     * @param weather
     * The weather
     */
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    /**
     *
     * @return
     * The base
     */
    public String getBase() {
        return base;
    }

    /**
     *
     * @param base
     * The base
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     *
     * @return
     * The main
     */
    public Main getMain() {
        return main;
    }

    /**
     *
     * @param main
     * The main
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     *
     * @return
     * The wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     *
     * @param wind
     * The wind
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     *
     * @return
     * The clouds
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     *
     * @param clouds
     * The clouds
     */
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    /**
     *
     * @return
     * The dt
     */
    public Integer getDt() {
        return dt;
    }

    /**
     *
     * @param dt
     * The dt
     */
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    /**
     *
     * @return
     * The sys
     */
    public Sys getSys() {
        return sys;
    }

    /**
     *
     * @param sys
     * The sys
     */
    public void setSys(Sys sys) {
        this.sys = sys;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The cod
     */
    public Integer getCod() {
        return cod;
    }

    /**
     *
     * @param cod
     * The cod
     */
    public void setCod(Integer cod) {
        this.cod = cod;
    }

    // INTERNAS

    public class Clouds {

        private Float all;

        /**
         *
         * @return
         * The all
         */
        public Float getAll() {
            return all;
        }

        /**
         *
         * @param all
         * The all
         */
        public void setAll(Float all) {
            this.all = all;
        }

    }

    public class Coord {

        private Float lon;
        private Float lat;

        /**
         *
         * @return
         * The lon
         */
        public Float getLon() {
            return lon;
        }

        /**
         *
         * @param lon
         * The lon
         */
        public void setLon(Float lon) {
            this.lon = lon;
        }

        /**
         *
         * @return
         * The lat
         */
        public Float getLat() {
            return lat;
        }

        /**
         *
         * @param lat
         * The lat
         */
        public void setLat(Float lat) {
            this.lat = lat;
        }

    }

    public class Main {

        private Float temp;
        private Float pressure;
        private Float humidity;
        private Double temp_min;
        private Double temp_max;
        private Float seaLevel;
        private Float grndLevel;

        /**
         *
         * @return
         * The temp
         */
        public Float getTemp() {
            return temp;
        }

        /**
         *
         * @param temp
         * The temp
         */
        public void setTemp(Float temp) {
            this.temp = temp;
        }

        /**
         *
         * @return
         * The pressure
         */
        public Float getPressure() {
            return pressure;
        }

        /**
         *
         * @param pressure
         * The pressure
         */
        public void setPressure(Float pressure) {
            this.pressure = pressure;
        }

        /**
         *
         * @return
         * The humidity
         */
        public Float getHumidity() {
            return humidity;
        }

        /**
         *
         * @param humidity
         * The humidity
         */
        public void setHumidity(Float humidity) {
            this.humidity = humidity;
        }

        /**
         *
         * @return
         * The temp_min
         */
        public Double getTemp_min() {
            return temp_min;
        }

        /**
         *
         * @param temp_min
         * The temp_min
         */
        public void setTemp_min(Double temp_min) {
            this.temp_min = temp_min;
        }

        /**
         *
         * @return
         * The temp_max
         */
        public Double getTemp_max() {
            return temp_max;
        }

        /**
         *
         * @param temp_max
         * The temp_max
         */
        public void setTemp_max(Double temp_max) {
            this.temp_max = temp_max;
        }

        /**
         *
         * @return
         * The seaLevel
         */
        public Float getSeaLevel() {
            return seaLevel;
        }

        /**
         *
         * @param seaLevel
         * The sea_level
         */
        public void setSeaLevel(Float seaLevel) {
            this.seaLevel = seaLevel;
        }

        /**
         *
         * @return
         * The grndLevel
         */
        public Float getGrndLevel() {
            return grndLevel;
        }

        /**
         *
         * @param grndLevel
         * The grnd_level
         */
        public void setGrndLevel(Float grndLevel) {
            this.grndLevel = grndLevel;
        }

    }

    public class Sys {

        private Float message;
        private String country;
        private Long sunrise;
        private Long sunset;

        /**
         *
         * @return
         * The message
         */
        public Float getMessage() {
            return message;
        }

        /**
         *
         * @param message
         * The message
         */
        public void setMessage(Float message) {
            this.message = message;
        }

        /**
         *
         * @return
         * The country
         */
        public String getCountry() {
            return country;
        }

        /**
         *
         * @param country
         * The country
         */
        public void setCountry(String country) {
            this.country = country;
        }

        /**
         *
         * @return
         * The sunrise
         */
        public Long getSunrise() {
            return sunrise;
        }

        /**
         *
         * @param sunrise
         * The sunrise
         */
        public void setSunrise(Long sunrise) {
            this.sunrise = sunrise;
        }

        /**
         *
         * @return
         * The sunset
         */
        public Long getSunset() {
            return sunset;
        }

        /**
         *
         * @param sunset
         * The sunset
         */
        public void setSunset(Long sunset) {
            this.sunset = sunset;
        }

    }

    public class Weather {

        private Integer id;
        private String main;
        private String description;
        private String icon;

        /**
         *
         * @return
         * The id
         */
        public Integer getId() {
            return id;
        }

        /**
         *
         * @param id
         * The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         *
         * @return
         * The main
         */
        public String getMain() {
            return main;
        }

        /**
         *
         * @param main
         * The main
         */
        public void setMain(String main) {
            this.main = main;
        }

        /**
         *
         * @return
         * The description
         */
        public String getDescription() {
            return description;
        }

        /**
         *
         * @param description
         * The description
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         *
         * @return
         * The icon
         */
        public String getIcon() {
            return icon;
        }

        /**
         *
         * @param icon
         * The icon
         */
        public void setIcon(String icon) {
            this.icon = icon;
        }

    }

    public class Wind {

        private Float speed;
        private Float deg;

        /**
         * @return The speed
         */
        public Float getSpeed() {
            return speed;
        }

        /**
         * @param speed The speed
         */
        public void setSpeed(Float speed) {
            this.speed = speed;
        }

        /**
         * @return The deg
         */
        public Float getDeg() {
            return deg;
        }

        /**
         * @param deg The deg
         */
        public void setDeg(Float deg) {
            this.deg = deg;
        }
    }

    public class Rain {

        @SerializedName("3h")
        private Double _3h;

        /**
         * @return The _3h
         */
        public Double get3h() {
            return _3h;
        }

        /**
         * @param _3h The 3h
         */
        public void set3h(Double _3h) {
            this._3h = _3h;
        }
    }
}
