package jack.me.sonweather.net.entity;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by zjchai on 2016/11/7.
 */
@Data
public class YYWeatherBaseResult {

    private static final String TAG = YYWeatherBaseResult.class.getSimpleName();

    @SerializedName("code")
    int code;
    @SerializedName("msg")
    String msg;
    @SerializedName("counts")
    int counts;

}
