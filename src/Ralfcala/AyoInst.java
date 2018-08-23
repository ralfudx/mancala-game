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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author - Raphael Owoicho (1115535)
 * @Version -  CMM013 MSc Project
 * @Date - 29/08/2013
 */

public class AyoInst extends JPanel implements MouseListener{
    private JPanel butPanel;
    public static JLabel menuName, textLabel, backLabel, cover;
    private Font textFont = new Font("Comic Sans MS", 2, 15);
    private Font nameFont = new Font("Times New Roman", 1, 25);
    private ImageIcon backImage;
    public static volatile boolean fromGame, ayo;
    public static int seeds;
    private Image dbImage;
    private Graphics dbGraphics;
    private String input, gName;
    
    public AyoInst(){
        //create......
        menuName = new JLabel("INSTRUCTIONS");
        cover = new JLabel();
        butPanel = new JPanel();
        textLabel = new JLabel();
       
        backImage = new ImageIcon(getClass().getResource("Images/deleted.png"));
        backLabel = new JLabel(backImage);
        
        //add Listeners.... 
        backLabel.addMouseListener(this);
        
        //customize
        textLabel.setFont(textFont);
        textLabel.setForeground(Color.white);
        menuName.setFont(nameFont);
        menuName.setForeground(Color.cyan);
        
        //set tool tip texts...
        backLabel.setToolTipText("Back");
        
        //set position on panel....
        cover.setBounds(0, 0, 700, 450);
        menuName.setBounds(115, 15, 280, 30);
        backLabel.setBounds(10, 360, 32, 32);
        textLabel.setBounds(10, 30, 420, 370);
        butPanel.setBackground(Color.BLACK);
        butPanel.setBounds(140, 20, 440, 400);
        butPanel.setLayout(null);
        
        butPanel.add(menuName);
        butPanel.add(backLabel);
        butPanel.add(textLabel);
        
        this.setLayout(null);
        this.add(butPanel);
        this.add(cover);
        
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
        if(Ralfcala.kalah){
            gName = "KALAH";
            input = "you capture the seed and place it in your Mancala.";
        }else{
            gName = "AYO";
            input = "and there are some seeds in the opposite pit on your opponents side, you capture "
                + "all the seeds in the two pits and place them in your Mancala.";
        }
        textLabel.setText("<html><p></p><p align = \"justify\">The object of the " + gName + " game is to collect as many seeds "
                + "in your mancala (big pit on your right side) as possible.</p>"
                + "<p><font color=\"#FF00FF\">The following rules also apply:</font></p>"
                + "<p align = \"justify\">1) You can only move the seeds on your side of the board.</p>"
                + "<p align = \"justify\">2) To make a move, you pick up all the seeds in a pit and distibute "
                + "them in a counterclockwise direction to the next pit. In this variant, you start with "+ seeds + " seeds</p>"
                + "<p align = \"justify\">3) If the last seed of a move lands on your mancala, you get another "
                + "turn to play again.</p>"
                + "<p align = \"justify\">4) If the last seed of a move lands on an empty pit on your side of the board, "
                + input + "</p>"
                + "<p><center><font color=\"#FF00FF\" " + "size=\"5\">Enjoy the Game!!!</font></center></p></html>");
        repaint();
    }
    
       
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==backLabel){
            Sound.sound3.playSound();
            if(fromGame){
                Main.cardMan.show(Main.panel, "game");
            }else{
                if(ayo){
                Main.cardMan.show(Main.panel, "ayoMenu");
                }else{
                    Main.cardMan.show(Main.panel, "kalahMenu");
                }
            }
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
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==backLabel){
            backLabel.setBounds(10, 360, 32, 32);
        }
    }
     
}
