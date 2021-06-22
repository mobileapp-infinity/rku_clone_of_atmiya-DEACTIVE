package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.AcademicContributionAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.models.AcademicContributionModel;

import java.util.ArrayList;

/*
* created by remish varsani ro display list of academic contribution on 16-03-2020
* */

public class AcademicContributionActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rvAcademicContribution;
    ArrayList<AcademicContributionModel> academicContributionModelsArrayList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    AcademicContributionAdapter academicContributionAdapter;
    ImageView ivBack;
    CustomBoldTextView txt_act,tv_emp_code;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_contribution);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }
    private void initView(){
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Academic Contribution");

        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        rvAcademicContribution = findViewById(R.id.rvAcademicContribution);
        layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);


        AcademicContributionModel academicContributionModel = new AcademicContributionModel();
        academicContributionModel.setIcone(R.drawable.publication_in_conference);
        academicContributionModel.setAcademicContributionTitle(this.getString(R.string.publication_in_conferences));
        academicContributionModelsArrayList.add(academicContributionModel);

        AcademicContributionModel academicContributionModel1 = new AcademicContributionModel();
        academicContributionModel1.setIcone(R.drawable.approval);
        academicContributionModel1.setAcademicContributionTitle(this.getString(R.string.publication_in_conferences_approval));
        academicContributionModelsArrayList.add(academicContributionModel1);

        AcademicContributionModel academicContributionModel2 = new AcademicContributionModel();
        academicContributionModel2.setIcone(R.drawable.publication_in_journals);
        academicContributionModel2.setAcademicContributionTitle(this.getString(R.string.publication_in_journals));
        academicContributionModelsArrayList.add(academicContributionModel2);

        AcademicContributionModel academicContributionModel3 = new AcademicContributionModel();
        academicContributionModel3.setIcone(R.drawable.approval);
        academicContributionModel3.setAcademicContributionTitle(this.getString(R.string.publication_in_journals_approve));
        academicContributionModelsArrayList.add(academicContributionModel3);

        AcademicContributionModel academicContributionModel4 = new AcademicContributionModel();
        academicContributionModel4.setIcone(R.drawable.book_or_chapter_publication);
        academicContributionModel4.setAcademicContributionTitle(this.getString(R.string.book_or_chapter_publication));
        academicContributionModelsArrayList.add(academicContributionModel4);

        AcademicContributionModel academicContributionModel5 = new AcademicContributionModel();
        academicContributionModel5.setIcone(R.drawable.approval);
        academicContributionModel5.setAcademicContributionTitle(this.getString(R.string.book_or_chapter_publication_approval));
        academicContributionModelsArrayList.add(academicContributionModel5);

        AcademicContributionModel academicContributionModel6 = new AcademicContributionModel();
        academicContributionModel6.setIcone(R.drawable.patent_awarded);
        academicContributionModel6.setAcademicContributionTitle(this.getString(R.string.patent_awarded_request));
        academicContributionModelsArrayList.add(academicContributionModel6);

        AcademicContributionModel academicContributionModel7 = new AcademicContributionModel();
        academicContributionModel7.setIcone(R.drawable.approval);
        academicContributionModel7.setAcademicContributionTitle(this.getString(R.string.patent_awarded_approval));
        academicContributionModelsArrayList.add(academicContributionModel7);

        AcademicContributionModel academicContributionModel8 = new AcademicContributionModel();
        academicContributionModel8.setIcone(R.drawable.pg_scholar);
        academicContributionModel8.setAcademicContributionTitle(this.getString(R.string.pg_scholars_guided_request));
        academicContributionModelsArrayList.add(academicContributionModel8);

        AcademicContributionModel academicContributionModel9 = new AcademicContributionModel();
        academicContributionModel9.setIcone(R.drawable.approval);
        academicContributionModel9.setAcademicContributionTitle(this.getString(R.string.pg_scholars_guided_approval));
        academicContributionModelsArrayList.add(academicContributionModel9);

        AcademicContributionModel academicContributionModel10 = new AcademicContributionModel();
        academicContributionModel10.setIcone(R.drawable.phd_scholar);
        academicContributionModel10.setAcademicContributionTitle(this.getString(R.string.phd_scholars_guided_request));
        academicContributionModelsArrayList.add(academicContributionModel10);

        AcademicContributionModel academicContributionModel11 = new AcademicContributionModel();
        academicContributionModel11.setIcone(R.drawable.approval);
        academicContributionModel11.setAcademicContributionTitle(this.getString(R.string.phd_scholars_guided_approval));
        academicContributionModelsArrayList.add(academicContributionModel11);

        AcademicContributionModel academicContributionModel12 = new AcademicContributionModel();
        academicContributionModel12.setIcone(R.drawable.cpd);
        academicContributionModel12.setAcademicContributionTitle(this.getString(R.string.cpd_application_request));
        academicContributionModelsArrayList.add(academicContributionModel12);

        AcademicContributionModel academicContributionModel13 = new AcademicContributionModel();
        academicContributionModel13.setIcone(R.drawable.approval);
        academicContributionModel13.setAcademicContributionTitle(this.getString(R.string.cpd_applciation_approval));
        academicContributionModelsArrayList.add(academicContributionModel13);

        AcademicContributionModel academicContributionModel14 = new AcademicContributionModel();
        academicContributionModel14.setIcone(R.drawable.fdp);
        academicContributionModel14.setAcademicContributionTitle(this.getString(R.string.fdp_attended_request));
        academicContributionModelsArrayList.add(academicContributionModel14);

        AcademicContributionModel academicContributionModel15 = new AcademicContributionModel();
        academicContributionModel15.setIcone(R.drawable.approval);
        academicContributionModel15.setAcademicContributionTitle(this.getString(R.string.fdp_attended_approval));
        academicContributionModelsArrayList.add(academicContributionModel15);

        AcademicContributionModel academicContributionModel16 = new AcademicContributionModel();
        academicContributionModel16.setIcone(R.drawable.consultancy);
        academicContributionModel16.setAcademicContributionTitle(this.getString(R.string.consultancy_request));
        academicContributionModelsArrayList.add(academicContributionModel16);

        AcademicContributionModel academicContributionModel17 = new AcademicContributionModel();
        academicContributionModel17.setIcone(R.drawable.approval);
        academicContributionModel17.setAcademicContributionTitle(this.getString(R.string.consultancy_approval));
        academicContributionModelsArrayList.add(academicContributionModel17);

        AcademicContributionModel academicContributionModel18 = new AcademicContributionModel();
        academicContributionModel18.setIcone(R.drawable.grant_received);
        academicContributionModel18.setAcademicContributionTitle(this.getString(R.string.grant_received_request));
        academicContributionModelsArrayList.add(academicContributionModel18);

        AcademicContributionModel academicContributionModel19 = new AcademicContributionModel();
        academicContributionModel19.setIcone(R.drawable.approval);
        academicContributionModel19.setAcademicContributionTitle(this.getString(R.string.grant_received_approval));
        academicContributionModelsArrayList.add(academicContributionModel19);

        AcademicContributionModel academicContributionModel21 = new AcademicContributionModel();
        academicContributionModel21.setIcone(R.drawable.salary_slip_dra_black);
        academicContributionModel21.setAcademicContributionTitle(this.getString(R.string.seed_money_received_from_university_request));
        academicContributionModelsArrayList.add(academicContributionModel21);

        AcademicContributionModel academicContributionModel22 = new AcademicContributionModel();
        academicContributionModel22.setIcone(R.drawable.approval);
        academicContributionModel22.setAcademicContributionTitle(this.getString(R.string.seed_money_received_from_university_approval));
        academicContributionModelsArrayList.add(academicContributionModel22);

        academicContributionAdapter = new AcademicContributionAdapter(this,academicContributionModelsArrayList);

        rvAcademicContribution.setLayoutManager(layoutManager);
        rvAcademicContribution.setAdapter(academicContributionAdapter);

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
        if (view.getId() == R.id.iv_back){
            onBackPressed();
        }
    }
}
