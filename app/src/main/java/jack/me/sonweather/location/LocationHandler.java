package jack.me.sonweather.location;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.util.ArrayList;
import java.util.List;

import jack.me.sonweather.R;
import jack.me.sonweather.SonApplication;

/**
 * Created by zjchai on 2016/11/3.
 */

public class LocationHandler implements ILocationHandler {

    private static final String TAG= LocationHandler.class.getSimpleName();
    private SonApplication sonApplication;

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    public static final int PERMISSON_REQUESTCODE = 0;

    private AMapLocationClientOption mLocationOption = null;
    private AMapLocationClient mLocationClient;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;


    public LocationHandler(SonApplication sonApplication) {
        this.sonApplication = sonApplication;
    }


    @Override
    public void getLocation(AMapLocationListener listener) {
        mLocationClient = new AMapLocationClient(sonApplication);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setNeedAddress(true);
        mLocationClient.setLocationListener(listener);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
//        mLocationOption.setInterval(3000);
        mLocationOption.setOnceLocationLatest(true);
        mLocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void checkPermissions(Activity activity) {
        if (isNeedCheck) {
            checkPermissions(activity, needPermissions);
        }
    }

    @Override
    public void handleRequestPermissionsResult(int requestCode, String[] permissions, int[] paramArrayOfInt, CallBack callBack) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            int i = verifyPermissions(paramArrayOfInt);
            if (i < 0) {
                Log.d(TAG, "handleRequestPermissionsResult: success");
            } else {
                Log.d(TAG, "handleRequestPermissionsResult: fail , permission:"+permissions[i]+",result:");
                if (callBack != null) {
                    callBack.onPermissionRequestFail(permissions[i],paramArrayOfInt[i]);
                }
                isNeedCheck = false;
            }
        }
    }


    public void checkPermissions(Activity activity, String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(activity, permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(activity,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }


    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param activity
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(Activity activity, String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(activity,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    activity, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }


    /**
     * 检测是否说有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private int verifyPermissions(int[] grantResults) {
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return i;
            }
        }
        return -1;
    }


}
