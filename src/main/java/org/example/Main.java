package org.example;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NullPointerException, RuntimeException {
        System.out.println("Hello world!");
        String COMMA_DELIMITER = ",";
        List<M> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Dateien/movies.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(   COMMA_DELIMITER);
                records.add(new M(){{
                    this.id = String.valueOf(values[0]);
                    this.title = values[1];
                }});
            }

            System.out.println("Bitte geben Sie die Filmnummer ein, zu der Sie eine Bewertung wünschen");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            String name = reader.readLine();

            if (name != "") {
                for (M record : records) {
                    M gefundenerRecord = new M() {{
                        this.title = "keiner";
                    }};
                    if (record.id.equals(name)) {
                        System.out.println("Es wurde der Film: " + record.title + " ausgewählt.");
                        gefundenerRecord = record;
                    }

            if (gefundenerRecord.title.equals("keiner")) {
                // nichts zu tun
            } else {
                List<R> rs2 = new ArrayList<>();
                try (BufferedReader br2 = new BufferedReader(new FileReader("Dateien/ratings.csv"))) {
                    String zeile;
                    while ((zeile = br2.readLine()) != null) {
                        String[] values = zeile.split(  COMMA_DELIMITER);
                        rs2.add(new R(){{
                            this.mId = values[1];
                            this.vote = values[2];
                        }});
                    }

                            System.out.println(gefundenerRecord);
                    double gesamt = 0;
                    int iterator = 0;
                    for (R r2 : rs2) {
                        if (r2.mId.toString().equals(gefundenerRecord.id.toString())) {
                            if (gefundenerRecord.ratings == null) {
                                gefundenerRecord.ratings = new String[] { r2.vote };
                            }
                            else {
                                String[] temp = 
                                Arrays.copyOf(gefundenerRecord.ratings, gefundenerRecord.ratings.length + 1);
                                
                                temp[temp.length -1] = r2.vote;
                                gefundenerRecord.ratings = temp;
                                ++iterator;
//                                System.out.println("Temp: " + Arrays.toString(temp));
//                                System.out.println("Ratings: " + Arrays.toString(gefundenerRecord.ratings));
                            }
                            double sum = 0;
                            for (int i = 0; i < gefundenerRecord.ratings.length; i++) {
                                sum = sum + Double.parseDouble(gefundenerRecord.ratings[i]);
                            }
                            gefundenerRecord.avRa = sum / gefundenerRecord.ratings.length;

                        }
                    }
                    
                    
                    
                    DecimalFormat f = new DecimalFormat("#0.00");
                    System.out.println("Die Bewertung für den Film ist: " + f.format(gefundenerRecord.avRa));

                } catch (Exception e) {
                    throw e;
                }
            }
                }
            }
        } catch (Exception e) {
            System.out.println("No File Found");
            System.err.println(e.toString());
        }
    }
}
