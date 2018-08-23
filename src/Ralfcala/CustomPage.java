/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ralfcala;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JTextField;

/**
 *
 * @author - Raphael Owoicho (1115535)
 * @Version -  CMM013 MSc Project
 * @Date - 29/08/2013
 */

public class CustomPage extends JPanel implements MouseListener, MouseMotionListener{
    public static JPanel butPanel, textPanel;
    public static JLabel menuName, textLabel, backLabel, cover, pNameLabel, p1Label,
            p2Label, soundLabel, compFinalLabel;
    private Font textFont = new Font("Comic Sans MS", 2, 15);
    private Font textFont2 = new Font("Comic Sans MS", 3, 18);
    private Font nameFont = new Font("Times New Roman", 1, 25);
    public static ImageIcon backImage, p1Icon, p2Icon, p3Icon, soundImage;
    public static volatile boolean entered, playSelect;
    private Image dbImage, sOnImage, sOffImage, playImage;;
    private Graphics dbGraphics;
    public static JTextField pName1, pName2;
    private int playButX = 330, playButY = 350;
    public static String compName, diffText, modeName, bgName, boardName, styleName,
            playerName, seedsName;
    public static Ralfcala game = new Ralfcala();
    
    public CustomPage(){
        //create......
        pNameLabel = new JLabel();
        pName1 = new JTextField("");
        pName2 = new JTextField("");
        menuName = new JLabel("SETTINGS");
        cover = new JLabel();
        butPanel = new JPanel();
        textPanel = new JPanel();
        compFinalLabel = new JLabel();
        textLabel = new JLabel();
        
       
        try {
            sOnImage = ImageIO.read(getClass().getResource("Images/tick.png"));
            sOffImage = ImageIO.read(getClass().getResource("Images/cancel.png"));
            playImage = ImageIO.read(getClass().getResource("Images/play2.png"));

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        
        backImage = new ImageIcon(getClass().getResource("Images/deleted.png"));
        backLabel = new JLabel(backImage);
        soundImage = new ImageIcon(getClass().getResource("Images/speaker2.png"));
        soundLabel = new JLabel(soundImage);
        p1Icon = new ImageIcon(getClass().getResource("Images/male_small.png"));
        p2Icon = new ImageIcon(getClass().getResource("Images/female_small.png"));
        p3Icon = new ImageIcon(getClass().getResource("Images/person_small.png"));
        p1Label = new JLabel();
        p2Label = new JLabel(p2Icon);
        
        //add Listeners.... 
        backLabel.addMouseListener(this);
        p1Label.addMouseListener(this);
        p2Label.addMouseListener(this);
        soundLabel.addMouseListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        //customize
        textLabel.setFont(textFont);
        textLabel.setForeground(Color.decode("#82CAFF"));
        menuName.setFont(nameFont);
        menuName.setForeground(Color.decode("#EBF4FA"));
        pName1.setFont(textFont2);
        pName1.setForeground(Color.decode("#810541"));
        pName2.setFont(textFont2);
        pName2.setForeground(Color.decode("#810541"));
        pNameLabel.setFont(textFont);
        pNameLabel.setForeground(Color.WHITE);
        
        //set tool tip texts...
        backLabel.setToolTipText("Back");
        p1Label.setToolTipText("Enter Player 1 Name");
        p2Label.setToolTipText("Enter Player 2 Name");
        soundLabel.setToolTipText("Game Sound");
        
        //set position on panel....
        cover.setBounds(0, 0, 700, 450);
        menuName.setBounds(160, 15, 280, 30);
        pNameLabel.setBounds(135, 209, 150, 15);
        p1Label.setBounds(100, 220, 36, 36);
        p2Label.setBounds(100, 265, 36, 36);
        pName1.setBounds(135, 230, 185, 25);
        pName2.setBounds(135, 275, 185, 25);
        backLabel.setBounds(10, 360, 32, 32);
        soundLabel.setBounds(380, 355, 42, 42);
        textLabel.setBounds(0, 0, 400, 150);
        textPanel.setBounds(40, 50, 360, 150);
        butPanel.setBackground(Color.BLACK);
        textPanel.setBackground(butPanel.getBackground());
        butPanel.setBounds(140, 20, 440, 400);
        butPanel.setLayout(null);
        
        butPanel.add(textPanel);
        textPanel.add(textLabel);
        butPanel.add(menuName);
        butPanel.add(backLabel);
        butPanel.add(soundLabel);
        butPanel.add(pNameLabel);
        butPanel.add(p1Label);
        butPanel.add(pName1);
        butPanel.add(p2Label);
        butPanel.add(pName2);
        
        this.setLayout(null);
        this.add(butPanel);
        this.add(cover);
        
    }// end constructor CustomPage
    
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
        textLabel.setText("<html><p align = \"justify\">Game Mode: <font color=\"#FF00FF\">" + modeName + " </font>"
                + "<br></br>Background: <font color=\"#FF00FF\">" + bgName + " </font>"
                + "<br></br>Board Style: <font color=\"#FF00FF\">" + boardName + " </font>"
                + "<br></br>Seeds per Pit: <font color=\"#FF00FF\">" + seedsName + " </font>"
                + "<br></br>Play style: <font color=\"#FF00FF\">" + styleName + " </font></p>"
                + "<center><font color=\"#810541\">" + playerName + " plays first... </font></center></html>");
        
        if(Sound.soundOn){
            udx.drawImage(sOnImage, 555, 395, this);
        }else{
            udx.drawImage(sOffImage, 555, 395, this);
        }
        //draw paint button and add conditions
        if(entered){
            udx.drawImage(playImage, playButX+5, playButY+5, this);
        }else{
            udx.drawImage(playImage, playButX, playButY, this);
        }
        if((playSelect)&&(playButX<=420)){
            playButX++;
        }
        if(playButX > 420){
            Main.cardMan.show(Main.panel, "gameCustom");
            playSelect = false;
            playButX = 330;
            game = new Ralfcala();
            if(!"HUMAN".equals(CustomMenu.playerL2.getText())){
                CustomPage.game.initializeBoard();
                if("PLAYER 2".equals(CustomMenu.playsL2.getText()) || "COMPUTER".equals(CustomMenu.playsL2.getText())){
                    game.turn = !game.turn;
                }
                CustomPage.game.printBoard2(false);
            }
            Sound.soundCustom.playSong();
        }
        

        repaint();
    }
    
