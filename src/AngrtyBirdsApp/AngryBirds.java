package AngrtyBirdsApp;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.JFrame;

import java.awt.event.*;
import java.io.*;

public class AngryBirds extends Panel implements Runnable, MouseListener, MouseMotionListener {

	IElementFactory ef = new ElementFactory();
	
	Element ebird = ef.createBird();
	Element dc =  ef.createDecor();
	Element tr = ef.createTerran();
	Element g = ef.createGravity(); 
	ArrayList <Element> pigs;
	
	int nb_pigs;	
	CollisionManager collision = new CollisionManager();
	DirectionManager dm =  new  DirectionManager();
    int mouseX, mouseY;                         // position de la souris lors de la sélection
    String message;                             // message à afficher en haut de l'écran
    int score;                                  // nombre de fois où le joueur a gagné
    boolean gameOver;                           // vrai lorsque le joueur a touché un bord ou le cochon
    boolean selecting;                          // vrai lorsque le joueur sélectionne l'angle et la vitesse
    Image buffer;
 
    // constructeur
    AngryBirds() {
        g.setGravity(0.1);
        score = 0;
        addMouseListener(this);
        addMouseMotionListener(this);
        
        ef.createBird();
        nb_pigs = calcule.nombre();
        pigs = new ArrayList<Element>();
        
        for (int i = 0; i < nb_pigs; i++) 
        	pigs.add(ef.createPig());       
        init();
        new Thread(this).start();
    }

    // gestion des événements souris
    public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) {
        if(gameOver) {
            init();
        } else if(selecting) {
        	ebird.setVelocityX((ebird.getX() - mouseX) / 20.0);
        	ebird.setVelocityY((ebird.getY()- mouseY)/20.0);
            message = "L'oiseau prend sont envol";
            selecting = false;
        }
        repaint();
    }
    public void mouseDragged(MouseEvent e) { mouseMoved(e); }
    public void mouseMoved(MouseEvent e) { 
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }

    // début de partie
    void init() {
        gameOver = false;
        selecting = true;
        ebird.setX(100);
        ebird.setY(400);
        ebird.setVelocityX(0);
        ebird.setVelocityY(0);       
        collision.addElement(ebird);
        
        for (int i = 0; i < nb_pigs; i++) {
        	pigs.get(i).setX(Math.random() * 500 + 200);
        	pigs.get(i).setY(480);
        	
        	collision.addElement(pigs.get(i));
        }

        message = "Choisissez l'angle et la vitesse.";
    }

    // fin de partie
    void stop() {
    	ebird.setVelocityX(0);
    	ebird.setVelocityY(0);
        gameOver = true;
    }

    // boucle qui calcule la position de l'oiseau en vol, effectue l'affichage et teste les conditions de victoire
    public void run() {
        while(true) {
            // un pas de simulation toutes les 10ms
            try { Thread.currentThread().sleep(10); } catch(InterruptedException e) { }

            if(!gameOver && !selecting) {

                // moteur physique
            	ebird.setX(ebird.getX()+ebird.getVelocityX());
            	ebird.setY(ebird.getY()+ebird.getVelocityY());
            	ebird.setVelocityY(ebird.getVelocityY()+g.getGravity());

                // conditions de victoire
            	if (collision.checkCollision() == 1) {
            		stop();
                	
                    message = "Gagné : cliquez pour recommencer.";
                    score++;
            	}

            	else if(ebird.getX() < 20 || ebird.getX() > 780 || 
            			ebird.getY() < 0 || ebird.getY() > 480) {
                    stop();
                    message = "Perdu : cliquez pour recommencer.";
                }

                // redessine
                repaint();
            }
        }
    }

    // évite les scintillements
    public void update(Graphics g) {
        paint(g);
    }

    // dessine le contenu de l'écran dans un buffer puis copie le buffer à l'écran
    public void paint(Graphics g2) {
        if(buffer == null) buffer = createImage(800, 600);
        Graphics2D g = (Graphics2D) buffer.getGraphics();

        // fond
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        tr.draw(g);

        // décor
        dc.draw(g);
        
        g.setColor(Color.BLACK);

        if(selecting)
        	{
        	dm.draw(g, (int)ebird.getX(), (int) ebird.getY(), mouseX, mouseY);
        	}
        ebird.draw(g);
        // cochon
     
        for (int i = 0; i < nb_pigs; i++)
        	pigs.get(i).draw(g);

        // messages
        g.setColor(Color.BLACK);
        g.drawString(message, 300, 100);
        g.drawString("score: " + score, 20, 20);

        // affichage à l'écran sans scintillement
        g2.drawImage(buffer, 0, 0, null);
    }

    // taille de la fenêtre
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    // met le jeu dans une fenêtre
    public static void main(String args[]) {
        Frame frame = new Frame("Oiseau pas content");
        final AngryBirds obj = new AngryBirds();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
        frame.add(obj);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}