package com.jgg.games.view.delegate;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ProgressBar;

import com.jgg.games.R;
import com.jgg.games.presenter.base.HeaderDelegate;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/4/13 0013.
 * 信息输入，修改昵称，或者其他
 */

public class WebviewDelegate extends HeaderDelegate {

    private WebView webView;
    private ProgressBar progressBar;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        setTitleAndBack(0);
        webView = get(R.id.webView);
        progressBar = get(R.id.pb_progress);
    }


    public void webviewSetting(String url){
        WebSettings settings = webView.getSettings();
        webView.setBackgroundColor(ContextCompat.getColor(this.getActivity(),R.color.color_tv_white));
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        // 自适应
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportMultipleWindows(true);

        // 设置可以支持缩放
        settings.setSupportZoom(true);
        //不显示webview缩放按钮
        settings.setDisplayZoomControls(false);
        // 设置出现缩放工具
        settings.setBuiltInZoomControls(true);
        //扩大比例的缩放
        settings.setUseWideViewPort(true);

        webView.setDrawingCacheEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);

            }
        });
        //可以操作progress
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    // 网页加载完成
                    progressBar.setVisibility(View.GONE);
                } else {
                    // 加载中
                    progressBar.setProgress(newProgress);
                }

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }


        });
        webView.loadUrl(url);
    }


    public void goBack(){
        if (webView == null){
            return;
        }
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }


    public void onPause(){
        if (webView != null) {
            webView.pauseTimers();
            callHiddenWebViewMethod("onPause");
        }
    }

    public void onResume(){
        if (webView != null) {
            webView.resumeTimers();
            callHiddenWebViewMethod("onResume");
        }
    }

    // ~~~~~~~~~~~~~处理FLASH退出的问题 ~~~~~~~~

    private void callHiddenWebViewMethod(String name) {
        if (webView != null) {
            try {
                Method method = WebView.class.getMethod(name);
                method.invoke(webView);
            } catch (NoSuchMethodException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
        }
    }
}
