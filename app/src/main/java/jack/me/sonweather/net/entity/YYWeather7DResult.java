package jack.me.sonweather.net.entity;

import com.google.gson.annotations.SerializedName;

import jack.me.sonweather.model.Weather7D;
import lombok.Data;

/**
 * Created by zjchai on 2016/11/6.
 */
@Data
public class YYWeather7DResult{

    private static final String TAG = YYWeather7DResult.class.getSimpleName();

    @SerializedName("code")
    int code;
    @SerializedName("msg")
    String msg;
    @SerializedName("counts")
    int counts;
    @SerializedName("data")
    Weather7D weather7D;
}
