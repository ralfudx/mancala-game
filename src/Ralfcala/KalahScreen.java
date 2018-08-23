/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ralfcala;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author - Raphael Owoicho (1115535)
 * @Version -  CMM013 MSc Project
 * @Date - 29/08/2013
 */

public class KalahScreen extends JPanel implements MouseListener, MouseMotionListener{
    private JPanel butPanel;
    private JLabel gameName, imageLabel, backLabel, cover, top;
    CardLayout cardMang;
    Main main;
    private volatile boolean playerSelect = false, but1entered = false, but2entered = false,
            abtEntered = false, instEntered = false;
    private ImageIcon menuImage, backIcon, nameIcon, p2nameIcon, background, foreground;
    private Graphics dbGraphics;
    private Image dbImage, oneImage, twoImage, instImage, abtImage;
    private int PX = 270, onePY = 112, twoPY = 157, instY = 202, abtY = 247, PXplus = 300,
            onePYplus = 125, twoPYplus = 170, instYplus = 215, abtYplus = 260;
    
    public KalahScreen(){
        //create......
        butPanel = new JPanel();
        
        background = new ImageIcon(getClass().getResource("Images/kalahbackG1.png"));
        cover = new JLabel(background);
        foreground = new ImageIcon(getClass().getResource("Images/kalahTop.png"));
        top = new JLabel(foreground);
        nameIcon = new ImageIcon(getClass().getResource("Images/kalah.png"));
        gameName = new JLabel(nameIcon);
        menuImage = new ImageIcon(getClass().getResource("Images/logo_small.png"));
        imageLabel = new JLabel(menuImage);
        backIcon = new ImageIcon(getClass().getResource("Images/deleted.png"));
        backLabel = new JLabel(backIcon);      
        
        try {
            abtImage = ImageIO.read(getClass().getResource("Images/about.png"));
            oneImage = ImageIO.read(getClass().getResource("Images/p1.png"));
            twoImage = ImageIO.read(getClass().getResource("Images/p2.png"));
            instImage = ImageIO.read(getClass().getResource("Images/instruct2.png"));

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        
        //add Listeners....
        imageLabel.addMouseListener(this);
        backLabel.addMouseListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        //set tool tip texts...
        //backLabel.setToolTipText("Back to Ayo Menu");
        backLabel.setToolTipText("CLOSE");
        imageLabel.setToolTipText("Back to Home Screen");
                
        //set position on panel....
        cover.setBounds(0, 0, 700, 450);
        top.setBounds(10, 10, 260, 380);
        gameName.setBounds(75, 20, 180, 50);
        imageLabel.setBounds(20, 10, 60, 60);
        backLabel.setBounds(20, 360, 32, 32);
        butPanel.setBackground(Color.decode("#7F5217"));
        butPanel.setBounds(210, 20, 280, 400);
        butPanel.setLayout(null);
        
        //add to panel...
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
        //draw play button and add conditions
        if(but1entered){
            udx.drawImage(oneImage, PX+5, onePY+5, this);
        }else{   
            udx.drawImage(oneImage, PX, onePY, this);
        }
        if(but2entered){
            udx.drawImage(twoImage, PX+5, twoPY+5, this);
        }else{
            udx.drawImage(twoImage, PX, twoPY, this);
        }
        if(instEntered){
            udx.drawImage(instImage, PX+5, instY+5, this);
        }else{
            udx.drawImage(instImage, PX, instY, this);
        }
        if(abtEntered){
            udx.drawImage(abtImage, PX+5, abtY+5, this);
        }else{
            udx.drawImage(abtImage, PX, abtY, this);
        }
        
        repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==imageLabel){
            Sound.sound3.playSound();
            Main.showThis();
        }
        if(e.getSource()==backLabel){
            Sound.sound3.playSound();
            Main.showThis();
            //Runtime.getRuntime().exit(0);
        }
        int mx = e.getX();
        int my = e.getY();
        if((mx > PXplus) && (mx < PXplus+oneImage.getWidth(this)-60) &&
            (my > onePYplus) && (my < onePYplus+oneImage.getHeight(this))){
            Sound.sound3.playSound();
            main.cardMan.show(main.panel, "p1Menu");
            p1Menu.setDefault();
            p1Menu.gameName.setIcon(nameIcon);
            p1Menu.ayo = false;
            p1Menu.cover.setIcon(background);
            p1Menu.top.setIcon(foreground);
        }
        if((mx > PXplus) && (mx < PXplus+twoImage.getWidth(this)-60) &&
            (my > twoPYplus) && (my < twoPYplus+twoImage.getHeight(this))){
            Sound.sound3.playSound();
            main.cardMan.show(main.panel, "p2Menu");
            p2Menu.setDefault();
            p2Menu.gameName.setIcon(nameIcon);
            p2Menu.ayo = false;
            p2Menu.cover.setIcon(background);
            p2Menu.top.setIcon(foreground);
        }
        if((mx > PXplus) && (mx < PXplus+instImage.getWidth(this)-60) &&
            (my > instYplus) && (my < instYplus+instImage.getHeight(this))){
            Sound.sound3.playSound();
            AyoInst.fromGame = false;
            AyoInst.cover.setIcon(background);
            AyoInst.ayo = false;
            main.cardMan.show(main.panel, "instAyo");
        }
        if((mx > PXplus) && (mx < PXplus+abtImage.getWidth(this)-60) &&
            (my > abtYplus) && (my < abtYplus+abtImage.getHeight(this))){
            Sound.sound3.playSound();
            About1.cover.setIcon(background);
            About1.ayo = false;
            main.cardMan.show(main.panel, "about1");
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
            backLabel.setBounds(15, 364, 35, 35);
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
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if((mx > PXplus) && (mx < PXplus+oneImage.getWidth(this)-60) &&
            (my > onePYplus) && (my < onePYplus+oneImage.getHeight(this))){
            but1entered = true;
        }else{
            but1entered = false;
        }
        if((mx > PXplus) && (mx < PXplus+twoImage.getWidth(this)-60) &&
            (my > twoPYplus) && (my < twoPYplus+twoImage.getHeight(this))){
            but2entered = true;
        }else{
            but2entered = false;
        }
        if((mx > PXplus) && (mx < PXplus+instImage.getWidth(this)-60) &&
            (my > instYplus) && (my < instYplus+instImage.getHeight(this))){
            instEntered = true;
        }else{
            instEntered = false;
        }
        if((mx > PXplus) && (mx < PXplus+abtImage.getWidth(this)-60) &&
            (my > abtYplus) && (my < abtYplus+abtImage.getHeight(this))){
            abtEntered = true;
        }else{
            abtEntered = false;
        }
    }
    
}// end class AyoMenu
