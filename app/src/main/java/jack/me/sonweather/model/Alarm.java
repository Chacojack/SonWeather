package jack.me.sonweather.model;

import lombok.Data;

/**
 * Created by zjchai on 2016/11/7.
 */
@Data
public class Alarm {

    private static final String TAG = Alarm.class.getSimpleName();

    String cityId;
    String cityName;
    String type;
    String level;
    String startTime;
    String endTime;
    String content;

}
