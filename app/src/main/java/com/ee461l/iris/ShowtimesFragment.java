package com.ee461l.iris;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

//        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; CPU iPhone OS 8_3 like Mac OS X) AppleWebKit/600.14 (KHTML, like Gecko) Mobile/12F70");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        String newUA= "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0";
        webView.getSettings().setUserAgentString(newUA);

        //webView.loadUrl("https://www.bing.com/search?q="+currentMovieTitle+"+showtimes");


        Geocoder geocoder = new Geocoder(this.getActivity(), Locale.getDefault());
//        // lat,lng, your current location
//
        List<Address> addresses = null;
        try {addresses = geocoder.getFromLocation(30.2672, -97.7431, 1);}
        catch (IOException e) {e.printStackTrace();}
        String postalCode = addresses.get(0).getPostalCode();

        Date d = new Date();
        String year = (d.getYear() + 1900) + "";
        String month = String.format("%02d", d.getMonth() + 1);
        String day = String.format("%02d", d.getDate());
        String fullDate = year + month + day;

        webView.loadUrl("http://www.rottentomatoes.com/showtimes/?zipcode=78705&day=20160416&movies=%5B771370507%5D&limit=20");
        return view;
    }



}
