/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ralfcala;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author - Raphael Owoicho (1115535)
 * @Version -  CMM013 MSc Project
 * @Date - 29/08/2013
 */

public class KalahMenu extends JPanel implements ActionListener{
    JPanel name, one, two, three, four, oneContain, twoContain, threeContain,
            fourContain, oneDisp, twoDisp, threeDisp, fourDisp, mainPanel;
    JLabel game, oneLabel, twoLabel, threeLabel, sound, type, music;
    JToggleButton soundSel, musicSel;
    JSlider musicSlide, soundSlide;
    Font font = new Font("Arial", Font.BOLD, 18);
    Font font2 = new Font("Serif", Font.ITALIC, 16);
    Font font4 = new Font("Serif", Font.PLAIN, 16);
    JButton human, easy, medium, hard, p1, p2, seed3,
            seed4, seed5, seed6, back, start;
    JButton [] buttonArray;
    //Dimension menuPanelSize = new Dimension(500, 50);
    //Dimension menuPanelSize4 = new Dimension(500, 60);
    String oneShow="Default: Human";
    String twoShow="Default: Player 1";
    String threeShow="Default: 4";
    private final int min=0, max=5, init3=3, init2=2;
    //MenuPanelAction actor;
    String oneText = "Player 2 is ", twoText = " plays first", entryText = "";
    int playerTwo = 0, goesFirst, noOfSeeds;
    Main main;
    
