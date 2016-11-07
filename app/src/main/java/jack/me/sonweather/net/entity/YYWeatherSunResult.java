package jack.me.sonweather.net.entity;

import com.google.gson.annotations.SerializedName;

import jack.me.sonweather.model.Sun;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by zjchai on 2016/11/7.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class YYWeatherSunResult extends YYWeatherBaseResult {

    private static final String TAG = YYWeatherSunResult.class.getSimpleName();

    @SerializedName("data")
    Sun sun;

}
