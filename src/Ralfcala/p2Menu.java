/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ralfcala;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author - Raphael Owoicho (1115535)
 * @Version -  CMM013 MSc Project
 * @Date - 29/08/2013
 */

public class p2Menu extends JPanel implements MouseListener, MouseMotionListener{
    private JPanel butPanel;
    public static JLabel pNameLabel, p1Label, p2Label, imageLabel, soundLabel,
            backLabel, cover;
    public static JLabel gameName, top;
    private static JTextField pName1, pName2;
    public static volatile boolean playSelect = false, entered = false, ayo;
    Main main;
    private Font textFont = new Font("Comic Sans MS", 2, 15);
    private Font textFont2 = new Font("Comic Sans MS", 3, 18);
    public static ImageIcon menuImage, soundImage, backImage, p1Icon, p2Icon, nameIcon, background;
    private Graphics dbGraphics;
    private Image dbImage, myImage, sOnImage, sOffImage, playImage;
    private int playButX = 330, playButY = 290;
    
    public p2Menu(){
        //create......
        pNameLabel = new JLabel("Player Names");
        pName1 = new JTextField("");
        pName2 = new JTextField("");
        gameName = new JLabel();
        butPanel = new JPanel();
        
        pName1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                pName1.setText("");
            }
        });
        pName2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                pName2.setText("");
            }
        });
        
        try {
            sOnImage = ImageIO.read(getClass().getResource("Images/tick.png"));
            sOffImage = ImageIO.read(getClass().getResource("Images/cancel.png"));
            playImage = ImageIO.read(getClass().getResource("Images/play.png"));

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        
        cover = new JLabel();
        top = new JLabel();
        menuImage = new ImageIcon(getClass().getResource("Images/logo_small.png"));
        imageLabel = new JLabel(menuImage);
        soundImage = new ImageIcon(getClass().getResource("Images/speaker.png"));
        soundLabel = new JLabel(soundImage);
        backImage = new ImageIcon(getClass().getResource("Images/deleted.png"));
        backLabel = new JLabel(backImage);
        p1Icon = new ImageIcon(getClass().getResource("Images/male_small.png"));
        p2Icon = new ImageIcon(getClass().getResource("Images/female_small.png"));
        p1Label = new JLabel(p1Icon);
        p2Label = new JLabel(p2Icon);
        
        //customize...
        pName1.setFont(textFont2);
        pName1.setForeground(new java.awt.Color(102, 204, 0));
        pName2.setFont(textFont2);
        pName2.setForeground(new java.awt.Color(102, 204, 0));
        pNameLabel.setFont(textFont);
        pNameLabel.setForeground(Color.BLACK);
        
        //add Listeners....
        imageLabel.addMouseListener(this);
        soundLabel.addMouseListener(this);
        backLabel.addMouseListener(this);
        p1Label.addMouseListener(this);
        p2Label.addMouseListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        //set tool tip texts...
        backLabel.setToolTipText("Back to Ayo Menu");
        soundLabel.setToolTipText("Game Sound");
        imageLabel.setToolTipText("Back to Home Screen");
        p1Label.setToolTipText("Enter Player 1 Name");
        p2Label.setToolTipText("Enter Player 2 Name");
        
        //set position on panel....
        cover.setBounds(0, 0, 700, 450);
        top.setBounds(10, 10, 260, 380);
        gameName.setBounds(85, 20, 180, 50);
        pNameLabel.setBounds(55, 89, 150, 15);
        p1Label.setBounds(20, 100, 36, 36);
        p2Label.setBounds(20, 145, 36, 36);
        pName1.setBounds(55, 110, 185, 25);
        pName2.setBounds(55, 155, 185, 25);
        imageLabel.setBounds(20, 10, 60, 60);
        soundLabel.setBounds(220, 350, 42, 42);
        backLabel.setBounds(20, 360, 32, 32);
        butPanel.setBackground(Color.decode("#7F5217"));
        butPanel.setBounds(210, 20, 280, 400);
        butPanel.setLayout(null);
        
        //add to panel...
        butPanel.add(pName1);
        butPanel.add(pName2);
        butPanel.add(pNameLabel);
        butPanel.add(gameName);
        butPanel.add(p1Label);
        butPanel.add(p2Label);
        butPanel.add(imageLabel);
        butPanel.add(soundLabel);
        butPanel.add(backLabel);
        butPanel.add(top);
 
        this.setLayout(null);
        //this.setBackground(Color.BLACK);
        this.add(butPanel);
        this.add(cover);
        
    }// end constructor p2Menu
    
    @Override
    public void paint(Graphics ralf){
        dbImage = createImage(getWidth(), getHeight());
        dbGraphics = dbImage.getGraphics();
        paintComponent(dbGraphics);
        ralf.drawImage(dbImage, 0, 0, this);
    }
    
    @Override
    public void paintComponent(Graphics udx){
        //call super class and paint images to screen
        super.paintComponents(udx);
        if(Sound.soundOn){
            udx.drawImage(sOnImage, 455, 395, this);
        }else{
            udx.drawImage(sOffImage, 455, 395, this);
        }
        //draw play button and add conditions
        if(entered){
            udx.drawImage(playImage, playButX+5, playButY+5, this);
        }else{
            udx.drawImage(playImage, playButX, playButY, this);
        }
        if((playSelect)&&(playButX<=420)){
            playButX++;
        }
        if(playButX > 420){
            main.cardMan.show(main.panel, "game");
            playSelect = false;
            playButX = 330;
            if(ayo){
                Sound.soundAyo.playSong();
            }else{
                Sound.soundKalah.playSong();
            }
        }

        repaint();
    }
    
    public static void mode2(){
            Game.gameStarted = false;
            Game.setPlayer(true);
            Game.initializeBoard();
            Game.playerLabel.setIcon(p1Label.getIcon());
            Game.compLabel.setIcon(p2Label.getIcon());
            Game.pName = pName1.getText();
            Game.compName = pName2.getText();
    }
    
    public static void setDefault(){
        pName1.setText("");
        pName2.setText("");
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==imageLabel){
            Sound.sound3.playSound();
            Main.showThis();
        }
        if(e.getSource()==soundLabel){
            if(Sound.soundOn){
                Sound.soundOn = false;
            }
            else if(!Sound.soundOn){
                Sound.soundOn = true;
                Sound.sound3.playSound();
            }
        }
        if(e.getSource() == p1Label){
            Sound.sound3.playSound();
            if(p1Label.getIcon() == p1Icon){
                p1Label.setIcon(p2Icon);
            }else{
                p1Label.setIcon(p1Icon);
            }
        }
        if(e.getSource() == p2Label){
            Sound.sound3.playSound();
            if(p2Label.getIcon() == p1Icon){
                p2Label.setIcon(p2Icon);
            }else{
                p2Label.setIcon(p1Icon);
            }
        }
        if(e.getSource()==backLabel){
            Sound.sound3.playSound();
            if(ayo){
                main.cardMan.show(main.panel, "ayoMenu");
            }else{
                main.cardMan.show(main.panel, "kalahMenu");
            }
        }
        int mx = e.getX();
        int my = e.getY();
        if(mx > playButX && mx < playButX+playImage.getWidth(this) &&
            my > playButY && my < playButY+playImage.getHeight(this)){
            Sound.sound2.playSound();
            playSelect = true;
            Game.select1 = false;
            mode2();
            Game.gameName.setIcon(gameName.getIcon());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("pressed!!!");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("Released!!!");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==imageLabel){
            imageLabel.setBounds(25, 15, 60, 60);
        }
        if(e.getSource()==soundLabel){
            soundLabel.setBounds(225, 355, 42, 42);
        }
        if(e.getSource()==backLabel){
            backLabel.setBounds(15, 365, 32, 32);
        }
        if(e.getSource()==p1Label){
            p1Label.setBounds(15, 103, 36, 36);
        }
        if(e.getSource()==p2Label){
            p2Label.setBounds(15, 148, 36, 36);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==imageLabel){
            imageLabel.setBounds(20, 10, 60, 60);
        }
        if(e.getSource()==soundLabel){
            soundLabel.setBounds(220, 350, 42, 42);
        }
        if(e.getSource()==backLabel){
            backLabel.setBounds(20, 360, 32, 32);
        }
        if(e.getSource()==p1Label){
            p1Label.setBounds(20, 100, 36, 36);
        }
        if(e.getSource()==p2Label){
            p2Label.setBounds(20, 145, 36, 36);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(mx > playButX && mx < playButX+playImage.getWidth(this) &&
            my > playButY && my < playButY+playImage.getHeight(this)){
            entered = true;
        }else{
            entered = false;
        }
    }
    
}
