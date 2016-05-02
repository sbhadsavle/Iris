package com.ee461l.iris;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
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
    //private Video searchResult;
    private List<Video> searchResult;

    private ImageView thumbnail;
    private ImageView thumbnail2;

    private TextView trailer_title;
    private TextView trailer_title2;

    private TextView d1;
    private TextView d2;

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
        trailer_title2 = (TextView)view.findViewById(R.id.video_title2);
        thumbnail = (ImageView)view.findViewById(R.id.video_thumbnail);
        thumbnail2 = (ImageView)view.findViewById(R.id.video_thumbnail2);
        d1 = (TextView)view.findViewById(R.id.description1);
        d2 = (TextView)view.findViewById(R.id.description2);

        while(searchResult == null){} // wait for results
        // display trailers;
        trailer_title.setText(searchResult.get(0).getTitle());
        trailer_title2.setText(searchResult.get(1).getTitle());

        d1.setText(Html.fromHtml("<i>" + searchResult.get(0).getDescription() + "</i>"));
        d2.setText(Html.fromHtml("<i>" + searchResult.get(1).getDescription() + "</i>"));

        Picasso.with(getActivity()).load(searchResult.get(0).getThumbnailURL()).
                placeholder(R.drawable.large_movie_poster).into(thumbnail);
        addClickListener();
        Picasso.with(getActivity()).load(searchResult.get(1).getThumbnailURL()).
                placeholder(R.drawable.large_movie_poster).into(thumbnail2);
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
                                                                   intent.putExtra("VIDEO_ID", searchResult.get(0).getId());
                                                                   startActivity(intent);
                                                               }
                                                           }
                                                       }

        );

        thumbnail2.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Context context = getActivity();
                                             if (context != null) {
                                                 Intent intent = new Intent(context, YoutubePlayerActivity.class);
                                                 intent.putExtra("VIDEO_ID", searchResult.get(1).getId());
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