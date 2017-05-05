package com.jgg.games.view.base;

import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.CommonEntity;
import com.jgg.games.model.entity.QnMsgEntity;
import com.jgg.games.model.manager.UploadFileManager;
import com.jgg.games.utils.StringUtils;
import com.jgg.games.utils.ToastUtil;
import com.jgg.selectimage.Boxing;
import com.jgg.selectimage.model.entity.BaseMedia;
import com.jgg.selectimage.model.entity.impl.ImageMedia;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20 0020.
 * 图片选择
 */

public abstract class ImageActivity<T extends HeaderDelegate> extends BaseActivity<T>{
    public static final int SINGLE_CODE = 1024; // 图片单选
    public static final int MULTI_CODE = 1025; // 图片多选

    public static final String HEAD = "avatars"; // 图片多选


    public int MAX = 9; // 最多图片
    public  ArrayList<BaseMedia> selectImg = new ArrayList<>();
    private  ArrayList<String> resultImg = new ArrayList<>();
    private  String url = "";
    public String headUrl = ""; // 上传完的头像地址
    boolean change;
    boolean isSuc; // 第一次图片上传成功


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            selectImg.clear();
            selectImg = Boxing.getResult(data);
            if (requestCode == SINGLE_CODE){ // 单选图片
                BaseMedia media = selectImg.get(0);
                resultImg.add(getSingleUrl(media));
                setImage(resultImg);
                change = true;
            }else if (requestCode == MULTI_CODE){ // 多选图片
                resultImg.clear();
                for (BaseMedia media:selectImg){
                    resultImg.add(getSingleUrl(media));
                }
            }

        }
    }

    // 成功后设置图片
    public abstract void setImage(List<String> url);

    public abstract void doPost();

    // media转url
    private String getSingleUrl(BaseMedia media){
        if (media instanceof ImageMedia) {
           return ((ImageMedia) media).getThumbnailPath();
        } else {
            return media.getPath();
        }
    }

    // 上传头像
    public void postImg(){
        //含图片先上传到七牛
        if (resultImg != null && resultImg.size()>0)
            url = resultImg.get(0);
        if (!StringUtils.isEmpty(url) && change && !isSuc) {
            UploadFileManager.getInstance(this).getUploadToken(HEAD,new CommonCallback<CommonEntity<QnMsgEntity>>() {
                @Override
                public void onSuccess(CommonEntity<QnMsgEntity> entity) {
                    QnMsgEntity response = entity.getData().getQiniuToken();
                    if (response != null) {
                        if (response.getCode() == 0) {
                            UploadFileManager.getInstance(ImageActivity.this).uploadHeadToQiniu(url, response, new UpCompletionHandler() {
                                @Override
                                public void complete(String key, ResponseInfo info, JSONObject response) {
                                    if (!info.isOK()) {
                                        ToastUtil.showToast("头像上传失败");
                                        isSuc = false;
                                    } else {
                                        try {
                                            JSONObject jsonStr  = response.getJSONObject("data");
                                            JSONObject result  = jsonStr.getJSONObject("qiniuCallback");
                                            headUrl = result.getString("url").toString();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        Log.d("dddd", headUrl + "----");
                                        isSuc = true;
                                        doPost();
                                    }
                                }
                            });

                        }
                    }
                }

                @Override
                public void onError(String error) {
                    ToastUtil.showToast(error);
                }
            });
        }else {
            doPost();
        }
    }

}