    public static void setDefault(){
        pName1.setText("");
        pName2.setText("");
    }
    
    public static void mode(){
        CustomGame.gameStarted = false;
        CustomGame.initializeBoard();
        CustomGame.playerLabel.setIcon(p1Label.getIcon());
        CustomGame.pName = pName1.getText();
        CustomGame.cover.setIcon(CustomMenu.cover.getIcon());
        setCompImage(CustomMenu.imageFlag);
        setCompName(CustomMenu.imageFlag);
        CustomGame.modeName = diffText;
        if("HUMAN".equals(CustomMenu.playerL2.getText())){
            CustomGame.compLabel.setIcon(p2Label.getIcon());
            CustomGame.compName = pName2.getText();
        }else{
            CustomGame.compLabel.setIcon(compFinalLabel.getIcon());
            CustomGame.compName = compName;
        }
        if("PLAYER 2".equals(CustomMenu.playsL2.getText()) || "COMPUTER".equals(CustomMenu.playsL2.getText())){
            CustomGame.player1 = false;
        }else{
            CustomGame.player1 = true;
        }
    }
    
    public static void setCompName(int myFlag){
        switch(myFlag){
            case 0: compName = "Player 2";
                diffText = "HUMAN-PLAYER";
                break;
            case 1: compName = "Easy-R0boT";
                diffText = "EASY";
                CustomGame.sDepth = "16";
                break;
            case 2: compName = "Medium-R0boT";
                diffText = "MEDIUM";
                CustomGame.sDepth = "14";
                break;
            case 3: compName = "Hard-R0boT";
                diffText = "HARD";
                CustomGame.sDepth = "10";
                break;
        }
    }
    
    public static void setCompImage(int myFlag){
        switch(myFlag){
            case 0: compFinalLabel.setIcon(CustomMenu.pIcon1);
                break;
            case 1: compFinalLabel.setIcon(CustomMenu.pIcon2);
                break;
            case 2: compFinalLabel.setIcon(CustomMenu.pIcon3);
                break;
            case 3: compFinalLabel.setIcon(CustomMenu.pIcon4);
                break;
        }
    }
    
       
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==backLabel){
            Sound.sound5.playSound();
            Main.cardMan.show(Main.panel, "customMenu");
        }
        if(e.getSource()==soundLabel){
            if(Sound.soundOn){
                Sound.soundOn = false;
            }
            else if(!Sound.soundOn){
                Sound.soundOn = true;
                Sound.sound5.playSound();
            }
        }
        if(e.getSource() == p1Label){
            Sound.sound5.playSound();
            if(p1Label.getIcon() == p1Icon){
                p1Label.setIcon(p2Icon);
            }else if(p1Label.getIcon() == p2Icon){
                p1Label.setIcon(p3Icon);
            }else if(p1Label.getIcon() == p3Icon){
                p1Label.setIcon(p1Icon);
            }
        }
        if(e.getSource() == p2Label){
            Sound.sound5.playSound();
            if(p2Label.getIcon() == p1Icon){
                p2Label.setIcon(p2Icon);
            }else if(p2Label.getIcon() == p2Icon){
                p2Label.setIcon(p3Icon);
            }else if(p2Label.getIcon() == p3Icon){
                p2Label.setIcon(p1Icon);
            }
        }
        int mx = e.getX();
        int my = e.getY();
        if(mx > playButX && mx < playButX+playImage.getWidth(this) &&
            my > playButY && my < playButY+playImage.getHeight(this)){
            Sound.sound6.playSound();
            playSelect = true;
            mode();
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
        if(e.getSource()==backLabel){
            backLabel.setBounds(5, 365, 32, 32);
        }
        if(e.getSource()==soundLabel){
            soundLabel.setBounds(377, 360, 42, 42);
        }
        if(e.getSource()==p1Label){
            p1Label.setBounds(95, 223, 36, 36);
        }
        if(e.getSource()==p2Label){
            p2Label.setBounds(95, 268, 36, 36);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==backLabel){
            backLabel.setBounds(10, 360, 32, 32);
        }
        if(e.getSource()==soundLabel){
            soundLabel.setBounds(380, 355, 42, 42);
        }
        if(e.getSource()==p1Label){
            p1Label.setBounds(100, 220, 36, 36);
        }
        if(e.getSource()==p2Label){
            p2Label.setBounds(100, 265, 36, 36);
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
