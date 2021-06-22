package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
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
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomEditText;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ViewPublicationInConferencePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.ApiConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.nbsp.materialfilepicker.MaterialFilePicker;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;


/*
 * created by remish varsnai to display publication in conferences on 16-30-2020
 * */

public class PublicationInConferencesActivity extends AppCompatActivity implements View.OnClickListener {

    CustomEditText etTitleOfResearchPaper, etNameOfTheConference, etDateOfPublication, etNameOfThePublisher, etTitleOfProceedings, etProceedingsISBNorISSN;
    Spinner spAcademicYear, spLevelOfconference;
    CustomBoldTextView tvSubmitPublicationInConferences, tvCancelPublicationInConferences,tvUpdatePublicationInConferences,tvCancelPublicationInConferencesUpdate,tv_version;
    ImageView ivBack, imvCalenderDateOfPublication;
    ArrayList<String> academicYearOpetion = new ArrayList<>();
    ArrayList<String> levelOfConferenceOpetion = new ArrayList<>();
    RequestQueue queue;
    Calendar myCalendar;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act;
    HashMap<Integer, String> hashMapLevelOfConference = new HashMap<>();
    CustomTextView tvChooseFile;
    RelativeLayout rlUploadFile;
    ImageView imgUploadFile,imgClear;
    String base64StringFile;
    String emp_id,user_id,ip,Year,Research_Paper,Proceedings,Conference_Name,Conference_Level,Publication_Date,Proceedings_ISBN_ISSN,Publisher_Name,File_Name;
    LinearLayout ll_update_publication_cancel,ll_submit_cancel;
    ViewPublicationInConferencePojo.DataBean dataBeanBean;
    private int hasfile = 0;

    HashMap<String, Integer> hashMapAcademicYearAndID = new HashMap<>();

    private ImageView iv_info;
    SimpleTooltip tooltip;
    private void showTooltip(View view) {

        tooltip = new SimpleTooltip.Builder(PublicationInConferencesActivity.this)
                .anchorView(view)
                .gravity(Gravity.BOTTOM)
                .modal(true)
                .arrowColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .text(getString(R.string.app_name))
                .contentView(R.layout.tooltip_file_upload_size)
                .build();
        tooltip.show();
    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String currentDate = simpleDateFormat.format(new Date());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_in_conferences);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        etDateOfPublication.setText(currentDate);

