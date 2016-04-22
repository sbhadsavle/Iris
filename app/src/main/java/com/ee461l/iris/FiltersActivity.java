package com.ee461l.iris;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


/**
 * Created by Jason on 4/22/2016.
 */
public class FiltersActivity extends AppCompatActivity implements
        OnClickListener {
    Button button;
    ListView listView;
    ArrayAdapter<String> adapter;
    String selected;
    private String id;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        findViewsById();

        String[] genres = getResources().getStringArray(R.array.genres);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, genres);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        id = "default";
        Bundle extra = getIntent().getExtras();
        if(extra != null){
            id = extra.getString("USER_ID");
        }

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        selected =sharedPref.getString(id, "");

        button.setOnClickListener(this);
    }

    private void findViewsById() {
        listView = (ListView) findViewById(R.id.listView);
        button = (Button) findViewById(R.id.button);
    }

    public void onClick(View v) {
        StringBuilder sb = new StringBuilder();
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add genres if it is checked i.e.) == TRUE!
            if (checked.valueAt(i)) {
                selectedItems.add(adapter.getItem(position));
                sb.append(i);
                sb.append(";");
            }
        }

        selected = sb.toString();

        String[] outputStrArr = new String[selectedItems.size()];

        for (int i = 0; i < selectedItems.size(); i++) {
            outputStrArr[i] = selectedItems.get(i);
        }

        Intent intent = new Intent(getApplicationContext(),BoxOfficeActivity.class);

        // Create a bundle object
        Bundle b = new Bundle();
        b.putStringArray("selectedItems", outputStrArr);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(id, selected);

        // Add the bundle to the intent.
        intent.putExtras(b);

        // start the ResultActivity
        startActivity(intent);
    }
}
