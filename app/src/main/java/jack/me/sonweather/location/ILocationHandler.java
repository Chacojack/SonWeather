package jack.me.sonweather.location;

import android.app.Activity;

import com.amap.api.location.AMapLocationListener;

/**
 * Created by zjchai on 2016/11/3.
 */

public interface ILocationHandler {
    void getLocation(AMapLocationListener listener);

    void checkPermissions(Activity activity);

    void handleRequestPermissionsResult(int requestCode, String[] permissions, int[] paramArrayOfInt, CallBack callBack);

    interface CallBack{
        void onPermissionRequestFail(String permission, int result);
    }

}
