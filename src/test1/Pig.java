package test1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pig {
	
	double pigX, pigY;
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
	

	public double getPigX() {
		return pigX;
	}

    public void draw(Graphics2D g) {
    	
//        g.setColor(Color.GREEN);
//        g.fillOval((int) getPigX() - 20, (int) getPigY() - 20, 40, 40);
    		g.drawImage(bufferI, null, ((int) getPigX())-65, ((int) getPigY()) -100);
    }
	
	public void setPigX(double pigX) {
		this.pigX = pigX;
	}

	public double getPigY() {
		return pigY;
	}

	public void setPigY(double pigY) {
		this.pigY = pigY;
	}
	
}
