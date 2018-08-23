/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ralfcala;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author - Raphael Owoicho (1115535)
 * @Version -  CMM013 MSc Project
 * @Date - 29/08/2013
 */

public class CustomMenu extends JPanel implements MouseListener{
    private JPanel butPanel, topPanel1, topPanel2, topPanel3, topPanel4, topPanel5, topPanel6, bottomPanel;
    public static JLabel tickLabel, backLabel, infoLabel, abtLabel, cover, resetLabel, topCover, bottomCover,
            playerL1, playerL2, playerL3, playsL1, playsL2, playsL3, backgroundL1, backgroundL2, boardL1, boardL2,
            seedsL1, seedsL2, seedsL3, pStyleL1, pStyleL2, pStyleL3, menuLabel;
    public static JLabel[] changeLabel;
    private Font textFont = new Font("Comic Sans MS", 2, 15);
    private Font textFont1 = new Font("Times New Roman", 1, 15);
    public static ImageIcon backImage, tickImage, abtImage, infoImage, resetImage, changeImage, styleIcon1, styleIcon2,
            pIcon1, pIcon2, pIcon3, pIcon4, pfIcon1, pfIcon2, pfIcon3, pfIcon4, seeds3, seeds4, seeds5, seeds6, menuImage,
            background1, background2, background3, background4;
    private int changeLabelY = 48;
    public static int imageFlag, translate;
    public static volatile boolean fromGame, ayo;
    private static String transform;
    public static CustomGame customGame;
    
