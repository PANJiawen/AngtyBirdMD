package test1;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bird {
    double birdX, birdY, velocityX, velocityY;  // informations relatives à l'oiseau
    
    public void draw(Graphics2D g) {
    	
    	g.setColor(Color.RED);

        g.fillOval(((int) getBirdX()) - 20, ((int) getBirdY()) - 20, 40, 40);
    }
    
	public double getBirdX() {
		return birdX;
	}

	public void setBirdX(double birdX) {
		this.birdX = birdX;
	}

	public double getBirdY() {
		return birdY;
	}

	public void setBirdY(double birdY) {
		this.birdY = birdY;
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
}