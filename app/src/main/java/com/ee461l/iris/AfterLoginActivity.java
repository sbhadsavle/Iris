package com.ee461l.iris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * Created by Andoji on 4/22/16.
 */
public class AfterLoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_afterlogin);
    }

    public void changeFilters(View view){
        Intent f = new Intent(this, FiltersActivity.class);
        startActivity(f);
    }

    public void goToMovies(View view){
        Intent b = new Intent(this, BoxOfficeActivity.class);
        startActivity(b);
    }

    public void onBackPressed(){ finish();}

}
