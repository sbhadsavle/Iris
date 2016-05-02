package com.ee461l.iris;

        import java.util.ArrayList;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.preference.PreferenceManager;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.loopj.android.http.JsonHttpResponseHandler;

public class BoxOfficeActivity extends AppCompatActivity {

    private String[] genres = new String[17];
    private String[] mpaaRatings = new String[5];
    private int rating = 0;

    private int getGenres = 0;

    ArrayList<BoxOfficeMovie> movies;

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
                    System.out.println("");
                    getGenres += 1;
                    if (getGenres == movies.size()) {
                        adapterMovies.clear();
                        movies = filterMovies();
                        adapterMovies.addAll(movies);
                    }
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
                    movies = BoxOfficeMovie.fromJson(items);
                    // Load model objects into the adapter which displays them
                    for (BoxOfficeMovie mov : movies) {
                        System.out.println("@moviessize " + movies.size());
                        fetchMovieGenres(mov);
                        for (long i = 0; i < 100000000; i++) {} // delay so that we can succeed
                    }

                    adapterMovies.addAll(movies);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private ArrayList<BoxOfficeMovie> filterMovies(){
        getPreferences();
        ArrayList<BoxOfficeMovie> filtered = new ArrayList<BoxOfficeMovie>();
        ArrayList<BoxOfficeMovie> filtered2 = new ArrayList<BoxOfficeMovie>();
        ArrayList<BoxOfficeMovie> filtered3 = new ArrayList<BoxOfficeMovie>();

        if(genres[0] != null && movies.size() != 0){
            for(BoxOfficeMovie b : movies){
                for(int j = 0; j < genres.length; j += 1){
                    if(genres[j] == null){
                        break;
                    }
                    String movieGenre = b.getGenres();
                    String[] g = movieGenre.split(", ");
                    boolean genre = false;
                    for(int i = 0; i < g.length; i += 1){
                        if(g[i].equals(genres[j])){
                            genre = true;
                            break;
                        }
                    }

                    if(genre){
                        filtered.add(b);
                        break;
                    }
                }
            }
         }else{
            filtered = movies;
         }

        if(mpaaRatings[0] != null && movies.size() != 0){
           for(BoxOfficeMovie b : movies){
                 boolean rating = false;
                for(int i = 0; i < mpaaRatings.length; i += 1){
                    if(mpaaRatings[i] == null){
                        break;
                    }
                    if(b.getMpaaRating().equals(mpaaRatings[i])){
                        rating = true;
                        break;
                    }
                }

                if(rating){
                    filtered2.add(b);
                }
           }
        }else{
            filtered2 = filtered;
        }

        if(rating != 0 && movies.size() != 0) {
            for (BoxOfficeMovie b : filtered2) {
                if (b.getCriticsScore() >= rating) {
                    filtered3.add(b);
                }
            }
        }else{
            filtered3 = filtered2;
        }

        return filtered3;
    }

    private void getPreferences(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        String id = LoginActivity.id;

        String selected = sharedPref.getString(id, "");
        String[] allGenres = getResources().getStringArray(R.array.genres);
        if(!selected.equals("")) {
            ArrayList<Integer> pos = new ArrayList<>();

            String[] positions = selected.split(";");

            for (int i = 0; i < positions.length; i += 1) {
                pos.add(Integer.valueOf(positions[i]));
            }

            int x = 0;
            for(Integer i: pos){
                switch(allGenres[i]){
                    case "Action and Adventure":
                        allGenres[i] = "Action & Adventure";
                        break;
                    case "Art House and International Movies":
                        allGenres[i] = "Art House & International Movies";
                        break;
                    case "Kids and Family":
                        allGenres[i] = "Kids & Family";
                        break;
                    case "Musical and Performing Arts":
                        allGenres[i] = "Musical & Performing Arts";
                        break;
                    case "Mystery and Suspense":
                        allGenres[i] = "Mystery & Suspense";
                        break;
                    case "Science Fiction and Fantasy":
                        allGenres[i] = "Science Fiction & Fantasy";
                        break;
                    case "Sports and Fitness":
                        allGenres[i] = "Sports & Fitness";
                        break;
                    default:
                        allGenres[i] = allGenres[i];
                        break;
                }

                genres[x]=allGenres[i];
                x += 1;
            }
        }

        String s = sharedPref.getString(id+"1",  "");
        if(!s.equals("")) {
            mpaaRatings = s.split(";");
            for(int i = 0; i < mpaaRatings.length; i += 1){
                if(mpaaRatings[i].equals("PG13")){
                    mpaaRatings[i]="PG-13";
                }

                if(mpaaRatings[i].equals("NC17")){
                    mpaaRatings[i] = "NC-17";
                }
            }
        }


        String t = sharedPref.getString(id+"2", "");
        if(!t.equals("")){
            switch (t){
                case "20% - 100%":
                    rating = 20;
                    break;
                case "40% - 100%":
                    rating = 40;
                    break;
                case "60% - 100%":
                    rating = 60;
                    break;
                case "80% - 100%":
                    rating = 80;
                    break;
                default:
                    rating = 0;
                    break;
            }
        }

        /*System.out.println("Saved Genres:");
        for(int i = 0; i < genres.length; i += 1){
            if(genres[i] == null){
                break;
            }
            System.out.println(genres[i]);
        }

        System.out.println("Saved MPAA Ratings:");
        for(int i = 0; i < mpaaRatings.length; i += 1){
            if(mpaaRatings[i] == null){
                break;
            }
            System.out.println(mpaaRatings[i]);
        }

        System.out.println("Saved Critics Score");
        System.out.println(rating);*/
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



