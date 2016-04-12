package com.ee461l.iris;

        import com.loopj.android.http.AsyncHttpClient;
        import com.loopj.android.http.JsonHttpResponseHandler;
        import com.loopj.android.http.RequestParams;

public class RottenTomatoesClient {
    private final String API_KEY = "3zwzy3u52sevavjyn444a3ta"; // Sarang's API Key
    private final String API_BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";
    private AsyncHttpClient client;

    public RottenTomatoesClient() {
        this.client = new AsyncHttpClient();
    }

    // http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=<key>
    public void getBoxOfficeMovies(JsonHttpResponseHandler handler) {
        //String url = getApiUrl("lists/movies/box_office.json");
        String url = getApiUrl("lists/movies/in_theaters.json");
        RequestParams params = new RequestParams("apikey", API_KEY);
        client.get(url, params, handler);
    }

    private String getApiUrl(String relativeUrl) {

        return API_BASE_URL + relativeUrl;
    }
}







// Sarang's key: 3zwzy3u52sevavjyn444a3ta
// (*)default key: 9htuhtcb4ymusd73d4z6jxcj

