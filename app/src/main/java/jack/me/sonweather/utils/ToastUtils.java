package jack.me.sonweather.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zjchai on 2016/11/6.
 */
public class ToastUtils {

    private static final String TAG = ToastUtils.class.getSimpleName();

    public static void toast(Context context,int resId){
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

}
