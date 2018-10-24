package Library;

import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * @authors
 * Oisin Murphy - D00191700
 * Patricia Bere - D00193593
 */

public class Items {
    private String type, name, genre, creator, description, state;
    private int releaseYear, rating;
    private float fee;
    ArrayList<Integer> ratings;
    
    //will be called in by books / movies for assigning vars through super();
    public Items() {
        this.type = "";
        this.name = "";
        this.genre = "";
        this.creator = "";
        this.description = "";
        this.releaseYear = 0000;
        this.fee = 0.00f;
        this.state = "";
        this.rating = 0;
        ratings = new ArrayList();
    }
    
    public Items(String type, String name, String genre, String creator, String description, int releaseYear, float fee, int rating) {
        this.type = type;
        this.name = name;
        this.genre = genre;
        this.creator = creator;
        this.description = description;
        this.releaseYear = releaseYear;
        this.fee = fee;
        this.state = "";
        this.rating = rating;
        ratings = new ArrayList();
    }
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
        
    public String getType() {
        return type;
    }
    
    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getCreator() {
        return creator;
    }

    public float getFee() {
        return fee;
    }

    public String getDescription() {
        return description;
    }
    
    public int getReleaseYear() {
        return releaseYear;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }
    
    public void addRating(int rating) {
        ratings.add(rating);
    }
    
    public void calculateRatings() {
        int total = 0, average;
        for (int i : ratings) {
            total += i;
        }
        average = total / ratings.size();
        setRating(average);
    }
    
    public String formatDescription() {
        String fDesc = description;
        StringTokenizer st = new StringTokenizer(fDesc, " ");
        String word, formatLine = "";
        int count = 0;
        while (st.hasMoreTokens()) {
            word = st.nextToken();
            count++;
            formatLine += word + " ";
            if (count % 10 == 0) {
                formatLine += "\n             ";
            }
        }
        return formatLine;
        
        //A BROKEN ATTEMPT
        /*
        StringBuilder sb = new StringBuilder(fDesc);
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            length += word.length();
            count++;
            if (count % 5 == 0) {
                sb.append("\n".charAt(length));
            }
        }
        return fDesc;
        */
    }
    
    @Override
    public int hashCode() {
        int hc = releaseYear;
        for (int i = 0; i < name.length(); i++) {
            hc += name.charAt(i);
        }
        return hc;
    }
    
    @Override
    public String toString() {
        return "Type: " + this.getType()
                + "\nName: " + this.getName()
                + "\nAuthor: " + this.getCreator() 
                + "\nRelease Date: " + this.getReleaseYear()
                + "\nGenre: " + this.getGenre()
                + "\nDescription: " + this.formatDescription()
                + "\nRental fee: $" + this.getFee();
    }
    
    @Override 
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        else if (other == null) {
            return false;
        }
        else if (getClass() != other.getClass()) {
            return false;
        }
        Items otherP = (Items)other;
        if (!name.equals(otherP.getName())) {
            return false;
        }
        else if (releaseYear != (otherP.getReleaseYear())) {
            return false;
        }
        return true;
    }
}
