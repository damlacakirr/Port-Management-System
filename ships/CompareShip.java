
package ships;
import java.util.*;



public class CompareShip implements Comparator<Ship>{
	
	public int compare(Ship s1, Ship s2) {
        return (s1.getID() - s2.getID());
    }
}