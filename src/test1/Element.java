package test1;

import java.awt.Graphics2D;

public class Element {
	double x,y;
	
	public Element() {
	}
	
    public void draw(Graphics2D g) {

    }
    
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int CollisionWith(Element e) { return 1;}
	
}
