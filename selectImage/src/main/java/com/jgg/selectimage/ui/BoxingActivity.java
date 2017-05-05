/*
 *  Copyright (C) 2017 Bilibili
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.jgg.selectimage.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jgg.selectimage.AbsBoxingActivity;
import com.jgg.selectimage.AbsBoxingViewFragment;
import com.jgg.selectimage.R;
import com.jgg.selectimage.model.config.BoxingConfig;
import com.jgg.selectimage.model.entity.BaseMedia;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BoxingActivity extends AbsBoxingActivity {
    private BoxingViewFragment mPickerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boxing);
        initView(getBoxingConfig());
        setTitleTxt(getBoxingConfig());
    }

    @NonNull
    @Override
    public AbsBoxingViewFragment onCreateBoxingView(ArrayList<BaseMedia> medias) {
        mPickerFragment = (BoxingViewFragment) getSupportFragmentManager().findFragmentByTag(BoxingViewFragment.TAG);
        if (mPickerFragment == null) {
            mPickerFragment = (BoxingViewFragment) BoxingViewFragment.newInstance().setSelectedBundle(medias);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, mPickerFragment, BoxingViewFragment.TAG).commit();
        }
        return mPickerFragment;
    }

    private void initView(BoxingConfig config){
        LinearLayout lyTitle = (LinearLayout) findViewById(R.id.ly_title);
        ImageView ivBack = (ImageView) findViewById(R.id.iv_back);
        if (config.getTitleBgColor() != 0) {
            lyTitle.setBackgroundColor(config.getTitleBgColor());
        }
        if (config.getBackRes() != 0) {
            ivBack.setImageResource(config.getBackRes());
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setTitleTxt(BoxingConfig config) {
        TextView titleTxt = (TextView) findViewById(R.id.pick_album_txt);
        if (config.getMode() == BoxingConfig.Mode.VIDEO) {
            titleTxt.setText(R.string.boxing_video_title);
            titleTxt.setCompoundDrawables(null, null, null, null);
            return;
        }
        mPickerFragment.setTitleTxt(titleTxt);
    }

    @Override
    public void onBoxingFinish(Intent intent, @Nullable List<BaseMedia> medias) {
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
