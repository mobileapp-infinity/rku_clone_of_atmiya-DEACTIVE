package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.infinity.infoway.rkuniversity.R;

public class PostDocument extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_document);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_act);
        setSupportActionBar(toolbar);
    }
}
