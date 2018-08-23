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
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author - Raphael Owoicho (1115535)
 * @Version -  CMM013 MSc Project
 * @Date - 29/08/2013
 */

public class GameMenu extends JPanel implements MouseListener{
    private JPanel butPanel, dispPanel;
    public static JLabel menuName, soundLabel, backLabel, restartLabel, resumeLabel,
            saveLabel, p1, p2, playSeeds1, playSeeds2, gameMode, playsNow, cover;
    Main main;
    private Font textFont = new Font("Comic Sans MS", 2, 15);
    private Font nameFont = new Font("Times New Roman", 1, 25);
    private Graphics dbGraphics;
    private ImageIcon soundImage1, soundImage2, backImage, restartImage, resumeImage, saveImage;
    private Image dbImage, sOnImage, sOffImage;
    public static volatile boolean custom;
    
    public GameMenu(){
        //create......
        menuName = new JLabel("SETTINGS");
        butPanel = new JPanel();
        dispPanel = new JPanel();
        p1 = new JLabel();
        p2 = new JLabel();
        playSeeds1 = new JLabel();
        playSeeds2 = new JLabel();
        gameMode = new JLabel();
        playsNow = new JLabel();
        cover = new JLabel();
        
         try {
            sOnImage = ImageIO.read(getClass().getResource("Images/tick.png"));
            sOffImage = ImageIO.read(getClass().getResource("Images/cancel.png"));

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        
        soundImage1 = new ImageIcon(getClass().getResource("Images/speaker.png"));
        soundImage2 = new ImageIcon(getClass().getResource("Images/speaker2.png"));
        soundLabel = new JLabel();
        backImage = new ImageIcon(getClass().getResource("Images/exit_door.png"));
        backLabel = new JLabel(backImage);
        restartImage = new ImageIcon(getClass().getResource("Images/restart.png"));
        restartLabel = new JLabel(restartImage);
        resumeImage = new ImageIcon(getClass().getResource("Images/resume.png"));
        resumeLabel = new JLabel(resumeImage);
        saveImage = new ImageIcon(getClass().getResource("Images/disk.png"));
        saveLabel = new JLabel(saveImage);
        
        //add Listeners.... 
        soundLabel.addMouseListener(this);
        backLabel.addMouseListener(this);
        resumeLabel.addMouseListener(this); 
        restartLabel.addMouseListener(this);
        saveLabel.addMouseListener(this);
        
        //customize
        p1.setFont(textFont);
        p1.setForeground(Color.white);
        p2.setFont(textFont);
        p2.setForeground(Color.white);
        playSeeds1.setFont(textFont);
        playSeeds1.setForeground(Color.magenta);
        playSeeds2.setFont(textFont);
        playSeeds2.setForeground(Color.magenta);
        gameMode.setFont(textFont);
        gameMode.setForeground(Color.cyan);
        playsNow.setFont(textFont);
        playsNow.setForeground(Color.white);
        menuName.setFont(nameFont);
        menuName.setForeground(Color.decode("#EBF4FA"));
        
        //set tool tip texts...
        backLabel.setToolTipText("Back to Ayo Menu");
        soundLabel.setToolTipText("Game Sound");
        resumeLabel.setToolTipText("Resume Game");
        restartLabel.setToolTipText("Start a New Game");
        saveLabel.setToolTipText("Save Current Game");
        
        //set position on panel....
        cover.setBounds(0, 0, 700, 450);
        p1.setBounds(10, 10, 220, 40);
        p2.setBounds(10, 80, 220, 40);
        playSeeds1.setBounds(25, 50, 220, 20);
        playSeeds2.setBounds(25, 120, 220, 20);
        gameMode.setBounds(10, 160, 220, 20);
        playsNow.setBounds(40, 190, 220, 20);
        menuName.setBounds(70, 10, 180, 50);
        soundLabel.setBounds(170, 350, 42, 42);
        backLabel.setBounds(225, 350, 35, 35);
        restartLabel.setBounds(70, 350, 35, 35);
        resumeLabel.setBounds(20, 350, 35, 35);
        saveLabel.setBounds(120, 350, 35, 35);
        butPanel.setBackground(Color.BLACK);
        dispPanel.setBackground(butPanel.getBackground());
        butPanel.setBounds(210, 20, 280, 400);
        //dispPanel.setBackground(Color.LIGHT_GRAY);
        dispPanel.setBounds(25, 50, 240, 250);
        dispPanel.setLayout(null);
        butPanel.setLayout(null);
        
        //add to panel...
        dispPanel.add(p1);
        dispPanel.add(p2);
        dispPanel.add(playSeeds1);
        dispPanel.add(playSeeds2);
        dispPanel.add(gameMode);
        dispPanel.add(playsNow);
        
        butPanel.add(menuName);
        butPanel.add(soundLabel);
        butPanel.add(backLabel);
        butPanel.add(resumeLabel);
        butPanel.add(restartLabel);
        butPanel.add(saveLabel);
        butPanel.add(dispPanel);
        
        this.setLayout(null);
        //this.add(dispPanel);
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
        if(Sound.soundOn){
            udx.drawImage(sOnImage, 405, 395, this);
        }else{
            udx.drawImage(sOffImage, 405, 395, this);
        }
        if(custom){
            soundLabel.setIcon(soundImage2);
        }else{
            soundLabel.setIcon(soundImage1);
        }
        
        repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==soundLabel){
            if(Sound.soundOn){
                Sound.soundOn = false;
                Sound.soundCustom.stopSong();
                Sound.soundAyo.stopSong();
                Sound.soundKalah.stopSong();
            }
            else if(!Sound.soundOn){
                Sound.soundOn = true;
                if(custom){
                    Sound.sound5.playSound();
                    Sound.soundCustom.playSong();
                }else{
                    Sound.sound3.playSound();
                    if(p1Menu.ayo || p2Menu.ayo){
                        Sound.soundAyo.playSong();
                    }else{
                        Sound.soundKalah.playSong();
                    }
                }
            }
        }
        if(e.getSource()==backLabel){
            Sound.sound7.playSound();

            UIManager UI=new UIManager();
            UI.put("OptionPane.background", Color.decode("#C2B280"));
            UI.put("Panel.background", Color.decode("#C2B280"));
            int option = JOptionPane.showConfirmDialog(null, "Current Game progress will be Lost.\nContinue?",
                "WARNING!!!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if(option == JOptionPane.OK_OPTION){
                if(custom){
                    Sound.sound5.playSound();
                    CustomMenu.resetMenu();
                    Sound.soundCustom.stopSong();
                    Main.cardMan.show(Main.panel, "customMenu");
                }else{
                    Sound.sound3.playSound();
                    if(!Ralfcala.kalah){
                        Game.startSeeds = "4";
                        main.cardMan.show(main.panel, "ayoMenu");
                        Sound.soundAyo.stopSong();
                    }else{
                        Game.startSeeds = "3";
                        main.cardMan.show(main.panel, "kalahMenu");
                        Sound.soundKalah.stopSong();
                    }
                }
            }
            
        }
        if(e.getSource()==resumeLabel){
            if(custom){
                Sound.sound5.playSound();
                Main.cardMan.show(Main.panel, "gameCustom");
            }else{
                Sound.sound3.playSound();
                main.cardMan.show(main.panel, "game");
            }
        }
        if(e.getSource()==restartLabel){
            if(custom){
                Sound.sound5.playSound();
                CustomMenu.customGame = new CustomGame();
                Main.panel.add(CustomMenu.customGame, "gameCustom");
                CustomPage.mode();
                Sound.soundCustom.stopSong();
                Main.cardMan.show(Main.panel, "gameCustom");
                CustomPage.game = new Ralfcala();
                CustomPage.game.initializeBoard();
                if("PLAYER 2".equals(CustomMenu.playsL2.getText()) || "COMPUTER".equals(CustomMenu.playsL2.getText())){
                    CustomPage.game.turn = !CustomPage.game.turn;
                }
                CustomPage.game.printBoard2(false);
                Sound.soundCustom.playSong();
            }else{
                Sound.sound3.playSound();
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
        if(e.getSource()==saveLabel){
            Sound.sound7.playSound();
            JOptionPane.showMessageDialog(null, "Sorry! This function is presently unavailable",
                "Game Information", JOptionPane.INFORMATION_MESSAGE);
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
        if(e.getSource()==soundLabel){
            soundLabel.setBounds(175, 355, 42, 42);
        }
        if(e.getSource()==backLabel){
            backLabel.setBounds(230, 355, 35, 35);
        }
        if(e.getSource()==resumeLabel){
            resumeLabel.setBounds(15, 355, 35, 35);
        }
        if(e.getSource()==restartLabel){
            restartLabel.setBounds(65, 355, 35, 35);
        }
        if(e.getSource()==saveLabel){
            saveLabel.setBounds(120, 345, 35, 35);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==soundLabel){
            soundLabel.setBounds(170, 350, 42, 42);
        }
        if(e.getSource()==backLabel){
            backLabel.setBounds(225, 350, 35, 35);
        }
        if(e.getSource()==resumeLabel){
            resumeLabel.setBounds(20, 350, 35, 35);
        }
        if(e.getSource()==restartLabel){
            restartLabel.setBounds(70, 350, 35, 35);
        }
        if(e.getSource()==saveLabel){
            saveLabel.setBounds(120, 350, 35, 35);
        }
    }
    
}
