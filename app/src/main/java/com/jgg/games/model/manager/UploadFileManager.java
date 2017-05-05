package com.jgg.games.model.manager;

import com.google.gson.Gson;
import com.jgg.games.http.HttpRequest;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.JsonPostBean;
import com.jgg.games.model.entity.QnMsgEntity;
import com.jgg.games.model.entity.SystemParams;
import com.jgg.games.model.entity.UploadParams;
import com.jgg.games.utils.SharedPreUtil;
import com.jgg.games.utils.Utils;
import com.qiniu.android.common.Zone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

/**
 * Created by dingdongdong on 2017/3/28.
 */
public class UploadFileManager {

    private static UploadFileManager instance = null;
    Configuration config = new Configuration.Builder()
            .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认256K
            .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认512K
            .connectTimeout(10) // 链接超时。默认10秒
            .responseTimeout(60) // 服务器响应超时。默认60秒
            .zone(Zone.zone1) // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
            .build();
    public UploadManager uploadManager = new UploadManager(config);


    public synchronized static UploadFileManager getInstance() {
        if (instance == null) {
            instance = new UploadFileManager();
        }
        return instance;
    }

    /**
     * 获取上传头像的token
     * @param otherCallback
     */
    public void getUploadToken(String type,CommonCallback otherCallback) {
        new HttpRequest().postUrl(true,getToken(type),otherCallback,QnMsgEntity.class);
    }

    /**
     * 上传头像到七牛
     * @param filePath
     * @param msg
     * @param completionHandler
     */
    public void uploadHeadToQiniu(String filePath, QnMsgEntity msg, UpCompletionHandler completionHandler) {
        if (msg == null) {
            return;
        }
        uploadManager.put(filePath,msg.getKey(), msg.getToken(), completionHandler, null);
    }

    /**
     * 获取上传文件的token
     * @param type image voice。。。
     * @param otherCallback
     */
    public void getUploadImageToken(String type,CommonCallback otherCallback) {
        new HttpRequest().postUrl(true,getUploadImageToken(type),otherCallback,QnMsgEntity.class);

    }

    /**
     * @param filePath          路径
     * @param msg
     * @param completionHandler
     */

    public void uploadImageToQiniu(String filePath, int i,QnMsgEntity msg, UpCompletionHandler completionHandler) {
        if (msg == null) {
            return;
        }
        uploadManager.put(filePath, msg.getPrefix() + i, msg.getToken(), completionHandler, null);
    }

    /**
     * avatars，头像；image，图片；audio，音频；video，视频
     *
     * @param type
     * @return
     */
    private String getToken(String type) {
        return "{\n" +
                "  qiniuToken(token: \"" + SharedPreUtil.getToken() + "\", type: \"" + type + "\") {\n" +
                "    token,key\n" +
                "  }\n" +
                "}";
    }

    private String getUploadHeadToken() {
        JsonPostBean bean = new JsonPostBean();
        SystemParams systemParams = getSystem("V1.2");
        UploadParams params = new UploadParams();
        params.setSid(SharedPreUtil.getSid());
        bean.setParams(params);
        bean.setMethod("10-1");
        bean.setSystem(systemParams);
        Gson gson = new Gson();
        return gson.toJson(bean);
    }

    private String getUploadImageToken( String type) {
        JsonPostBean bean = new JsonPostBean();
        SystemParams systemParams = getSystem("V1.2");
        UploadParams params = new UploadParams();
        params.setSid(SharedPreUtil.getSid());
        params.setType(type);
        bean.setParams(params);
        bean.setMethod("10-2");
        bean.setSystem(systemParams);
        Gson gson = new Gson();
        return gson.toJson(bean);
    }

    private SystemParams getSystem(String version) {
        SystemParams system = new SystemParams();
        system.setFrom("android");
        system.setSign(Utils.getSign(Utils.getUnixTime()));
        system.setTime(Utils.getUnixTime());
        system.setVersion(version);
        return system;
    }
}
