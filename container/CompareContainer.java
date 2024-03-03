package container;
import java.util.*;

public class CompareContainer implements Comparator<Container>{
	
		public int compare(Container s1, Container s2) {
	        return (s1.getID() - s2.getID());
	    }
	}