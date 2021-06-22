package com.infinity.infoway.rkuniversity.student.e_learning.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.student.e_learning.adapter.ELearningViewPagerAdapter;
import com.infinity.infoway.rkuniversity.student.e_learning.fragment.EnrollToGroupFragment;
import com.infinity.infoway.rkuniversity.student.e_learning.fragment.JoinGroupFragment;

public class ELearningActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView ivBackELearning;
    TabLayout tlELearning;
    ViewPager vpELearning;
    JoinGroupFragment joinGroupFragment;
    EnrollToGroupFragment enrollToGroupFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_learning);
        initView();
    }


    private void initView() {

        ivBackELearning = findViewById(R.id.ivBackELearning);
        ivBackELearning.setOnClickListener(this);

        tlELearning = findViewById(R.id.tlELearning);
        vpELearning = findViewById(R.id.vpELearning);
        tlELearning.setupWithViewPager(vpELearning);

        joinGroupFragment = JoinGroupFragment.newInstance();
        enrollToGroupFragment = enrollToGroupFragment.newInstance();
        setupViewPager(vpELearning);
    }

    private void setupViewPager(ViewPager viewPager) {
        ELearningViewPagerAdapter eLearningViewPagerAdapter = new ELearningViewPagerAdapter(getSupportFragmentManager());
        eLearningViewPagerAdapter.addFragment(joinGroupFragment, "JOIN GROUP");
        eLearningViewPagerAdapter.addFragment(enrollToGroupFragment, "ENROLL TO GROUP");
        viewPager.setAdapter(eLearningViewPagerAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivBackELearning) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}