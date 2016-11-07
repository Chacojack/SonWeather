package jack.me.sonweather.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by zjchai on 2016/11/7.
 */
@Data
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

}