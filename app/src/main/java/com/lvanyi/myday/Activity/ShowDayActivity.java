package com.lvanyi.myday.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lvanyi.myday.Model.ItemModel;
import com.lvanyi.myday.Model.SharePicModel;
import com.lvanyi.myday.R;
import com.lvanyi.myday.Widget.MainToolBar;
import com.lvanyi.myday.picgen.GeneratePictureManager;

import java.io.File;
import java.text.SimpleDateFormat;

public class ShowDayActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ShowDayActivity";
    private TextView mTitle, mRemark, mStartDay, mDateDiff, mType;
    private MainToolBar mToolBar;
    private ImageView mResultIv;
    private ItemModel this_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_day);
        mTitle = findViewById(R.id.show_day_title);
        mRemark = findViewById(R.id.show_day_remake);
        mStartDay = findViewById(R.id.show_day_startDate);
        mDateDiff = findViewById(R.id.show_day_dateDiff);
        mType = findViewById(R.id.show_day_type);
        mToolBar = findViewById(R.id.tlb_show_day);
        mResultIv = findViewById(R.id.mgv);


        Intent dateIntent = getIntent();
        this_item = (ItemModel) dateIntent.getSerializableExtra("this_item");


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String start_day = dateFormat.format(this_item.getStartDate());

        mTitle.setText("");
        mRemark.setText(this_item.getRemark());
        mType.setText(String.valueOf(this_item.getType()));
        mStartDay.setText(start_day);
        mDateDiff.setText(this_item.getDateDiff());

        mToolBar.setmTitle(this_item.getTitle());
        mToolBar.setmLeftBtnClickListener(this);
        mToolBar.setmRightBtnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left_toolbar:
                finish();
                break;
            case R.id.btn_right_toolbar:
                clickRightBtn();
                String state = Environment.getExternalStorageState();
                Log.d(TAG, state);
                break;
        }
    }

    private void clickRightBtn() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String start_day = dateFormat.format(this_item.getStartDate());
        String appName = this.getString(R.string.app_name);
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath() + "/"+appName+"/"
                + start_day + (int)(Math.random() * 1000000) + ".png";
        SharePicModel sharePicModel = new SharePicModel((ViewGroup) getWindow().getDecorView());
        sharePicModel.setAvatarResId(R.mipmap.app_icon);
        sharePicModel.setItemModel(this_item);
        sharePicModel.setSavePath(filePath);
        GeneratePictureManager.getInstance().generate(sharePicModel, (throwable, bitmap) -> {
            if (throwable != null || bitmap == null) {
                Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
                mResultIv.setImageBitmap(bitmap);
            } else {
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                mResultIv.setImageBitmap(bitmap);
                File file = new File(filePath);
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri = Uri.fromFile(file);
                intent.setData(uri);
                sendBroadcast(intent);
            }
        });

    }
}
