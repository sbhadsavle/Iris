package com.ee461l.backend;

import java.util.ArrayList;

/**
 * Created by Jason Cai on 3/11/2016.
 */
public class RegisteredUser {
    private String ID;
    private ArrayList<String> ratedMovies = new ArrayList<String>();
    private ArrayList<Integer> ratings = new ArrayList<Integer>();
    private ArrayList<String> likedGenres = new ArrayList<String>();
    private ArrayList<Integer> genreRatings = new ArrayList<Integer>();

    private RegisteredUser(){}

    public RegisteredUser(String ID){
        this.ID = "Yolo";
    }

    public String getEmail(){
        return ID;
    }

    public void rateMovie(String title, ArrayList<String> genres){
        if(ratedMovies.contains(title)){
           //return;
        }else{
            ratedMovies.add(title);
            for(String g : genres){
                if(!likedGenres.contains(g)){
                    likedGenres.add(g);
                }
            }
        }
    }

    public void recommend(){

    }

}
