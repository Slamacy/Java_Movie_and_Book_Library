package Library;
import java.util.ArrayList;

/*
 * @authors
 * Oisin Murphy - D00191700
 * Patricia Bere - D00193593
 */

public class Library {
    private ArrayList<Items> contents;
    
    public Library() {
        contents = new ArrayList<>();
    }
    
    public void addToLib(Items item) {
        contents.add(item);
    }
    
    public ArrayList<Items> getArray() {
        return contents;
    }
    
    public int getSize() {
        return contents.size();
    }
    
    public Items get(int i) {
        return contents.get(i);
    }
    
    public void setType(int i, String newType) {
        contents.get(i).setType(newType);
    }
    
    public void setName(int i, String newName) {
        contents.get(i).setName(newName);
    }
    
    public void setGenre(int i, String newGenre) {
        contents.get(i).setGenre(newGenre);
    }
    
    public void setCreator(int i, String newCreator) {
        contents.get(i).setCreator(newCreator);
    }
    
    public void setDescription(int i, String newDescription) {
        contents.get(i).setDescription(newDescription);
    }
    
    public void setReleaseYear(int i, int newReleaseYear) {
        contents.get(i).setReleaseYear(newReleaseYear);
    }
    
    public void setFee(int i, float newFee) {
        contents.get(i).setFee(newFee);
    }
    
    public void setPageCount(int i, int newCount) {
        Items item = contents.get(i);
        if(item instanceof Book)
        {
            Book bItem = (Book)item;
            bItem.setPageCount(newCount);
        }
    }
    
    public void setActors(int i, String[] newActors) {
        Items item = contents.get(i);
        if(item instanceof Movie)
        {
            Movie mItem = (Movie)item;
            mItem.addActors(newActors);
        }
    }
    
    public void setRunningTime(int i, String newRunningTime) {
        Items item = contents.get(i);
        if(item instanceof Movie)
        {
            Movie mItem = (Movie)item;
            mItem.setRunningTime(newRunningTime);
        }
    }
    
    public Book getBook(int i) {
        return (Book)contents.get(i);
    }
    
    public Movie getMovie(int i) {
        return (Movie)contents.get(i);
    }
    
    //used for debugging
    public void printArray() {
        int i = 1;
        for (int j = 0; i < contents.size(); i++) {
            System.out.println("Library contents[" + i + "] :" + contents.get(j).toString());
        }
    }
}
