package jack.me.sonweather.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by zjchai on 2016/11/7.
 */
@Data
public class Air {

    private static final String TAG = Air.class.getSimpleName();

    @SerializedName("city_id")
    String cityId;
    @SerializedName("city_name")
    String cityName;
    @SerializedName("last_update")
    String lastUpdate;

    String aqi;
    String pollutant;  //主要污染物
    String pm25;
    String pm10;
    String so2;
    String no2;
    String level;
    String grade;
    String color;
    String health;
    String measure;

}
