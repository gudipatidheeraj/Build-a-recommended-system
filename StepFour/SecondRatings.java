/**
 * SecondRatings class contains various methods to extract information from the corresponding
 * ArrayLists.
 * 
 * @ Gudipati Dheeraj
 * @ 4/6/2020
 * fs=firstRatings
 **/

import java.util.*;
import java.util.Iterator;
import java.util.ArrayList;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings (String moviefile, String ratingsfile) {
        FirstRatings fs = new FirstRatings ();
        myMovies = fs.loadMovies(moviefile);
        myRaters = fs.loadRaters(ratingsfile);
    }
    
    public int getMovieSize () {
        return myMovies.size();
    }
    
    public int getRaterSize () {
        return myRaters.size();
    }
    
    private double getAverageByID (String id, int minimalRaters) {
        double sum = 0.0;
        int count = 0;
       
        for (Rater rater : myRaters) {
            if (rater.hasRating(id)) {
                sum += rater.getRating(id);
                count += 1;
            }
        }
        
        if (count >= minimalRaters) {
            return sum / count;
        } else {
            return 0.0;
        }
    }
    
    public ArrayList<Rating> getAverageRatings (int minimalRaters) {
        ArrayList<Rating> averageRatings = new ArrayList<Rating> ();
        
        for (Movie movie : myMovies) {
            String movieID = movie.getID();
            //double average = Math.round(getAverageByID(movieID, minimalRaters) * 100.0) / 100.0;
            double average = getAverageByID(movieID, minimalRaters);
            if (average != 0.0) {
                Rating rating = new Rating (movieID, average);
                averageRatings.add(rating);
            }
        }
        
        return averageRatings;
    }
    
    public String getTitle (String id) {
        String title = null;
        
        for (Movie movie : myMovies) {
            if (movie.getID().equals(id)) {
                title = movie.getTitle();
            }
        }
        
        if (title != null) {
            return title;
        } else {
            return "No movie with such ID was found.";
        }
    }
    
    public String getID (String title) {
        String movieID = null;
        
        for (Movie movie : myMovies) {
            if (movie.getTitle().equals(title)) {
                movieID = movie.getID();
            }
        }
        
        if (movieID != null) {
            return movieID;
        } else {
            return "NO SUCH TITLE.";
        }
    }
    
    
}
