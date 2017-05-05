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

package com.jgg.selectimage.model.config;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

public class BoxingConfig implements Parcelable {
    public static final int DEFAULT_SELECTED_COUNT = 9;

    private Mode mMode = Mode.SINGLE_IMG;
    private ViewMode mViewMode = ViewMode.PREVIEW;
    private BoxingCropOption mCropOption;

    private boolean mNeedCamera;
    private boolean mNeedGif;
    private boolean mNeedPaging = true;
    private int titleColor = Color.BLACK;
    private int titleBgColor = 0;
    private int backRes = 0;

    private int mMaxCount = DEFAULT_SELECTED_COUNT;

    public enum Mode {
        SINGLE_IMG, MULTI_IMG, VIDEO
    }

    public enum ViewMode {
        PREVIEW, EDIT, PRE_EDIT
    }

    public BoxingConfig() {
    }

    public BoxingConfig(Mode mode) {
        this.mMode = mode;
    }

    public boolean isNeedCamera() {
        return mNeedCamera;
    }

    public boolean isNeedPaging() {
        return mNeedPaging;
    }

    public Mode getMode() {
        return mMode;
    }

    public ViewMode getViewMode() {
        return mViewMode;
    }

    public BoxingCropOption getCropOption() {
        return mCropOption;
    }

    public int getTitleBgColor() {
        return titleBgColor;
    }

    public int getBackRes() {
        return backRes;
    }

    /**
     * get the max count set by {@link #withMaxCount(int)}, otherwise return 9.
     */
    public int getMaxCount() {
        if (mMaxCount > 0) {
            return mMaxCount;
        }
        return DEFAULT_SELECTED_COUNT;
    }

    public boolean isNeedLoading() {
        return mViewMode == ViewMode.EDIT;
    }

    public boolean isNeedEdit() {
        return mViewMode != ViewMode.PREVIEW;
    }

    public boolean isVideoMode() {
        return mMode == Mode.VIDEO;
    }

    public boolean isMultiImageMode() {
        return mMode == Mode.MULTI_IMG;
    }

    public boolean isSingleImageMode() {
        return mMode == Mode.SINGLE_IMG;
    }

    public boolean isNeedGif() {
        return mNeedGif;
    }

    /**
     * call this means gif is needed.
     */
    public BoxingConfig needGif() {
        this.mNeedGif = true;
        return this;
    }

    /**
     * call this means camera is needed.
     */
    public BoxingConfig needCamera() {
        this.mNeedCamera = true;
        return this;
    }

    public BoxingConfig setTitleBg(int titleBg) {
        this.titleBgColor = titleBg;
        return this;
    }

    public BoxingConfig setBackBg(int backRes) {
        this.backRes = backRes;
        return this;
    }

    public BoxingConfig setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        return this;
    }
    /**
     * call this means paging is needed,by default is true.
     */
    public BoxingConfig needPaging(boolean needPaging) {
        this.mNeedPaging = needPaging;
        return this;
    }

    public BoxingConfig withViewer(ViewMode viewMode) {
        this.mViewMode = viewMode;
        return this;
    }

    public BoxingConfig withCropOption(BoxingCropOption cropOption) {
        this.mCropOption = cropOption;
        return this;
    }

    /**
     * set the max count of selected medias in {@link Mode#MULTI_IMG}
     * @param count max count
     */
    public BoxingConfig withMaxCount(int count) {
        if (count < 1) {
            return this;
        }
        this.mMaxCount = count;
        return this;
    }

    @Override
    public String toString() {
        return "BoxingConfig{" +
                "mMode=" + mMode +
                ", mNeedCamera=" + mNeedCamera +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mMode == null ? -1 : this.mMode.ordinal());
        dest.writeInt(this.mViewMode == null ? -1 : this.mViewMode.ordinal());
        dest.writeParcelable(this.mCropOption, flags);
        dest.writeByte(this.mNeedCamera ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mNeedGif ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mNeedPaging ? (byte) 1 : (byte) 0);
        dest.writeInt(this.mMaxCount);
    }

    protected BoxingConfig(Parcel in) {
        int tmpMMode = in.readInt();
        this.mMode = tmpMMode == -1 ? null : Mode.values()[tmpMMode];
        int tmpMViewMode = in.readInt();
        this.mViewMode = tmpMViewMode == -1 ? null : ViewMode.values()[tmpMViewMode];
        this.mCropOption = in.readParcelable(BoxingCropOption.class.getClassLoader());
        this.mNeedCamera = in.readByte() != 0;
        this.mNeedGif = in.readByte() != 0;
        this.mNeedPaging = in.readByte() != 0;
        this.mMaxCount = in.readInt();
    }

    public static final Creator<BoxingConfig> CREATOR = new Creator<BoxingConfig>() {
        @Override
        public BoxingConfig createFromParcel(Parcel source) {
            return new BoxingConfig(source);
        }

        @Override
        public BoxingConfig[] newArray(int size) {
            return new BoxingConfig[size];
        }
    };
}
