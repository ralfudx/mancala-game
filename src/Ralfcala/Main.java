/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ralfcala;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author - Raphael Owoicho (1115535)
 * @Version -  CMM013 MSc Project
 * @Date - 29/08/2013
 */

public class Main extends Canvas implements MouseListener, MouseMotionListener{
    public static final int MAX_SPAWN = 24;
    public static final int X = 700;
    public static final int Y = 450;
    public static final double GRAVITY = 1500;
    public static final double DRAG = 0.2;
    public static final double BOUNCE = 0.9;
    public static JFrame frame;
    public static BufferStrategy b;
    private static GraphicsEnvironment ge;
    private static GraphicsDevice gd;
    private static GraphicsConfiguration gc;
    private static BufferedImage buffer, ayoImage, frameImage, kalahImage, customImage, quitImage;
    private static Graphics graphics;
    private static Graphics2D g2d;
    private static AffineTransform at;
    public static ArrayList<Spawn> living = new ArrayList<Spawn>();
    public static volatile boolean isRunning = true, goBack, nownow;
    private static int xPoint=135, yPoint=30, xGreen=550, yGreen=230, xButs=570, yAyo=265,
            yKalah=305, yCustom=345, xQuit=600, yQuit=385, xShake = 35;
    private static Font butFont = new Font("Serif", Font.BOLD|Font.ITALIC, 20);
    private static boolean ayoHover, kalahHover, customHover, quitHover;
    private static final String TITLE = "Ralf's 2D Mancala";
    public static JPanel panel;
    private static Image mancalaImage, titleIcon, shakeImage;
    private AyoMenu ayoMenu;
    private AyoFrame ayo;
    private GameMenu gMenu;
    private AyoInst ayoInstruct;
    private KalahScreen kalah;
    private CustomMenu custom;
    private About1 about1;
    private p1Menu p1M;
    private p2Menu p2M;
    private KalahMenu owareMenu;
    private CustomInst customInstruct;
    private CustomPage customPage;
    public static Game mancala;
    public static CardLayout cardMan;
    
    
    public Main(){
        // Create canvas for painting...
        this.setIgnoreRepaint(true);
        this.setSize(X, Y);
        
        try {
            titleIcon = ImageIO.read(getClass().getResource("Images/logo_small.png"));
            shakeImage = ImageIO.read(getClass().getResource("Images/container.png"));
            mancalaImage = ImageIO.read(getClass().getResource("Images/mancalas.png"));
            frameImage = ImageIO.read(getClass().getResource("Images/green.png"));
            ayoImage = ImageIO.read(getClass().getResource("Images/ayoButton.png"));
            kalahImage = ImageIO.read(getClass().getResource("Images/kalahButton.png"));
            customImage = ImageIO.read(getClass().getResource("Images/customButton.png"));
            quitImage = ImageIO.read(getClass().getResource("Images/logout_small.png"));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        
        panel = new JPanel();
        cardMan = new CardLayout();
        ayoMenu = new AyoMenu();
        ayo = new AyoFrame();
        kalah = new KalahScreen();
        custom = new CustomMenu();
        owareMenu = new KalahMenu();
        ayoInstruct = new AyoInst();
        customInstruct = new CustomInst();
        customPage = new CustomPage();
        about1 = new About1();
        gMenu = new GameMenu();
        p1M = new p1Menu();
        p2M = new p2Menu();
        
        // Create frame and add items...
        frame = new JFrame(TITLE);
        frame.setIconImage(titleIcon);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(X, Y);
        frame.setResizable(false);
        
        // Add panels to panel for card layout, and display...
        panel.setLayout(cardMan);
        panel.add(this, "screen");
        panel.add(ayoMenu, "ayoMenu");
        panel.add(ayo, "ayoFrame");
        panel.add(kalah, "kalahMenu");
        panel.add(custom, "customMenu");
        panel.add(owareMenu, "owareMenu");
        panel.add(gMenu, "ayoGMenu");
        panel.add(ayoInstruct, "instAyo");
        panel.add(customInstruct, "instCustom");
        panel.add(customPage, "pageCustom");
        panel.add(about1, "about1");
        panel.add(p1M, "p1Menu");
        panel.add(p2M, "p2Menu");
        cardMan.show(panel, "screen");
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        
        // center the window on the screen.
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // Set up the BufferStrategy for double buffering.
        this.createBufferStrategy(2);
        b = this.getBufferStrategy();
        
        // Get graphics configuration...
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = ge.getDefaultScreenDevice();
        gc = gd.getDefaultConfiguration();
        
        // Create off-screen drawing surface
        buffer = gc.createCompatibleImage(X, Y);
        
        // Objects needed for rendering...
        graphics = null;
        g2d = null;
        
        //add listeners to canvas
        addMouseListener(this);
        addMouseMotionListener(this);
        
    
    }// end constructor Main
    
    public static void main(String[] args){
        // Initialize some things...
        Main main = new Main();
        initializeThreads();
    }

    public static void runAnimation(){
        // Set up some variables.
        int fps = 0;
        int frames = 0;
        long totalTime = 0;
        long curTime = System.currentTimeMillis();
        long lastTime = curTime;
        // Start the loop.
        while (isRunning) {
            try {
              // Calculations for FPS.
              lastTime = curTime;
              curTime = System.currentTimeMillis();
              totalTime += curTime - lastTime;
              if (totalTime > 1000) {
                totalTime -= 1000;
                fps = frames;
                frames = 0;
              }
              ++frames;
              // clear back buffer...
              g2d = buffer.createGraphics();
              g2d.setColor(Color.decode("#CC2EFA"));
              g2d.fillRect(0, 0, X, Y);
              // Draw entities
              g2d.drawImage(shakeImage, xShake, yPoint+40, frame);
              //g2d.drawString("M A N C A L A", xPoint, yPoint);
              shakeMove();
              if(living.size()==24){
                  xShake = 36;
                  g2d.setColor(Color.white);
                  g2d.setFont(new Font("Chiller", Font.PLAIN, 25));
                  g2d.drawString("CHOOSE  VARIANT", 280, 200);
              }else{
                if(goBack){
                    xShake++;
                }else{
                  xShake--;
                }
              }
              for (int i = 0; i < living.size(); i++) {
                  at = new AffineTransform();
                  at.translate(living.get(i).getX(), living.get(i).getY());
                  if ((i%4)==0){
                    g2d.setColor(Color.BLUE);
                  }
                  else if((i%4)==1){
                    g2d.setColor(Color.ORANGE);
                  }
                  else if((i%4)==2){
                    g2d.setColor(Color.GREEN);
                  }
                  else if((i%4)==3){
                    g2d.setColor(Color.RED);
                  }
                  Spawn s = living.get(i);
                  g2d.fill(new Ellipse2D.Double(s.getX(), s.getY(),
                          s.getRadius() * 2, s.getRadius() * 2));
              }
              // display Buttons and Frame...
              g2d.drawImage(frameImage, xGreen, yGreen, frame);
              if(ayoHover){
                 g2d.drawImage(ayoImage, xButs+3, yAyo+3, frame); 
              }else{
                 g2d.drawImage(ayoImage, xButs, yAyo, frame);
              }
              if(kalahHover){
                 g2d.drawImage(kalahImage, xButs+3, yKalah+3, frame); 
              }else{
                 g2d.drawImage(kalahImage, xButs, yKalah, frame);
              }
              if(customHover){
                 g2d.drawImage(customImage, xButs+3, yCustom+3, frame); 
              }else{
                 g2d.drawImage(customImage, xButs, yCustom, frame);
              }
              if(quitHover){
                 g2d.drawImage(quitImage, xQuit+3, yQuit+3, frame); 
              }else{
                 g2d.drawImage(quitImage, xQuit, yQuit, frame);
              }
              //Game name
              g2d.drawImage(mancalaImage, xPoint, yPoint, panel);
              /**else if (xPoint<-50){
                  xPoint = 680;
              }*/
              // Blit image and flip...
              graphics = b.getDrawGraphics();
              graphics.drawImage(buffer, 0, 0, null);
              if (!b.contentsLost()) b.show();
              // Let the OS have a little time...
              Thread.sleep(10);
            } catch (InterruptedException e) {
            } finally {
              // release resources
              if (graphics != null) graphics.dispose();
              if (g2d != null) g2d.dispose();
            }
        }
    }// end method run Animation
    
    public static void shakeMove(){
        if(xShake == 10){
            goBack = true;
        }
        if(xShake == 35){
            goBack = false;
        }
    }
    
    public static void stopThreads(){
        living.clear();
        Sound.sound0.stopSong();
    }
    
    public static void initializeThreads(){
        if(!isRunning){
            isRunning = true;
        }
        Sound.sound0.playSong();
        // Create and start threads.b
        Thread moveEngine = new MoveEngine();
        moveEngine.start();
        Thread makeBall = new MakeBall();
        makeBall.start();
        // Run the animation loop.
        runAnimation();
    }
    
    public static void showThis(){
        Sound.sound0.playSong();
        xShake = 35;
        living.clear();
        cardMan.show(panel, "screen");
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(mx > xButs && mx < xButs+ayoImage.getWidth(this) &&
            my > yAyo && my < yAyo+ayoImage.getHeight(this)){
            Sound.sound1.playSound();
            GameMenu.custom = false;
            stopThreads();
            Ralfcala.kalah = false;
            AyoInst.seeds = 4;
            Ralfcala.startSeeds = 4;
            Game.startSeeds = "4";
            cardMan.show(panel, "ayoMenu");
            mancala = new Game();
            panel.add(mancala, "game");
        }
        else if(mx > xButs && mx < xButs+kalahImage.getWidth(this) &&
            my > yKalah && my < yKalah+kalahImage.getHeight(this)){
            Sound.sound1.playSound();
            GameMenu.custom = false;
            stopThreads();
            Ralfcala.kalah = true;
            AyoInst.seeds = 3;
            Ralfcala.startSeeds = 3;
            Game.startSeeds = "3";
            cardMan.show(panel, "kalahMenu");
            mancala = new Game();
            panel.add(mancala, "game");
        }
        else if(mx > xButs && mx < xButs+customImage.getWidth(this) &&
            my > yCustom && my < yCustom+customImage.getHeight(this)){
            Sound.sound1.playSound();
            GameMenu.custom = true;
            Ralfcala.kalah = false;
            stopThreads();
            cardMan.show(panel, "customMenu");
        }
        else if(mx > xQuit && mx < xQuit+quitImage.getWidth(this) &&
            my > yQuit && my < yQuit+quitImage.getHeight(this)){
            Sound.sound7.playSound();
            Runtime.getRuntime().exit(0);
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
        System.out.println("Entered");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Dragged");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(mx > xButs && mx < xButs+ayoImage.getWidth(this) &&
            my > yAyo && my < yAyo+ayoImage.getHeight(this)){
            ayoHover = true;
        }else{
            ayoHover = false;
        }
        if(mx > xButs && mx < xButs+kalahImage.getWidth(this) &&
            my > yKalah && my < yKalah+kalahImage.getHeight(this)){
            kalahHover = true;
        }else{
            kalahHover = false;
        }
        if(mx > xButs && mx < xButs+customImage.getWidth(this) &&
            my > yCustom && my < yCustom+customImage.getHeight(this)){
            customHover = true;
        }else{
            customHover = false;
        }
        if(mx > xQuit && mx < xQuit+quitImage.getWidth(this) &&
            my > yQuit && my < yQuit+quitImage.getHeight(this)){
            quitHover = true;
        }else{
            quitHover = false;
        }
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Exited");
    }

    public static boolean allDead(){
      if (living.size() < 1){
          return true;
      }
      else{
          return false;
      }
    }

    public static synchronized int giveBirth(int x, int y, double vx,
            double vy, int m){
      if (living.size() >= MAX_SPAWN) return 1;
      living.add(new Spawn(x, y, vx, vy, m));
      return 0;
    }
  
}// end class Main
