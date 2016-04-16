package com.ee461l.iris;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by saran on 4/15/2016.
 */
public class ShowtimesFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static ShowtimesFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ShowtimesFragment fragment = new ShowtimesFragment();
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
        View view = inflater.inflate(R.layout.fragment_showtimes, container, false);
        //TextView textView = (TextView) view;
        //textView.setText("Fragment #" + mPage + "Pizza");
        WebView webView = (WebView) view;
        String currentMovieTitle = ExploreMovieActivity.getTheMovie().getTitle();
        currentMovieTitle = currentMovieTitle.replace(" ", "+");
        webView.loadUrl("https://www.google.com/search?q="+currentMovieTitle+"+showtimes");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }});
        return view;
    }

}
