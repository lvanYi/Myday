package com.lvanyi.myday.picgen;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.lvanyi.myday.R;


/**
 * Created by HomgWu on 2017/11/29.
 */

public abstract class GenerateModel {
    protected Context mContext;
    protected String mSavePath;
    protected ViewGroup mRootView;
    protected String appName;
    private GeneratePictureManager mGeneratePictureManager;

    public GenerateModel(ViewGroup rootView) {
        mContext = rootView.getContext();
        mRootView = rootView;
        appName = mContext.getString(R.string.app_name);
        mGeneratePictureManager = GeneratePictureManager.getInstance();
    }

    protected abstract void startPrepare(GeneratePictureManager.OnGenerateListener listener) throws Exception;

    public abstract View getView();

    protected void prepared(GeneratePictureManager.OnGenerateListener listener) {
        mGeneratePictureManager.prepared(this, listener);
    }

    public String getSavePath() {
        return mSavePath;
    }

    public void setSavePath(String savePath) {
        mSavePath = savePath;
    }

    public String getAppName() {
        return appName;
    }
}
