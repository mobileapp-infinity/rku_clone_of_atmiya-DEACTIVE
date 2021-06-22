package com.infinity.infoway.rkuniversity.student.fee_details.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

public class PaytmPaymentActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    LinearLayout llPaytmPaymentProgressbar;
    AppCompatImageView ivClosePaytmPayment;
    WebView webViewPaytm;
//    TextViewRegularFont tvMsg;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paytm_payment);
        initView();
        url = getIntent().getStringExtra("url");
        setUpWebView();
    }

    private void setUpWebView() {
        webViewPaytm.loadUrl(url);
        System.out.println("url response::::::::::::::   " + url + "");


        webViewPaytm.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                if (URLUtil.isNetworkUrl(url))
                {
                    return false;
                }
                if (appInstalledOrNot(url))
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
                else
                {
                    // do something if app is not installed
                }
                return true;
            }

        });


        webViewPaytm.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                llPaytmPaymentProgressbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                llPaytmPaymentProgressbar.setVisibility(View.GONE);
//                wvprivacy.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
//                wvprivacy.setInitialScale(2);
//                wvprivacy.getSettings().getDefaultFontSize();

                System.out.println("font size :::::: "+ webViewPaytm.getSettings().getDefaultFontSize());
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.i("WEB_VIEW_TEST", "error code:" + errorCode);
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });


        webViewPaytm.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                super.onProgressChanged(view, progress);
            }
        });
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(PaytmPaymentActivity.this);
        connectionDetector = new ConnectionDetector(PaytmPaymentActivity.this);
        llPaytmPaymentProgressbar = findViewById(R.id.llPaytmPaymentProgressbar);
        ivClosePaytmPayment = findViewById(R.id.ivClosePaytmPayment);
        ivClosePaytmPayment.setOnClickListener(this);
        webViewPaytm = findViewById(R.id.webViewPaytm);
//        tvMsg = findViewById(R.id.tvMsg);

        webViewPaytm.getSettings().setJavaScriptEnabled(true);
        webViewPaytm.getSettings().setLoadWithOverviewMode(true);

        webViewPaytm.getSettings().setDomStorageEnabled(true);
        webViewPaytm.getSettings().setBuiltInZoomControls(true);
        webViewPaytm.getSettings().setUseWideViewPort(true);
        webViewPaytm.clearCache(true);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivClosePaytmPayment) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }

}