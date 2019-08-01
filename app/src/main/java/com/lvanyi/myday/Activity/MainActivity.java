package com.lvanyi.myday.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.lvanyi.myday.Database.ItemDao;
import com.lvanyi.myday.Fragment.FormerlyFragment;
import com.lvanyi.myday.Fragment.FutureFragment;
import com.lvanyi.myday.Fragment.MyFragment;
import com.lvanyi.myday.Model.ItemModel;
import com.lvanyi.myday.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;
    private Toolbar mToolbar;
    private ImageButton mImageButton;
    private TextView mTitle;
    private List<ItemModel> list = new ArrayList<ItemModel>();
    private Calendar mCalendar = Calendar.getInstance();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("111111");

        mToolbar=findViewById(R.id.toolbar_main);
        mViewPager  = findViewById(R.id.viewpager_main);
        mBottomNavigationView =findViewById(R.id.notification_main);
        mImageButton = findViewById(R.id.imb_add_mian);
        mTitle = findViewById(R.id.tv_mian_title);

        //mToolbar.setTitleTextColor(0xFFD9D44B);
        mToolbar.setTitle("");
        //mToolbar.setLogo(R.mipmap.icon);
        setActionBar(mToolbar);
        //mToolbar.setNavigationIcon(R.mipmap.icon);


        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddItemActivity.class);
                startActivityForResult(intent,1);
            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Fragment MyFragment = new MyFragment();
        final ArrayList<Fragment> fgLists=new ArrayList<>(3);
        fgLists.add(MyFragment);
        fgLists.add(new FormerlyFragment());
        fgLists.add(new FutureFragment());


        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fgLists.get(position);
            }

            @Override
            public int getCount() {
                return fgLists.size();
            }
        };

        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            private static final float MIN_SCALE = 0.85f;
            private static final float MIN_ALPHA = 0.5f;
            @Override
            public void transformPage(@NonNull View page, float position) {
                int pageWidth = page.getWidth();
                int pageHeight = page.getHeight();
                /**
                 * position：这个position不是手指滑动的坐标位置，而是滑动页面相对于手机屏幕的位置，
                 * 范围位：[-1,1],[-1,0)表示页面向左滑出屏幕，0表示处于中心（即当前显示）的页面，
                 * (0,1]表示页面向右滑出屏幕
                 */
                if (position < -1) { //表示已经滑出屏幕（左边）
                    page.setAlpha(0);
                } else if (position <= 1) { // [-1,1]
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                    if (position < 0) {
                        page.setTranslationX(horzMargin - vertMargin / 2);
                    } else {
                        page.setTranslationX(-horzMargin + vertMargin / 2);
                    }
                    //缩放
                    page.setScaleX(scaleFactor);
                    page.setScaleY(scaleFactor);

                    //设置透明度
                    page.setAlpha(MIN_ALPHA +
                            (scaleFactor - MIN_SCALE) /
                                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));
                } else {
                    //表示已经滑出屏幕（右边）
                    page.setAlpha(0);
                }

            }
        });
        mViewPager.setOffscreenPageLimit(2);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_dashboard:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_notifications:
                        mViewPager.setCurrentItem(2);
                        break;
                }

                return false;
            }
        });

        initdatas();

        if (list.size()!=0){
            mTitle.setText("");
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    // TODO: 2018/10/18 mtitle的处理 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    mCalendar.set(data.getIntExtra("year",1970),data.getIntExtra("month",1),data.getIntExtra("day",1));
                    list.add(new ItemModel(mCalendar.getTime(),data.getStringExtra("title"),data.getStringExtra("remark")));
                    mTitle.setText("");
                    Log.e("1", "run:---------> onActivityResult");
                }
        }
    }

    private void initdatas() {
        /*Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(2018, 9, 9);
        list.add(new ItemModel(mCalendar.getTime(), "1", "11111"));
        mCalendar.set(2019, 9, 9);
        list.add(new ItemModel(mCalendar.getTime(), "2", "22222"));*/
        ItemDao itemDao = new ItemDao(getApplicationContext());
        list = itemDao.queryAll();
    }


    public List<ItemModel> getList() {
        return list;
    }
}
