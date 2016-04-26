package com.ee461l.iris;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class UnitTest {
    private static ArrayList<BoxOfficeMovie> movies = new ArrayList<>();
    private RottenTomatoesClient client;
    getReverseGeoCoding geocod = new getReverseGeoCoding();

    //Replace lat,long,zipcode with any city's to test (Currently: Katy, TX)
    private double lat = 29.7420;
    private double lon = -95.8248;
    private String zipcode = "77494";
    //Replace string below with the Name of a currently airing movie (Currently: The Jungle Book)
    private String movieName = "The Jungle Book";


    @Test
    public void zipcodeIsCorrect() throws Exception {
        geocod.getAddress(lat,lon);
        assertEquals(geocod.getPIN(),zipcode);
    }

    @Test
    public void listContainsMovie() throws Exception {
        client = new RottenTomatoesClient();
        client.getBoxOfficeMovies(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int code, JSONObject body) {
                JSONArray items = null;
                try {
                    // Get the movies json array
                    items = body.getJSONArray("movies");
                    // Parse json array into array of model objects
                    movies = BoxOfficeMovie.fromJson(items);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        assertTrue(containsMovie());
    }

    public boolean containsMovie(){
        for(BoxOfficeMovie m: movies){
            if(m.getTitle().equals(movieName)){
                return true;
            }
        }
        return false;
    }
}