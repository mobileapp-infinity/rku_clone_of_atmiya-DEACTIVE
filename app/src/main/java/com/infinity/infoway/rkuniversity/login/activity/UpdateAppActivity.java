package com.infinity.infoway.rkuniversity.login.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;


public class UpdateAppActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseUpdateApp;
    AppCompatImageView imgUpdateAppGif;
    //    WebView wvUpdateAppGif;
    LinearLayout llUpdateApp;
    ExtendedFloatingActionButton btnUpdateApp;
    TextViewRegularFont btnNotNow;

    private String APP_UPDATE_TYPE = "";
    private boolean IS_FORCE_UPDATE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_app);
        initView();

        if (getIntent().hasExtra(IntentConstants.APP_UPDATE_TYPE)) {
            APP_UPDATE_TYPE = getIntent().getStringExtra(IntentConstants.APP_UPDATE_TYPE);
            IS_FORCE_UPDATE = getIntent().getBooleanExtra(IntentConstants.IS_FORCE_UPDATE, false);

            if (IS_FORCE_UPDATE && APP_UPDATE_TYPE.equalsIgnoreCase(IntentConstants.FORCE_UPDATE)) {
                btnNotNow.setVisibility(View.GONE);
            }
        }


        try {
            Glide.with(this).load(R.raw.update_app_gif).error(R.drawable.ic_no_data_found).into(imgUpdateAppGif);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(UpdateAppActivity.this);
        connectionDetector = new ConnectionDetector(UpdateAppActivity.this);
        ivCloseUpdateApp = findViewById(R.id.ivCloseUpdateApp);
        ivCloseUpdateApp.setOnClickListener(this);
//        wvUpdateAppGif = findViewById(R.id.wvUpdateAppGif);
        imgUpdateAppGif = findViewById(R.id.imgUpdateAppGif);
        llUpdateApp = findViewById(R.id.llUpdateApp);
        llUpdateApp.startAnimation(AnimationUtils.loadAnimation(UpdateAppActivity.this, R.anim.slide_up));
        btnUpdateApp = findViewById(R.id.btnUpdateApp);
        btnUpdateApp.setOnClickListener(this);
        btnNotNow = findViewById(R.id.btnNotNow);
        btnNotNow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseUpdateApp) {
            onBackPressed();
        } else if (v.getId() == R.id.btnUpdateApp) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                finish();
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                finish();
            }
        } else if (v.getId() == R.id.btnNotNow) {
            redirectToLoginActivity();
        }
    }

    @Override
    public void onBackPressed() {
        if (IS_FORCE_UPDATE && APP_UPDATE_TYPE.equalsIgnoreCase(IntentConstants.FORCE_UPDATE)) {
            finish();
        } else {
            redirectToLoginActivity();
        }
    }


    private void redirectToLoginActivity() {
        Intent openMainActivity = new Intent(UpdateAppActivity.this, LoginActivity.class);
        startActivity(openMainActivity);
        finish();
    }

}