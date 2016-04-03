package com.ee461l.iris;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ExploreMovieActivity extends AppCompatActivity {

    private static BoxOfficeMovie theMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_movie);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MovieFragmentPagerAdapter(getSupportFragmentManager(),
                ExploreMovieActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        // assign the movie. NOTE: you need to manually load it. It doesn't load here
        theMovie = (BoxOfficeMovie) getIntent().getSerializableExtra(BoxOfficeActivity.MOVIE_DETAIL_KEY);
    }

    public static BoxOfficeMovie getTheMovie(){
        return theMovie;
    }
}
