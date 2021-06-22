package com.infinity.infoway.rkuniversity.faculty.faculty_news;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;

import java.util.ArrayList;

public class FacultyNewsFragment extends Fragment {

    ArrayList<FacultyNewsPojo.GroupNewsDetail> groupNewsDetailArrayList;
    LinearLayout llFacultyNewsList, llNoDataFoundFacultyNewsFragment;
    RecyclerView rvFacultyNews;
    FacultyNewsActivity facultyNewsActivity;

    public FacultyNewsFragment() {

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        facultyNewsActivity = (FacultyNewsActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            groupNewsDetailArrayList = (ArrayList<FacultyNewsPojo.GroupNewsDetail>) bundle.getSerializable(IntentConstants.FACULTY_NEWS_GROUP_WISE);
        } else {
            groupNewsDetailArrayList = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faculty_news, container, false);
        initView(view);
        displayFacultyNews();
        return view;
    }

    private void initView(View view) {
        llFacultyNewsList = view.findViewById(R.id.llFacultyNewsList);
        llNoDataFoundFacultyNewsFragment = view.findViewById(R.id.llNoDataFoundFacultyNewsFragment);
        rvFacultyNews = view.findViewById(R.id.rvFacultyNews);
    }

    private void displayFacultyNews() {
        if (groupNewsDetailArrayList != null && groupNewsDetailArrayList.size() > 0) {
            llNoDataFoundFacultyNewsFragment.setVisibility(View.GONE);
            llFacultyNewsList.setVisibility(View.VISIBLE);
            rvFacultyNews.setAdapter(new FacultyNewsListAdapter(facultyNewsActivity, groupNewsDetailArrayList));
        } else {
            llNoDataFoundFacultyNewsFragment.setVisibility(View.VISIBLE);
            llFacultyNewsList.setVisibility(View.GONE);
        }
    }
}

