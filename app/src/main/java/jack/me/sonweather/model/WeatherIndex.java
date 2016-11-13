package jack.me.sonweather.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * Created by zjchai on 2016/11/13.
 */
@Data
@Builder
public class WeatherIndex {

    private String type;
    private int index ;
    private String shortDesc;
    private String longDesc;

    @Tolerate
    public WeatherIndex(){

    }

}
