package com.ee461l.iris;

        import java.util.ArrayList;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.loopj.android.http.JsonHttpResponseHandler;

public class BoxOfficeActivity extends AppCompatActivity {
    private ListView lvMovies;
    private BoxOfficeMoviesAdapter adapterMovies;
    private RottenTomatoesClient client;
    public static final String MOVIE_DETAIL_KEY = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_office);
        lvMovies = (ListView) findViewById(R.id.lvMovies);
        ArrayList<BoxOfficeMovie> aMovies = new ArrayList<BoxOfficeMovie>();
        adapterMovies = new BoxOfficeMoviesAdapter(this, aMovies);
        lvMovies.setAdapter(adapterMovies);
        // Fetch the data remotely
        fetchBoxOfficeMovies();
        setupMovieSelectedListener();
    }

    private void fetchMovieGenres(final BoxOfficeMovie b) {
        RottenTomatoesClient client = new RottenTomatoesClient();
        client.getMovieDetailed(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int code, JSONObject body) {
                System.out.println("@success!");
                JSONArray items = null;
                try {
                    // Get the movies json array
                    items = body.getJSONArray("genres");
                    ArrayList<String> genres = new ArrayList<String>();
                    // populate the genres
                    for (int i = 0; i < items.length(); i++) {
                        genres.add(items.getString(i));
                    }
                    //adapterMovies.add(b);
                    int ind = adapterMovies.getPosition(b);
                    adapterMovies.getItem(ind).genres = genres;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, Long.parseLong(b.getID()));
    }

    private void fetchBoxOfficeMovies() {
        Toast.makeText(getApplicationContext(), "Loading movies...", Toast.LENGTH_LONG).show();
        client = new RottenTomatoesClient();
        client.getBoxOfficeMovies(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int code, JSONObject body) {
                JSONArray items = null;
                try {
                    // Get the movies json array
                    items = body.getJSONArray("movies");
                    // Parse json array into array of model objects
                    ArrayList<BoxOfficeMovie> movies = BoxOfficeMovie.fromJson(items);
                    // Load model objects into the adapter which displays them
                    for (BoxOfficeMovie mov : movies) {
                        System.out.println("@moviessize " + movies.size());
                        fetchMovieGenres(mov);
                        for (long i = 0; i < 25000000; i++){} // delay so that we can succeed
                    }
                    adapterMovies.addAll(movies);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setupMovieSelectedListener() {
        lvMovies.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View item, int position, long rowId) {
                // previously used to redirect to BoxOfficeDetailActivity
                Intent i = new Intent(BoxOfficeActivity.this, ExploreMovieActivity.class);
                i.putExtra(MOVIE_DETAIL_KEY, adapterMovies.getItem(position));
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
    }

}



