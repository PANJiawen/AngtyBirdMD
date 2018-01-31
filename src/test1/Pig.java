package test1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pig extends Element{
	
	double x, y;
	BufferedImage bufferI;
	
    public Pig () {
    	File f = new File ("./angrybirds_pig.png");
    	
    	try {
			this.bufferI =  ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	



    public void draw(Graphics2D g) {
    	
//        g.setColor(Color.GREEN);
//        g.fillOval((int) getPigX() - 20, (int) getPigY() - 20, 40, 40);
    		g.drawImage(bufferI, null, ((int) getX())-65, ((int) getY()) -100);
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
	
}
