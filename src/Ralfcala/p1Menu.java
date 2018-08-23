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

public class p1Menu extends JPanel implements MouseListener, MouseMotionListener{
    private JPanel butPanel;
    private static JLabel pNameLabel, startsLabel, imageLabel, soundLabel, backLabel,
            pLabel, dInputLabel, easyLabel, medLabel, hardLabel, humanLabel, compLabel,
            whoLabel, compFinalLabel;
    public static JLabel cover, gameName, top;
    public static JTextField pName;
    Main main;
    public static Ralfcala game = new Ralfcala();
    public static volatile boolean playSelect = false, entered = false, switched = false, ayo;
    private Font textFont = new Font("Comic Sans MS", 2, 15);
    private Font textFont1 = new Font("Comic Sans MS", 3, 18);
    private Font textFont2 = new Font("Times New Roman", Font.BOLD|Font.ITALIC, 18);
    public static ImageIcon menuImage, backImage, soundImage, playerIcon1, playerIcon2,
            easyIcon, medIcon, hardIcon, compIcon, nameIcon, background;
    private Graphics dbGraphics;
    private Image dbImage, myImage, sOnImage, sOffImage, playImage, gameImage;
    private static int playButX = 330, playButY = 290, imageFlag;
    private static String diffText1 = "EASY", diffText2 = "MEDIUM", diffText3 = "HARD",
            startsText1 = "Human", startsText2 = "Computer", compName;
    
