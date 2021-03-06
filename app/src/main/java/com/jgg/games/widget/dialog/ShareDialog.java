package com.jgg.games.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.adapter.ShareAdapter;
import com.jgg.games.model.entity.ShareEntity;
import com.jgg.games.utils.AppUtils;
import com.jgg.games.utils.DisplayUtil;
import com.jgg.games.utils.StringUtil;
import com.jgg.games.utils.ToastUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class ShareDialog implements View.OnClickListener, AdapterView.OnItemClickListener {

    public final static int SHARE_OF_IMAGE = 1; // 分享图片
    public final static int SHARE_OF_WEB = 2; // 分享网页

    private ArrayList<ShareEntity> platforms = new ArrayList<>();
    private ShareAdapter adapter;
    private GridView gridView;
    private TextView tvCancel;
    private View popView;
    private Context mContext;
    private Dialog mDialog;
    private String imageUrl;
    private String title;
    private String content;
    private File file; // 分享本地图片
    private String url;
    private int type = SHARE_OF_WEB;

    public ShareDialog(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        mDialog = new Dialog(mContext, R.style.dialog_notitle);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_share, null);
        gridView = (GridView) view.findViewById(R.id.gv_share);
        tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        popView = view.findViewById(R.id.popup_view);

        adapter = new ShareAdapter(mContext, R.layout.item_share, platforms);
        gridView.setAdapter(adapter);
        //设置dialog的宽高为屏幕的宽高
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(DisplayUtil.getScreenWidth(mContext), DisplayUtil.getScreenHeight((Activity) mContext));
        mDialog.setContentView(view, layoutParams);
        mDialog.setCanceledOnTouchOutside(true);
        tvCancel.setOnClickListener(this);
        popView.setOnClickListener(this);
        gridView.setOnItemClickListener(this);

    }

    // 设置分享类型 图片还是网页
    public ShareDialog setShareType(int type) {
        this.type = type;
        return this;
    }

    public ShareDialog setFile(File file) {
        this.file = file;
        return this;
    }

    public ShareDialog setImageUrl(String url) {
        this.imageUrl = url;
        return this;
    }

    // 设置分享内容
    public ShareDialog setContent(String title, String content, String url) {
        this.title = title;
        this.content = content;
        this.url = url;
        return this;
    }

    public void show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    @Override
    public void onClick(View v) {
        mDialog.dismiss();
    }

    private void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    public ShareDialog initPlatforms(@ShareEntity.ShareType int... list) {
        platforms.clear();
        for (int e : list) {
            if (e != ShareEntity.GENERIC) {
                platforms.add(ShareEntity.toSnsPlatform(e));
            }
        }
        adapter.setDatas(platforms);
        return this;
    }


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);

            ToastUtil.showToast(platform + " 分享成功啦");

            dismiss();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtil.showToast(platform + " 分享失败啦");
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
                dismiss();
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtil.showToast(platform + " 分享取消了");
            dismiss();
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ShareEntity entity = platforms.get(position);
        if (entity != null) {
            if (entity.mPlatform == ShareEntity.WEIXIN) { // 微信
                shareWeixin(SHARE_MEDIA.WEIXIN);
            } else if (entity.mPlatform == ShareEntity.WEIXIN_CIRCLE) { // 朋友圈
                shareWxCircle(SHARE_MEDIA.WEIXIN_CIRCLE);
            } else if (entity.mPlatform == ShareEntity.QQ) { // qq
                shareQQ(SHARE_MEDIA.QQ);
            } else if (entity.mPlatform == ShareEntity.QZONE) { // 空间
                shareQzone(SHARE_MEDIA.QZONE);
            } else if (entity.mPlatform == ShareEntity.SINA) { // 新浪微博
                shareSina(SHARE_MEDIA.SINA);
            } else if (entity.mPlatform == ShareEntity.FRIENDS) { // 好友

            } else if (entity.mPlatform == ShareEntity.GENERIC) { // 圈子

            } else if (entity.mPlatform == ShareEntity.CIRCLE) { // 公会

            }
        }
    }

    private void shareWeixin(SHARE_MEDIA platform) {
        if (!AppUtils.isInstallApp(mContext, "com.tencent.mm")) {
            ToastUtil.showToast(R.string.umeng_share_weixin);
            return;
        }
        share(platform);
    }

    private void shareWxCircle(SHARE_MEDIA platform) {
        if (!AppUtils.isInstallApp(mContext, "com.tencent.mm")) {
            ToastUtil.showToast(R.string.umeng_share_weixin);
            return;
        }
        share(platform);
    }

    private void shareQQ(SHARE_MEDIA platform) {
        if (!AppUtils.isInstallApp(mContext, "com.tencent.mobileqq")) {
            ToastUtil.showToast(R.string.umeng_share_qq);
            return;
        }
        share(platform);
    }

    private void shareQzone(SHARE_MEDIA platform) {
        if (!AppUtils.isInstallApp(mContext, "com.tencent.mobileqq")) {
            ToastUtil.showToast(R.string.umeng_share_qq);
            return;
        }
        share(platform);
    }

    private void shareSina(SHARE_MEDIA platform) {
        share(platform);
    }

    private void share(SHARE_MEDIA platform) {
        if (type == SHARE_OF_WEB) {
            shareWeb(platform);
        } else {
            sharePic(platform);
        }
    }

    private void sharePic(SHARE_MEDIA platform) {
        if (StringUtil.isEmpty(imageUrl) && file == null) {
            return;
        }
        UMImage image = null;
        if (StringUtil.isEmpty(imageUrl) && file != null){
            image = new UMImage(mContext, file);
        }else if (!StringUtil.isEmpty(imageUrl) && file == null){
            image = new UMImage(mContext, imageUrl);
        }
        new ShareAction((Activity) mContext).withText(title).setPlatform(platform)
                .withMedia(image).setCallback(umShareListener).share();
    }

    protected void shareWeb(SHARE_MEDIA platform) {
        UMImage image;
        if (StringUtil.isEmpty(url)) {
            return;
        }
        if (!StringUtil.isEmpty(imageUrl)) {
            image = new UMImage(mContext, imageUrl);
        } else {
            image = new UMImage(mContext, R.drawable.default_bg);
        }
        UMWeb web = new UMWeb(url);
        web.setTitle(title);
        web.setThumb(image);
        if (!StringUtil.isEmpty(content)) {
            web.setDescription(content);
        }
        new ShareAction((Activity) mContext).setPlatform(platform)
                .withMedia(web).setCallback(umShareListener).share();
    }

//    // 用法
//    public void lizi(Context context) {
//        new ShareDialog(context).initPlatforms(ShareEntity.WEIXIN, ShareEntity.WEIXIN_CIRCLE, ShareEntity.QQ, ShareEntity.QZONE, ShareEntity.SINA, ShareEntity.FRIENDS, ShareEntity.GUILD, ShareEntity.CIRCLE)
//                .setShareType(ShareDialog.SHARE_OF_WEB).setContent("分享标题", "分享内容", "http://192.168.244.65:4000/cos/590069366a3b2b9c5373d91b#/betprotect", "").show();
//
//    }
}
