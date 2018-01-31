package AngrtyBirdsApp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollisionManager {
	ArrayList<Element> elements;
	
	public CollisionManager() {
		elements = new ArrayList<Element>();
	}

	public void addElement(Element e) {
		elements.add(e);
	}
	
	public int checkCollision() {
		for (int i = 0; i < elements.size(); i++) {
			for (int j = i+1; j < elements.size(); j++) {
				if (calcule.element_distance (elements.get(i), elements.get(j)) < 65){
					elements.get(i).CollisionWith(elements.get(j));
					elements.get(j).CollisionWith(elements.get(i));
					
					if (elements.get(i) instanceof Bird && elements.get(j) instanceof Pig)
							return 1;
				}
			}
		}
		return 0;
	}
	
}
