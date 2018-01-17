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
	Pig my_pig =  new Pig();
	Decor dc =  new Decor();
	Terran tr = new Terran();
	
	Gravity g = new Gravity();
//    double gravity;                             // gravité
    int mouseX, mouseY;                         // position de la souris lors de la sélection
    String message;                             // message à afficher en haut de l'écran
    int score;                                  // nombre de fois où le joueur a gagné
    boolean gameOver;                           // vrai lorsque le joueur a touché un bord ou le cochon
    boolean selecting;                          // vrai lorsque le joueur sélectionne l'angle et la vitesse
    Image buffer;
    
//    //BufferedImage bufferI;
//    
//    
//    
//    JFrame jf = new JFrame();
//    MyCanvas mc = new MyCanvas();
    
    
    
    
    
    
    // image pour le rendu hors écran

    // calcule la distance entre deux points
    static double distance(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // constructeur
    AngryBirds() {
        g.setGravity(0.1);
        score = 0;
        addMouseListener(this);
        addMouseMotionListener(this);
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
        	my_bird.setVelocityX((my_bird.getBirdX() - mouseX) / 20.0);
            //velocityX = (birdX - mouseX) / 20.0;
        	my_bird.setVelocityY((my_bird.getBirdY()- mouseY)/20.0);
//            velocityY = (birdY - mouseY) / 20.0;
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
        my_bird.setBirdX(100);
        my_bird.setBirdY(400);
        my_bird.setVelocityX(0);
        my_bird.setVelocityY(0);
        my_pig.setPigX(Math.random() * 500 + 200);
        my_pig.setPigY(480);
//        birdX = 100;
//        birdY = 400;
//        velocityX = 0;
//        velocityY = 0;
//        pigX = Math.random() * 500 + 200; // position aléatoire pour le cochon
//        pigY = 480;
        message = "Choisissez l'angle et la vitesse.";
    }

    // fin de partie
    void stop() {
    	my_bird.setVelocityX(0);
    	my_bird.setVelocityY(0);
//        velocityX = 0;
//        velocityY = 0;
        gameOver = true;
    }

    // boucle qui calcule la position de l'oiseau en vol, effectue l'affichage et teste les conditions de victoire
    public void run() {
        while(true) {
            // un pas de simulation toutes les 10ms
            try { Thread.currentThread().sleep(10); } catch(InterruptedException e) { }

            if(!gameOver && !selecting) {

                // moteur physique
            	my_bird.setBirdX(my_bird.getBirdX()+my_bird.getVelocityX());
            	my_bird.setBirdY(my_bird.getBirdY()+my_bird.getVelocityY());
            	my_bird.setVelocityY(my_bird.getVelocityY()+g.getGravity());
//                birdX += velocityX;
//                birdY += velocityY;
//                velocityY += gravity;

                // conditions de victoire
            	if(distance(my_bird.getBirdX(), my_bird.getBirdY(), my_pig.getPigX(), my_pig.getPigY()) < 35) {
//                	if(distance(my_bird.getBirdX(), my_bird.getBirdY(), pigX, pigY) < 35) {
//                if(distance(birdX, birdY, pigX, pigY) < 35) {
                    stop();
                    message = "Gagné : cliquez pour recommencer.";
                    score++;
                } else if(my_bird.getBirdX() < 20 || my_bird.getBirdX() > 780 || 
                		my_bird.getBirdY() < 0 || my_bird.getBirdY() > 480) {
//                } else if(birdX < 20 || birdX > 780 || birdY < 0 || birdY > 480) {
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

        // décor
        dc.draw(g);
        tr.draw(g);
        g.setColor(Color.BLACK);
//        g.drawLine(0, 500, 800, 500);
       //g.drawLine(100, 500, 100, 400);

        // oiseau
        //g.setColor(Color.RED);
        if(selecting) g.drawLine((int)my_bird.getBirdX(), (int) my_bird.getBirdY(), mouseX, mouseY); // montre l'angle et la vitesse
        my_bird.draw(g);
        //g.fillOval(((int) my_bird.getBirdX()) - 20, ((int) my_bird.getBirdY()) - 20, 40, 40);
//        if(selecting) g.drawLine((int) birdX, (int) birdY, mouseX, mouseY); // montre l'angle et la vitesse
//        g.fillOval((int) birdX - 20, (int) birdY - 20, 40, 40);

        // cochon
        my_pig.draw(g);
//        g.setColor(Color.GREEN);
//        g.fillOval((int) my_pig.getPigX() - 20, (int) my_pig.getPigY() - 20, 40, 40);

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
    }
}