package com.infinity.infoway.rkuniversity.student.message_history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageHistoryActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseMessageHistory;
    RecyclerView rvStudentMessageHistoryList;
    LinearLayout llStudentMessageHistory, llStudentMessageHistoryProgressbar, llNoDataFoundStudentMessageHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_history);
        initView();
        getStudentMessageHistoryListApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(MessageHistoryActivity.this);
        connectionDetector = new ConnectionDetector(MessageHistoryActivity.this);
        ivCloseMessageHistory = findViewById(R.id.ivCloseMessageHistory);
        ivCloseMessageHistory.setOnClickListener(this);
        rvStudentMessageHistoryList = findViewById(R.id.rvStudentMessageHistoryList);
        llStudentMessageHistory = findViewById(R.id.llStudentMessageHistory);
        llStudentMessageHistoryProgressbar = findViewById(R.id.llStudentMessageHistoryProgressbar);
        llNoDataFoundStudentMessageHistory = findViewById(R.id.llNoDataFoundStudentMessageHistory);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseMessageHistory) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getStudentMessageHistoryListApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llStudentMessageHistory.setVisibility(View.GONE);
            llStudentMessageHistoryProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundStudentMessageHistory.setVisibility(View.GONE);
            ApiImplementer.getStudentMessageHistoryListApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getSwdYearId(), new Callback<ArrayList<MessageHistoryListPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<MessageHistoryListPojo>> call, Response<ArrayList<MessageHistoryListPojo>> response) {
                    llStudentMessageHistoryProgressbar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                        llStudentMessageHistory.setVisibility(View.VISIBLE);
                        rvStudentMessageHistoryList.setAdapter(new MessageHistoryListAdapter(MessageHistoryActivity.this, response.body()));
                    } else {
                        llStudentMessageHistory.setVisibility(View.GONE);
                        llNoDataFoundStudentMessageHistory.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<MessageHistoryListPojo>> call, Throwable t) {
                    llStudentMessageHistory.setVisibility(View.GONE);
                    llStudentMessageHistoryProgressbar.setVisibility(View.GONE);
                    llNoDataFoundStudentMessageHistory.setVisibility(View.VISIBLE);
                    Toast.makeText(MessageHistoryActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


}