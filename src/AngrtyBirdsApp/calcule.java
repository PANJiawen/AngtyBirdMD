package AngrtyBirdsApp;

import java.util.Random;

public class calcule {
    public static int  nombre() {
    	
        int max=10;
        int min=1;
        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }
    
    public static double distance(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    public static double element_distance (Element e1, Element e2) {
    	return distance (e1.getX(), e1.getY(), e2.getX(), e2.getY());
    }
}
