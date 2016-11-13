package jack.me.sonweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Created by zjchai on 2016/11/13.
 */
@Data
public class WeatherIndexs {

    @SerializedName("cityId")
    String cityId;
    @SerializedName("cityName")
    String cityName;
    @SerializedName("forecastDate")
    String refreshTime;
    @SerializedName("list")
    List<WeatherIndex> weatherIndexList;

}
