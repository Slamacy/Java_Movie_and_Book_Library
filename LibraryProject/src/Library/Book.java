package Library;

/*
 * @authors
 * Oisin Murphy - D00191700
 * Patricia Bere - D00193593
 */

public class Book extends Items {
    private int pageCount;
    
    public Book() {
        pageCount = 0;
    }
    
    public Book(String type, String name, String genre, String creator, String description, int releaseYear, float fee, int pageCount, int rating) {
        super(type, name, genre, creator, description, releaseYear, fee, rating);
        this.pageCount = pageCount;
    }
    
    public int getPageCount() {
        return this.pageCount;
    }
    
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nPage Count: " + this.getPageCount();
    }
    
    @Override 
    public int hashCode() {
        int hc = super.hashCode();
        for (int i = 0; i < pageCount; i++) {
            hc += pageCount;
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
        Book otherP = (Book)other;
        if (!this.getName().equals(otherP.getName())) {
            return false;
        }
        else if (this.getReleaseYear() != (otherP.getReleaseYear())) {
            return false;
        }
        return true;
    }
}
