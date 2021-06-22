package com.infinity.infoway.rkuniversity.faculty.faculty_news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.DownloadPdfFromUrl;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import static com.infinity.infoway.rkuniversity.faculty.faculty_news.FacultyNewsListAdapter.FACULTY_NEWS_FOLDER_NAME;

public class FacultyNewsDetailActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView ivCloseFacultyNewsDetails;
    SliderView sliderView;
    FacultyNewsPojo.GroupNewsDetail groupNewsDetail;
    TextViewRegularFont tvNewsDate, tvNewsContent;
    ExtendedFloatingActionButton efabDownloadFacultyNewsDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_news_detail);
        initView();

        if (getIntent().hasExtra(IntentConstants.FACULTY_NEWS_DETAILS)) {
            groupNewsDetail = (FacultyNewsPojo.GroupNewsDetail) getIntent().getSerializableExtra(IntentConstants.FACULTY_NEWS_DETAILS);
        }

        try {
            setUpNewsAdapter();
        } catch (Exception ex) {
            Toast.makeText(this, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

    private void setUpNewsAdapter() {
        ArrayList<FacultyNewsSliderImageModel> facultyNewsSliderImageModelArrayList = new ArrayList<>();

        FacultyNewsSliderImageModel facultyNewsSliderImageModel1 = new FacultyNewsSliderImageModel();
        facultyNewsSliderImageModel1.setImgUrl(groupNewsDetail.getPh1());

        if (!CommonUtil.checkIsEmptyOrNullCommon(groupNewsDetail.getPh1())) {
            facultyNewsSliderImageModel1.setImgUrl(groupNewsDetail.getPh1());
        }

        FacultyNewsSliderImageModel facultyNewsSliderImageModel2 = new FacultyNewsSliderImageModel();
        facultyNewsSliderImageModel2.setImgUrl(groupNewsDetail.getPh2());
        if (!CommonUtil.checkIsEmptyOrNullCommon(groupNewsDetail.getPh2())) {
            facultyNewsSliderImageModel2.setImgUrl(groupNewsDetail.getPh2());
        }

        facultyNewsSliderImageModel1.setNewsText(groupNewsDetail.getCnSubject());
        facultyNewsSliderImageModel2.setNewsText(groupNewsDetail.getCnSubject());

        facultyNewsSliderImageModelArrayList.add(facultyNewsSliderImageModel1);
        facultyNewsSliderImageModelArrayList.add(facultyNewsSliderImageModel2);
        FacultyNewsSliderAdapter adapter = new FacultyNewsSliderAdapter(FacultyNewsDetailActivity.this, facultyNewsSliderImageModelArrayList);

        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        String newsDate = "-";

        if (!CommonUtil.checkIsEmptyOrNullCommon(groupNewsDetail.getCnDate())) {
            newsDate = groupNewsDetail.getCnDate() + "";
        }

        if (newsDate.contains("/")) {
            String dateArray[] = newsDate.split("/");
            newsDate = dateArray[0] + " " + CommonUtil.getMonthSortNameFromNumber(Integer.parseInt(dateArray[1])) + ", " + dateArray[2];
        }

        tvNewsDate.setText(newsDate);

        if (!CommonUtil.checkIsEmptyOrNullCommon(groupNewsDetail.getCnContent())) {
            tvNewsContent.setText(groupNewsDetail.getCnContent() + "");

        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(groupNewsDetail.getCn_file()) &&
                groupNewsDetail.getCn_file().length() > 7) {
            efabDownloadFacultyNewsDoc.setVisibility(View.VISIBLE);
        } else {
            efabDownloadFacultyNewsDoc.setVisibility(View.GONE);
        }
    }

    private void initView() {
        ivCloseFacultyNewsDetails = findViewById(R.id.ivCloseFacultyNewsDetails);
        ivCloseFacultyNewsDetails.setOnClickListener(this);
        sliderView = findViewById(R.id.sliderView);
        tvNewsDate = findViewById(R.id.tvNewsDate);
        tvNewsContent = findViewById(R.id.tvNewsContent);
        efabDownloadFacultyNewsDoc = findViewById(R.id.efabDownloadFacultyNewsDoc);
        efabDownloadFacultyNewsDoc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyNewsDetails) {
            onBackPressed();
        } else if (v.getId() == R.id.efabDownloadFacultyNewsDoc) {
            String fileUrl = groupNewsDetail.getCn_file();
            String fileExtension = fileUrl.substring(fileUrl.lastIndexOf("."));
            new DownloadPdfFromUrl(FacultyNewsDetailActivity.this, groupNewsDetail.getCn_file().trim(), fileExtension, FACULTY_NEWS_FOLDER_NAME);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}