package com.ee461l.iris;

import java.util.ArrayList;

/**
 * Created by Jason Cai on 3/11/2016.
 */
public class RegisteredUser {
    private String ID;
    private String password;
    private ArrayList<BoxOfficeMovie> ratedMovies = new ArrayList<BoxOfficeMovie>();
    private ArrayList<Integer> ratings = new ArrayList<Integer>();
    private ArrayList<String> likedGenres = new ArrayList<String>();
    private ArrayList<Integer> genreRatings = new ArrayList<Integer>();

    private RegisteredUser(){}

    public RegisteredUser(String ID, String password){
        this.ID = ID;
        this.password = password;
    }

    public String getEmail(){
        return ID;
    }

    public boolean login(String pass){
        return this.password.equals(pass);
    }

    public void rateMovie(String title, int rating){
        if(ratedMovies.contains(title)){
           //return;
        }else{
            BoxOfficeMovie movie = new BoxOfficeMovie();
            ratedMovies.add(movie);
            ratings.add(rating);
        }
    }

    public void recommend(){

    }

}