    public CustomMenu(){
        //create......
        tickLabel = new JLabel();
        cover = new JLabel();
        playerL1 = new JLabel("Player 2 is:");
        playerL2 = new JLabel();
        playerL3 = new JLabel();
        playsL1 = new JLabel("Who plays first?");
        playsL2 = new JLabel();
        playsL3 = new JLabel();
        backgroundL1 = new JLabel("Background-Style:");
        backgroundL2 = new JLabel();
        boardL1 = new JLabel("Board-Style:");
        boardL2 = new JLabel();
        seedsL1 = new JLabel("Seeds Per Pit:");
        seedsL2 = new JLabel();
        seedsL3 = new JLabel();
        pStyleL1 = new JLabel("Play-Style:");
        pStyleL2 = new JLabel();
        pStyleL3 = new JLabel();
        butPanel = new JPanel();
        topPanel1 = new JPanel();
        topPanel2 = new JPanel();
        topPanel3 = new JPanel();
        topPanel4 = new JPanel();
        topPanel5 = new JPanel();
        topPanel6 = new JPanel();
        changeLabel = new JLabel[6];
        bottomPanel = new JPanel();
        topCover = new JLabel();
        bottomCover = new JLabel();
       
        background1 = new ImageIcon(getClass().getResource("Images/texture.png"));
        background2 = new ImageIcon(getClass().getResource("Images/ayobackG1.png"));
        background3 = new ImageIcon(getClass().getResource("Images/pattern_fill.png"));
        background4 = new ImageIcon(getClass().getResource("Images/kalahbackG1.png"));
        backImage = new ImageIcon(getClass().getResource("Images/deleted.png"));
        backLabel = new JLabel(backImage);
        menuImage = new ImageIcon(getClass().getResource("Images/custom.png"));
        menuLabel = new JLabel(menuImage);
        tickImage = new ImageIcon(getClass().getResource("Images/next.png"));
        tickLabel = new JLabel(tickImage);
        infoImage = new ImageIcon(getClass().getResource("Images/games_inst.png"));
        infoLabel = new JLabel(infoImage);
        abtImage = new ImageIcon(getClass().getResource("Images/info2.png"));
        abtLabel = new JLabel(abtImage);
        changeImage = new ImageIcon(getClass().getResource("Images/change.png"));
        resetImage = new ImageIcon(getClass().getResource("Images/reset.png"));
        resetLabel = new JLabel(resetImage);
        styleIcon1 = new ImageIcon(getClass().getResource("Images/anticlockwise.png"));
        styleIcon2 = new ImageIcon(getClass().getResource("Images/clockwise.png"));
        seeds3 = new ImageIcon(getClass().getResource("Images/three.png"));
        seeds4 = new ImageIcon(getClass().getResource("Images/four.png"));
        seeds5 = new ImageIcon(getClass().getResource("Images/five.png"));
        seeds6 = new ImageIcon(getClass().getResource("Images/six.png"));
        pfIcon1 = new ImageIcon(getClass().getResource("Images/p1icon.png"));
        pfIcon2 = new ImageIcon(getClass().getResource("Images/p2icon.png"));
        pfIcon3 = new ImageIcon(getClass().getResource("Images/computer2.png"));
        pfIcon4 = new ImageIcon(getClass().getResource("Images/male_small.png"));
        pIcon1 = new ImageIcon(getClass().getResource("Images/person_small.png"));
        pIcon2 = new ImageIcon(getClass().getResource("Images/easyRobot_small.png"));
        pIcon3 = new ImageIcon(getClass().getResource("Images/medRobot_small.png"));
        pIcon4 = new ImageIcon(getClass().getResource("Images/hardRobot_small.png"));
        
        resetMenu();
        
        //changeLabel properties...
        for(int i=0; i<6; i++){
            changeLabel[i] = new JLabel(changeImage);
            changeLabel[i].addMouseListener(this);
            changeLabel[i].setToolTipText("Change");
            changeLabel[i].setBounds(380, changeLabelY, 40, 40);
            butPanel.add(changeLabel[i]);
            changeLabelY += 50;
        }
        
        //add Listeners.... 
        tickLabel.addMouseListener(this);
        backLabel.addMouseListener(this);
        infoLabel.addMouseListener(this);
        abtLabel.addMouseListener(this);
        resetLabel.addMouseListener(this);
        
        //customize
        playerL1.setFont(textFont);
        playerL1.setForeground(Color.decode("#673F9B"));
        playsL1.setFont(textFont);
        playsL1.setForeground(playerL1.getForeground());
        backgroundL1.setFont(textFont);
        backgroundL1.setForeground(playerL1.getForeground());
        boardL1.setFont(textFont);
        boardL1.setForeground(playerL1.getForeground());
        seedsL1.setFont(textFont);
        seedsL1.setForeground(playerL1.getForeground());
        pStyleL1.setFont(textFont);
        pStyleL1.setForeground(playerL1.getForeground());
        playerL2.setFont(textFont1);
        playerL2.setForeground(Color.decode("#426A0E"));
        playsL2.setFont(textFont1);
        playsL2.setForeground(playerL2.getForeground());
        backgroundL2.setFont(textFont1);
        backgroundL2.setForeground(playerL2.getForeground());
        boardL2.setFont(textFont1);
        boardL2.setForeground(playerL2.getForeground());
        seedsL2.setFont(textFont1);
        seedsL2.setForeground(playerL2.getForeground());
        pStyleL2.setFont(textFont1);
        pStyleL2.setForeground(playerL2.getForeground());
        topPanel1.setBackground(Color.decode("#FFCBA4"));
        topPanel2.setBackground(topPanel1.getBackground());
        topPanel3.setBackground(topPanel1.getBackground());
        topPanel4.setBackground(topPanel1.getBackground());
        topPanel5.setBackground(topPanel1.getBackground());
        topPanel6.setBackground(topPanel1.getBackground());
        bottomPanel.setBackground(topPanel1.getBackground());
        
        //set tool tip texts...
        backLabel.setToolTipText("Back");
        tickLabel.setToolTipText("Continue");
        abtLabel.setToolTipText("About");
        infoLabel.setToolTipText("Instructions");
        
        //set position on panel....
        cover.setBounds(0, 0, 700, 450);
        topCover.setBounds(5, 45, 430, 350);
        bottomCover.setBounds(0, 0, 700, 450);
        topPanel1.setBounds(5, 5, 430, 85);
        topPanel2.setBounds(5, 95, 430, 45);
        topPanel3.setBounds(5, 145, 430, 45);
        topPanel4.setBounds(5, 195, 430, 45);
        topPanel5.setBounds(5, 245, 430, 45);
        topPanel6.setBounds(5, 295, 430, 50);
        bottomPanel.setBounds(5, 350, 430, 45);
        
        playerL1.setBounds(15, 60, 200, 25);
        playsL1.setBounds(15, 115, 200, 25);
        backgroundL1.setBounds(15, 165, 200, 25);
        boardL1.setBounds(15, 215, 200, 25);
        seedsL1.setBounds(15, 265, 200, 25);
        pStyleL1.setBounds(15, 315, 200, 25);
        playerL2.setBounds(215, 60, 200, 25);
        playsL2.setBounds(215, 115, 200, 25);
        backgroundL2.setBounds(215, 165, 200, 25);
        boardL2.setBounds(215, 215, 200, 25);
        seedsL2.setBounds(215, 265, 200, 25);
        pStyleL2.setBounds(215, 315, 200, 25);
        playerL3.setBounds(160, 45, 45, 45);
        playsL3.setBounds(160, 95, 45, 45);
        seedsL3.setBounds(160, 245, 45, 45);
        pStyleL3.setBounds(160, 300, 45, 45);
        
        menuLabel.setBounds(75, 4, 300, 41);
        tickLabel.setBounds(395, 355, 32, 32);
        backLabel.setBounds(10, 355, 32, 32);
        abtLabel.setBounds(170, 355, 32, 32);
        infoLabel.setBounds(215, 355, 32, 32);
        resetLabel.setBounds(260, 355, 32, 32);
        butPanel.setBackground(Color.decode("#CBA3FF"));
        butPanel.setBounds(140, 20, 440, 400);
        butPanel.setLayout(null);
        
        butPanel.add(playerL1);
        butPanel.add(playerL2);
        butPanel.add(playerL3);
        butPanel.add(playsL1);
        butPanel.add(playsL2);
        butPanel.add(playsL3);
        butPanel.add(backgroundL1);
        butPanel.add(backgroundL2);
        butPanel.add(boardL1);
        butPanel.add(boardL2);
        butPanel.add(seedsL1);
        butPanel.add(seedsL2);
        butPanel.add(seedsL3);
        butPanel.add(pStyleL1);
        butPanel.add(pStyleL2);
        butPanel.add(pStyleL3);
        
        butPanel.add(menuLabel);
        butPanel.add(tickLabel);
        butPanel.add(backLabel);
        butPanel.add(abtLabel);
        butPanel.add(infoLabel);
        butPanel.add(resetLabel);
        butPanel.add(topPanel1);
        butPanel.add(topPanel2);
        butPanel.add(topPanel3);
        butPanel.add(topPanel4);
        butPanel.add(topPanel5);
        butPanel.add(topPanel6);
        butPanel.add(bottomPanel);
        
        this.setLayout(null);
        this.add(butPanel);
        this.add(cover);
        
    }//end constructor CustomMenu
    
