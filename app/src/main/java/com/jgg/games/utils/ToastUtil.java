package com.jgg.games.utils;

import android.widget.Toast;

import com.jgg.games.base.CosApp;


/**
 * Created by android2 on 2015/8/27.
 */
public class ToastUtil {


    // 显示吐司
    public static void showToast(int str){
        Toast.makeText(CosApp.context, CosApp.context.getResources().getText(str), Toast.LENGTH_SHORT).show();
    }
    // 显示吐司
    public static void showToast(String content){
        Toast.makeText(CosApp.context, content, Toast.LENGTH_SHORT).show();
    }
}
