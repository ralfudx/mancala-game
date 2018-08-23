/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ralfcala;

public class MakeBall extends Thread
{
  public void run()
  {
    while (Main.isRunning) {
      Main.giveBirth(80, 100, Math.random() * 1000.0,
                     Math.random() * 1000.0, 10);
      try 
      {
        sleep(500);
      } 
      catch (InterruptedException e) 
      {
      }
    }
  }
}
