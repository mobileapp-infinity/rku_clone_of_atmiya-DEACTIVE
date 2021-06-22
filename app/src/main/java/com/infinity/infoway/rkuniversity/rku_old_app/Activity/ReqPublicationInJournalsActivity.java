package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomEditText;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetAcademicContributionYearPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewPublicationInJournalsPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.ApiConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;


public class ReqPublicationInJournalsActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act;
    RequestQueue queue;

    Spinner spAcademicYearPublicationInJournalsReq, spTypeofAuthorPublicationInJournalsReq,
            spLevelOfJournalPublicationInJournalsReq, spUGCCarePublicationInJournalsReq,
            spYearOfPublicationInJournalReq;

    CustomEditText etNosofAuthorsPublicationInJournalsReq, etTitleofResearchPaperPublicationInJournalsReq,
            etNameOfJournalsPublicationInJournalReq,
            etProceedingsISBNorISSNPublicationInJournalsReq;

    RelativeLayout rlUploadFilePublicationInJournalReq;

    CustomTextView tvChooseFilePublicationInJournalReq;

    ImageView imgUploadFilePublicationInJournalReq, imgClearPublicationInJournalReq, ivBack;

    LinearLayout ll_submit_cancel_publication_in_journal_req, ll_update_publication_in_journal_req;

    CustomBoldTextView tvSubmitPublicationInJournalReq, tvCancelPublicationInJournalReq,
            tvUpdatePublicationInJournalReq, tvCancelPublicationInJournalReqUpdate, tv_version;

    ArrayList<String> academicYearOpetion = new ArrayList<>();
    ArrayList<String> yearOfPublicationOpetion = new ArrayList<>();


    ArrayList<String> typesOfAuthorOpetion = new ArrayList<>();
    HashMap<Integer, String> hashMaptypesOfAuthor = new HashMap<>();

    ArrayList<String> levelOfJournalsOpetion = new ArrayList<>();
    HashMap<Integer, String> hashMaplevelOfJournals = new HashMap<>();

    ArrayList<String> ugcaAreOpetion = new ArrayList<>();
    HashMap<Integer, String> hashMapugcaAre = new HashMap<>();

    String base64File;
    int hasFile = 0;

    ReqViewPublicationInJournalsPojo.DataBean dateBean;

    HashMap<String, Integer> hashMapAcademicYearAndID = new HashMap<>();

    private ImageView iv_info;
    SimpleTooltip tooltip;

    private void showTooltip(View view) {

        tooltip = new SimpleTooltip.Builder(ReqPublicationInJournalsActivity.this)
                .anchorView(view)
                .gravity(Gravity.BOTTOM)
                .modal(true)
                .arrowColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .text(getString(R.string.app_name))
                .contentView(R.layout.tooltip_file_upload_size)
                .build();
        tooltip.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_publication_in_journals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getYearApiCall();
    }

    private boolean isValid() {

        if (academicYearOpetion.get(spAcademicYearPublicationInJournalsReq.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "Select Academic Year");
            return false;
        } else if (TextUtils.isEmpty(etNosofAuthorsPublicationInJournalsReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "Enter Nos. of Authors");
            return false;
        } else if (typesOfAuthorOpetion.get(spTypeofAuthorPublicationInJournalsReq.getSelectedItemPosition()).equalsIgnoreCase("Select type")) {
            DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "Select type of author");
            return false;
        } else if (TextUtils.isEmpty(etTitleofResearchPaperPublicationInJournalsReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "Enter Title of Reaserch Paper");
            return false;
        } else if (TextUtils.isEmpty(etNameOfJournalsPublicationInJournalReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "Enter Name of Journals");
            return false;
        } else if (yearOfPublicationOpetion.get(spYearOfPublicationInJournalReq.getSelectedItemPosition()).equalsIgnoreCase("Select Year of Publication")) {
            DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "Select Year of Publication");
            return false;
        } else if (TextUtils.isEmpty(etProceedingsISBNorISSNPublicationInJournalsReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "Enter Proceedings ISBN | ISSN");
            return false;
        } else if (levelOfJournalsOpetion.get(spLevelOfJournalPublicationInJournalsReq.getSelectedItemPosition()).equalsIgnoreCase("Select level of journal")) {
            DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "Select level of journal");
            return false;
        } else if (ugcaAreOpetion.get(spUGCCarePublicationInJournalsReq.getSelectedItemPosition()).equalsIgnoreCase("Select Category")) {
            DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "Select UGC Care");
            return false;
        } else if (!tvChooseFilePublicationInJournalReq.getText().toString().contains(".pdf")) {
            DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "Select File");
            return false;
        }

        return true;
    }

    private void initView() {
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Publication in Journals");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        iv_info = (ImageView) findViewById(R.id.iv_info);
        iv_info.setVisibility(View.VISIBLE);
        iv_info.setOnClickListener(this);

        queue = Volley.newRequestQueue(this);
        spAcademicYearPublicationInJournalsReq = findViewById(R.id.spAcademicYearPublicationInJournalsReq);
        spTypeofAuthorPublicationInJournalsReq = findViewById(R.id.spTypeofAuthorPublicationInJournalsReq);
        spLevelOfJournalPublicationInJournalsReq = findViewById(R.id.spLevelOfJournalPublicationInJournalsReq);
        spUGCCarePublicationInJournalsReq = findViewById(R.id.spUGCCarePublicationInJournalsReq);
        spYearOfPublicationInJournalReq = findViewById(R.id.spYearOfPublicationInJournalReq);

        academicYearOpetion.add("Select Year");

        yearOfPublicationOpetion.add("Select Year of publication");

        hashMaptypesOfAuthor.put(0, "Select type");
        hashMaptypesOfAuthor.put(1, "Primary");
        hashMaptypesOfAuthor.put(2, "Co-author");

        typesOfAuthorOpetion.add(hashMaptypesOfAuthor.get(0));
        typesOfAuthorOpetion.add(hashMaptypesOfAuthor.get(1));
        typesOfAuthorOpetion.add(hashMaptypesOfAuthor.get(2));


//        ArrayAdapter<String> levelOfAuthor = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, typesOfAuthorOpetion);
//        spTypeofAuthorPublicationInJournalsReq.setAdapter(levelOfAuthor); // this will set list of values to spinner

        SpinnerSimpleAdapter levelOfAuthor = new SpinnerSimpleAdapter(ReqPublicationInJournalsActivity.this, typesOfAuthorOpetion);
        spTypeofAuthorPublicationInJournalsReq.setAdapter(levelOfAuthor);

        spTypeofAuthorPublicationInJournalsReq.setSelection(typesOfAuthorOpetion.indexOf(0));


        hashMaplevelOfJournals.put(0, "Select level of journal");
        hashMaplevelOfJournals.put(1, "National");
        hashMaplevelOfJournals.put(2, "International");

        levelOfJournalsOpetion.add(hashMaplevelOfJournals.get(0));
        levelOfJournalsOpetion.add(hashMaplevelOfJournals.get(1));
        levelOfJournalsOpetion.add(hashMaplevelOfJournals.get(2));

//        ArrayAdapter<String> levelOfJournals = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, levelOfJournalsOpetion);
//        spLevelOfJournalPublicationInJournalsReq.setAdapter(levelOfJournals); // this will set list of values to spinner

        SpinnerSimpleAdapter levelOfJournals = new SpinnerSimpleAdapter(ReqPublicationInJournalsActivity.this, levelOfJournalsOpetion);
        spLevelOfJournalPublicationInJournalsReq.setAdapter(levelOfJournals);

        spLevelOfJournalPublicationInJournalsReq.setSelection(levelOfJournalsOpetion.indexOf(0));

        hashMapugcaAre.put(0, "Select Category");
        hashMapugcaAre.put(1, "A");
        hashMapugcaAre.put(2, "B");
        hashMapugcaAre.put(3, "C");
        hashMapugcaAre.put(4, "D");
        hashMapugcaAre.put(5, "None");

        ugcaAreOpetion.add(hashMapugcaAre.get(0));
        ugcaAreOpetion.add(hashMapugcaAre.get(1));
        ugcaAreOpetion.add(hashMapugcaAre.get(2));
        ugcaAreOpetion.add(hashMapugcaAre.get(3));
        ugcaAreOpetion.add(hashMapugcaAre.get(4));
        ugcaAreOpetion.add(hashMapugcaAre.get(5));


//        ArrayAdapter<String> levelUGCCare = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ugcaAreOpetion);
//        spUGCCarePublicationInJournalsReq.setAdapter(levelUGCCare); // this will set list of values to spinner

        SpinnerSimpleAdapter levelUGCCare = new SpinnerSimpleAdapter(ReqPublicationInJournalsActivity.this, ugcaAreOpetion);
        spUGCCarePublicationInJournalsReq.setAdapter(levelUGCCare);

        spUGCCarePublicationInJournalsReq.setSelection(ugcaAreOpetion.indexOf(0));

        etNosofAuthorsPublicationInJournalsReq = findViewById(R.id.etNosofAuthorsPublicationInJournalsReq);
        etTitleofResearchPaperPublicationInJournalsReq = findViewById(R.id.etTitleofResearchPaperPublicationInJournalsReq);
        etNameOfJournalsPublicationInJournalReq = findViewById(R.id.etNameOfJournalsPublicationInJournalReq);
//        etYearOfPublicationInJournalReq = findViewById(R.id.etYearOfPublicationInJournalReq);
        etProceedingsISBNorISSNPublicationInJournalsReq = findViewById(R.id.etProceedingsISBNorISSNPublicationInJournalsReq);

        rlUploadFilePublicationInJournalReq = findViewById(R.id.rlUploadFilePublicationInJournalReq);
        rlUploadFilePublicationInJournalReq.setOnClickListener(this);
        tvChooseFilePublicationInJournalReq = findViewById(R.id.tvChooseFilePublicationInJournalReq);
        tvChooseFilePublicationInJournalReq.setOnClickListener(this);

        imgUploadFilePublicationInJournalReq = findViewById(R.id.imgUploadFilePublicationInJournalReq);
        imgUploadFilePublicationInJournalReq.setOnClickListener(this);
        imgClearPublicationInJournalReq = findViewById(R.id.imgClearPublicationInJournalReq);
        imgClearPublicationInJournalReq.setOnClickListener(this);

        ll_submit_cancel_publication_in_journal_req = findViewById(R.id.ll_submit_cancel_publication_in_journal_req);
        ll_update_publication_in_journal_req = findViewById(R.id.ll_update_publication_in_journal_req);


        tvSubmitPublicationInJournalReq = findViewById(R.id.tvSubmitPublicationInJournalReq);
        tvSubmitPublicationInJournalReq.setOnClickListener(this);
        tvCancelPublicationInJournalReq = findViewById(R.id.tvCancelPublicationInJournalReq);
        tvCancelPublicationInJournalReq.setOnClickListener(this);
        tvUpdatePublicationInJournalReq = findViewById(R.id.tvUpdatePublicationInJournalReq);
        tvUpdatePublicationInJournalReq.setOnClickListener(this);
        tvCancelPublicationInJournalReqUpdate = findViewById(R.id.tvCancelPublicationInJournalReqUpdate);
        tvCancelPublicationInJournalReqUpdate.setOnClickListener(this);

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
        if (v.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (v.getId() == R.id.rlUploadFilePublicationInJournalReq ||
                v.getId() == R.id.tvChooseFilePublicationInJournalReq ||
                v.getId() == R.id.imgUploadFilePublicationInJournalReq) {

            new MaterialFilePicker()
                    .withActivity(this)
                    .withRequestCode(IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT)
                    .withFilter(Pattern.compile(".*\\.pdf$")) // Filtering files and directories by file name using regexp
                    .withFilterDirectories(false) // Set directories filterable (false by default)
                    .withHiddenFiles(true) // Show hidden files and folders
                    .start();

        } else if (v.getId() == R.id.imgClearPublicationInJournalReq) {
            tvChooseFilePublicationInJournalReq.setClickable(true);
            rlUploadFilePublicationInJournalReq.setClickable(true);
            tvChooseFilePublicationInJournalReq.setText("Choose File");
            imgClearPublicationInJournalReq.setVisibility(View.GONE);
            imgUploadFilePublicationInJournalReq.setVisibility(View.VISIBLE);
        } else if (v.getId() == R.id.tvSubmitPublicationInJournalReq) {
            if (isValid()) {

                String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";
                String year = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearPublicationInJournalsReq.getSelectedItemPosition())));
                String Author_no = etNosofAuthorsPublicationInJournalsReq.getText().toString();
                String Author_type = typesOfAuthorOpetion.get(spTypeofAuthorPublicationInJournalsReq.getSelectedItemPosition());
                String Research_paper = etTitleofResearchPaperPublicationInJournalsReq.getText().toString();
                String Journal_name = etNameOfJournalsPublicationInJournalReq.getText().toString();
                String Publication_year = yearOfPublicationOpetion.get(spYearOfPublicationInJournalReq.getSelectedItemPosition());
                String isbn_issn_number = etProceedingsISBNorISSNPublicationInJournalsReq.getText().toString();
                String journal_level = levelOfJournalsOpetion.get(spLevelOfJournalPublicationInJournalsReq.getSelectedItemPosition());
                String ugc_category = ugcaAreOpetion.get(spUGCCarePublicationInJournalsReq.getSelectedItemPosition());
                String file_name = base64File;

                if (!TextUtils.isEmpty(file_name)) {
                    callSavePublicationInJournalsApi(emp_id, user_id, ip, year, Author_no, Author_type, Research_paper, Journal_name,
                            Publication_year, isbn_issn_number, journal_level, ugc_category, file_name);
                } else {
                    DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "File Not Found");
                }
            }
        } else if (v.getId() == R.id.tvUpdatePublicationInJournalReq) {
            if (isValid()) {

                String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";
                String year = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearPublicationInJournalsReq.getSelectedItemPosition())));
                String Author_no = etNosofAuthorsPublicationInJournalsReq.getText().toString();
                String Author_type = typesOfAuthorOpetion.get(spTypeofAuthorPublicationInJournalsReq.getSelectedItemPosition());
                String Research_paper = etTitleofResearchPaperPublicationInJournalsReq.getText().toString();
                String Journal_name = etNameOfJournalsPublicationInJournalReq.getText().toString();
                String Publication_year = yearOfPublicationOpetion.get(spYearOfPublicationInJournalReq.getSelectedItemPosition());
                String isbn_issn_number = etProceedingsISBNorISSNPublicationInJournalsReq.getText().toString();
                String journal_level = levelOfJournalsOpetion.get(spLevelOfJournalPublicationInJournalsReq.getSelectedItemPosition());
                String ugc_category = ugcaAreOpetion.get(spUGCCarePublicationInJournalsReq.getSelectedItemPosition());
                String filename = base64File;

                if (hasFile == 0) {
                    if (dateBean != null) {
                        callUpdatePublicationInJournalsApi(String.valueOf(dateBean.getId()), emp_id, user_id, ip,
                                year, Author_no, Author_type,
                                Research_paper, Journal_name, Publication_year, isbn_issn_number,
                                journal_level, ugc_category, "", String.valueOf(hasFile));
                    }

                } else {
                    if (!TextUtils.isEmpty(base64File)) {
                        callUpdatePublicationInJournalsApi(String.valueOf(dateBean.getId()), emp_id, user_id, ip,
                                year, Author_no, Author_type,
                                Research_paper, Journal_name, Publication_year, isbn_issn_number,
                                journal_level, ugc_category, filename, String.valueOf(hasFile));
                    } else {
                        DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "Select File");
                    }
                }
            }
        } else if (v.getId() == R.id.tvCancelPublicationInJournalReq || v.getId() == R.id.tvCancelPublicationInJournalReqUpdate) {
            onBackPressed();
        } else if (v.getId() == R.id.iv_info) {
            showTooltip(v);
        }
    }

    private void getYearApiCall() {
        DialogUtils.showProgressDialog(ReqPublicationInJournalsActivity.this, "");
        String url = URLS.Get_YEAR;

        StringRequest requestGetYear = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        if (jsonArray != null && jsonArray.length() > 0) {

                            for (int i = 0; i < jsonArray.length(); i++) {
                                hashMapAcademicYearAndID.put(jsonArray.getJSONObject(i).getString(ApiConstants.YEAR_NAME),
                                        jsonArray.getJSONObject(i).getInt(ApiConstants.YEAR_ID));
                                academicYearOpetion.add(jsonArray.getJSONObject(i).getString(ApiConstants.YEAR_NAME));
                            }
                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(ReqPublicationInJournalsActivity.this, academicYearOpetion);
                            spAcademicYearPublicationInJournalsReq.setAdapter(academicYearAdapter);

                            callAcademicContributionAPI();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }


    private void callAcademicContributionAPI() {

//        DialogUtils.showProgressDialog(ReqFDPAttendedActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.GET, URLS.Get_Academic_Contributions_year_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        GetAcademicContributionYearPojo getAcademicContributionYearPojo = gson.fromJson("{\"Data\":" + response + "}", GetAcademicContributionYearPojo.class);

                        if (getAcademicContributionYearPojo != null && getAcademicContributionYearPojo.getData().size() > 0) {

                            for (int i = 0 ; i < getAcademicContributionYearPojo.getData().size() ; i++){
                                yearOfPublicationOpetion.add(String.valueOf(getAcademicContributionYearPojo.getData().get(i).getYear()));
                            }

                            SpinnerSimpleAdapter yearOfPublicationOpetionAdapter = new SpinnerSimpleAdapter(ReqPublicationInJournalsActivity.this, yearOfPublicationOpetion);
                            spYearOfPublicationInJournalReq.setAdapter(yearOfPublicationOpetionAdapter);

                            if (getIntent().hasExtra(IntentConstants.REQ_VIEW_PUBLICATION_IN_JOURNALS_POJO)) {
                                ll_submit_cancel_publication_in_journal_req.setVisibility(View.GONE);
                                ll_update_publication_in_journal_req.setVisibility(View.VISIBLE);

                                dateBean = (ReqViewPublicationInJournalsPojo.DataBean) getIntent().getSerializableExtra(IntentConstants.REQ_VIEW_PUBLICATION_IN_JOURNALS_POJO);

                                spAcademicYearPublicationInJournalsReq.setSelection(academicYearOpetion.indexOf(dateBean.getYear_name()));
                                etNosofAuthorsPublicationInJournalsReq.setText(String.valueOf(dateBean.getIpj_author_no()));
                                spTypeofAuthorPublicationInJournalsReq.setSelection(typesOfAuthorOpetion.indexOf(dateBean.getIpj_author_type()));
                                etTitleofResearchPaperPublicationInJournalsReq.setText(dateBean.getIpj_research_paper());
                                etNameOfJournalsPublicationInJournalReq.setText(dateBean.getIpj_journal_name());
                                spYearOfPublicationInJournalReq.setSelection(yearOfPublicationOpetion.indexOf(String.valueOf(dateBean.getPublication_year())));
                                etProceedingsISBNorISSNPublicationInJournalsReq.setText(String.valueOf(dateBean.getIpj_isbn_issn_number()));
                                spLevelOfJournalPublicationInJournalsReq.setSelection(levelOfJournalsOpetion.indexOf(dateBean.getIpj_Journal_level()));
                                spUGCCarePublicationInJournalsReq.setSelection(ugcaAreOpetion.indexOf(dateBean.getUGC_Category()));
                                rlUploadFilePublicationInJournalReq.setClickable(true);
                                imgUploadFilePublicationInJournalReq.setVisibility(View.GONE);
                                imgClearPublicationInJournalReq.setVisibility(View.VISIBLE);
                                tvChooseFilePublicationInJournalReq.setText(dateBean.getIpj_file_upload());

                            } else {
                                spAcademicYearPublicationInJournalsReq.setSelection(academicYearOpetion.indexOf(0));
                            }


                        }else {
                            Toast.makeText(ReqPublicationInJournalsActivity.this, "Academic Contribution opetion null or empty", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(ReqPublicationInJournalsActivity.this, "Exception:- "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ReqPublicationInJournalsActivity.this, "Response Empty or null", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        savePublicationInConferenceRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(savePublicationInConferenceRequest);

    }


    private void callSavePublicationInJournalsApi(final String emp_id, final String user_id, final String ip,
                                                  final String year, final String Author_no, final String Author_type,
                                                  final String Research_Paper, final String Journal_Name, final String publication_year, final String isbn_issn_number,
                                                  final String Journal_Level, final String UGC_Category, final String Filename) {

        DialogUtils.showProgressDialog(ReqPublicationInJournalsActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Save_Publication_in_Journals, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    //Toast.makeText(ReqPublicationInJournalsActivity.this, response, Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, responsePojo.getData().get(0).getMsg());
                    Intent intent = new Intent(ReqPublicationInJournalsActivity.this, ReqPublicationInJournalsListActivity.class);
                    setResult(RESULT_OK, intent);
                    finish();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("emp_id", emp_id);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                params2.put("year", year);
                params2.put("Author_no", Author_no);
                params2.put("Author_type", Author_type);
                params2.put("Research_Paper", Research_Paper);
                params2.put("Journal_Name", Journal_Name);
                params2.put("publication_year", publication_year);
                params2.put("isbn_issn_number", isbn_issn_number);
                params2.put("Journal_Level", Journal_Level);
                params2.put("UGC_Category", UGC_Category);
                params2.put("Filename", Filename);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        savePublicationInConferenceRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(savePublicationInConferenceRequest);
    }


    private void callUpdatePublicationInJournalsApi(final String id, final String emp_id, final String user_id, final String ip,
                                                    final String year, final String Author_no, final String Author_type,
                                                    final String Research_Paper, final String Journal_Name, final String publication_year, final String isbn_issn_number,
                                                    final String Journal_Level, final String UGC_Category, final String Filename, final String hasfile) {

        DialogUtils.showProgressDialog(ReqPublicationInJournalsActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Update_Publication_in_Journals, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    // Toast.makeText(ReqPublicationInJournalsActivity.this, response, Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, responsePojo.getData().get(0).getMsg());
                    Intent intent = new Intent(ReqPublicationInJournalsActivity.this, ReqPublicationInJournalsListActivity.class);
                    setResult(RESULT_OK, intent);
                    finish();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", id);
                params2.put("emp_id", emp_id);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                params2.put("year", year);
                params2.put("Author_no", Author_no);
                params2.put("Author_type", Author_type);
                params2.put("Research_Paper", Research_Paper);
                params2.put("Journal_Name", Journal_Name);
                params2.put("publication_year", publication_year);
                params2.put("isbn_issn_number", isbn_issn_number);
                params2.put("Journal_Level", Journal_Level);
                params2.put("UGC_Category", UGC_Category);
                params2.put("Filename", Filename);
                params2.put("hasfile", hasfile);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        savePublicationInConferenceRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(savePublicationInConferenceRequest);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT && resultCode == RESULT_OK) {
            try {
                // String filePath = intent.getData().getPath();
                //  String filename = filePath.substring(filePath.lastIndexOf("/") + 1);
                //String selectedFilePath = FileUtils.getPath(this, uri);
                String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                final File file = new File(filePath);

                if (file != null && file.length() > 0) {

                    if (file.length() > 2000000) { //2000000bytes == 2MB
                        Toast.makeText(this, "File length must be less than 2mb", Toast.LENGTH_SHORT).show();
                    } else {
                        hasFile = 1;
                        tvChooseFilePublicationInJournalReq.setClickable(false);
                        rlUploadFilePublicationInJournalReq.setClickable(false);
                        imgUploadFilePublicationInJournalReq.setVisibility(View.GONE);
                        imgClearPublicationInJournalReq.setVisibility(View.VISIBLE);

                        byte[] buffer = new byte[(int) file.length() + 100];
                        int length = new FileInputStream(file).read(buffer);
                        base64File = Base64.encodeToString(buffer, 0, length,
                                Base64.NO_WRAP);
                        tvChooseFilePublicationInJournalReq.setText(file.getName());
                    }
                } else {
                    DialogUtils.Show_Toast(ReqPublicationInJournalsActivity.this, "File Not Found");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
