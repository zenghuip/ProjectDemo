package com.jgg.games.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jgg.selectimage.Boxing;
import com.jgg.selectimage.model.config.BoxingConfig;
import com.jgg.selectimage.model.config.BoxingCropOption;
import com.jgg.selectimage.model.entity.BaseMedia;
import com.jgg.selectimage.ui.BoxingActivity;
import com.jgg.selectimage.utils.BoxingFileHelper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Locale;

import com.jgg.games.R;
import com.jgg.games.base.CosApp;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class ImageUtil {


    /**
     * 显示圆角图片
     * @param url
     * @param imageView
     */
    public static void displayCircleImage(String url, ImageView imageView) {
        Glide.with(CosApp.context)
                .load(url)
                .error(R.drawable.default_head)
                .bitmapTransform(new CropCircleTransformation(CosApp.context))
                .crossFade() // 淡入淡出动画效果
                .into(imageView);
    }

    /**
     * 显示图片
     * @param url
     * @param imageView
     */
    public static void displayImg(String url, ImageView imageView) {

        displayImg(url,imageView,R.drawable.default_bg);
    }

    public static void displayImg(String url, ImageView imageView, int defaultImg) {
        Glide.with(CosApp.context)
                .load(url)
                .error(defaultImg)
                .crossFade() // 淡入淡出动画效果
//                .placeholder(defaultImg) // 缺省的占位图片，一般可以设置一个加载中的进度图
                .into(imageView);
    }

    private static BoxingConfig config(Context context, BoxingConfig.Mode mode,boolean needCarame, boolean needCrop,int max){
        BoxingConfig config = new BoxingConfig(mode);
        if (needCarame){
            config.needCamera();
        }
        if (needCrop){
            String cachePath = BoxingFileHelper.getCacheDir(context);
            if (!TextUtils.isEmpty(cachePath)) {
                Uri destUri = new Uri.Builder()
                        .scheme("file")
                        .appendPath(cachePath)
                        .appendPath(String.format(Locale.US, "%s.jpg", System.currentTimeMillis()))
                        .build();
                config.withCropOption(new BoxingCropOption(destUri));
            }
        }
        if (config.isMultiImageMode()) {
            config.withMaxCount(max);
        }
        return config;
    }

    /**
     * 单张图片选择
     * @param context
     * @param needCarame 是否需要相机
     * @param needCrop 是否需要裁剪
     * @param code
     */
    public static void intentSingle(Activity context,boolean needCarame,boolean needCrop,int code){
        Boxing.of(config(context, BoxingConfig.Mode.SINGLE_IMG,needCarame,needCrop,1)).withIntent(context, BoxingActivity.class).start(context, code);
    }

    public static void intentSingle(Fragment context, boolean needCarame, boolean needCrop, int code){
        Boxing.of(config(context.getActivity(), BoxingConfig.Mode.SINGLE_IMG,needCarame,needCrop,1)).withIntent(context.getActivity(), BoxingActivity.class).start(context, code);
    }

    /**
     * 多图选择
     * @param context
     * @param selectedMedias 已选图片
     * @param needCarame
     * @param max 最多张数
     * @param code
     */
    public static void intentMulit(Fragment context, ArrayList<? extends BaseMedia> selectedMedias, boolean needCarame, int max, int code){
        Boxing.of(config(context.getActivity(), BoxingConfig.Mode.MULTI_IMG,needCarame,false,max))
                .withIntent(context.getActivity(), BoxingActivity.class,selectedMedias)
                .start(context, code);
    }

    public static void intentMulit(Activity context, ArrayList<? extends BaseMedia> selectedMedias, boolean needCarame, int max, int code){
        Boxing.of(config(context, BoxingConfig.Mode.MULTI_IMG,needCarame,false,max))
                .withIntent(context, BoxingActivity.class,selectedMedias)
                .start(context, code);
    }


    public static String toBase64(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();

        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    public static Bitmap decodeResource(Context context, int resId) {
        return BitmapFactory.decodeResource(context.getResources(), resId);
    }

    public static Bitmap toBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}
