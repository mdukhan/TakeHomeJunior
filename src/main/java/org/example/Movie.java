package org.example;

import java.util.ArrayList;
import java.util.List;

public class Movie implements DataItem  {

    private String movieId;
    private String title;

    private String[] ratings;

    private double avRa;

    public String getTitle() {
        return title;
    }

    public String[] getRatings() {
        return ratings;
    }

    public String getMovieId() {
        return movieId;
    }

    public double getAvRa() {
        return avRa;
    }

    public void setRatings(String[] ratings) {
        this.ratings = ratings;
    }

    public void setAvRa(double avRa) {
        this.avRa = avRa;
    }

    @Override
    public void initialize(String[] values) {
        this.movieId = String.valueOf(values[0]);
        this.title = values[1];
    }
}
