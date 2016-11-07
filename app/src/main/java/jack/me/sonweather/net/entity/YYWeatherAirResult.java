package jack.me.sonweather.net.entity;

import com.google.gson.annotations.SerializedName;

import jack.me.sonweather.model.Air;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by zjchai on 2016/11/7.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class YYWeatherAirResult extends YYWeatherBaseResult {

    private static final String TAG = YYWeatherAirResult.class.getSimpleName();

    @SerializedName("data")
    Air air;

}
