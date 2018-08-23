/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ralfcala;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 *
 * @author - Raphael Owoicho (1115535)
 * @Version -  CMM013 MSc Project
 * @Date - 29/08/2013
 */

public class Sound {
    private AudioClip clip;
    public static final Sound sound0 = new Sound("Sound/backSound.wav");
    public static final Sound sound1 = new Sound("Sound/mainButtonSound.wav");
    public static final Sound sound2 = new Sound("Sound/startGameSound.wav");
    public static final Sound sound3 = new Sound("Sound/ayoButtonSound.wav");
    public static final Sound sound4 = new Sound("Sound/pickSeeds.wav");
    public static final Sound sound5 = new Sound("Sound/customButtonSound.wav");
    public static final Sound sound6 = new Sound("Sound/startCGSound.wav");
    public static final Sound sound7 = new Sound("Sound/warningSound.wav");
    public static final Sound soundAyo = new Sound("Sound/AyoGameSound.wav");
    public static final Sound soundKalah = new Sound("Sound/KalahGameSound.wav");
    public static final Sound soundCustom = new Sound("Sound/customGameSound.wav");
    public static final Sound soundWaiting = new Sound("Sound/waitingSound.wav");
    public static final Sound soundWin = new Sound("Sound/winSound.wav");
    public static final Sound soundLose = new Sound("Sound/loseSound.wav");
    public static final Sound soundTie = new Sound("Sound/tieSound.wav");
    public static volatile boolean soundOn = true;
    
    public Sound(String soundFile){
        try{
            clip = Applet.newAudioClip(Sound.class.getResource(soundFile));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void playSound(){
        if (soundOn){
            try{
                new Thread(){
                    @Override
                    public void run(){
                        clip.play();
                    }
                }.start();

            }catch(Exception ez){
                ez.printStackTrace();
            }
        }
    }
    public void playSong(){
        try{
            new Thread(){
                @Override
                public void run(){
                    clip.loop();
                }
            }.start();
            
        }catch(Exception ez){
            ez.printStackTrace();
        }
    }
    
    public void stopSong(){
        try{
            new Thread(){
                @Override
                public void run(){
                    clip.stop();
                }
            }.start();
            
        }catch(Exception ez){
            ez.printStackTrace();
        }
    }
    
}
