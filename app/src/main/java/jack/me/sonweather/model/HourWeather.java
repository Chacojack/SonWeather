package jack.me.sonweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Created by zjchai on 2016/11/7.
 */
@Data
public class HourWeather {

    private static final String TAG = HourWeather.class.getSimpleName();

    @SerializedName("cityId")
    String cityId;
    @SerializedName("cityName")
    String cityName;
    @SerializedName("sj")
    String refreshTime;
    @SerializedName("list")
    List<Weather> weatherList;

}
