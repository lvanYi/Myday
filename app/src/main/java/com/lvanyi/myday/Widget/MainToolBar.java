package com.lvanyi.myday.Widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lvanyi.myday.R;

public class MainToolBar extends Toolbar {
    private TextView mTitle;
    private ImageButton mLeftBtn, mRightBtn;

    public MainToolBar(Context context) {
        super(context);
        Log.d("1", "MainToolBar: ");
        LayoutInflater.from(context).inflate(R.layout.main_toolbar_layout, this);
        mTitle = findViewById(R.id.tv_title_toolbar);
        mLeftBtn = findViewById(R.id.btn_left_toolbar);
        mRightBtn = findViewById(R.id.btn_right_toolbar);
    }

    public MainToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d("2", "MainToolBar: ");
        LayoutInflater.from(context).inflate(R.layout.main_toolbar_layout, this);
        mTitle = findViewById(R.id.tv_title_toolbar);
        mLeftBtn = findViewById(R.id.btn_left_toolbar);
        mRightBtn = findViewById(R.id.btn_right_toolbar);
    }

    public MainToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("3", "MainToolBar: ");
        LayoutInflater.from(context).inflate(R.layout.main_toolbar_layout, this);
        mTitle = findViewById(R.id.tv_title_toolbar);
        mLeftBtn = findViewById(R.id.btn_left_toolbar);
        mRightBtn = findViewById(R.id.btn_right_toolbar);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setmTitle(String title){
        this.setTitle("");
        mTitle.setVisibility(VISIBLE);
        mTitle.setText(title);
    }

    public void setmTitleColor(int color) {
        mTitle.setTextColor(color);
    }

    public void setmLeftBtn(Drawable drawable){
        mLeftBtn.setImageDrawable(drawable);
    }

    public void setmLeftBtnClickListener(OnClickListener onClickListener) {
        mLeftBtn.setOnClickListener(onClickListener);
    }

    public void setmRightBtn(Drawable drawable){
        mRightBtn.setImageDrawable(drawable);
    }

    public void setmRightBtnClickListener(OnClickListener onClickListener) {
        mRightBtn.setOnClickListener(onClickListener);
    }
}
