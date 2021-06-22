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
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

public class AxisAndHdfcPaymentWebViewActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    LinearLayout llAxisHdfcPaymentProgressbar;
    AppCompatImageView ivCloseAxisHdfcPayment;
    WebView webView;
    TextViewRegularFont tvMsg;
    String url = "", status = "";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_axis_and_hdfc_payment_web_view);
        initView();

        tvMsg = findViewById(R.id.tvMsg);
        url = getIntent().getStringExtra("url" + "");
        status = getIntent().getStringExtra("res_status" + "");
        setUpWebView();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(AxisAndHdfcPaymentWebViewActivity.this);
        connectionDetector = new ConnectionDetector(AxisAndHdfcPaymentWebViewActivity.this);
        llAxisHdfcPaymentProgressbar = findViewById(R.id.llAxisHdfcPaymentProgressbar);
        ivCloseAxisHdfcPayment = findViewById(R.id.ivCloseAxisHdfcPayment);
        ivCloseAxisHdfcPayment.setOnClickListener(this);
        webView = findViewById(R.id.webView);
        tvMsg = findViewById(R.id.tvMsg);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.clearCache(true);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivCloseAxisHdfcPayment) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void setUpWebView() {
        if (status.contentEquals("1")) {


            llAxisHdfcPaymentProgressbar.setVisibility(View.GONE);
            webView.setVisibility(View.GONE);
            tvMsg.setText(url + "");


        } else if (status.contentEquals("0")) {
            llAxisHdfcPaymentProgressbar.setVisibility(View.VISIBLE);
            webView.setVisibility(View.VISIBLE);

        }

        webView.loadUrl(url);


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (URLUtil.isNetworkUrl(url)) {
                    return false;
                }
                if (appInstalledOrNot(url)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } else {
                    // do something if app is not installed
                }
                return true;
            }

        });


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                llAxisHdfcPaymentProgressbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                llAxisHdfcPaymentProgressbar.setVisibility(View.GONE);
//                wvprivacy.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
//                wvprivacy.setInitialScale(2);
//                wvprivacy.getSettings().getDefaultFontSize();

                System.out.println("font size :::::: " + webView.getSettings().getDefaultFontSize());
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.i("WEB_VIEW_TEST", "error code:" + errorCode);
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });


        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                super.onProgressChanged(view, progress);
            }
        });
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