    public KalahMenu(){
        this.setPreferredSize(new Dimension(600, 400));
        
        //create JPanels and items
        name = new JPanel();
        mainPanel = new JPanel();
        one = new JPanel();
        two = new JPanel();
        three = new JPanel();
        four = new JPanel();
        oneContain = new JPanel();
        twoContain = new JPanel();
        threeContain = new JPanel();
        fourContain = new JPanel();
        oneDisp = new JPanel();
        twoDisp = new JPanel();
        threeDisp = new JPanel();
        fourDisp = new JPanel();
        
        //actor = new MenuPanelAction(this);
        
        //create sliders
       /* musicSlide = new JSlider(JSlider.HORIZONTAL, min, max, init3);
        musicSlide.setMajorTickSpacing(1);
        musicSlide.setPaintTicks(true);
        musicSlide.setPaintLabels(true);
        //musicSlide.addChangeListener(this);
        soundSlide = new JSlider(JSlider.HORIZONTAL, min, max, init2);
        soundSlide.setMajorTickSpacing(1);
        soundSlide.setPaintTicks(true);
        soundSlide.setPaintLabels(true);*/
        
        //create labels
        game = new JLabel("KALAH");
        game.setFont(font);
        initializeDisplayLabel();
        sound = new JLabel("Sound");
        type = new JLabel("Mancala Type");
        music = new JLabel("Music");
        sound.setFont(font4);
        type.setFont(font4);
        music.setFont(font4);
        
        one.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, Color.GRAY), 
                "Player 2 is:", TitledBorder.LEFT, TitledBorder.TOP, font2, Color.black));
        two.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, Color.DARK_GRAY), 
                "Who plays first:", TitledBorder.LEFT, TitledBorder.TOP, font2, Color.black));
        three.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, Color.GRAY), 
                "How many seeds:", TitledBorder.LEFT, TitledBorder.TOP, font2, Color.black));
        four.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, Color.DARK_GRAY), 
                "Options:", TitledBorder.LEFT, TitledBorder.TOP, font2, Color.black));
        
        //create JButtons
        human = new JButton("HUMAN");
        easy = new JButton("EASY");
        medium = new JButton("MEDIUM");
        hard = new JButton("HARD");
        p1 = new JButton("PLAYER 1");
        p2 = new JButton("PLAYER 2");
        seed3 = new JButton("3");
        seed4 = new JButton("4");
        seed5 = new JButton("5");
        seed6 = new JButton("6");
        back = new JButton("BACK");
        start = new JButton("START");
        buttonArray = new JButton[10];
        buttonArray[0] = human;
        buttonArray[1] = easy;
        buttonArray[2] = medium;
        buttonArray[3] = hard;
        buttonArray[4] = p1;
        buttonArray[5] = p2;
        buttonArray[6] = seed3;
        buttonArray[7] = seed4;
        buttonArray[8] = seed5;
        buttonArray[9] = seed6;
               
        //add actionListener
        human.addActionListener(this);
        easy.addActionListener(this);
        medium.addActionListener(this);
        hard.addActionListener(this);
        p1.addActionListener(this);
        p2.addActionListener(this);
        seed3.addActionListener(this);
        seed4.addActionListener(this);
        seed5.addActionListener(this);
        seed6.addActionListener(this);
        back.addActionListener(this);
        start.addActionListener(this);
        
        //add panels
        //one...
        one.setLayout(null);
        one.add(oneContain);
        one.add(oneDisp);
        oneContain.setLayout(new GridLayout(1,4,8,8));
        /**oneContain.setBackground(Color.BLACK);
        one.setBackground(Color.BLACK);
        oneDisp.setBackground(Color.BLACK);*/
        //two...
        two.setLayout(null);
        two.add(twoContain);
        two.add(twoDisp);
        twoContain.setLayout(new GridLayout(1,2,8,8));
        /**twoContain.setBackground(Color.BLACK);
        two.setBackground(Color.BLACK);
        twoDisp.setBackground(Color.BLACK);*/
        //three...
        three.setLayout(null);
        three.add(threeContain);
        three.add(threeDisp);
        threeContain.setLayout(new GridLayout(1,4,8,8));
        /**threeContain.setBackground(Color.BLACK);
        three.setBackground(Color.BLACK);
        threeDisp.setBackground(Color.BLACK);*/
        //four...
        four.setLayout(null);
        four.add(fourDisp);
        four.add(fourContain);
        fourDisp.setLayout(null);
        fourContain.setLayout(null);
        //additems to containers...
        oneContain.add(human);
        oneContain.add(easy);
        oneContain.add(medium);
        oneContain.add(hard);
        oneDisp.add(oneLabel);
        twoContain.add(p1);
        twoContain.add(p2);
        twoDisp.add(twoLabel);
        threeContain.add(seed3);
        threeContain.add(seed4);
        threeContain.add(seed5);
        threeContain.add(seed6);
        threeDisp.add(threeLabel);
        fourContain.add(back);
        fourContain.add(start);
        fourDisp.add(sound);
        fourDisp.add(music);
        
        mainPanel.setLayout(null);
        mainPanel.add(game);
        mainPanel.add(one);
        mainPanel.add(two);
        mainPanel.add(three);
        mainPanel.add(four);
        game.setBounds(200, 1, 500, 50);
        one.setBounds(1, 51, 500, 90);
        two.setBounds(1, 141, 500, 90);
        three.setBounds(1, 231, 500, 90);
        four.setBounds(1, 321, 500, 85);
        oneContain.setBounds(50, 25, 400, 30);
        oneDisp.setBounds(150, 50, 200, 25);
        twoContain.setBounds(100, 25, 300, 30);
        twoDisp.setBounds(150, 50, 200, 25);
        threeContain.setBounds(100, 25, 300, 30);
        threeDisp.setBounds(150, 50, 200, 25);
        fourContain.setBounds(15, 40, 450, 40);
        fourDisp.setBounds(150, 15, 300, 35);
        back.setBounds(15, 12, 80, 25);
        start.setBounds(370, 12, 80, 25);
        sound.setBounds(20, 7, 200, 20);
        music.setBounds(100, 7, 200, 20);
        mainPanel.setBounds(100, 20, 500, 410);
        
        this.add(mainPanel);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
       
    }//end constructor KalahMenu
    
    public final void initializeDisplayLabel(){
        oneLabel = new JLabel(oneShow);
        oneLabel.setForeground(Color.LIGHT_GRAY);
        oneLabel.setFont(font2);
        playerTwo = 0;
        twoLabel = new JLabel(twoShow);
        twoLabel.setForeground(Color.LIGHT_GRAY);
        twoLabel.setFont(font2);
        goesFirst = 1;
        threeLabel = new JLabel(threeShow);
        threeLabel.setForeground(Color.LIGHT_GRAY);
        threeLabel.setFont(font2);
        setSeeds(4);
    }//end method initializeDisplayLabel
   

    //@Override
    public void stateChanged(ChangeEvent e) {
    }
    
    public void setSeeds(int input){
        this.noOfSeeds = input;
    }
    
    public int getSeeds(){
        return noOfSeeds;
    }
    
    public void setPlayer(int put){
        this.playerTwo = put;
    }
    
    public int getPlayerTwo(){
        return playerTwo;
    }
    
    public String doSomething(){
        String rite = "";
        switch(getPlayerTwo()){
            case 0:
                rite = "Human";
                break;
            case 1:
                rite = "Easy Robot";
                break;
            case 2:
                rite = "Medium Robot";
                break;
            case 3:
                rite = "Hard Robot";
                break;
        }
        return rite;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //for all buttons
        for(int j=0; j<10; j++){
            if(e.getSource()==buttonArray[j]){
                Sound.sound3.playSound();
            }
        }
        //for first panel
        if(e.getSource()==human){
            setPlayer(0);
            oneLabel.setText(oneText + doSomething());
            oneLabel.setForeground(Color.BLACK);
        }
        if(e.getSource()==easy){
            setPlayer(1);
            oneLabel.setText(oneText + doSomething());
            oneLabel.setForeground(Color.BLACK);
        }
        if(e.getSource()==medium){
            setPlayer(2);
            oneLabel.setText(oneText + doSomething());
            oneLabel.setForeground(Color.BLACK);
        }
        if(e.getSource()==hard){
            setPlayer(3);
            oneLabel.setText(oneText + doSomething());
            oneLabel.setForeground(Color.BLACK);
        }

        //for second panel
        if(e.getSource()==p1){
            twoLabel.setText("Player 1" + twoText);
            twoLabel.setForeground(Color.BLACK);
            goesFirst=1;
        }
        if(e.getSource()==p2){
            twoLabel.setText("Player 2" + twoText);
            twoLabel.setForeground(Color.BLACK);
            goesFirst=2;
        }

        //for third panel
        if(e.getSource()==seed3){
            threeLabel.setText("3 Seeds");
            threeLabel.setForeground(Color.BLACK);
            setSeeds(3);
        }
        if(e.getSource()==seed4){
            threeLabel.setText("4 Seeds");
            threeLabel.setForeground(Color.BLACK);
            setSeeds(4);
        }
        if(e.getSource()==seed5){
            threeLabel.setText("5 Seeds");
            threeLabel.setForeground(Color.BLACK);
            setSeeds(5);
        }
        if(e.getSource()==seed6){
            threeLabel.setText("6 Seeds");
            threeLabel.setForeground(Color.BLACK);
            setSeeds(6);
        }
        System.out.println("seeds set to " + getSeeds());
        
        //for fourth panel
        if(e.getSource()==back){
            System.exit(0);
        }
        if(e.getSource()==start){
            main.cardMan.show(main.panel, "ayoFrame");
        }
    }
 
    
    public String pasteToScreen(){
        String play2 = "", output = "";
        
        switch(goesFirst){
            case 1:
                output = "Player 1";
                break;
            case 2:
                switch(playerTwo){
                    case 0:
                        play2 = "Human Player 2";
                        break;
                    case 1:
                        play2 = "Easy Robot";
                        break;
                    case 2:
                        play2 = "Medium Robot";
                        break;
                    case 3:
                        play2 = "Hard Robot";
                        break;
                }
                output = play2;
                break;
        }
        return output + " plays first";
    }
    
}
