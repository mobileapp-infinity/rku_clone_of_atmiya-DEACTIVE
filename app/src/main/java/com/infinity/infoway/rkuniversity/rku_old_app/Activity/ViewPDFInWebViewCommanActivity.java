package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

public class ViewPDFInWebViewCommanActivity extends AppCompatActivity implements View.OnClickListener{

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act,tv_version;
    ImageView ivBack;
    RequestQueue queue;

//    PDFView idPDF;

//    PDFView pdfView;

    WebView reqPublicationInConferenceWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_publication_in_conference_web_view);
        initView();

        if (getIntent().hasExtra(IntentConstants.VIEW_PDF_COMMAN)){

            String url = getIntent().getStringExtra(IntentConstants.VIEW_PDF_COMMAN);

            if (!TextUtils.isEmpty(url)){

                try {
                    DialogUtils.showProgressDialog(this,"");


                    reqPublicationInConferenceWebView.setWebViewClient(new WebViewClient(){

                        @Override
                        public void onPageFinished(WebView view, String url) {
                            reqPublicationInConferenceWebView.loadUrl("javascript:(function() { " +
                                    "document.querySelector('[role=\"toolbar\"]').remove();})()");
                            DialogUtils.hideProgressDialog();
                        }
                    });
                    //https://docs.google.com/viewerng/viewer?embedded=true&url=
                    reqPublicationInConferenceWebView.loadUrl("https://docs.google.com/gview?embedded=true&url="+url);

//                    Uri myUri = Uri.parse(url);

//                    idPDF.fromFile(url);

//                    pdfView.fromUri(myUri);
                }
                catch (Exception e) {
                    Toast.makeText(this, "Exception:- "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

              //  reqPublicationInConferenceWebView.loadUrl(url);
            }else {
                Toast.makeText(this, "URL Null or Empty", Toast.LENGTH_SHORT).show();
            }

        }


    }

    private void initView(){

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("View PDF");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

//        idPDF = findViewById(R.id.idPDF);

//        pdfView = findViewById(R.id.pdfView);

        reqPublicationInConferenceWebView = findViewById(R.id.reqPublicationInConferenceWebView);

        reqPublicationInConferenceWebView.getSettings().setJavaScriptEnabled(true);
        reqPublicationInConferenceWebView.getSettings().setBuiltInZoomControls(true);
        reqPublicationInConferenceWebView.getSettings().setDisplayZoomControls(false);
        reqPublicationInConferenceWebView.setWebChromeClient(new WebChromeClient());

//
//        reqPublicationInConferenceWebView.setInitialScale(180);
//
//        reqPublicationInConferenceWebView.getSettings().setJavaScriptEnabled(true);
//        reqPublicationInConferenceWebView.getSettings().setDomStorageEnabled(true);
//        // wvprivacy.getSettings().setLoadWithOverviewMode(true);
//        reqPublicationInConferenceWebView.getSettings().setBuiltInZoomControls(true);
//        reqPublicationInConferenceWebView.getSettings().setUseWideViewPort(true);
//        reqPublicationInConferenceWebView.clearCache(true);

        PackageInfo pInfo = null;
        assert pInfo != null;

        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        tv_version = (CustomBoldTextView) findViewById(R.id.tv_version);
        tv_version.setText(pInfo.versionName);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back){
            onBackPressed();
        }
    }

}
