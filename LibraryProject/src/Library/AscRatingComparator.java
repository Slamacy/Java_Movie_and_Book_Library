package Library;

import java.util.Comparator;

/*
 * @authors
 * Oisin Murphy - D00191700
 * Patricia Bere - D00193593
 */

public class AscRatingComparator implements Comparator<Items> {
    @Override
    public int compare(Items o1, Items o2) {
        return o1.getRating() - o2.getRating();
    }
}    

