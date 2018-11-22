package com.example.android.fa3el5eer;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.fa3el5eer.login.LoginActivity;

public class AboutAppActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private Button button;
    private TextView[] mDots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        viewPager = findViewById(R.id.viewPagerSlide);
        linearLayout = findViewById(R.id.linSlide);

        SlidePager slidePager = new SlidePager(this);
        viewPager.setAdapter(slidePager);

        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(viewPagerListner);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutAppActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }
    public void addDotsIndicator(int position){
        mDots = new TextView[2];
        linearLayout.removeAllViews();
        for (int i = 0; i <mDots.length ; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.gray));
            linearLayout.addView(mDots[i]);
        }

        if (mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.black));
        }

    }
    ViewPager.OnPageChangeListener viewPagerListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



}
