package com.sesxh.commoncore;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import io.reactivex.Observable;

import com.sesxh.rxpermissions.RxPermissions;

import java.util.HashMap;
import java.util.Map;



/**
 * @author LYH
 * @date 2021/1/15
 * @time 17:10
 * @desc
 **/
public class PermissionUtils {

    private static final Map<Object, RxPermissions> container=new HashMap<>();

   public static Observable<Boolean> request(FragmentActivity activity, String... permission){

       return permission(activity)
               .request(permission);

   }


    public static Observable<Boolean> request(Fragment fragment, String... permission){

        return permission(fragment)
                .request(permission);

    }


    private static RxPermissions permission(FragmentActivity activity) {
        RxPermissions rxPermissions;
        if(container.containsKey(activity)){
            rxPermissions=container.get(activity);
        }else {
            rxPermissions = new RxPermissions(activity);
            container.put(activity,rxPermissions);
        }
        return rxPermissions;
    }

    private static RxPermissions permission(Fragment fragment) {
        RxPermissions rxPermissions;
        if(container.containsKey(fragment)){
            rxPermissions=container.get(fragment);
        }else {
            rxPermissions = new RxPermissions(fragment);
            container.put(fragment,rxPermissions);
        }
        return rxPermissions;
    }



}
