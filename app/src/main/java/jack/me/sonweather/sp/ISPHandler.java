package jack.me.sonweather.sp;

/**
 * Created by zjchai on 2016/11/3.
 */

public interface ISPHandler {
    void setLocation(String location);

    String getLocation();

    void setLoadedCities(boolean loadedCities);

    boolean isLoadedCities();
}
