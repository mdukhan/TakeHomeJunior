package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Movie implements DataItem  {

    private String movieId;
    private String title;
    private List<String> ratings=new ArrayList<>();
    private double averageRating;

    public String getTitle() {
        return title;
    }

    public List<String> getRatings() {
        return ratings;
    }

    public String getMovieId() {
        return movieId;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public void initialize(String[] values) {
        this.movieId = String.valueOf(values[0]);
        this.title = values[1];
    }
}
