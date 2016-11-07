package jack.me.sonweather.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by zjchai on 2016/11/6.
 */
@Data
public class Weather {

    private static final String TAG = Weather.class.getSimpleName();

    @SerializedName("tq1")
    private String dayWeather;
    @SerializedName("tq2")
    private String nightWeather;
    @SerializedName("qw1")
    private String dayTemperature;
    @SerializedName("qw2")
    private String nightTemperature;
    @SerializedName("fl1")
    private String dayWindPower;
    @SerializedName("fl2")
    private String nightWindPower;
    @SerializedName("fx1")
    private String dayWindDirection;
    @SerializedName("fx2")
    private String nightWindDirection;
    @SerializedName("date")
    private String date;


}
