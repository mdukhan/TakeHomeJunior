package org.example;

public class Rating implements DataItem {

    private String rating;
    private String movieId;

    public String getMovieId() {
        return movieId;
    }

    public String getRating() {
        return rating;
    }

    @Override
    public void initialize(String[] values) {
        this.movieId = values[1];
        this.rating = values[2];
    }
}
