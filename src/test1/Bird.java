package test1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird extends Element{
    double x, y, velocityX, velocityY;  // informations relatives à l'oiseau
    BufferedImage bufferI;
    BufferedImage bufferII;
    int type = 1;
    
    public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Bird () {
    	
    	File f = new File ("./angrybirds.png");
    	File f2 = new File ("./bird3.png");
    	
    	try {
			this.bufferI =  ImageIO.read(f);
			this.bufferII =  ImageIO.read(f2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    public void draw(Graphics2D g) {
    	
    	//g.setColor(Color.RED);

        //g.fillOval(((int) getBirdX()) - 20, ((int) getBirdY()) - 20, 40, 40);
    	if (type == 1) {
    		g.drawImage(bufferI, null, ((int) getX())-65, ((int) getY()) -100);
    	}else if (type == 2){
    		g.drawImage(bufferII,null,((int) getX())-65, ((int) getY()) -100);
    	}
    	
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
	
	public int CollisionWith(Element e) { 
		
		return type=2;
		}
	
}