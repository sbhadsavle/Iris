package com.ee461l.iris;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by sarang on 3/31/2016.
 */
public class ExclusiveContentFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    private ImageView ivPosterImage;
    private TextView tvTitle;
    private TextView tvSynopsis;
    private TextView tvCast;
    private TextView tvAudienceScore;
    private TextView tvCriticsScore;
    private TextView tvCriticsConsensus;

    public static ExclusiveContentFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ExclusiveContentFragment fragment = new ExclusiveContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_box_office_detail, container, false);
        //TextView textView = (TextView) view;
        //textView.setText("Fragment #" + mPage);
        // Fetch views
        ivPosterImage = (ImageView) view.findViewById(R.id.ivPosterImage);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvSynopsis = (TextView) view.findViewById(R.id.tvSynopsis);
        tvCast = (TextView) view.findViewById(R.id.tvCast);
        tvCriticsConsensus = (TextView) view.findViewById(R.id.tvCriticsConsensus);
        tvAudienceScore =  (TextView) view.findViewById(R.id.tvAudienceScore);
        tvCriticsScore = (TextView) view.findViewById(R.id.tvCriticsScore);
        // get the movie
        BoxOfficeMovie movie = ExploreMovieActivity.getTheMovie();
        // load it
        loadMovie(movie);
        return view;
    }

    // Populate the data for the movie
    @SuppressLint("NewApi")
    public void loadMovie(BoxOfficeMovie movie) {
//        if (android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.HONEYCOMB) {
//            getActionBar().setTitle(movie.getTitle());
//        }
        // Populate data
        tvTitle.setText(movie.getTitle());
        tvCriticsScore.setText(Html.fromHtml("<b>Critics Score:</b> " + movie.getCriticsScore() + "%"));
        tvAudienceScore.setText(Html.fromHtml("<b>Audience Score:</b> " + movie.getAudienceScore() + "%"));
        tvCast.setText(movie.getCastList());
        tvSynopsis.setText(Html.fromHtml("<b>Synopsis:</b> " + movie.getSynopsis()));
        tvCriticsConsensus.setText(Html.fromHtml("<b>Consensus:</b> " + movie.getCriticsConsensus()));
        // R.drawable.large_movie_poster from
        // http://content8.flixster.com/movie/11/15/86/11158674_pro.jpg -->
        Picasso.with(getActivity()).load(movie.getLargePosterUrl()). // CONTEXT ???
                placeholder(R.drawable.large_movie_poster).
                into(ivPosterImage);
    }
}
