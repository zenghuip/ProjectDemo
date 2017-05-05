package com.jgg.games.utils;

import android.Manifest;

import com.mukesh.permissions.AppPermissions;

/**
 * Created by Administrator on 2017/4/12 0012.
 * 权限验证
 */

public class PerssionUtil {

    public static final String[] ALL_PERMISSIONS = {
            Manifest.permission.READ_SMS, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    public static final int CAMERA_REQUEST_CODE = 1;
    public static final int SMS_REQUEST_CODE = 2;
    public static final int STORAGE_REQUEST_CODE = 3;
    public static final int ALL_REQUEST_CODE = 0;


    public void checkPermission(AppPermissions appPermissions,String perssion,int code){
        if(!appPermissions.hasPermission(perssion)){
            appPermissions.requestPermission(perssion, code);
        }
    }

    public void checkPermission(AppPermissions appPermissions,String[] perssion,int code){
        if(!appPermissions.hasPermission(perssion)){
            appPermissions.requestPermission(perssion, code);
        }
    }

}
