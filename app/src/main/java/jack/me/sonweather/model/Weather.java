package jack.me.sonweather.model;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * Created by zjchai on 2016/11/6.
 */
@Data
@Builder
public class Weather {

    private static final String TAG = Weather.class.getSimpleName();

    @SerializedName(value = "tq1",alternate = "tq")
    private String dayWeather;
    @SerializedName("tq2")
    private String nightWeather;
    @SerializedName(value = "qw1",alternate = "qw")
    private String dayTemperature;
    @SerializedName("qw2")
    private String nightTemperature;
    @SerializedName(value = "fl1",alternate = "fl")
    private String dayWindPower;
    @SerializedName("fl2")
    private String nightWindPower;
    @SerializedName(value = "fx1",alternate = "fx")
    private String dayWindDirection;
    @SerializedName("fx2")
    private String nightWindDirection;
    @SerializedName("sd")
    private String humidity;
    @SerializedName(value = "date",alternate = "sj")
    private String date;

    @Tolerate
    public Weather() {
    }
}
