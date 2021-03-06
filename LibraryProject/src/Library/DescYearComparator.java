package Library;

import java.util.Comparator;

/*
 * @authors
 * Oisin Murphy - D00191700
 * Patricia Bere - D00193593
 */

public class DescYearComparator implements Comparator<Items> { 
    @Override
    public int compare(Items o1, Items o2) {
        return o2.getReleaseYear() - o1.getReleaseYear();
    }
}
