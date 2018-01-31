package test1;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.JFrame;
import java.awt.event.*;
import java.io.*;

public class AngryBirds extends Panel implements Runnable, MouseListener, MouseMotionListener {
	Bird my_bird = new Bird();
//	Pig pig1 =  new Pig();
//	Pig pig2 =  new Pig();

	int nb_pigs;
	ArrayList <Pig> pigs;
	CollisionManager collision = new CollisionManager();
	Decor dc =  new Decor();
	Terran tr = new Terran();
	DirectionManager dm =  new  DirectionManager();
	Gravity g = new Gravity();                             // gravit�
    int mouseX, mouseY;                         // position de la souris lors de la s�lection
    String message;                             // message � afficher en haut de l'�cran
    int score;                                  // nombre de fois o� le joueur a gagn�
    boolean gameOver;                           // vrai lorsque le joueur a touch� un bord ou le cochon
    boolean selecting;                          // vrai lorsque le joueur s�lectionne l'angle et la vitesse
    Image buffer;

    // calcule la distance entre deux points

    
    // constructeur
    AngryBirds() {
        g.setGravity(0.1);
        score = 0;
        addMouseListener(this);
        addMouseMotionListener(this);
        
        nb_pigs = calcule.nombre();
        pigs = new ArrayList<Pig>();
        
        for (int i = 0; i < nb_pigs; i++) 
        	pigs.add(new Pig());       
        init();
        new Thread(this).start();
    }

    // gestion des �v�nements souris
    public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) {
        if(gameOver) {
            init();
        } else if(selecting) {
        	my_bird.setVelocityX((my_bird.getX() - mouseX) / 20.0);
        	my_bird.setVelocityY((my_bird.getY()- mouseY)/20.0);
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

    // d�but de partie
    void init() {
        gameOver = false;
        selecting = true;
        my_bird.setX(100);
        my_bird.setY(400);
        my_bird.setVelocityX(0);
        my_bird.setVelocityY(0);       
        collision.addElement(my_bird);
        
        for (int i = 0; i < nb_pigs; i++) {
        	pigs.get(i).setX(Math.random() * 500 + 200);
        	pigs.get(i).setY(480);
        	
        	collision.addElement(pigs.get(i));
        }
        //pig1.setX(Math.random() * 500 + 200);
        //pig1.setY(480);
        //pig2.setX(Math.random() * 500 + 200);
        //pig2.setY(480);
        //pigs.add(pig1);
        //pigs.add(pig2);
        
//        System.out.println("breake");
//        for (int i = 1; i <= clc.nombre();i++) {
//        	System.out.println(i);
//        	
//        	
//        	
//            pig1.setX(Math.random() * 500 + 200);
//            pig1.setY(480);
//        }
        
        
        message = "Choisissez l'angle et la vitesse.";
    }

    // fin de partie
    void stop() {
    	my_bird.setVelocityX(0);
    	my_bird.setVelocityY(0);
        gameOver = true;
    }

    // boucle qui calcule la position de l'oiseau en vol, effectue l'affichage et teste les conditions de victoire
    public void run() {
        while(true) {
            // un pas de simulation toutes les 10ms
            try { Thread.currentThread().sleep(10); } catch(InterruptedException e) { }

            if(!gameOver && !selecting) {

                // moteur physique
            	my_bird.setX(my_bird.getX()+my_bird.getVelocityX());
            	my_bird.setY(my_bird.getY()+my_bird.getVelocityY());
            	my_bird.setVelocityY(my_bird.getVelocityY()+g.getGravity());

                // conditions de victoire
            	if (collision.checkCollision() == 1) {
            		stop();
                	
                    message = "Gagn� : cliquez pour recommencer.";
                    score++;
            	}
//            	if(calcule.distance(my_bird.getX(), my_bird.getY(), pig1.getX(), pig1.getY()) < 65
//            			||calcule.distance(my_bird.getX(), my_bird.getY(), pig2.getX(), pig2.getY()) < 65) {
//
//                } 
            	else if(my_bird.getX() < 20 || my_bird.getX() > 780 || 
                		my_bird.getY() < 0 || my_bird.getY() > 480) {
                    stop();
                    message = "Perdu : cliquez pour recommencer.";
                }

                // redessine
                repaint();
            }
        }
    }

    // �vite les scintillements
    public void update(Graphics g) {
        paint(g);
    }

    // dessine le contenu de l'�cran dans un buffer puis copie le buffer � l'�cran
    public void paint(Graphics g2) {
        if(buffer == null) buffer = createImage(800, 600);
        Graphics2D g = (Graphics2D) buffer.getGraphics();

        // fond
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        tr.draw(g);

        // d�cor
        dc.draw(g);
        
        g.setColor(Color.BLACK);

        if(selecting)
        	{
        	dm.draw(g, (int)my_bird.getX(), (int) my_bird.getY(), mouseX, mouseY);
        	}
        my_bird.draw(g);
        // cochon
        
//        pig1.draw(g);
//        pig2.draw(g);
        
        for (int i = 0; i < nb_pigs; i++)
        	pigs.get(i).draw(g);

        // messages
        g.setColor(Color.BLACK);
        g.drawString(message, 300, 100);
        g.drawString("score: " + score, 20, 20);

        // affichage � l'�cran sans scintillement
        g2.drawImage(buffer, 0, 0, null);
    }

    // taille de la fen�tre
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    // met le jeu dans une fen�tre
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