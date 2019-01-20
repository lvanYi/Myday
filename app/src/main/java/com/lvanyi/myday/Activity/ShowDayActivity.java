package com.lvanyi.myday.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lvanyi.myday.Model.ItemModel;
import com.lvanyi.myday.R;

import java.text.SimpleDateFormat;

public class ShowDayActivity extends AppCompatActivity{
    private static final String TAG = "ShowDayActivity";
    private TextView mTitle,mRemark,mStartDay,mDateDiff,mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_day);
        mTitle=findViewById(R.id.show_day_title);
        mRemark=findViewById(R.id.show_day_remake);
        mStartDay=findViewById(R.id.show_day_startDate);
        mDateDiff=findViewById(R.id.show_day_dateDiff);
        mType=findViewById(R.id.show_day_type);
        Intent dateIntent = getIntent();
        ItemModel this_item = (ItemModel) dateIntent.getSerializableExtra("this_item");


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String start_day = dateFormat.format(this_item.getStartDate());

        mTitle.setText(this_item.getTitle());
        mRemark.setText(this_item.getRemark());
        mType.setText(String.valueOf(this_item.getType()));
        mStartDay.setText(start_day);
        mDateDiff.setText(this_item.getDateDiff());

    }

}
