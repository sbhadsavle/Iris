package com.ee461l.iris;

import android.util.Log;

import com.google.api.client.http.HttpResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sarang on 4/25/2016.
 */
public class parser_Json {
    public static JSONObject getJSONfromURL(String url) {

        // initialize
        InputStream is = null;
        String result = "";
        JSONObject jObject = null;

        // http post
        try {
            URL newurl = new URL(url);
            URLConnection urlConnection = newurl.openConnection();

//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPost httppost = new HttpPost(url);
//            HttpResponse response = httpclient.execute(httppost);
//            HttpEntity entity = response.getEntity();
//            is = entity.getContent();
            is = new BufferedInputStream(urlConnection.getInputStream());
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }

        // convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObject = new JSONObject(result);
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }

        return jObject;
    }

}
