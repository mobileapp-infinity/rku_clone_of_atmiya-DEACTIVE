package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.CommitteeExpandableListAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.infinity.infoway.rkuniversity.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestingActivity extends AppCompatActivity {

    ExpandableListView expLvCommittee;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    CommitteeExpandableListAdapter committeeExpandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        expLvCommittee = findViewById(R.id.expLvCommittee);
        prepareExpandableListViewForCommittee();
    }



    private void prepareExpandableListViewForCommittee(){
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding Header data
        listDataHeader.add("Committee");

        // Adding child data
        List<String> listItem = new ArrayList<String>();
        listItem.add("Committee");
        listItem.add("Committee Member");

        // Adding child data
//        listDataHeader.add("Top 250");
//        listDataHeader.add("Now Showing");
//        listDataHeader.add("Coming Soon..");
//
//        // Adding child data
//        List<String> top250 = new ArrayList<String>();
//        top250.add("The Shawshank Redemption");
//        top250.add("The Godfather");
//        top250.add("The Godfather: Part II");
//        top250.add("Pulp Fiction");
//        top250.add("The Good, the Bad and the Ugly");
//        top250.add("The Dark Knight");
//        top250.add("12 Angry Men");
//
//        List<String> nowShowing = new ArrayList<String>();
//        nowShowing.add("The Conjuring");
//        nowShowing.add("Despicable Me 2");
//        nowShowing.add("Turbo");
//        nowShowing.add("Grown Ups 2");
//        nowShowing.add("Red 2");
//        nowShowing.add("The Wolverine");
//
//        List<String> comingSoon = new ArrayList<String>();
//        comingSoon.add("2 Guns");
//        comingSoon.add("The Smurfs 2");
//        comingSoon.add("The Spectacular Now");
//        comingSoon.add("The Canyons");
//        comingSoon.add("Europa Report");
//
//        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
//        listDataChild.put(listDataHeader.get(1), nowShowing);
//        listDataChild.put(listDataHeader.get(2), comingSoon);

        listDataChild.put(listDataHeader.get(0), listItem); // Header, Child data

        committeeExpandableListAdapter = new CommitteeExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expLvCommittee.setAdapter(committeeExpandableListAdapter);

        expLvCommittee.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d("onGroupClick:", "worked");
                parent.expandGroup(groupPosition);
                return false;
            }
        });

    }

}
