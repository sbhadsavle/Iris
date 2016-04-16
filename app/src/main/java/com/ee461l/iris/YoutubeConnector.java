package com.ee461l.iris;

import android.content.Context;
import android.util.Log;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 3/21/2016.
 */

public class YoutubeConnector {
    private YouTube youtube;
    private YouTube.Search.List query;

    // Your developer key goes here
    public static final String KEY
            = "AIzaSyA7WOh5PmngaXw7BmvLWKq3JJlMJi055Hc";

    public YoutubeConnector() {
        youtube = new YouTube.Builder(new NetHttpTransport(),
                new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest hr) throws IOException {}
        }).build();

        try{
            query = youtube.search().list("id,snippet");
            query.setKey(KEY);
            query.setType("video");
            query.setFields("items(id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url)");
        }catch(IOException e){
            Log.d("YC", "Could not initialize: " + e);
        }
    }

    public Video search(String keywords) {
        query.setQ(keywords);
        try {
            SearchListResponse response = query.execute();
            List<SearchResult> results = response.getItems();

            List<Video> items = new ArrayList<Video>();
           /* for(SearchResult result:results){
                Video item = new Video();
                item.setTitle(result.getSnippet().getTitle());
                item.setDescription(result.getSnippet().getDescription());
                item.setThumbnailURL(result.getSnippet().getThumbnails().getDefault().getUrl());
                item.setId(result.getId().getVideoId());
                items.add(item);
            }*/
            Video item = new Video();
            SearchResult result = results.get(0);
            item.setTitle(result.getSnippet().getTitle());
            item.setDescription(result.getSnippet().getDescription());
            item.setThumbnailURL(result.getSnippet().getThumbnails().getDefault().getUrl());
            item.setId(result.getId().getVideoId());
            return item;
        } catch (IOException e) {
            Log.d("YC", "Could not search: " + e);
            return null;
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }

    }
}