package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Supplier;

public class Util {


    public static Movie findMovieById(List<Movie> movies, String movieId) {
        for (Movie movie : movies) {
            if (movie.getMovieId().equals(movieId)) {
                System.out.println("Es wurde der Film: " + movie.getTitle() + " ausgewählt.");
                return movie;
            }
        }
        System.out.println("Sorry! Der FilmID "+ movieId +" wurde leider nicht gefunden.");
        return null;
    }

    public static double calculateAverageRating(Movie selectedMovie,List<Rating> ratings) {
        Double averageRating=0.0;
            if (selectedMovie != null&&!selectedMovie.getTitle().equals("keiner")) {
                    for (Rating rating : ratings) {
                        if (rating.getMovieId().equals(selectedMovie.getMovieId())) {
                            selectedMovie.getRatings().add(rating.getRating());
                            averageRating = calculateAverage(selectedMovie.getRatings());
                        }
                    }
                selectedMovie.setAverageRating(averageRating);
                System.out.println("Die Bewertung für den Film ist: " + Constants.decimalFormat.format(averageRating));
            }
        return averageRating;
    }

    private static double calculateAverage(List<String> ratings) {
        double sum = 0.0;
        for (String rating : ratings) {
            sum += Double.parseDouble(rating);
        }
        return sum / ratings.size();
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
        } catch (IOException e) {
            System.out.println("Ein Fehler beim Lesen von einer CSV Datei ist aufgetreten: " + e.getMessage());
        }
        return data;
    }

    public static String readInputFromUser() {
        System.out.println("Bitte geben Sie die Filmnummer ein, zu der Sie eine Bewertung wünschen");
        String input = null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            input = reader.readLine();
            if(input.isEmpty())
                throw new IllegalArgumentException("Die Suche nach einem Film mit leerem String ist fehlgeschlagen. Bitte versuchen Sie es erneut!");
            else if(!isNumeric(input))
                throw new IllegalArgumentException("Nur Zahlen können eingegeben werden. Bitte versuchen Sie es erneut!");
        } catch (IOException e) {
            System.out.println("Ein Fehler beim Lesen von Eingaben ist aufgetreten: " + e.getMessage());
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return input;
    }

    public static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true; // Input is a valid integer
        } catch (NumberFormatException e) {
            return false; // Input is not a valid integer
        }
    }
}
