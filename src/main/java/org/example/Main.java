package org.example;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
                // Step 1: Read movies from CSV file
                List<Movie> movies = Util.CSVFileReader(Constants.MoviesPath, Movie::new);

                // Step 2: Read input from the user
                String movieId = Util.readInputFromUser();

                // Step 3: Find the selected movie
                Movie selectedMovie = Util.findMovieById(movies, movieId);

                // Step 4: Read ratings from CSV file
                List<Rating> ratings = Util.CSVFileReader(Constants.RatingsPath, Rating::new);

                // Step 5: Calculate average rating
                Util.calculateAverageRating(selectedMovie, ratings);
        }
}

