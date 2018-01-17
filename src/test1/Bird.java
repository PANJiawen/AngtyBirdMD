package test1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird {
    double birdX, birdY, velocityX, velocityY;  // informations relatives à l'oiseau
    BufferedImage bufferI;
    
    public Bird () {
    	File f = new File ("./angrybirds.png");
    	
    	try {
			this.bufferI =  ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    public void draw(Graphics2D g) {
    	
    	//g.setColor(Color.RED);

        //g.fillOval(((int) getBirdX()) - 20, ((int) getBirdY()) - 20, 40, 40);
    	g.drawImage(bufferI, null, ((int) getBirdX())-65, ((int) getBirdY()) -100);
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