        getYearApiCall();

    }


    private boolean isValide() {

        if (academicYearOpetion.get(spAcademicYear.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(PublicationInConferencesActivity.this, "Select Academic Year");
            return false;
        } else if (TextUtils.isEmpty(etTitleOfResearchPaper.getText().toString())) {
            DialogUtils.Show_Toast(PublicationInConferencesActivity.this, "Enter Title of Research Paper");
            return false;
        } else if (TextUtils.isEmpty(etTitleOfProceedings.getText().toString())) {
            DialogUtils.Show_Toast(PublicationInConferencesActivity.this, "Enter Title of Proceedings");
            return false;
        } else if (TextUtils.isEmpty(etNameOfTheConference.getText().toString())) {
            DialogUtils.Show_Toast(PublicationInConferencesActivity.this, "Enter Name of the conference");
            return false;
        }else if (levelOfConferenceOpetion.get(spLevelOfconference.getSelectedItemPosition()).equalsIgnoreCase("Select Level of Conference")) {
            DialogUtils.Show_Toast(PublicationInConferencesActivity.this, "Select Level of Conference");
            return false;
        } else if (TextUtils.isEmpty(etDateOfPublication.getText().toString())) {
            DialogUtils.Show_Toast(PublicationInConferencesActivity.this, "Enter Date of Publication");
            return false;
        }else if (TextUtils.isEmpty(etProceedingsISBNorISSN.getText().toString())) {
            DialogUtils.Show_Toast(PublicationInConferencesActivity.this, "Enter Proceedings ISBN | ISSN");
            return false;
        }
        else if (TextUtils.isEmpty(etNameOfThePublisher.getText().toString())) {
            DialogUtils.Show_Toast(PublicationInConferencesActivity.this, "Enter Name of the publisher");
            return false;
        }  else if (!tvChooseFile.getText().toString().contains(".pdf")) {
            DialogUtils.Show_Toast(PublicationInConferencesActivity.this, "Select File");
            return false;
        }

        return true;
    }

    private void initView() {

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Publication in Conferences");

        tvChooseFile = findViewById(R.id.tvChooseFile);
        tvChooseFile.setOnClickListener(this);

        imgUploadFile = findViewById(R.id.imgUploadFile);
        imgClear = findViewById(R.id.imgClear);
        imgClear.setOnClickListener(this);

        myCalendar = Calendar.getInstance();

        queue = Volley.newRequestQueue(this);

        iv_info = (ImageView) findViewById(R.id.iv_info);
        iv_info.setOnClickListener(this);
        iv_info.setVisibility(View.VISIBLE);


        rlUploadFile = findViewById(R.id.rlUploadFile);
        rlUploadFile.setOnClickListener(this);


        etTitleOfResearchPaper = findViewById(R.id.etTitleOfResearchPaper);
        etNameOfTheConference = findViewById(R.id.etNameOfTheConference);
        etDateOfPublication = findViewById(R.id.etDateOfPublication);
        etNameOfThePublisher = findViewById(R.id.etNameOfThePublisher);
        etTitleOfProceedings = findViewById(R.id.etTitleOfProceedings);
        etProceedingsISBNorISSN = findViewById(R.id.etProceedingsISBNorISSN);


        spAcademicYear = findViewById(R.id.spAcademicYear);
        spLevelOfconference = findViewById(R.id.spLevelOfConference);

        tvSubmitPublicationInConferences = findViewById(R.id.tvSubmitPublicationInConferences);
        tvSubmitPublicationInConferences.setOnClickListener(this);
        tvCancelPublicationInConferences = findViewById(R.id.tvCancelPublicationInConferences);
        tvCancelPublicationInConferences.setOnClickListener(this);
        tvUpdatePublicationInConferences = findViewById(R.id.tvUpdatePublicationInConferences);
        tvUpdatePublicationInConferences.setOnClickListener(this);
        tvCancelPublicationInConferencesUpdate = findViewById(R.id.tvCancelPublicationInConferencesUpdate);
        tvCancelPublicationInConferencesUpdate.setOnClickListener(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        imvCalenderDateOfPublication = findViewById(R.id.imvCalenderDateOfPublication);
        imvCalenderDateOfPublication.setOnClickListener(this);

        academicYearOpetion.add("Select Year");

        hashMapLevelOfConference.put(0, "Select Level of Conference");
        hashMapLevelOfConference.put(1, "National");
        hashMapLevelOfConference.put(2, "International");

        levelOfConferenceOpetion.add(hashMapLevelOfConference.get(0));
        levelOfConferenceOpetion.add(hashMapLevelOfConference.get(1));
        levelOfConferenceOpetion.add(hashMapLevelOfConference.get(2));

        ll_update_publication_cancel = findViewById(R.id.ll_update_publication_cancel);
        ll_submit_cancel = findViewById(R.id.ll_submit_cancel);

//        ArrayAdapter<String> levelOfConferenceAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, levelOfConferenceOpetion);
//        spLevelOfconference.setAdapter(levelOfConferenceAdapter); // this will set list of values to spinner

        SpinnerSimpleAdapter spinnerSimpleAdapter = new SpinnerSimpleAdapter(PublicationInConferencesActivity.this, levelOfConferenceOpetion);

        spLevelOfconference.setAdapter(spinnerSimpleAdapter);

        spLevelOfconference.setSelection(levelOfConferenceOpetion.indexOf(0));

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
    public void onClick(View view) {
        if (view.getId() == R.id.tvSubmitPublicationInConferences) {
            if (isValide()) {

                emp_id = mySharedPreferecesRKUOLD.getEmpID();
                user_id = mySharedPreferecesRKUOLD.getUserID();
                ip = "0";
                Year = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYear.getSelectedItemPosition())));
                Research_Paper = etTitleOfResearchPaper.getText().toString();
                Proceedings = etTitleOfProceedings.getText().toString();
                Conference_Name = etNameOfTheConference.getText().toString();
                Conference_Level = levelOfConferenceOpetion.get(spLevelOfconference.getSelectedItemPosition());
                Publication_Date = etDateOfPublication.getText().toString();
                Proceedings_ISBN_ISSN = etProceedingsISBNorISSN.getText().toString();
                Publisher_Name = etNameOfThePublisher.getText().toString();
                if (!TextUtils.isEmpty(base64StringFile)){
                    File_Name = base64StringFile;
                    callSavePublicationInConferenceApi(emp_id,user_id,ip,Year,Research_Paper,Proceedings,Conference_Name,Conference_Level,Publication_Date,Proceedings_ISBN_ISSN,
                            Publisher_Name,File_Name);
                }else {
                    DialogUtils.Show_Toast(PublicationInConferencesActivity.this, "Select File");
                }
            }
        } else if (view.getId() == R.id.tvCancelPublicationInConferences) {
            onBackPressed();
        } else if (view.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (view.getId() == R.id.imvCalenderDateOfPublication) {

            final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    String myFormat1 = "dd/MM/yyyy";
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat1, Locale.US);
                    etDateOfPublication.setText(sdf.format(myCalendar.getTime()) + "");

                }
            };

            DatePickerDialog datePickerDialog = new DatePickerDialog(PublicationInConferencesActivity.this, date1, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();

        } else if (view.getId() == R.id.rlUploadFile || view.getId() == R.id.tvChooseFile) {

//            UploadDocumentUtility.uploadDocument(this,"application/pdf","Select PDF",IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT);


            new MaterialFilePicker()
                    .withActivity(this)
                    .withRequestCode(IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT)
                    .withFilter(Pattern.compile(".*\\.pdf$")) // Filtering files and directories by file name using regexp
                    .withFilterDirectories(false) // Set directories filterable (false by default)
                    .withHiddenFiles(true) // Show hidden files and folders
                    .start();

//            Intent intent = new Intent(this, FilePickerActivity.class);
//            intent.putExtra(FilePickerActivity.ARG_FILTER, Pattern.compile(".*\\.pdf$"));
//            intent.putExtra(FilePickerActivity., true);
//            intent.putExtra(FilePickerActivity.ARG, true);
//            startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT);

        }else if (view.getId() == R.id.imgClear){
            tvChooseFile.setClickable(true);
            rlUploadFile.setClickable(true);
            tvChooseFile.setText("Choose File");
            imgClear.setVisibility(View.GONE);
            imgUploadFile.setVisibility(View.VISIBLE);
        }else if (view.getId() == R.id.tvUpdatePublicationInConferences){

            if (isValide()){
                emp_id = mySharedPreferecesRKUOLD.getEmpID();
                user_id = mySharedPreferecesRKUOLD.getUserID();
                ip = "0";
                Year = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYear.getSelectedItemPosition())));
                Research_Paper = etTitleOfResearchPaper.getText().toString();
                Proceedings = etTitleOfProceedings.getText().toString();
                Conference_Name = etNameOfTheConference.getText().toString();
                Conference_Level = levelOfConferenceOpetion.get(spLevelOfconference.getSelectedItemPosition());
                Publication_Date = etDateOfPublication.getText().toString();
                Proceedings_ISBN_ISSN = etProceedingsISBNorISSN.getText().toString();
                Publisher_Name = etNameOfThePublisher.getText().toString();

                if (hasfile == 0){
                    updatePublicationInConference(String.valueOf(hasfile),String.valueOf(dataBeanBean.getId()),emp_id,user_id,ip,Year,Research_Paper,Proceedings,Conference_Name,Conference_Level,Publication_Date,Proceedings_ISBN_ISSN,
                            Publisher_Name,"");
                }else {
                    if (!TextUtils.isEmpty(base64StringFile)){
                        File_Name = base64StringFile;
                        updatePublicationInConference(String.valueOf(hasfile),String.valueOf(dataBeanBean.getId()),emp_id,user_id,ip,Year,Research_Paper,Proceedings,Conference_Name,Conference_Level,Publication_Date,Proceedings_ISBN_ISSN,
                                Publisher_Name,File_Name);
                    }else {
                        DialogUtils.Show_Toast(PublicationInConferencesActivity.this, "Select File");
                    }
                }
            }
        }else if (view.getId() == R.id.tvCancelPublicationInConferencesUpdate){
            onBackPressed();
        }else if (view.getId() == R.id.iv_info){
            showTooltip(view);
        }
    }

    private void getYearApiCall() {
        DialogUtils.showProgressDialog(PublicationInConferencesActivity.this, "");
        String url = URLS.Get_YEAR;

        StringRequest requestGetYear = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        if (jsonArray != null && jsonArray.length() > 0) {

                            for (int i = 0; i < jsonArray.length(); i++) {

                                hashMapAcademicYearAndID.put(jsonArray.getJSONObject(i).getString(ApiConstants.YEAR_NAME),
                                        jsonArray.getJSONObject(i).getInt(ApiConstants.YEAR_ID));

                                academicYearOpetion.add(jsonArray.getJSONObject(i).getString(ApiConstants.YEAR_NAME));
                            }

//                            ArrayAdapter<String> academicYearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, academicYearOpetion);
//                            spAcademicYear.setAdapter(academicYearAdapter);

                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(PublicationInConferencesActivity.this, academicYearOpetion);
                            spAcademicYear.setAdapter(academicYearAdapter);


                            if (getIntent().hasExtra(IntentConstants.UPDATE_PUBLICATION_IN_CONFERENCE)){
                                ll_submit_cancel.setVisibility(View.GONE);
                                ll_update_publication_cancel.setVisibility(View.VISIBLE);

                                dataBeanBean = (ViewPublicationInConferencePojo.DataBean) getIntent().getSerializableExtra(IntentConstants.UPDATE_PUBLICATION_IN_CONFERENCE);

                                spAcademicYear.setSelection(academicYearOpetion.indexOf(dataBeanBean.getYear_name()));
                                etTitleOfResearchPaper.setText(dataBeanBean.getIpc_paper_title());
                                etNameOfTheConference.setText(dataBeanBean.getIpc_conference_name());
                                etDateOfPublication.setText(dataBeanBean.getIpc_publication_date());
                                etNameOfThePublisher.setText(dataBeanBean.getIpc_publisher_name());
                                etTitleOfProceedings.setText(dataBeanBean.getProceedings_Title());
                                spLevelOfconference.setSelection(levelOfConferenceOpetion.indexOf(dataBeanBean.getIpc_level()));
                                etProceedingsISBNorISSN.setText(dataBeanBean.getIpc_isbn_issn_number());
                                rlUploadFile.setClickable(true);
                                imgUploadFile.setVisibility(View.GONE);
                                imgClear.setVisibility(View.VISIBLE);
                                tvChooseFile.setText(dataBeanBean.getFileName());

                            }else {
                                spAcademicYear.setSelection(academicYearOpetion.indexOf(0));
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PublicationInConferencesActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT &&resultCode == RESULT_OK) {

//            Uri uri ;
//
//            if (intent.getData() == null){
//                uri = (Uri)intent.getExtras().get("data");
//            }else {
//                uri = intent.getData();
//            }

            try {


               // String filePath = intent.getData().getPath();
              //  String filename = filePath.substring(filePath.lastIndexOf("/") + 1);
                //String selectedFilePath = FileUtils.getPath(this, uri);
                String filePath = intent.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                final File file = new File(filePath);

                if (file != null && file.length() > 0 ){

                    if (file.length() > 2000000){ //2000000bytes == 2MB
                        Toast.makeText(this, "File length must be less than 2mb", Toast.LENGTH_SHORT).show();
                    }else {
                        hasfile = 1;
                        tvChooseFile.setClickable(false);
                        rlUploadFile.setClickable(false);
                        imgUploadFile.setVisibility(View.GONE);
                        imgClear.setVisibility(View.VISIBLE);

                        byte[] buffer = new byte[(int) file.length() + 100];
                        int length = new FileInputStream(file).read(buffer);
                        base64StringFile = Base64.encodeToString(buffer, 0, length,
                                Base64.NO_WRAP);
                        tvChooseFile.setText(file.getName());
                    }
                }else {
                    DialogUtils.Show_Toast(PublicationInConferencesActivity.this, "File Not Found");
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
//        else if (requestCode == IntentConstants.REQUEST_CODE_FOR_READ_EXTERNAL_STORAGE_PERMISSION &&
//                ContextCompat.checkSelfPermission(PublicationInConferencesActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
//            //permission ganted
//            //requestForWriteExternalStoragePermission();
//
//        }
//        else if (requestCode == IntentConstants.REQUEST_CODE_FOR_WRITE_EXTERNAL_STORAGE_PERMISSION &&
//                ContextCompat.checkSelfPermission(PublicationInConferencesActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
//            //permission ganted
//            UploadDocumentUtility.uploadDocument(this,"application/pdf","Select PDF",IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT);
//        }
    }


//    private void requestForReadExternalStoragePermission(){
//
//       if (ContextCompat.checkSelfPermission(PublicationInConferencesActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//
//           if (ActivityCompat.shouldShowRequestPermissionRationale(PublicationInConferencesActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//               new AlertDialog.Builder(this)
//                       .setTitle("Required Read External Storage Permission!")
//                       .setMessage("To Upload Document we need read external storage permission")
//                       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                           @Override
//                           public void onClick(DialogInterface dialogInterface, int i) {
//                               ActivityCompat.requestPermissions(PublicationInConferencesActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
//                           }
//                       })
//                       .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                           @Override
//                           public void onClick(DialogInterface dialogInterface, int i) {
//                               dialogInterface.cancel();
//                           }
//                       })
//                       .create().show();
//           } else if (mySharedPrefereces.getIsPermissionGranted()) {
//                   new AlertDialog.Builder(this)
//                           .setTitle("Required Read External Storage Permission!")
//                           .setMessage("To Enable Read External Storage Permission click on Ok button")
//                           .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                               @Override
//                               public void onClick(DialogInterface dialogInterface, int i) {
//                                   Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                   Uri uri = Uri.fromParts("package", getPackageName(), null);
//                                   intent.setData(uri);
//                                   startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_READ_EXTERNAL_STORAGE_PERMISSION);
//                               }
//                           })
//                           .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                               @Override
//                               public void onClick(DialogInterface dialogInterface, int i) {
//                                   dialogInterface.cancel();
//                               }
//                           })
//                           .create().show();
//           } else {
//               ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
//           }
//           mySharedPrefereces.setIsPermissionGranted();
//
//       } else {
//           //permission granted
//          // requestForWriteExternalStoragePermission();
//           UploadDocumentUtility.uploadDocument(this,"application/pdf","Select PDF",IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT);
//       }
//    }

    //private void requestForWriteExternalStoragePermission(){

//        if (ContextCompat.checkSelfPermission(PublicationInConferencesActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(PublicationInConferencesActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                new AlertDialog.Builder(this)
//                        .setTitle("Required Write External Storage Permission!")
//                        .setMessage("We need Write external storage permission")
//                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                ActivityCompat.requestPermissions(PublicationInConferencesActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
//                            }
//                        })
//                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.cancel();
//                            }
//                        })
//                        .create().show();
//            } else if (mySharedPrefereces.getIsPermissionGrantedForWriteExternalStorage()) {
//                new AlertDialog.Builder(this)
//                        .setTitle("Required Write External Storage Permission!")
//                        .setMessage("To Enable Write External Storage Permission click on Ok button")
//                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                Uri uri = Uri.fromParts("package", getPackageName(), null);
//                                intent.setData(uri);
//                                startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_WRITE_EXTERNAL_STORAGE_PERMISSION);
//                            }
//                        })
//                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.cancel();
//                            }
//                        })
//                        .create().show();
//            } else {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
//            }
//            mySharedPrefereces.setIsPermissionGrantedForWriteExternalStorage();
//
//        } else {
//            //permission granted
//            UploadDocumentUtility.uploadDocument(this,"application/pdf","Select PDF",IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT);
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 100 && ContextCompat.checkSelfPermission(PublicationInConferencesActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
//            //permission granted
//            //requestForWriteExternalStoragePermission();
//            UploadDocumentUtility.uploadDocument(this,"application/pdf","Select PDF",IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT);
//        }
////        else if (requestCode == 200 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
////            //permission granted
////            UploadDocumentUtility.uploadDocument(this,"application/pdf","Select PDF",IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT);
////        }
//    }



    private void callSavePublicationInConferenceApi(final String emp_id, final String user_id, final String ip, final String Year, final String Research_Paper, final String Proceedings,
                                                    final String Conference_Name, final String Conference_Level, final String Publication_Date, final String Proceedings_ISBN_ISSN, final String Publisher_Name,
                                                    final String File_Name){

        DialogUtils.showProgressDialog(PublicationInConferencesActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.SAVE_PUBLICATION_IN_CONFERENCE , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)){
                   // Toast.makeText(PublicationInConferencesActivity.this, response, Toast.LENGTH_SHORT).show();

                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}",SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(PublicationInConferencesActivity.this, responsePojo.getData().get(0).getMsg());

                    Intent intent = new Intent(PublicationInConferencesActivity.this, PublicationInConferencesListActivity.class);
                    setResult(RESULT_OK,intent);
                    finish();
                    Log.d("TAG","Response:- "+response);
                }else {
                    DialogUtils.Show_Toast(PublicationInConferencesActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PublicationInConferencesActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("emp_id", emp_id);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                params2.put("Year", Year);
                params2.put("Research_Paper", Research_Paper);
                params2.put("Proceedings", Proceedings);
                params2.put("Conference_Name", Conference_Name);
                params2.put("Conference_Level", Conference_Level);
                params2.put("Publication_Date", Publication_Date);
                params2.put("Proceedings_ISBN_ISSN", Proceedings_ISBN_ISSN);
                params2.put("Publisher_Name", Publisher_Name);
                params2.put("File_Name", File_Name);
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


    private void updatePublicationInConference(final String hasfile,final String id, final String emp_id, final String user_id, final String ip, final String Year, final String Research_Paper,
                                                    final String Proceedings, final String Conference_Name, final String Conference_Level, final String Publication_Date,
                                                    final String Proceedings_ISBN_ISSN,final String Publisher_Name,final String File_Name){

        DialogUtils.showProgressDialog(PublicationInConferencesActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.UPDATE_PUBLICATION_IN_CONFERENCE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)){
//                    Toast.makeText(PublicationInConferencesActivity.this, response, Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}",SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(PublicationInConferencesActivity.this, responsePojo.getData().get(0).getMsg());
                    Intent intent = new Intent(PublicationInConferencesActivity.this, PublicationInConferencesListActivity.class);
                    setResult(RESULT_OK,intent);
                    finish();
                    Log.d("TAG","Response:- "+response);
                }else {
                    DialogUtils.Show_Toast(PublicationInConferencesActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PublicationInConferencesActivity.this, "Please Try Again Later");
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
                params2.put("Year", Year);
                params2.put("Research_Paper", Research_Paper);
                params2.put("Proceedings", Proceedings);
                params2.put("Conference_Name", Conference_Name);
                params2.put("Conference_Level", Conference_Level);
                params2.put("Publication_Date", Publication_Date);
                params2.put("Proceedings_ISBN_ISSN", Proceedings_ISBN_ISSN);
                params2.put("Publisher_Name", Publisher_Name);
                params2.put("File_Name", File_Name);
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

}