    public static void resetMenu(){
        playerL2.setText("HUMAN");
        playerL3.setIcon(pIcon1);
        playsL2.setText("PLAYER 1");
        playsL3.setIcon(pfIcon1);
        backgroundL2.setText("BEE CANVAS");
        boardL2.setText("CLASSIC");
        seedsL2.setText("THREE");
        seedsL3.setIcon(seeds3);
        pStyleL2.setText("ANTICLOCKWISE");
        pStyleL3.setIcon(styleIcon1);
        cover.setIcon(background1);
        transform = "3";
        translate = 3;
        CustomInst.seeds = 3;
    }
    
    public void switchPStyle(){
        if("ANTICLOCKWISE".equals(pStyleL2.getText())){
            pStyleL2.setText("CLOCKWISE");
            pStyleL3.setIcon(styleIcon2);
        }else{
            pStyleL2.setText("ANTICLOCKWISE");
            pStyleL3.setIcon(styleIcon1);
        }
    }
    
    public void switchPFirst(){
        if("HUMAN".equals(playerL2.getText())){
            if("PLAYER 1".equals(playsL2.getText())){
                playsL2.setText("PLAYER 2");
                playsL3.setIcon(pfIcon2);
            }else{
                playsL2.setText("PLAYER 1");
                playsL3.setIcon(pfIcon1);
            }
        }else{
            if("PLAYER 1".equals(playsL2.getText())||"ME".equals(playsL2.getText())){
                playsL2.setText("COMPUTER");
                playsL3.setIcon(pfIcon3);
            }else{
                playsL2.setText("ME");
                playsL3.setIcon(pfIcon4);
            }
        }
    }
    
