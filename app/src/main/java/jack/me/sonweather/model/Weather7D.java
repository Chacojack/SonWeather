package jack.me.sonweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Created by zjchai on 2016/11/6.
 */
@Data
public class Weather7D {

    private static final String TAG = Weather7D.class.getSimpleName();

    @SerializedName("cityId")
    private String cityId;
    @SerializedName("cityName")
    private String cityName;
    @SerializedName("sj")
    private String refreshTime;
    @SerializedName("list")
    private List<Weather> weathers;

}
