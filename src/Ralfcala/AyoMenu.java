/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ralfcala;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author - Raphael Owoicho (1115535)
 * @Version -  CMM013 MSc Project
 * @Date - 29/08/2013
 */

public class AyoMenu extends JPanel implements MouseListener, MouseMotionListener{
    private JPanel butPanel;
    private JButton p1But, abtBut, instBut;
    private JLabel gameName, imageLabel, backLabel, cover, top;
    CardLayout cardMang;
    Main main;
    private Font textFont1 = new Font("Comic Sans MS", 3, 15);
    private Font textFont2 = new Font("Comic Sans MS", 3, 11);
    private volatile boolean playerSelect = false, but1entered = false, but2entered = false;
    private ImageIcon menuImage,abticon, insticon, backIcon, nameIcon, background;
    public static ImageIcon foreground;
    private Graphics dbGraphics;
    private Image dbImage, oneImage, twoImage;
    private int onePX = 278, onePY = 112, twoPX = 390, twoPY = 112, abtX = 290, abtY = 250;
    
    public AyoMenu(){
        //create......
        p1But = new JButton("PLAYER");
        abtBut = new JButton("ABOUT");
        instBut = new JButton("INSTRUCTIONS");
        butPanel = new JPanel();
        
        background = new ImageIcon(getClass().getResource("Images/ayobackG1.png"));
        cover = new JLabel(background);
        foreground = new ImageIcon(getClass().getResource("Images/ayoTop.png"));
        top = new JLabel(foreground);
        nameIcon = new ImageIcon(getClass().getResource("Images/ayo.png"));
        gameName = new JLabel(nameIcon);
        menuImage = new ImageIcon(getClass().getResource("Images/logo_small.png"));
        imageLabel = new JLabel(menuImage);
        backIcon = new ImageIcon(getClass().getResource("Images/deleted.png"));
        backLabel = new JLabel(backIcon);      
        
        try {
            oneImage = ImageIO.read(getClass().getResource("Images/p1icon.png"));
            twoImage = ImageIO.read(getClass().getResource("Images/p2icon.png"));

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        //myImage = ImageIO.read(getClass().getResource("Images/logo_small.png"));
        
        //add Listeners....
        abtBut.addMouseListener(this);
        instBut.addMouseListener(this);
        imageLabel.addMouseListener(this);
        backLabel.addMouseListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        //set tool tip texts...
        //backLabel.setToolTipText("Back to Ayo Menu");
        backLabel.setToolTipText("CLOSE");
        imageLabel.setToolTipText("Back to Home Screen");
        
        //add button icons
        abticon = new ImageIcon(getClass().getResource("Images/info.png"));
        abtBut.setIcon(abticon);
        insticon = new ImageIcon(getClass().getResource("Images/instruct.png"));
        instBut.setIcon(insticon);
        
        //remove spacing between the image and button's borders
        abtBut.setMargin(new Insets(0, 0, 0, 0));
        instBut.setMargin(new Insets(0, 0, 0, 0));
        
        // add different button background
        p1But.setBackground(Color.decode("#7F5217"));
        p1But.setForeground(Color.WHITE);
        p1But.setFont(textFont1);
        instBut.setBackground(Color.decode("#7F5217"));
        instBut.setForeground(Color.WHITE);
        instBut.setFont(textFont2);
        abtBut.setBackground(Color.decode("#7F5217"));
        abtBut.setForeground(Color.WHITE);
        abtBut.setFont(textFont1);
        
        //remove the button border
        p1But.setBorder(null);
        
        //set position on panel....
        gameName.setBounds(75, 20, 180, 50);
        p1But.setBounds(85, 90, 110, 35);
        cover.setBounds(0, 0, 700, 450);
        top.setBounds(10, 10, 260, 380);
        abtBut.setBounds(70, 175, 140, 35);
        instBut.setBounds(70, 133, 140, 35);
        imageLabel.setBounds(20, 10, 60, 60);
        backLabel.setBounds(20, 360, 32, 32);
        butPanel.setBackground(Color.decode("#7F5217"));
        butPanel.setBounds(210, 20, 280, 400);
        butPanel.setLayout(null);
        
        //add to panel...
        butPanel.add(p1But);
        butPanel.add(abtBut);
        butPanel.add(instBut);
        butPanel.add(gameName);
        butPanel.add(imageLabel);
        butPanel.add(backLabel);
        butPanel.add(top);
        
        this.setLayout(null);
        this.add(butPanel);
        this.add(cover);
        
    }// end constructor AyoMenu
    
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
        if(but1entered){
            udx.drawImage(oneImage, onePX-5, onePY-5, this);
        }else{   
            udx.drawImage(oneImage, onePX, onePY, this);
        }
        if(but2entered){
            udx.drawImage(twoImage, twoPX+5, twoPY+5, this);
        }else{
            udx.drawImage(twoImage, twoPX, twoPY, this);
        }
        
        repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == abtBut){
            Sound.sound3.playSound();
            About1.ayo = true;
            About1.cover.setIcon(background);
            main.cardMan.show(main.panel, "about1");
        }
        if(e.getSource() == instBut){
            Sound.sound3.playSound();
            AyoInst.fromGame = false;
            AyoInst.ayo = true;
            AyoInst.cover.setIcon(background);
            main.cardMan.show(main.panel, "instAyo");
            
        }
        if(e.getSource()==imageLabel){
            Sound.sound3.playSound();
            Main.showThis();
        }
        if(e.getSource()==backLabel){
            Sound.sound3.playSound();
            //Runtime.getRuntime().exit(0);
            Main.showThis();
        }
        int mx = e.getX();
        int my = e.getY();
        if(mx > onePX && mx < onePX+oneImage.getWidth(this) &&
            my > onePY && my < onePY+oneImage.getHeight(this)){
            Sound.sound3.playSound();
            main.cardMan.show(main.panel, "p1Menu");
            p1Menu.setDefault();
            p1Menu.gameName.setIcon(nameIcon);
            p1Menu.ayo = true;
            p1Menu.cover.setIcon(background);
            p1Menu.top.setIcon(foreground);
        }
        if(mx > twoPX && mx < twoPX+twoImage.getWidth(this) &&
            my > twoPY && my < twoPY+twoImage.getHeight(this)){
            Sound.sound3.playSound();
            main.cardMan.show(main.panel, "p2Menu");
            p2Menu.setDefault();
            p2Menu.gameName.setIcon(nameIcon);
            p2Menu.ayo = true;
            p2Menu.cover.setIcon(background);
            p2Menu.top.setIcon(foreground);
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
        if(e.getSource()==backLabel){
            backLabel.setBounds(15, 365, 35, 35);
        }
        if(e.getSource()==abtBut){
            abtBut.setBounds(40, 175, 140, 35);
        }
        if(e.getSource()==instBut){
            instBut.setBounds(100, 133, 140, 35);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==imageLabel){
            imageLabel.setBounds(20, 10, 60, 60);
        }
        if(e.getSource()==backLabel){
            backLabel.setBounds(20, 360, 32, 32);
        }
        if(e.getSource()==abtBut){
            abtBut.setBounds(70, 175, 140, 35);
        }
        if(e.getSource()==instBut){
            instBut.setBounds(70, 133, 140, 35);
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(mx > onePX && mx < onePX+oneImage.getWidth(this) &&
            my > onePY && my < onePY+oneImage.getHeight(this)){
            but1entered = true;
        }else{
            but1entered = false;
        }
        if(mx > twoPX && mx < twoPX+twoImage.getWidth(this) &&
            my > twoPY && my < twoPY+twoImage.getHeight(this)){
            but2entered = true;
        }else{
            but2entered = false;
        }
    }
    
}// end class AyoMenu