    public void switchPlayer(){
        switch(playerL2.getText()){
            case "HUMAN":
                playerL2.setText("EASY R0boT");
                playerL3.setIcon(pIcon2);
                playsL2.setText("ME");
                playsL3.setIcon(pfIcon4);
                imageFlag = 1;
                break;
            case "EASY R0boT":
                playerL2.setText("MEDIUM R0boT");
                playerL3.setIcon(pIcon3);
                imageFlag = 2;
                break;
            case "MEDIUM R0boT":
                playerL2.setText("HARD R0boT");
                playerL3.setIcon(pIcon4);
                imageFlag = 3;
                break;
            case "HARD R0boT":
                playerL2.setText("HUMAN");
                playerL3.setIcon(pIcon1);
                playsL2.setText("PLAYER 1");
                playsL3.setIcon(pfIcon1);
                imageFlag = 0;
                break;
        }
    }
    
    public void switchSeeds(){
        switch(seedsL2.getText()){
            case "THREE":
                seedsL2.setText("FOUR");
                seedsL3.setIcon(seeds4);
                transform = "4";
                translate = 4;
                CustomInst.seeds = 4;
                break;
            case "FOUR":
                seedsL2.setText("FIVE");
                seedsL3.setIcon(seeds5);
                transform = "5";
                translate = 5;
                CustomInst.seeds = 5;
                break;
            case "FIVE":
                seedsL2.setText("SIX");
                seedsL3.setIcon(seeds6);
                transform = "6";
                translate = 6;
                CustomInst.seeds = 6;
                break;
            case "SIX":
                seedsL2.setText("THREE");
                seedsL3.setIcon(seeds3);
                transform = "3";
                translate = 3;
                CustomInst.seeds = 3;
                break;
        }
    }
    
    public void switchBoard(){
        switch(boardL2.getText()){
            case "CLASSIC":
                boardL2.setText("ANCIENT");
                break;
            case "ANCIENT":
                boardL2.setText("IMPROVISED");
                break;
            case "IMPROVISED":
                boardL2.setText("NO BOARD");
                break;
            case "NO BOARD":
                boardL2.setText("CLASSIC");
                break;
        }
    }
    
    public void switchBackground(){
        switch(backgroundL2.getText()){
            case "BEE CANVAS":
                backgroundL2.setText("TEXTURE-FILL");
                cover.setIcon(background2);
                break;
            case "TEXTURE-FILL":
                backgroundL2.setText("FADE PATTERN");
                cover.setIcon(background3);
                break;
            case "FADE PATTERN":
                backgroundL2.setText("IMAGE");
                cover.setIcon(background4);
                break;
            case "IMAGE":
                backgroundL2.setText("BEE CANVAS");
                cover.setIcon(background1);
                break;
        }
    }
    