    public p1Menu(){
        //create......
        pNameLabel = new JLabel("Player Name");
        whoLabel = new JLabel(startsText1);
        startsLabel = new JLabel("plays first");
        dInputLabel = new JLabel(diffText1);
        pName = new JTextField("");
        butPanel = new JPanel();
        compFinalLabel = new JLabel();
        
        pName.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                pName.setText("");
            }
        });
        
        try {
            sOnImage = ImageIO.read(getClass().getResource("Images/tick.png"));
            sOffImage = ImageIO.read(getClass().getResource("Images/cancel.png"));
            playImage = ImageIO.read(getClass().getResource("Images/play.png"));

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        
        
        //gameName = new JLabel(gameImage);
        cover = new JLabel();
        top = new JLabel();
        gameName = new JLabel();
        menuImage = new ImageIcon(getClass().getResource("Images/logo_small.png"));
        imageLabel = new JLabel(menuImage);
        backImage = new ImageIcon(getClass().getResource("Images/deleted.png"));
        backLabel = new JLabel(backImage);
        soundImage = new ImageIcon(getClass().getResource("Images/speaker.png"));
        soundLabel = new JLabel(soundImage);
        playerIcon1 = new ImageIcon(getClass().getResource("Images/male_small.png"));
        playerIcon2 = new ImageIcon(getClass().getResource("Images/female_small.png"));
        pLabel = new JLabel(playerIcon1);
        easyIcon = new ImageIcon(getClass().getResource("Images/easyRobot_small.png"));
        easyLabel = new JLabel(easyIcon);
        medIcon = new ImageIcon(getClass().getResource("Images/medRobot_small.png"));
        medLabel = new JLabel(medIcon);
        hardIcon = new ImageIcon(getClass().getResource("Images/hardRobot_small.png"));
        hardLabel = new JLabel(hardIcon);
        humanLabel = new JLabel(pLabel.getIcon());
        compIcon = new ImageIcon(getClass().getResource("Images/computer.png"));
        compLabel = new JLabel(compIcon);
        
        //customize....
        pName.setFont(textFont1);
        pName.setForeground(new java.awt.Color(102, 204, 0));
        pNameLabel.setFont(textFont);
        pNameLabel.setForeground(Color.BLACK);
        dInputLabel.setFont(textFont2);
        dInputLabel.setForeground(Color.BLACK);
        startsLabel.setFont(textFont);
        startsLabel.setForeground(dInputLabel.getForeground());
        whoLabel.setFont(textFont);
        whoLabel.setForeground(dInputLabel.getForeground());
        
        //add Listeners....
        imageLabel.addMouseListener(this);
        pLabel.addMouseListener(this);
        soundLabel.addMouseListener(this);
        backLabel.addMouseListener(this);
        easyLabel.addMouseListener(this);
        medLabel.addMouseListener(this);
        hardLabel.addMouseListener(this);
        humanLabel.addMouseListener(this);
        compLabel.addMouseListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        //set tool tip texts...
        backLabel.setToolTipText("Back to Ayo Menu");
        soundLabel.setToolTipText("Game Sound");
        imageLabel.setToolTipText("Back to Home Screen");
        pLabel.setToolTipText("Change Icon");
        easyLabel.setToolTipText("EASY");
        medLabel.setToolTipText("MEDIUM");
        hardLabel.setToolTipText("HARD");
        humanLabel.setToolTipText("YOU");
        compLabel.setToolTipText("COMPUTER");
        
        //set position on panel....
        cover.setBounds(0, 0, 700, 450);
        top.setBounds(10, 10, 260, 380);
        imageLabel.setBounds(20, 10, 60, 60);
        gameName.setBounds(85, 20, 180, 50);
        pNameLabel.setBounds(55, 83, 150, 15);
        pLabel.setBounds(20, 89, 36, 36);
        pName.setBounds(55, 100, 185, 25);
        dInputLabel.setBounds(20, 170, 100, 15);
        easyLabel.setBounds(110, 138, 42, 42);
        medLabel.setBounds(160, 140, 42, 42);
        hardLabel.setBounds(210, 140, 42, 42);
        whoLabel.setBounds(20, 210, 100, 15);
        startsLabel.setBounds(20, 227, 100, 18);
        humanLabel.setBounds(135, 207, 36, 36);
        compLabel.setBounds(195, 205, 42, 42);
        backLabel.setBounds(20, 360, 32, 32);
        soundLabel.setBounds(220, 350, 42, 42);
        butPanel.setBackground(Color.decode("#7F5217"));
        butPanel.setBounds(210, 20, 280, 400);
        butPanel.setLayout(null);
        
        //add to panel...
        butPanel.add(pName);
        butPanel.add(pNameLabel);
        butPanel.add(dInputLabel);
        butPanel.add(humanLabel);
        butPanel.add(compLabel);
        butPanel.add(startsLabel);
        butPanel.add(gameName);
        butPanel.add(imageLabel);
        butPanel.add(soundLabel);
        butPanel.add(backLabel);
        butPanel.add(pLabel);
        butPanel.add(easyLabel);
        butPanel.add(medLabel);
        butPanel.add(hardLabel);
        butPanel.add(whoLabel);
        butPanel.add(top);
 
        this.setLayout(null);
        //this.setBackground(Color.BLACK);
        this.add(butPanel);
        this.add(cover);
        
        
    }// end constructor p1Menu
    
    public static void setDefault(){
        dInputLabel.setText(diffText1);
        dInputLabel.setForeground(Color.BLACK);
        imageFlag = 0;
        Game.sDepth = "16";
        pName.setText("");
        switched = false;
        startsLabel.setForeground(Color.BLACK);
        whoLabel.setForeground(Color.BLACK);
        whoLabel.setText(startsText1);
        humanLabel.setBounds(135, 207, 36, 36);
        compLabel.setBounds(195, 205, 42, 42);
    }
    
    public static void setCompName(int myFlag){
        switch(myFlag){
            case 0: compName = "Kofizzy";
                break;
            case 1: compName = "FX603";
                break;
            case 2: compName = "Rafa-555";
                break;
        }
    }
    
    public static void setCompImage(int myFlag){
        switch(myFlag){
            case 0: compFinalLabel.setIcon(easyIcon);
                break;
            case 1: compFinalLabel.setIcon(medIcon);
                break;
            case 2: compFinalLabel.setIcon(hardIcon);
                break;
        }
    }
    
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
            game = new Ralfcala();
            p1Menu.game.initializeBoard();
            if(switched){
                game.turn = !game.turn;
            }
            p1Menu.game.printBoard(false);
            if(ayo){
                Sound.soundAyo.playSong();
            }else{
                Sound.soundKalah.playSong();
            }
        }
        

        repaint();
    }
    
    public static void mode1(){
        Game.gameStarted = false;
        Game.initializeBoard();
        Game.pName = pName.getText();
        Game.playerLabel.setIcon(pLabel.getIcon());
        if(!switched){
            Game.setPlayer(true);

        }else{
            Game.setPlayer(false);
        }
        setCompImage(imageFlag);
        Game.compLabel.setIcon(compFinalLabel.getIcon());
        setCompName(imageFlag);
        Game.compName = compName;
        Game.modeName = dInputLabel.getText();
    }

    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == imageLabel){
            Sound.sound3.playSound();
            Main.showThis();
        }
        if(e.getSource() == soundLabel){
            if(Sound.soundOn){
                Sound.soundOn = false;
            }
            else if(!Sound.soundOn){
                Sound.soundOn = true;
                Sound.sound3.playSound();
            }
        }
        if(e.getSource() == easyLabel){
            Sound.sound3.playSound();
            dInputLabel.setText(diffText1);
            dInputLabel.setForeground(Color.BLACK);
            imageFlag = 0;
            Game.sDepth = "16";
        }
        if(e.getSource() == medLabel){
            Sound.sound3.playSound();
            dInputLabel.setText(diffText2);
            dInputLabel.setForeground(Color.BLUE);
            imageFlag = 1;
            Game.sDepth = "14";
        }
        if(e.getSource() == hardLabel){
            Sound.sound3.playSound();
            dInputLabel.setText(diffText3);
            dInputLabel.setForeground(Color.RED);
            imageFlag = 2;
            Game.sDepth = "10";
        }
        if(e.getSource() == humanLabel){
            Sound.sound3.playSound();
            switched = false;
            startsLabel.setForeground(Color.BLACK);
            whoLabel.setForeground(Color.BLACK);
            whoLabel.setText(startsText1);
            humanLabel.setBounds(135, 207, 36, 36);
            compLabel.setBounds(195, 205, 42, 42);
        }
        if(e.getSource() == compLabel){
            Sound.sound3.playSound();
            switched = true;
            startsLabel.setForeground(Color.BLUE);
            whoLabel.setForeground(Color.BLUE);
            whoLabel.setText(startsText2);
            compLabel.setBounds(135, 207, 42, 42);
            humanLabel.setBounds(195, 205, 36, 36);
        }
        if(e.getSource() == pLabel){
            Sound.sound3.playSound();
            if(pLabel.getIcon() == playerIcon1){
                pLabel.setIcon(playerIcon2);
                humanLabel.setIcon(playerIcon2);
            }else{
                pLabel.setIcon(playerIcon1);
                humanLabel.setIcon(playerIcon1);
            }
        }
        if(e.getSource() == backLabel){
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
            Game.select1 = true;
            Game.gameName.setIcon(gameName.getIcon());
            mode1();
            
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
        if(e.getSource()==pLabel){
            pLabel.setBounds(13, 92, 36, 36);
        }
        if(e.getSource()==easyLabel){
            easyLabel.setBounds(113, 141, 42, 42);
        }
        if(e.getSource()==medLabel){
            medLabel.setBounds(163, 143, 42, 42);
        }
        if(e.getSource()==hardLabel){
            hardLabel.setBounds(213, 143, 42, 42);
        }
        if(e.getSource()==humanLabel){
            if(switched){
                humanLabel.setBounds(198, 208, 36, 36);
            }else{
                humanLabel.setBounds(138, 210, 36, 36);
            }
        }
        if(e.getSource()==compLabel){
            if(switched){
                compLabel.setBounds(138, 210, 42, 42);
            }else{
                compLabel.setBounds(198, 208, 42, 42);
            }
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
        if(e.getSource()==pLabel){
            pLabel.setBounds(20, 89, 36, 36);
        }
        if(e.getSource()==easyLabel){
            easyLabel.setBounds(110, 138, 42, 42);
        }
        if(e.getSource()==medLabel){
            medLabel.setBounds(160, 140, 42, 42);
        }
        if(e.getSource()==hardLabel){
            hardLabel.setBounds(210, 140, 42, 42);
        }
        if(e.getSource()==humanLabel){
            if(switched){
                humanLabel.setBounds(195, 205, 36, 36);
            }else{
                humanLabel.setBounds(135, 207, 36, 36);
            }
        }
        if(e.getSource()==compLabel){
            if(switched){
                compLabel.setBounds(135, 207, 42, 42);
            }else{
                compLabel.setBounds(195, 205, 42, 42);
            }
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
