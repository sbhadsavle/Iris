package com.ee461l.iris;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Jason on 4/22/2016.
 */
public class FiltersDirectorActivity extends AppCompatActivity implements
        View.OnClickListener {
    String director = "";
    String dir = "";
    String id = "default";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_filters_director);

        //findViewById();
        Bundle extra = getIntent().getExtras();
        if(extra != null){
            id = extra.getString("USER_ID");
        }
        dir = new StringBuilder("Director").append(id).toString();

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        director =sharedPref.getString(dir, "");
    }
    public void onClick(View v){

    }
}
