package com.lvanyi.myday.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toolbar;

import com.lvanyi.myday.Database.DBHelper;
import com.lvanyi.myday.Database.ItemDao;
import com.lvanyi.myday.Model.ItemModel;
import com.lvanyi.myday.R;

import java.util.Calendar;

public class AddItemActivity extends AppCompatActivity {

    private static final String TAG = "AddItemActivity";
    private Toolbar mToolbar;
    private ImageButton mBtnGoBack,mBtnOk;
    private EditText mEdtTitle,mEdtRemark;
    private DatePicker mDatePicker;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);
        mToolbar = findViewById(R.id.toolbar_additem);
        mBtnGoBack = findViewById(R.id.btn_goback_additem);
        mBtnOk = findViewById(R.id.btn_ok_additem);
        mEdtTitle = findViewById(R.id.edt_title_additem);
        mEdtRemark = findViewById(R.id.edt_remark_additem);
        mDatePicker = findViewById(R.id.dtp_pickerday_additem);
        mToolbar.setTitle("");
        setActionBar(mToolbar);

        buttonListener();

    }

    private void buttonListener() {
        View.OnClickListener listener =  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_ok_additem:
                        if(mEdtTitle.getText().toString().isEmpty()||mEdtRemark.getText().toString().isEmpty()){
                            finish();
                            break;
                        }else {
                            Intent mIntent=new Intent();
                            mIntent.putExtra("year",mDatePicker.getYear());
                            mIntent.putExtra("month",mDatePicker.getMonth());
                            mIntent.putExtra("day",mDatePicker.getDayOfMonth());
                            mIntent.putExtra("title",mEdtTitle.getText().toString());
                            mIntent.putExtra("remark",mEdtRemark.getText().toString());
                            setResult(RESULT_OK,mIntent);
                            finish();
                        }
                    case R.id.btn_goback_additem:
                        finish();
                        break;
                }

            }
        };
        mBtnGoBack.setOnClickListener(listener);
        mBtnOk.setOnClickListener(listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mEdtTitle.getText().toString().isEmpty()||mEdtRemark.getText().toString().isEmpty()){
            Log.d(TAG, "-------------------onDestroy+notadd--------------");
        }else {
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.set(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
            ItemDao itemDao = new ItemDao(getApplicationContext());
            itemDao.insert(new ItemModel(mCalendar.getTime(), mEdtTitle.getText().toString(), mEdtRemark.getText().toString()));
            Log.d(TAG, "-------------------onDestroy+add--------------");
        }

    }
}
