package org.example;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");

        //1
        List<Movie> movies = Util.CSVFileReader(Constants.MoviesPath, Movie::new);

        //2
        String movieId = Util.readInputFromUser();

        //3
        Movie selectedMovie = Util.findMoviebyId(movies, movieId);

        //4
        List<Rating> ratings = Util.CSVFileReader(Constants.RatingsPath, Rating::new);

        //5
        Util.calculateAverageRating(selectedMovie, ratings);
    }
}
