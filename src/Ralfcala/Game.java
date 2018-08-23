/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ralfcala;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
//import javax.swing.UIManager;

/**
 *
 * @author - Raphael Owoicho (1115535)
 * @Version -  CMM013 MSc Project
 * @Date - 29/08/2013
 */

public class Game extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
    private String up = "Testing";
    private Image dbImage;
    private Graphics dbGraphics;
    //public static ArrayList<MyOval> combine = new ArrayList<>();
    Graphics2D g2d;
    Ellipse2D oval23 [], ovali;
    static Ralfcala game;
    public static JButton but [];
    private int collect, startPits1, startPits2, zeroSeeds = 0;
    public static volatile boolean player1 = true, changePlayer = true, select1 = true, playsFirst,
            gameStarted = false, ayo, rEntered, eEntered;
    public static String startSeeds, manSeeds = "0", pName, compName, modeName, info, sDepth;
    private Image pTurnImage, pWaitImage, p1Image, p2Image, easyImage, medImage, hardImage,
            restartImage, exitImage, boardImage1, boardImage2;
    public static ImageIcon quitImage, setImage, helpImage, pImage, nameIcon, background; 
    public static JLabel quitLabel, setLabel, helpLabel, playerLabel, compLabel, gameName, cover;
    private static int player1Score, player2Score, exitImageX = 350, imageY = 290;
    
    public Game(){
        setLabel = new JLabel();
        helpLabel = new JLabel();
        this.setPreferredSize(new Dimension(700, 450));
        //addMouseListener(this);
        //addMouseMotionListener(this);
        this.setLayout(null);
        but = new JButton[14];
        for(int i= 0; i<14; i++){
            if(i!=0 && i!=7){
                but[i] = new JButton(startSeeds);
                but[i].setBackground(getBackground());
                but[i].setBorder(null);
                but[i].setOpaque(false);
                but[i].addActionListener(this);
                this.add(but[i]);
            }
            else{
                but[i] = new JButton(manSeeds);
                but[i].setBackground(getBackground());
                but[i].setBorder(null);
                but[i].setOpaque(false);
                but[i].setEnabled(false);
                but[i].addActionListener(this);
                this.add(but[i]);
            }
        }
        
        try {
            pTurnImage = ImageIO.read(getClass().getResource("Images/playerTurn.png"));
            pWaitImage = ImageIO.read(getClass().getResource("Images/playerWait.png"));
            boardImage1 = ImageIO.read(getClass().getResource("Images/gameBoard1.png"));
            boardImage2 = ImageIO.read(getClass().getResource("Images/gameBoard2.png"));
            p1Image = ImageIO.read(getClass().getResource("Images/male.png"));
            p2Image = ImageIO.read(getClass().getResource("Images/female.png"));
            easyImage = ImageIO.read(getClass().getResource("Images/easyRobot.png"));
            medImage = ImageIO.read(getClass().getResource("Images/mediumRobot.png"));
            hardImage = ImageIO.read(getClass().getResource("Images/hardRobot.png"));
            exitImage = ImageIO.read(getClass().getResource("Images/exit_door.png"));
            restartImage = ImageIO.read(getClass().getResource("Images/restart.png"));

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        
        
        background = new ImageIcon(getClass().getResource("Images/gameBackG.png"));
        cover = new JLabel(background);
        gameName = new JLabel();
        quitImage = new ImageIcon(getClass().getResource("Images/exit.png"));
        quitLabel = new JLabel(quitImage);
        setImage = new ImageIcon(getClass().getResource("Images/settings.png"));
        setLabel = new JLabel(setImage);
        helpImage = new ImageIcon(getClass().getResource("Images/helpicon.png"));
        helpLabel = new JLabel(helpImage);
        playerLabel = new JLabel();
        compLabel = new JLabel();     
        
        //set position on panel and add
        cover.setBounds(0, 0, 700, 450);
        gameName.setBounds(280, 15, 170, 38);
        quitLabel.setBounds(10, 400, 35, 35);
        setLabel.setBounds(50, 400, 36, 36);
        helpLabel.setBounds(90, 400, 36, 36);
        this.add(setLabel);
        this.add(helpLabel);
        this.add(quitLabel);
        this.add(gameName);
        this.add(cover);
        
        //add Listeners
        quitLabel.addMouseListener(this);
        setLabel.addMouseListener(this);
        helpLabel.addMouseListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        //add tool tip text
        quitLabel.setToolTipText("Quit");
        setLabel.setToolTipText("Settings");
        helpLabel.setToolTipText("Help");
        
        //game = new Ralfcala();
        
    }//end constructor Game
    
    
    @Override
    public void paint(Graphics ralf){
        dbImage = createImage(getWidth(), getHeight());
        dbGraphics = dbImage.getGraphics();
        paintComponent(dbGraphics);
        //paintAgain(dbGraphics);
        ralf.drawImage(dbImage, 0, 0, this);
    }
    
    
    @Override
    public void paintComponent(Graphics udx){
        //call super class and paint images to screen
        super.paintComponents(udx);
        //set font and color
        udx.setFont(new Font("Comic Sans MS", Font.BOLD|Font.ITALIC, 15));
        udx.setColor(Color.BLUE);
        if(pName.equals("")){
            pName = "Player 1";
        }else{
            pName = pName;
        }
        if(compName.equals("")){
            compName = "Player 2";
        }else{
            compName = compName;
        }
        
        //select gameMode
        if(select1){
            //draw player name
                udx.drawString(pName, 600, 435);
           
                udx.drawString(compName, 100, 20);
                
            //select big player and computer images
            if(playerLabel.getIcon()== p1Menu.playerIcon1){
                udx.drawImage(p1Image, 600, 330, this);
            }else{
                udx.drawImage(p2Image, 600, 330, this);
            }

            if(compLabel.getIcon()== p1Menu.hardIcon){
                udx.drawImage(hardImage, 10, 5, this);
            }else if(compLabel.getIcon()== p1Menu.medIcon){
                udx.drawImage(medImage, 10, 5, this);
            }else{
                udx.drawImage(easyImage, 10, 5, this);
            }
        }else{
            //draw player name
                udx.drawString(pName, 600, 435);
                
                udx.drawString(compName, 100, 20);
            if(playerLabel.getIcon()== p2Menu.p1Icon){
                udx.drawImage(p1Image, 600, 330, this);
            }else{
                udx.drawImage(p2Image, 600, 330, this);
            }
            if(compLabel.getIcon()== p2Menu.p1Icon){
                udx.drawImage(p1Image, 10, 5, this);
            }else{
                udx.drawImage(p2Image, 10, 5, this);
            }
        }
        //declare some things...
        if(gameStarted){
            info = " plays now";
        }else{
            info = " plays first";
        }
        //draw player signal button
        if(select1){
            udx.setColor(Color.GRAY);
            udx.drawString(modeName + " MODE", 300, 80);
            if(p1Menu.game.turn){
                udx.drawImage(pTurnImage, 570, 390, this);
                udx.drawImage(pWaitImage, 100, 60, this);
                udx.drawString(pName + info, 20, 380);
            }else{
                udx.drawImage(pWaitImage, 570, 390, this);
                udx.drawImage(pTurnImage, 100, 60, this);
                udx.drawString(compName + info, 20, 380);
            }
        }else{
            if(player1){
                udx.drawImage(pTurnImage, 570, 390, this);
                udx.setColor(Color.GRAY);
                udx.drawString(pName + info, 20, 380);
                for(int k=1; k<7; k++){
                    if(Integer.parseInt(but[k].getText())==0){
                        but[k].setEnabled(false);
                    }else{
                        but[k].setEnabled(true);
                    }
                }
                for(int k=8; k<14; k++){
                   but[k].setEnabled(false); 
                }
            }else{
                udx.drawImage(pWaitImage, 570, 390, this);
                udx.setColor(Color.GRAY);
                udx.drawString(compName + info, 20, 380);
            }
            if(!player1){
                udx.drawImage(pTurnImage, 100, 60, this);
                for(int k=8; k<14; k++){
                    if(Integer.parseInt(but[k].getText())==0){
                        but[k].setEnabled(false);
                    }else{
                        but[k].setEnabled(true);
                    } 
                }
                for(int k=1; k<7; k++){
                   but[k].setEnabled(false); 
                }
            }else{
                udx.drawImage(pWaitImage, 100, 60, this);
            }
        }
        //udx.fillOval(335, 70, 20, 20);
        //draw board and pits
        if(Ralfcala.kalah){
        udx.drawImage(boardImage1, 45, 95, this);
        }else{
            udx.drawImage(boardImage2, 50, 98, this);
        }
        int x = 70;
        int y = 120, y2 = 220, yMan = 140;
        int xRad = 60;
        int yRad = 70, yRadMan = 130;
        oval23 = new Ellipse2D[14];
        g2d = (Graphics2D) udx;
        for(int i= 0; i<14; i++){
            // Draw centered text
            String text = but[i].getText(), show = but[i].getText();
            FontMetrics fm = udx.getFontMetrics();
            double textWidth = fm.getStringBounds(text, udx).getWidth();
            //set tool tip text
            /**try {                             
                UIManager.setLookAndFeel(new ToolTipLookAndFeel());
            } catch(Exception ex) {       
                System.err.println("ToolTipLookAndFeel exception!");
                System.err.println(ex.getMessage());
            }*/
            but[i].setToolTipText("<html><p><font color=\"#000\" " + "size=\"8\">"+ show + "</font></p></html>");
            
            if(i==0){
                but[i].setBounds(x, yMan, xRad, yRadMan);
                oval23[i] = new Ellipse2D.Double(x, yMan, xRad, yRadMan);
                //udx.setColor(Color.GRAY);
                //g2d.fill(oval23[i]);
                udx.setColor(Color.WHITE);
                udx.drawString(text, (int) (x+xRad/2 - textWidth/2),(int) (yMan+yRadMan/2 + fm.getMaxAscent() / 2));
                x += 70;
            }
            else if(i!=0 && i<7){
                but[i].setBounds(x, y2, xRad, yRad);
                oval23[i] = new Ellipse2D.Double(x, y2, xRad, yRad);
                //udx.setColor(Color.GRAY);
                //g2d.fill(oval23[i]);
                udx.setColor(Color.WHITE);
                udx.drawString(text, (int) (x+xRad/2 - textWidth/2),(int) (y2+yRad/2 + fm.getMaxAscent() / 2));
                x += 70;
            }else if(i==7){
                but[i].setBounds(x, yMan, xRad, yRadMan);
                oval23[i] = new Ellipse2D.Double(x, yMan, xRad, yRadMan);
                //udx.setColor(Color.GRAY);
                //g2d.fill(oval23[i]);
                udx.setColor(Color.WHITE);
                udx.drawString(text, (int) (x+xRad/2 - textWidth/2),(int) (yMan+yRadMan/2 + fm.getMaxAscent() / 2));
                x -= 70;
            }else if(i>7 && i<14){
                but[i].setBounds(x, y, xRad, yRad);
                oval23[i] = new Ellipse2D.Double(x, y, xRad, yRad);
                //udx.setColor(Color.GRAY);
                //g2d.fill(oval23[i]);
                udx.setColor(Color.WHITE);
                udx.drawString(text, (int) (x+xRad/2 - textWidth/2),(int) (y+yRad/2 + fm.getMaxAscent() / 2));
                x -= 70;
            }
            
        }
        //for end game
        for(int i= 0; i<14; i++){
            if(pitsEmpty()){
                if(p1Menu.ayo || p2Menu.ayo){
                    Sound.soundAyo.stopSong();
                }else{
                    Sound.soundKalah.stopSong();
                }
                Color rect = Color.decode("#C2B280");
                Color wins = Color.decode("#348017");
                Color lose = Color.decode("#FF2400");
                Color players = Color.decode("#810541");
                Color tie = Color.decode("#583759");
                player1Score = getRemSeeds1() + Integer.parseInt(but[7].getText());
                player2Score = getRemSeeds2() + Integer.parseInt(but[0].getText());
                g2d.setFont(new Font("Serif", Font.BOLD|Font.ITALIC, 20));
                udx.setColor(rect);
                udx.fill3DRect(240, 175, 200, 160, true);
                if(rEntered){
                    udx.drawImage(restartImage, imageY-5, imageY+5, this);
                }else{
                    udx.drawImage(restartImage, imageY, imageY, this);
                }
                if(eEntered){
                    udx.drawImage(exitImage, exitImageX+5, imageY+5, this);
                }else{
                    udx.drawImage(exitImage, exitImageX, imageY, this);
                }
                //for win
                if(player1Score>player2Score){
                    udx.setColor(wins);
                    if(select1){
                        udx.drawString("YOU WIN!", 290, 200);
                    }else{
                        udx.drawString("P-1 WINS!", 290, 200);
                    }
                }
                //for lose
                if(player1Score<player2Score){
                    udx.setColor(lose);
                    if(select1){
                        udx.drawString("YOU LOSE!", 290, 200);
                    }else{
                        udx.drawString("P-2 WINS!", 290, 200);
                    }
                }
                //for tie
                if(player1Score==player2Score){
                    udx.setColor(tie);
                    udx.drawString("GAME TIE!", 290, 200);
                }
                udx.setColor(players);
                udx.drawString(pName + ":", 260, 240);
                udx.drawString(compName + ":", 260, 270);
                udx.drawString(""+player1Score, 400, 240);
                udx.drawString("" + player2Score, 400, 270);
                but[i].setEnabled(false);
            }
        }
        
        SwingUtilities.getWindowAncestor(this).repaint();
         
    }// end method PaintComponent
    
    /**public void paintAgain(Graphics rafa){
        //draw seeds ***
        rafa.setColor(Color.red);
        //udx.fillOval(320, 320, 10, 10);
        ovali = new Ellipse2D.Double();
        int xOval, yOval;
        for(int i=0; i<14; i++){
            int allSeeds = Integer.parseInt(but[i].getText());
            
            for(int k=0; k<allSeeds; k++){
                //udx.setColor(Color.black);
                //udx.drawOval(oval13x(i), oval13y(i), 15, 15);
                rafa.setColor(Color.red);
                rafa.fillOval(oval13x(i), oval13y(i), 15, 15);
            }
        }
    }*/
    
    public int oval13x(int j){
        int ovalino = (int) oval23[j].getWidth()-30;
        int suc = (new Random().nextInt(ovalino));
        return (((int) (oval23[j].getX()+10)+ suc));
    }
    
    public int oval13y(int j){
        int ovalino = (int) oval23[j].getHeight()-30;
        int suc = (new Random().nextInt(ovalino));
        return (((int) (oval23[j].getY()+10)+ suc));
    }
        
    
    public int getP1Seeds(){
        int playSeeds1 = 0;
        for(int i=1; i<7; i++){
            playSeeds1 += Integer.parseInt(but[i].getText());
        }
        return playSeeds1;
    }
    
    public int getP2Seeds(){
        int playSeeds2 = 0;
        for(int i=8; i<14; i++){
            playSeeds2 += Integer.parseInt(but[i].getText());
        }
        return playSeeds2;
    }
    
    public int getManSeeds1(){
        return Integer.parseInt(but[7].getText());
    }
    
    public int getManSeeds2(){
        return Integer.parseInt(but[0].getText());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /**for(int i= 0; i<14; i++){
            if ((e.getButton() == 1) && oval23[i].contains(e.getX(), e.getY()) ) {
                g2d.setColor(getBackground());
                repaint();
                System.out.println("Pit " + i + ", X = " + e.getX()+ " Y = " + e.getY());
            }
        }*/
        if(!pitsEmpty()){
            if(e.getSource()==setLabel){
                Sound.sound3.playSound();
                GameMenu.cover.setIcon(cover.getIcon());
                Main.cardMan.show(Main.panel, "ayoGMenu");
                GameMenu.p1.setText("<html><p>" + pName + " has <font color=\"#FF00FF\">" + 
                        getManSeeds1() + "</font> seeds in Store</p></html>");
                GameMenu.p2.setText("<html><p>" + compName + " has <font color=\"#FF00FF\">" + 
                        getManSeeds2() + "</font> seeds in Store</p></html>");
                GameMenu.playSeeds1.setText("(" + getP1Seeds() + " seeds remaining in pits)");
                GameMenu.playSeeds2.setText("(" + getP2Seeds() + " seeds remaining in pits)");
                if(select1){
                    GameMenu.gameMode.setText("<html><p><font color=\"#808080\">MODE: </font>" + modeName);
                }else{
                    GameMenu.gameMode.setText("<html><p><font color=\"#808080\">MODE: </font> HUMAN vs HUMAN");
                }
                if(player1 || p1Menu.game.turn){
                    GameMenu.playsNow.setText(pName + info + " . . .");
                }else{
                    GameMenu.playsNow.setText(compName + info + " . . .");
                }
            }
            if(e.getSource()==helpLabel){
                Sound.sound3.playSound();
                AyoInst.fromGame = true;
                AyoInst.cover.setIcon(background);
                Main.cardMan.show(Main.panel, "instAyo");
            }
            if(e.getSource()==quitLabel){
                Sound.sound7.playSound();
                Runtime.getRuntime().exit(0);
            }
        }
        int mx = e.getX();
        int my = e.getY();
        if(mx > exitImageX && mx < exitImageX+exitImage.getWidth(this) &&
            my > imageY && my < imageY+exitImage.getHeight(this)){
            Sound.sound3.playSound();
            Sound.soundWaiting.stopSong();
            if(p1Menu.ayo || p2Menu.ayo){
                Game.startSeeds = "4";
                Main.cardMan.show(Main.panel, "ayoMenu");
                Sound.soundAyo.stopSong();
            }else{
                Game.startSeeds = "3";
                Main.cardMan.show(Main.panel, "kalahMenu");
                Sound.soundKalah.stopSong();
            }
        }

        if(mx > imageY && mx < imageY+restartImage.getWidth(this) &&
            my > imageY && my < imageY+restartImage.getHeight(this)){
            Sound.sound3.playSound();
            Sound.soundWaiting.stopSong();
            if(Game.select1){
                p1Menu.mode1();
            }else{
                p2Menu.mode2();
            }
            if(p1Menu.ayo || p2Menu.ayo){
                Sound.soundAyo.stopSong();
            }else{
                Sound.soundKalah.stopSong();
            }
            Main.mancala = new Game();
            Main.panel.add(Main.mancala, "game");
            Main.cardMan.show(Main.panel, "game");
            if(Game.select1){
                p1Menu.game = new Ralfcala();
                p1Menu.game.initializeBoard();
                if(p1Menu.switched){
                    p1Menu.game.turn = !p1Menu.game.turn;
                }
                p1Menu.game.printBoard(false);
            }
            if(p1Menu.ayo || p2Menu.ayo){
                Sound.soundAyo.playSong();
            }else{
                Sound.soundKalah.playSong();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(!pitsEmpty()){
            if(e.getSource()==quitLabel){
                quitLabel.setBounds(15, 405, 35, 35);
            }
            if(e.getSource()==setLabel){
                setLabel.setBounds(55, 405, 36, 36);
            }
            if(e.getSource()==helpLabel){
                helpLabel.setBounds(95, 405, 36, 36);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==quitLabel){
            quitLabel.setBounds(10, 400, 35, 35);
        }
        if(e.getSource()==setLabel){
            setLabel.setBounds(50, 400, 36, 36);
        }
        if(e.getSource()==helpLabel){
            helpLabel.setBounds(90, 400, 36, 36);
        }
    }
    
    public JButton getNext(int enter){
        JButton next=null;
        int plus = (enter+1);
        if(enter==13 && !player1){
            next = but[0];
        }
        else if(enter==13 && player1){
            next = but[1];
        }
        else if(enter==6 && !player1){
            next = but[8];
        }
        else if((enter>=0)&&(enter<13)){
            next = but[plus];
        }
        return next;
           
    }
    
    public JButton oppositeTake(int input){
       JButton opposite=null;
       switch(input){
           case 1: opposite = but[13];
               break;
           case 2: opposite = but[12];
               break;
           case 3: opposite = but[11];
               break;
           case 4: opposite = but[10];
               break;
           case 5: opposite = but[9];
               break;
           case 6: opposite = but[8];
               break;
           case 8: opposite = but[6];
               break;
           case 9: opposite = but[5];
               break;
           case 10: opposite = but[4];
               break;
           case 11: opposite = but[3];
               break;
           case 12: opposite = but[2];
               break;
           case 13: opposite = but[1];
               break;
       }
       return opposite;
    }
    
    public static void setPlayer(boolean what){
        player1 = what;
    }
    
    public void switchPlayer(){
        if(player1){
            this.player1 = false;
        }
        else{
            this.player1 = true;
        }
    }
    
    public ArrayList getEmptyPits1(){
        ArrayList zeroPits = new ArrayList();     
        for(int y = 1; y < 7; y++){
            if(Integer.parseInt(but[y].getText()) == 0) {
                zeroPits.add(y);
            }
        }
        return zeroPits;
    }
    
    public ArrayList getEmptyPits2(){
        ArrayList zeroPits = new ArrayList();     
        for(int y = 8; y < 14; y++){
            if(Integer.parseInt(but[y].getText()) == 0) {
                zeroPits.add(y);
            }
        }
        return zeroPits;
    }
    
    public boolean pitsEmpty(){
        ArrayList emptySet1 = new ArrayList();
        ArrayList emptySet2 = new ArrayList();
        for(int h=1; h<7; h++){
            emptySet1.add(h);
        }
        for(int m=8; m<14; m++){
            emptySet2.add(m);
        }
        if(getEmptyPits1().containsAll(emptySet1) || getEmptyPits2().containsAll(emptySet2)){
            return true;
        }else{
            return false;
        }
    }
    
    public int getRemSeeds1(){
        int addSeeds1 = 0;
        for(int i=1; i<7; i++){
            addSeeds1 += Integer.parseInt(but[i].getText());
        }
        return addSeeds1;
    }
    
    public int getRemSeeds2(){
        int addSeeds2 = 0;
        for(int j=8; j<14; j++){
            addSeeds2 += Integer.parseInt(but[j].getText());
        }
        return addSeeds2;
    }
    
    public static void initializeBoard(){
        for(int i= 0; i<14; i++){
            if(i!=0 && i!=7){
                but[i].setText(startSeeds);
            }
            else{
                but[i].setText(manSeeds);
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        gameStarted = true;
        if(select1){
            boolean gameEnd = false;
            for(int i=1; i<7; i++){
                final JButton src = (JButton) e.getSource();
                if (src == but[i]) {
                    Sound.sound4.playSound();
                    gameEnd = p1Menu.game.move(i-1);
                }
            }
            for(int i=8; i<14; i++){
                final JButton src = (JButton) e.getSource();
                if (src == but[i]) {
                    Sound.sound4.playSound();
                    gameEnd = p1Menu.game.move(i-1);
                }
            }
            p1Menu.game.printBoard(!gameEnd);
        }else{
            if(player1){
                for(int i=1; i<7; i++){
                    startPits1 = Integer.parseInt(but[i].getText());
                    if(e.getSource()==but[i] && startPits1!=0){
                        Sound.sound4.playSound();
                        System.out.println("button " + i + " is " + but[i].getText());
                        collect = Integer.parseInt(but[i].getText());
                        but[i].setText(Integer.toString(zeroSeeds));
                        while(collect!=0){
                            getNext(i).setText(Integer.toString((Integer.parseInt(getNext(i).getText()))+1));
                            collect--;
                            if((i>=0)&&(i<13)){
                                i++;
                            }
                            else if(i==13){
                                i=1;
                            }
                        }
                        int last = i;
                        if(Ralfcala.kalah){
                            if(last>0 && last<7 && Integer.parseInt(but[last].getText()) == 1){
                                System.out.println("button " + i + " was a zero button so " 
                                        + Integer.parseInt(oppositeTake(last).getText()) + " seeds will be collected");
                                int pack = Integer.parseInt(oppositeTake(last).getText());
                                oppositeTake(last).setText(Integer.toString(zeroSeeds));
                                but[last].setText(Integer.toString(zeroSeeds));
                                but[7].setText(Integer.toString((Integer.parseInt(but[7].getText()))+pack+1));
                            }
                        }else{
                            if(last>0 && last<7 && Integer.parseInt(but[last].getText()) == 1 &&
                                    Integer.parseInt(oppositeTake(last).getText())!=0){
                                System.out.println("button " + i + " was a zero button so " 
                                        + Integer.parseInt(oppositeTake(last).getText()) + " seeds will be collected");
                                int pack = Integer.parseInt(oppositeTake(last).getText());
                                oppositeTake(last).setText(Integer.toString(zeroSeeds));
                                but[last].setText(Integer.toString(zeroSeeds));
                                but[7].setText(Integer.toString((Integer.parseInt(but[7].getText()))+pack+1));
                            }
                        }
                        if(last==7){
                            System.out.println("play again!!!");
                            changePlayer = false;
                        }else{
                            changePlayer = true;
                        }
                    }
                }
            }

            else if(!player1){
                for(int j=8; j<14; j++){
                    startPits2 = Integer.parseInt(but[j].getText());
                    if(e.getSource()==but[j] && startPits2!=0){
                        Sound.sound4.playSound();
                        System.out.println("button " + j + " is " + but[j].getText());
                        collect = Integer.parseInt(but[j].getText());
                        but[j].setText(Integer.toString(zeroSeeds));
                        while(collect!=0){
                            getNext(j).setText(Integer.toString((Integer.parseInt(getNext(j).getText()))+1));
                            collect--;
                            if((j>=0)&&(j<13)&&(j!=6)){
                                j++;
                            }
                            else if(j==13){
                                j=0;
                            }
                            else if(j==6){
                                j=8;
                            }
                        }
                        int last = j;
                        if(Ralfcala.kalah){
                            if(last>7 && last<14 && Integer.parseInt(but[last].getText()) == 1){
                                System.out.println(" button " + j + " was a zero button so " 
                                        + Integer.parseInt(oppositeTake(last).getText()) + " seeds will be collected");
                                int pack = Integer.parseInt(oppositeTake(last).getText());
                                oppositeTake(last).setText(Integer.toString(zeroSeeds));
                                but[last].setText(Integer.toString(zeroSeeds));
                                but[0].setText(Integer.toString((Integer.parseInt(but[0].getText()))+pack+1));
                            }
                        }else{
                            if(last>7 && last<14 && Integer.parseInt(but[last].getText()) == 1 &&
                                    Integer.parseInt(oppositeTake(last).getText())!=0){
                                System.out.println(" button " + j + " was a zero button so " 
                                        + Integer.parseInt(oppositeTake(last).getText()) + " seeds will be collected");
                                int pack = Integer.parseInt(oppositeTake(last).getText());
                                oppositeTake(last).setText(Integer.toString(zeroSeeds));
                                but[last].setText(Integer.toString(zeroSeeds));
                                but[0].setText(Integer.toString((Integer.parseInt(but[0].getText()))+pack+1));
                            }
                        }
                        if(last==0){
                            System.out.println("play again!!!");
                            changePlayer = false;
                        }else{
                            System.out.println("no way");
                            changePlayer = true;
                        }
                    }
                }
            }
            if(changePlayer){
                switchPlayer();
                //System.out.println(getRemSeeds1() + " " + getRemSeeds2());
            }
        }
    }//end method action performed

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(mx > exitImageX && mx < exitImageX+exitImage.getWidth(this) &&
            my > imageY && my < imageY+exitImage.getHeight(this)){
            eEntered = true;
        }else{
            eEntered = false;
        }

        if(mx > imageY && mx < imageY+restartImage.getWidth(this) &&
            my > imageY && my < imageY+restartImage.getHeight(this)){
            rEntered = true;
        }else{
            rEntered = false;
        }

    }
    
}
