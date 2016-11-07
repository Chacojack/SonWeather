package jack.me.sonweather.net.entity;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by zjchai on 2016/11/6.
 */
@Data
public class YYWeatherBaseResult {

    private static final String TAG = YYWeatherBaseResult.class.getSimpleName();

    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("counts")
    private int counts;

}
