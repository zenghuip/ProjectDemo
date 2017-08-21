package com.jgg.games.base;

import android.app.Application;
import android.content.Context;

import com.jgg.games.utils.LogUtils;
import com.jgg.rxretrofitlibrary.retrofit_rx.RxRetrofitApp;
import com.jgg.selectimage.BoxingCrop;
import com.jgg.selectimage.BoxingMediaLoader;
import com.jgg.selectimage.loader.IBoxingMediaLoader;

import com.jgg.games.utils.Utils;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


public class CosApp extends Application {
	private static final String TAG = "CosApp";
	public static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
        context = this.getApplicationContext();
		Utils.init(getApplicationContext());
        RxRetrofitApp.init(this);
		AppConfig.setDeBugModel(false);

        // 第三方登录或分享配置
        setThirdLoginOrShare();
		UMShareAPI.get(this);
	}

	// 第三方登录配置
	private void setThirdLoginOrShare(){
		PlatformConfig.setWeixin(AppConfig.WEIXIN_APP_ID, AppConfig.WEIXIN_APP_SECRET);
		PlatformConfig.setQQZone(AppConfig.QQ_APP_ID, AppConfig.QQ_APP_SECRET);
		PlatformConfig.setSinaWeibo(AppConfig.WEIBO_APP_ID, AppConfig.WEIBO_APP_SECRET, AppConfig.WEIBO_APP_URL);
	}


}
