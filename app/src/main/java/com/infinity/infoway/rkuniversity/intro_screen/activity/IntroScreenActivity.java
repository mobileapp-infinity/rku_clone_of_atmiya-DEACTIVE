package com.infinity.infoway.rkuniversity.intro_screen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.intro_screen.adapter.IntroScreenViewpagerAdapter;
import com.infinity.infoway.rkuniversity.intro_screen.fragments.FirstIntroFragment;
import com.infinity.infoway.rkuniversity.intro_screen.fragments.SecondeIntroFragment;
import com.infinity.infoway.rkuniversity.intro_screen.fragments.ThirdIntroFragment;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.Timer;
import java.util.TimerTask;

public class IntroScreenActivity extends AppCompatActivity {

    ViewPager vpIntroScreen;
    SpringDotsIndicator dotsIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        initView();
    }

    private void initView() {
        dotsIndicator = findViewById(R.id.dots_indicator);
        vpIntroScreen = findViewById(R.id.vpIntroScreen);

        IntroScreenViewpagerAdapter adapter = new IntroScreenViewpagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstIntroFragment(), "");
        adapter.addFragment(new SecondeIntroFragment(), "");
        adapter.addFragment(new ThirdIntroFragment(), "");

        vpIntroScreen.setAdapter(adapter);
        dotsIndicator.setViewPager(vpIntroScreen);

        pageSwitcher(1);

    }

    Timer timer;
    int page = 0;

    public void pageSwitcher(int seconds) {
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 400, 800); // delay
        // in
        // milliseconds
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {

            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            runOnUiThread(new Runnable() {
                public void run() {

                    if (page > 2) { // In my case the number of pages are 5
                        page = 0;
//                        timer.cancel();
//                        // Showing a toast for just testing purpose
//                        Toast.makeText(getApplicationContext(), "Timer stoped",
//                                Toast.LENGTH_LONG).show();
                    } else {
                        vpIntroScreen.setCurrentItem(page++);
                    }
                }
            });

        }
    }


}