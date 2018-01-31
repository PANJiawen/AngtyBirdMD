package test1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DirectionManager {


	
	BufferedImage bufferI;
	
	
    public DirectionManager () {
    	File f = new File ("./angrybirds.png");
    	
    	try {
			this.bufferI =  ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	
    }
    
    public void draw(Graphics2D g,int birdx,int birdy, int mousex, int mousey) {
    	

    	g.drawLine((int)birdx, (int) birdy, mousex, mousey); 
//    	g.drawArc(55, 55, 55, 55, mousex, mousey);



  }
	
}
