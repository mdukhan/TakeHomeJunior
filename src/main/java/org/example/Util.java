package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Util {


    public static Movie findMoviebyId(List<Movie> movies, String movieId) {
        for (Movie movie : movies) {
            if (movie.getMovieId().equals(movieId)) {
                System.out.println("Es wurde der Film: " + movie.getTitle() + " ausgewählt.");
                return movie;
            }
        }
        System.out.println("Sorry! Der FilmId"+ movieId +"wurde leider nicht gefunden.");
        return null;
    }


    public static double calculateAverageRating(Movie selectedMovie,List<Rating> ratings) {
        Double avRating=0.0;
            if (selectedMovie != null&&!selectedMovie.getTitle().equals("keiner")) {
                    for (Rating rating : ratings) {
                        if (rating.getMovieId().equals(selectedMovie.getMovieId())) {
                            if (selectedMovie.getRatings() == null) {
                                selectedMovie.setRatings(new String[]{rating.getRating()});
                            } else {
                                selectedMovie.setRatings(Arrays.copyOf(selectedMovie.getRatings(), selectedMovie.getRatings().length + 1));
                                selectedMovie.getRatings()[selectedMovie.getRatings().length - 1] = rating.getRating();
                            }
                            double sum = 0;
                            for (int i = 0; i < selectedMovie.getRatings().length; i++) {
                                sum = sum + Double.parseDouble(selectedMovie.getRatings()[i]);
                            }
                            avRating=sum / selectedMovie.getRatings().length;
                        }
                    }
                selectedMovie.setAvRa(avRating);
                System.out.println("Die Bewertung für den Film ist: " + Constants.decimalFormat.format(avRating));
            }
        return avRating;
    }

    public static <T extends DataItem> List<T> CSVFileReader(String filePath, Supplier<T> itemSupplier) {
        List<T> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(Constants.COMMA_DELIMITER);
                T item = itemSupplier.get();
                item.initialize(values);
                data.add(item);
            }
        } catch (Exception e) {
            System.out.println("Error reading data from CSV: " + e.getMessage());
        }
        return data;
    }

    public static String readInputFromUser() throws IOException {
        System.out.println("Bitte geben Sie die Filmnummer ein, zu der Sie eine Bewertung wünschen");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            input = reader.readLine();
            if(input.equals(""))
                throw new IllegalArgumentException("Cannot search for a movie with empty String. Please try again!");
        } catch (IOException e) {
            System.out.println("Error reading input from console: " + e.getMessage());
        }
        return input;
    }
}
