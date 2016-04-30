package com.ee461l.iris;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Richard on 4/3/2016.
 */
public class TrailersFragment extends Fragment {
    private Video trailer;
    private int mPage;
    public static final String ARG_PAGE = "ARG_PAGE";
    private Video searchResult;

    private ImageView thumbnail;
    private TextView trailer_title;
    public static TrailersFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        TrailersFragment fragment = new TrailersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_youtube, container, false);
        // get the movie
        BoxOfficeMovie movie = ExploreMovieActivity.getTheMovie();
        //find the movie on youtube
        String title = movie.getTitle();
        title = title + " trailer";
        searchOnYoutube(title);
        //update the view
        trailer_title = (TextView)view.findViewById(R.id.video_title);
        thumbnail = (ImageView)view.findViewById(R.id.video_thumbnail);
        while(searchResult == null){} // wait for results
        // display trailers;
        trailer_title.setText(searchResult.getTitle());
        Picasso.with(getActivity()).load(searchResult.getThumbnailURL()).
                placeholder(R.drawable.large_movie_poster).into(thumbnail);
        addClickListener();
        return view;
    }

    // so that when user clicks it will play the video
    private void addClickListener(){
        thumbnail.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Context context = getActivity();
                                             if (context != null) {
                                                 Intent intent = new Intent(context, YoutubePlayerActivity.class);
                                                 intent.putExtra("VIDEO_ID", searchResult.getId());
                                                 startActivity(intent);
                                             }
                                         }
                                     }

        );
    }



    private void searchOnYoutube(final String keywords){
        new Thread(){
            public void run(){
                YoutubeConnector yc = new YoutubeConnector();
                searchResult = yc.search(keywords);

            }
        }.start();
    }



}