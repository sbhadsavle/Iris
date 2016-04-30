package com.ee461l.iris;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


/**
 * Created by Jason on 4/22/2016.
 */
public class FiltersActivity extends AppCompatActivity implements
        OnClickListener, OnItemSelectedListener {
    Button button;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> gen = new ArrayList<>();
    String selected;
    private String id;
    String[] genres;
    String selectedSpinner;
    ArrayList<String> MPAA;
    String rating;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        findViewsById();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(this,
                R.array.movieRatings, android.R.layout.simple_spinner_item);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapt);
//        spinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        MPAA = new ArrayList<String>();

        genres = getResources().getStringArray(R.array.genres);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, genres);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        id = LoginActivity.id;

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        selected =sharedPref.getString(id, "");

        if(!selected.equals("")) {
            ArrayList<Integer> pos = new ArrayList<>();

            String[] positions = selected.split(";");

            for (int i = 0; i < positions.length; i += 1) {
                pos.add(Integer.valueOf(positions[i]));
            }

            for (Integer i : pos) {
                listView.setItemChecked(i,true);
            }


        }

        String s = sharedPref.getString(id+"1",  "");
        if(!s.equals("")){
            String[] mpaaRatings = s.split(";");
            for(int i = 0; i < mpaaRatings.length; i++){
                CheckBox cb = null;
                switch(mpaaRatings[i]){
                    case "G":
                        cb = (CheckBox) findViewById(R.id.checkBox);
                        break;
                    case "PG":
                        cb = (CheckBox) findViewById(R.id.checkBox2);
                        break;
                    case "PG13":
                        cb = (CheckBox) findViewById(R.id.checkBox3);
                        break;
                    case "R":
                        cb = (CheckBox) findViewById(R.id.checkBox4);
                        break;
                    case "NC17":
                        cb = (CheckBox) findViewById(R.id.checkBox5);
                        break;
                }
                cb.setChecked(true);
            }
        }

        button.setOnClickListener(this);
    }

    private void findViewsById() {
        listView = (ListView) findViewById(R.id.listView);
        button = (Button) findViewById(R.id.button);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        selected =sharedPref.getString(id, "");
        if(!selected.equals("")) {
            ArrayList<Integer> pos = new ArrayList<>();

            String[] positions = selected.split(";");

            for (int i = 0; i < positions.length; i += 1) {
                pos.add(Integer.valueOf(positions[i]));
            }

            for (Integer i : pos) {
                listView.setItemChecked(i, true);
            }

        }
        String s = sharedPref.getString(id + "1", "");
        if(!s.equals("")){
            String[] mpaaRatings = s.split(";");
            for(int i = 0; i < mpaaRatings.length; i++){
                CheckBox cb = null;
                switch(mpaaRatings[i]){
                    case "G":
                        cb = (CheckBox) findViewById(R.id.checkBox);
                        break;
                    case "PG":
                        cb = (CheckBox) findViewById(R.id.checkBox2);
                        break;
                    case "PG13":
                        cb = (CheckBox) findViewById(R.id.checkBox3);
                        break;
                    case "R":
                        cb = (CheckBox) findViewById(R.id.checkBox4);
                        break;
                    case "NC17":
                        cb = (CheckBox) findViewById(R.id.checkBox5);
                        break;
                }
                cb.setChecked(true);
            }
        }
    }


    public void onClick(View v) {
        StringBuilder sb = new StringBuilder();
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add genres if it is checked i.e.) == TRUE!
            if(checked.valueAt(i)) {
                selectedItems.add(adapter.getItem(position));
                sb.append(checked.keyAt(i) + ";");
            }
        }

        selected = sb.toString();

        String[] outputStrArr = new String[selectedItems.size()];

        for (int i = 0; i < selectedItems.size(); i++) {
            outputStrArr[i] = selectedItems.get(i);
        }

        CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox);
        CheckBox cb2 = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox cb3 = (CheckBox) findViewById(R.id.checkBox3);
        CheckBox cb4 = (CheckBox) findViewById(R.id.checkBox4);
        CheckBox cb5 = (CheckBox) findViewById(R.id.checkBox5);

        sb = new StringBuilder();
        if(cb1.isChecked()){
            sb.append("G;");
        }
        if(cb2.isChecked()){
            sb.append("PG;");
        }
        if(cb3.isChecked()){
            sb.append("PG13;");
        }
        if(cb4.isChecked()){
            sb.append("R;");
        }
        if(cb5.isChecked()){
            sb.append("NC17;");
        }

        String mpaaRating = sb.toString();

        Intent intent = new Intent(getApplicationContext(),BoxOfficeActivity.class);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(id, selected);
        editor.putString(id+"1", mpaaRating);
        editor.putString(id+"2", rating);

        editor.commit();

        // Add the bundle to the intent.
        intent.putExtra("selectedItems", outputStrArr);

        // start the ResultActivity
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        rating = "All Movies";
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        rating = (String)parent.getItemAtPosition(position);
    }
}
