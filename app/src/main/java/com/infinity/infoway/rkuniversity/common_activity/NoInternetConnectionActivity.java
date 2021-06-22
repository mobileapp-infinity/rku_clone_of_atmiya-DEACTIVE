package com.infinity.infoway.rkuniversity.common_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;

public class NoInternetConnectionActivity extends AppCompatActivity implements View.OnClickListener {

    ConnectionDetector connectionDetector;
    TextViewRegularFont btnTryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet_connection);
        initView();
    }

    private void initView() {
        connectionDetector = new ConnectionDetector(NoInternetConnectionActivity.this);
        btnTryAgain = findViewById(R.id.btnTryAgain);
        btnTryAgain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnTryAgain) {
            if (!connectionDetector.isConnectingToInternet()) {
                Toast.makeText(this, "No internet connection!", Toast.LENGTH_SHORT).show();
            } else {
                setResult(RESULT_OK);
                finish();
            }
        }
    }
}