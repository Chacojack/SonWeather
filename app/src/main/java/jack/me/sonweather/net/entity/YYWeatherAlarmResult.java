package jack.me.sonweather.net.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import jack.me.sonweather.model.Alarm;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by zjchai on 2016/11/7.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class YYWeatherAlarmResult extends YYWeatherBaseResult {

    private static final String TAG = YYWeatherAlarmResult.class.getSimpleName();

    @SerializedName("data")
    List<Alarm> alarms;

}
