package AngrtyBirdsApp;

import java.awt.Graphics2D;

public abstract class Element {
	double x,y,velocityX, velocityY, gravity;
	
	public Element() {
	}
	
    public void draw(Graphics2D g) {

    }
    
	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
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

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	

	
	
	
	public int CollisionWith(Element e) { return 1;}
	
}
