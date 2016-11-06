package jack.me.sonweather.sp;

import android.content.Context;

/**
 * Created by zjchai on 2016/11/3.
 */

public class SPHandler implements ISPHandler {

    LocationSP locationSP;
    SystemSP systemSP;

    public SPHandler(Context context) {
        locationSP = new LocationSP(context);
        systemSP = new SystemSP(context);
    }

    @Override
    public void setLocation(String location) {
        locationSP.location().put(location);
    }

    @Override
    public String getLocation() {
        return locationSP.location().get();
    }

    @Override
    public void setLoadedCities(boolean loadedCities) {
        systemSP.loadedCities().put(loadedCities);
    }

    @Override
    public boolean isLoadedCities() {
        return systemSP.loadedCities().get();
    }

}
