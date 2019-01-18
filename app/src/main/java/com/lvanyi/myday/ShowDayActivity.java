package com.lvanyi.myday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShowDayActivity extends AppCompatActivity{
    private TextView t1,t2,t3,t4,t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_day2);
        t1=findViewById(R.id.text1);
        t2=findViewById(R.id.text2);
        t3=findViewById(R.id.text3);
        t4=findViewById(R.id.text4);

        Intent dateIntent = getIntent();
        t1.setText(dateIntent.getStringExtra("title"));
        t2.setText(dateIntent.getStringExtra("remark"));
        //t3.setText(dateIntent.getIntExtra("type",-1));
        t4.setText(dateIntent.getStringExtra("startdate"));



    }

}
