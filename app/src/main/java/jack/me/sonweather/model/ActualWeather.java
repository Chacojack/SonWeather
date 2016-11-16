package jack.me.sonweather.model;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Tolerate;

/**
 * Created by zjchai on 2016/11/7.
 */
@Data
@Builder(builderMethodName = "mBuilder")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ActualWeather extends Weather{

    private static final String TAG = ActualWeather.class.getSimpleName();

    @SerializedName("cityId")
    String cityId;
    @SerializedName("cityName")
    String cityName;
    @SerializedName("lastUpdate")
    String lastUpdate;

    @Tolerate
    public ActualWeather() {
    }
}
