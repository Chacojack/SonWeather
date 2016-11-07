package jack.me.sonweather.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by zjchai on 2016/11/7.
 */
@Data
public class Sun {

    private static final String TAG = Sun.class.getSimpleName();

    String cityId;
    String cityName;
    String date;
    @SerializedName("sunRise")
    String sunRise;
    @SerializedName("sunSet")
    String sunSet;

}
