package Library;

/*
 * @authors
 * Oisin Murphy - D00191700
 * Patricia Bere - D00193593
 */

public class Movie extends Items {
    String runningTime;
    String[] actors;
    
    public Movie() {
        this.runningTime = "";
        actors = new String[3];     
    }
 
    public Movie(String type, String name, String genre, String creator, String description, int releaseYear, float fee, String[] actors, String runningTime, int rating) {
        super(type, name, genre, creator, description, releaseYear, fee, rating);
        this.runningTime = runningTime;
        this.actors = actors;
    }

    public String getActors() {
        return actors[0] + ", " + actors[1] + ", " +  actors[2];
    }
    
    public String getActor(int i) {
        return actors[i];
    }

    public void addActors(String[] topActors) {
        for (int i = 0; i < actors.length; i++) {
            actors[i] = topActors[i];
        }
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }
    
    @Override
    public String toString() {
        return super.toString()
                + "\nActors : " + this.getActors()
                + "\nRunning time: " + this.getRunningTime() + " mins";
    }
    
    @Override 
    public int hashCode() {
        int hc = super.hashCode();
        for (int i = 0; i < runningTime.length(); i++) {
            hc += runningTime.charAt(i);
        }
        return hc;
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
        Movie otherP = (Movie)other;
        if (!this.getName().equals(otherP.getName())) {
            return false;
        }
        else if (this.getReleaseYear() != (otherP.getReleaseYear())) {
            return false;
        }
        return true;
    }
}