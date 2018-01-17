package test1;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Decor {

	BufferedImage bufferI;
	
    public Decor () {
    	File f = new File ("./images.jpg");
    	
    	try {
			this.bufferI =  ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	
    }
    
    public void draw(Graphics2D g) {
    	

  		g.drawImage(bufferI, null, 50, 400);
  }
	
}
