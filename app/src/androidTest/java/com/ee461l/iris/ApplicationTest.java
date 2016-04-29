package com.ee461l.iris;

import android.app.Application;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import java.util.ArrayList;

import org.junit.runner.RunWith;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest extends ApplicationTestCase<Application> {
    private static ArrayList<BoxOfficeMovie> movies = new ArrayList<>();
    private RottenTomatoesClient client;
    getReverseGeoCoding geocod = new getReverseGeoCoding();

    //Replace lat,long,zipcode with any city's to test (Currently: Katy, TX)
    private double lat = 29.7420;
    private double lon = -95.8248;
    private String zipcode = "77494";
    //Replace string below with the Name of a currently airing movie (Currently: The Jungle Book)
    private String movieName = "The Jungle Book";

    public ApplicationTest() {
        super(Application.class);
    }

//    @Test
//    public void testZipcode() throws Exception {
//        geocod.getAddress(lat, lon);
//        assertEquals(geocod.getPIN(), zipcode);
//    }

    @Test
    public void testMovieList() throws Exception {
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

        boolean res = false;

        for(BoxOfficeMovie b : movies){
            if(b.getTitle().equals(movieName)) {
                res = true;
            }
        }
        assertTrue(res);
    }

}