package com.example.mughz.slider;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout slider;
    private int count;
    private ImageView[] slide;
    private CustomPagerAdapter pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        slider = (LinearLayout) findViewById(R.id.slider);
        pager = new CustomPagerAdapter(this);
        viewPager.setAdapter(pager);
        count = pager.getCount();
        slide = new ImageView[count];

        for (int i = 0; i < count; i++) {
            slide[i] = new ImageView(this);
            slide[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);
            slider.addView(slide[i], params);

        }
        slide[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<count;i++){
                    slide[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactive_dot));
                }
                slide[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SlideTimerTask(), 2000,4000);

    }
    public class SlideTimerTask extends TimerTask{
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(viewPager.getCurrentItem() < pager.getCount()-1){
                        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                    }else{
                        viewPager.setCurrentItem(0);
                    }

                }
            });
        }
    }
}
