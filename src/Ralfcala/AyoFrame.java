/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ralfcala;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author - Raphael Owoicho (1115535)
 * @Version -  CMM013 MSc Project
 * @Date - 29/08/2013
 */

public class AyoFrame extends JPanel implements ActionListener{
    int w = 500, h = 400;
    Dimension dim = new Dimension(w,h);
    JPanel panel1, panelL, panelR, ayoPanel, topP, bottomP, leftP, rightP, insideP, tp1, tp2, tp3;
    JButton button6, gridButtons [], menuBut;
    JLabel p1Seeds, p2Seeds, gameName, display;
    int startSeeds = 4, zeroSeeds = 0, collect;
    Font seedFont = new Font("Magneta", Font.PLAIN, 30);
    Font nameFont = new Font("Serif", Font.ITALIC, 50);
    AyoMenu menu;
    boolean player1 = true, player2 = false;
    
    public AyoFrame(){
        this.setSize(dim);
        this.setPreferredSize(dim);
        menu = new AyoMenu();
        
        panel1 = new JPanel();
        panelL = new JPanel();
        panelR = new JPanel();
        ayoPanel = new JPanel();
        topP = new JPanel();
        leftP = new JPanel();
        rightP = new JPanel();
        insideP = new JPanel();
        bottomP = new JPanel();
        button6 = new JButton();
        menuBut = new JButton("MENU");
        menuBut.setBounds(20, 50, 100, 35);
        menuBut.addActionListener(this);
        tp1 = new JPanel();
        tp2 = new JPanel();
        tp3 = new JPanel();
        p1Seeds = new JLabel("Player 1 Seeds");
        p2Seeds = new JLabel("Player 2 Seeds");
        gameName = new JLabel("AYO.mancala");
        display = new JLabel("");
        gameName.setFont(nameFont);
        display.setFont(nameFont);
        
        gridButtons = new JButton[14];
        gridButtons[13] = new JButton();
        gridButtons[12] = new JButton();
        gridButtons[13].setText(Integer.toString(zeroSeeds));
        gridButtons[12].setText(Integer.toString(zeroSeeds));
        gridButtons[13].addActionListener(this);
        gridButtons[12].addActionListener(this);
        gridButtons[13].setEnabled(false);
        //gridButtons[13].setForeground(Color.red);
        gridButtons[13].setFont(seedFont);
        gridButtons[12].setEnabled(false);
        //gridButtons[12].setForeground(Color.RED);
        gridButtons[12].setFont(seedFont);
        
        panel1.setLayout(new GridLayout(2,6));
        panelL.setLayout(new GridLayout(1,1));
        panelR.setLayout(new GridLayout(1,1));
        for(int i=0; i<12; i++){
            gridButtons[i] = new JButton();
            gridButtons[i].setText(Integer.toString(startSeeds));
            gridButtons[i].setFont(seedFont);
            gridButtons[i].addActionListener(this);
            panel1.add(gridButtons[i]);
            if((i>5)&&(i<=11)){
                gridButtons[i].setForeground(Color.BLUE);
                if(player1==false){
                    gridButtons[i].setEnabled(player1);
                }
            }
            else if((i>=0)&&(i<6)){
                gridButtons[i].setForeground(Color.ORANGE);
                if(player2==true){
                    gridButtons[i].setEnabled(player2);
                }
            }
        }
        
        panelL.add(gridButtons[13]);
        panelR.add(gridButtons[12]);
        this.setLayout(null);
        this.add(topP);
        this.add(insideP);
        this.add(bottomP);
        
        insideP.setLayout(new BorderLayout());
        insideP.add(leftP, BorderLayout.WEST);
        insideP.add(ayoPanel, BorderLayout.CENTER);
        insideP.add(rightP, BorderLayout.EAST);
        
        topP.setLayout(null);
        topP.add(gameName);
        gameName.setBounds(200, 10, 300, 60);
        topP.setBounds(1, 1, 690, 70);
        insideP.setBounds(1, 71, 690, 310);
        bottomP.setBounds(1, 381, 690, 70);
        
        bottomP.setPreferredSize(new Dimension(500, 100));
        bottomP.setLayout(null);
        //bottomP.add(display);
        bottomP.add(menuBut);
        menuBut.setBounds(10, 20, 70, 30);
        
        leftP.setLayout(new GridLayout(2,1));
        leftP.setPreferredSize(new Dimension(120, 400));
        leftP.add(p1Seeds);
        
        rightP.setLayout(new GridLayout(2,1));
        rightP.setPreferredSize(new Dimension(120, 400));
        rightP.add(p2Seeds);
        
        ayoPanel.setLayout(new BorderLayout());
        ayoPanel.add(panel1, BorderLayout.CENTER);
        ayoPanel.add(panelL, BorderLayout.WEST);
        ayoPanel.add(panelR, BorderLayout.EAST);
        
    }//end constructor GamePanel
    
