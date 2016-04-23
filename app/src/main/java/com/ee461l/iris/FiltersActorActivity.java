package com.ee461l.iris;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Jason on 4/22/2016.
 */
public class FiltersActorActivity extends AppCompatActivity implements
        View.OnClickListener {
    Button button;
    String[] actorList;
    String actors;
    String acts;
    private String id = "default";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_filters_actor);

        //findViewById();
        Bundle extra = getIntent().getExtras();
        if(extra != null){
            id = extra.getString("USER_ID");
        }

        acts=new StringBuilder("Actors").append(id).toString();

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        actors =sharedPref.getString(acts, "");
        actorList = actors.split(",");
    }

    public void onClick(View v){

    }
}