    public void transfer(){
        CustomPage.bgName = backgroundL2.getText();
        CustomPage.seedsName = seedsL2.getText();
        CustomPage.boardName = boardL2.getText();
        CustomPage.styleName = pStyleL2.getText();
        CustomGame.startSeeds = transform;
        Ralfcala.startSeeds = translate;
        if("HUMAN".equals(playerL2.getText())){
            CustomPage.modeName = "HUMAN vs HUMAN";
        }else{
            CustomPage.modeName = playerL2.getText();
        }
        if("ME".equals(playsL2.getText())){
            CustomPage.playerName = "HUMAN";   
        }else{
            CustomPage.playerName = playsL2.getText();
        }
    }
    
       
    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i=0; i<6; i++){
            if(e.getSource()==changeLabel[i]){
               Sound.sound5.playSound();
               switch(i){
                   case 0: switchPlayer();
                       break;
                   case 1: switchPFirst();
                       break;
                   case 2: switchBackground();
                       break;
                   case 3: switchBoard();
                       break;
                   case 4: switchSeeds();
                       break;
                   case 5: switchPStyle();
                       break;
               }
            }
        }
        if(e.getSource()==backLabel){
            Sound.sound5.playSound();
            resetMenu();
            Main.showThis();
            //Runtime.getRuntime().exit(0);
        }
        if(e.getSource()==tickLabel){
            Sound.sound5.playSound();
            if("HUMAN".equals(playerL2.getText())){
                CustomPage.pNameLabel.setText("Player Names");
                CustomPage.butPanel.add(CustomPage.p2Label);
                CustomPage.butPanel.add(CustomPage.pName2);
                CustomPage.p1Label.setIcon(CustomPage.p3Icon);
                CustomPage.p2Label.setIcon(CustomPage.p2Icon);
            }else{
                CustomPage.pNameLabel.setText("Player Name");
                CustomPage.butPanel.remove(CustomPage.p2Label);
                CustomPage.butPanel.remove(CustomPage.pName2);
                CustomPage.p1Label.setIcon(CustomPage.p1Icon);
                CustomPage.p2Label.setIcon(playerL3.getIcon());
            }
            transfer();
            CustomPage.setDefault();
            CustomPage.cover.setIcon(cover.getIcon());
            Main.cardMan.show(Main.panel, "pageCustom");
            customGame = new CustomGame();
            Main.panel.add(customGame, "gameCustom");
        }
        if(e.getSource()==abtLabel){
            Sound.sound5.playSound();
            About1.cover.setIcon(cover.getIcon());
            About1.custom = true;
            Main.cardMan.show(Main.panel, "about1");
        }
        if(e.getSource()==infoLabel){
            Sound.sound5.playSound();
            CustomInst.fromGame = false;
            CustomInst.cover.setIcon(cover.getIcon());
            Main.cardMan.show(Main.panel, "instCustom");
        }
        if(e.getSource()==resetLabel){
            Sound.sound5.playSound();
            resetMenu();
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
        if(e.getSource()==tickLabel){
            tickLabel.setBounds(400, 360, 32, 32);
        }
        if(e.getSource()==backLabel){
            backLabel.setBounds(5, 360, 32, 32);
        }
        if(e.getSource()==abtLabel){
            abtLabel.setBounds(165, 360, 32, 32);
        }
        if(e.getSource()==infoLabel){
            infoLabel.setBounds(210, 360, 32, 32);
        }
        if(e.getSource()==resetLabel){
            resetLabel.setBounds(255, 360, 32, 32);
        }
        for(int i=0; i<6; i++){
            if(e.getSource()==changeLabel[i]){
                changeLabel[i].setBounds(385, changeLabel[i].getY(), 40, 40);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==tickLabel){
            tickLabel.setBounds(395, 355, 32, 32);
        }
        if(e.getSource()==backLabel){
            backLabel.setBounds(10, 355, 32, 32);
        }
        if(e.getSource()==abtLabel){
            abtLabel.setBounds(170, 355, 32, 32);
        }
        if(e.getSource()==infoLabel){
            infoLabel.setBounds(215, 355, 32, 32);
        }
        if(e.getSource()==resetLabel){
            resetLabel.setBounds(260, 355, 32, 32);
        }
        for(int i=0; i<6; i++){
            if(e.getSource()==changeLabel[i]){
                changeLabel[i].setBounds(380, changeLabel[i].getY(), 40, 40);
            }
        }
    }
     
}
