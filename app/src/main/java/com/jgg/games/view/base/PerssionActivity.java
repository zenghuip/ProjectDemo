package com.jgg.games.view.base;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.mukesh.permissions.AppPermissions;

import java.util.ArrayList;
import java.util.List;

import com.jgg.games.utils.PerssionUtil;

/**
 * Created by Administrator on 2017/3/20 0020.
 * 权限验证
 */

public abstract class PerssionActivity<T extends HeaderDelegate> extends BaseActivity<T>{

    public AppPermissions permissions;

    @Override
    protected void initValue() {
        super.initValue();
        permissions = new AppPermissions(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PerssionUtil.ALL_REQUEST_CODE:
                List<Integer> permissionResults = new ArrayList<>();
                for (int grantResult : grantResults) {
                    permissionResults.add(grantResult);
                }
                if (permissionResults.contains(PackageManager.PERMISSION_DENIED)) {
                    Toast.makeText(this, "All Permissions not granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "All Permissions granted", Toast.LENGTH_SHORT).show();
                }
                break;
            case PerssionUtil.CAMERA_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "Camera Permissions not granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Camera Permissions granted", Toast.LENGTH_SHORT).show();
                }
                break;
            case PerssionUtil.SMS_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "SMS Permissions not granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "SMS Permissions granted", Toast.LENGTH_SHORT).show();
                }
                break;
            case PerssionUtil.STORAGE_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "Storage Permissions not granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Storage Permissions granted", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
