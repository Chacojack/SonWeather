package jack.me.sonweather.net.entity;

import com.google.gson.annotations.SerializedName;

import jack.me.sonweather.model.ActualWeather;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zjchai on 2016/11/7.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class YYWeatherActualResult extends YYWeatherBaseResult{

    private static final String TAG = YYWeatherActualResult.class.getSimpleName();

    @SerializedName("data")
    ActualWeather actualWeather;

}
