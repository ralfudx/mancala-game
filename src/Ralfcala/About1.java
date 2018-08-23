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

public class About1 extends JPanel implements MouseListener{
    private JPanel butPanel;
    public static JLabel menuName, textLabel, backLabel, cover;
    public static volatile boolean ayo, custom;
    private Font textFont = new Font("Comic Sans MS", 2, 15);
    private Font nameFont = new Font("Times New Roman", 1, 25);
    private ImageIcon backImage;
    
    public About1(){
        //create......
        menuName = new JLabel("MANCALA");
        cover = new JLabel();
        butPanel = new JPanel();
        textLabel = new JLabel("<html><p align = \"justify\">Mancala is a family of two-player, zero sum board games of perfect information "
                + "played around the world particularly in Africa. It is played by moving seeds/pebbles around an array "
                + "of holes distributed over two rows or more.</p><p></p>"
                + "<p align = \"justify\">This version of the game serves as a project submitted by "
                + "Raphael Owoicho (1115535) as part of the requirements "
                + "for the degree of MSc in Computing: Software Technology with Network Management at the School "
                + "of Computing and Digital media, Robert Gordon University Aberdeen, United Kingdom.</p>"
                + "<center><p><font color=\"#FF00FF\">August 2013<br>"
                + "</br>Supervised by: Dr. Robin Boswell</font></p></center></html>");
       
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
        backLabel.setToolTipText("Back to Menu");;
        
        //set position on panel....
        cover.setBounds(0, 0, 700, 450);
        menuName.setBounds(145, 15, 280, 30);
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
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==backLabel){
            if(custom){
                Sound.sound5.playSound();
                Main.cardMan.show(Main.panel, "customMenu");
            }else{
                Sound.sound3.playSound();
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
