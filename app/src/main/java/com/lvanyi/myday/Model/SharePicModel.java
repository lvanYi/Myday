package com.lvanyi.myday.Model;

import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lvanyi.myday.R;
import com.lvanyi.myday.picgen.GenerateModel;
import com.lvanyi.myday.picgen.GeneratePictureManager;

import java.text.SimpleDateFormat;


/**
 * Created by HomgWu on 2017/11/29.
 */

public class SharePicModel extends GenerateModel {
    private ImageView mTitleAvatarIv;
    private View mSharePicView;
    private TextView mTitleTv, mRemarkTv, mStartDayTv, mDiffDayTv;
    private int mAvatarResId;
    private ItemModel itemModel;

    public SharePicModel(ViewGroup rootView) {
        super(rootView);
    }

    @Override
    protected void startPrepare(GeneratePictureManager.OnGenerateListener listener) throws Exception {
        mSharePicView = LayoutInflater.from(mContext).inflate(R.layout.layout_share_pic_model, mRootView, false);
        mTitleTv = mSharePicView.findViewById(R.id.invitation_share_link_pic_title_tv);
        mRemarkTv = mSharePicView.findViewById(R.id.invitation_share_link_pic_remark_tv);
        mStartDayTv = mSharePicView.findViewById(R.id.invitation_share_link_pic_start_day_tv);
        mDiffDayTv = mSharePicView.findViewById(R.id.invitation_share_link_pic_diff_day_tv);
        mTitleAvatarIv = mSharePicView.findViewById(R.id.invitation_share_link_pic_avatar_iv);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String start_day = dateFormat.format(itemModel.getStartDate());

        RoundedBitmapDrawable circularBitmapDrawable =
                RoundedBitmapDrawableFactory.create(mContext.getResources(), BitmapFactory.decodeResource(mContext.getResources(), mAvatarResId));
        circularBitmapDrawable.setCircular(true);

        mTitleAvatarIv.setImageDrawable(circularBitmapDrawable);
        mTitleTv.setText(itemModel.getTitle());
        mRemarkTv.setText(itemModel.getRemark());
        mStartDayTv.setText(start_day);
        mDiffDayTv.setText(itemModel.getDateDiff());

        prepared(listener);
    }

    @Override
    public View getView() {
        return mSharePicView;
    }

    public void setAvatarResId(int avatarResId) {
        mAvatarResId = avatarResId;
    }

    public void setItemModel(ItemModel itemModel) {
        this.itemModel = itemModel;
    }
}
