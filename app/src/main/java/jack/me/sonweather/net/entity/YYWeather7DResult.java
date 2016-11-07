package jack.me.sonweather.net.entity;

import com.google.gson.annotations.SerializedName;

import jack.me.sonweather.model.Weather7D;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by zjchai on 2016/11/6.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class YYWeather7DResult extends YYWeatherBaseResult{

    private static final String TAG = YYWeather7DResult.class.getSimpleName();

    @SerializedName("data")
    Weather7D weather7D;
}