    public void setStartSeeds(int entry){
        startSeeds = entry;
    }
    
    public int getStartSeeds(){
        return startSeeds;   
    }
    
    public JButton getNext1(int enter){
        JButton next=null;
        int minus = (enter-1);
        int plus = (enter+1);
        if(enter==0){
            next = gridButtons[6];
        }
        else if((enter>0)&&(enter<6)){
            next = gridButtons[minus];
        }
        else if(enter==11){
            next = gridButtons[12];
        }
        else if((enter>5)&&(enter<11)){
            next = gridButtons[plus];
        }
        //else if(enter==13){
           // next = gridButtons[6];
        //}
        else if(enter==12){
            next = gridButtons[5];
        }
        return next;
           
    }
    
    public JButton getNext2(int enter){
        JButton next=null;
        int minus = (enter-1);
        int plus = (enter+1);
        if(enter==0){
            next = gridButtons[13];
        }
        else if((enter>0)&&(enter<6)){
            next = gridButtons[minus];
        }
        else if(enter==11){
            next = gridButtons[5];
        }
        else if((enter>5)&&(enter<11)){
            next = gridButtons[plus];
        }
        else if(enter==13){
            next = gridButtons[6];
        }
        //else if(enter==12){
          //  next = gridButtons[5];
        //}
        return next;
           
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //for player1
        for(int i=6; i<12; i++){
            if (e.getSource()== gridButtons[i]){
                Sound.sound4.playSound();
                collect = Integer.parseInt(gridButtons[i].getText());
                gridButtons[i].setText(Integer.toString(zeroSeeds));
                while(collect!=0){
                    getNext1(i).setText(Integer.toString((Integer.parseInt(getNext1(i).getText()))+1));
                    collect--;
                    if((i>0)&&(i<6)){
                        i--;
                    }
                    else if((i>5)&&(i<11)){
                        i++;
                    }
                    else if(i==0){
                        i=6;
                    }
                    //else if (i==13){
                       //i=6;
                    //}
                    else if(i==11){
                        //Sound.sound3.playSound();
                        i=12;
                    }
                    else if(i==12){
                        i=5;
                    }
                }
            }
            
        }
        //for player2
        for(int i=0; i<6; i++){
            if (e.getSource()== gridButtons[i]){
                Sound.sound4.playSound();
                collect = Integer.parseInt(gridButtons[i].getText());
                gridButtons[i].setText(Integer.toString(zeroSeeds));
                while(collect!=0){
                    getNext2(i).setText(Integer.toString((Integer.parseInt(getNext2(i).getText()))+1));
                    collect--;
                    if((i>0)&&(i<6)){
                        i--;
                    }
                    else if((i>5)&&(i<11)){
                        i++;
                    }
                    else if(i==0){
                        //Sound.sound3.playSound();
                        i=13;
                    }
                    else if (i==13){
                        i=6;
                    }
                    else if(i==11){
                        i=5;
                    }
                    //else if(i==12){
                        //i=5;
                    //}
                }
            }
            
        }
        //System.out.println("excellent " + menu.getSeeds());
        player1 = !player1;
        player2 = !player2;
        
        if(e.getSource() == menuBut){
            Sound.sound3.playSound();
            Main.cardMan.show(Main.panel, "ayoGMenu");
        }
    }// end method action performed
}// end class AyoFrame

