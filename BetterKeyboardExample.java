/* This class contains some small improvements over the KeyboardExample class.

   We do a pretty good job of correcting some of the flaws, but there's still
   room for more improvement. See EvenBetterKeyboardExample.java for the next
   upgrade!
   
   MAKE SURE TO CALL panel.mainLoop() AT THE END OF THE MAIN METHOD FROM
   GraphicsMainExample.java.
   
   The main addition from KeyboardExample: We have combined the mainLoop idea
   from the AnimationExample with the keyboard movement. Instead of changing
   the position directly when a key is pressed, we just change the speed. The
   position changes inside a loop - every 20 milliseconds, the loop checks the
   xSpeed and ySpeed of the square and moves it accordingly.
   Advantages:  
     + Creates smoother movement,
     + removes dependency on how fast your operating system spams key presses,
       and also
     + allows us to reliably move diagonally
 */

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BetterKeyboardExample extends JPanel implements KeyListener
{
  BufferedImage charac; 
  
  // How many pixels to move per frame max
  int maxSpeed = 5;
  int x = 50;
  int y = 50; 

  public BetterKeyboardExample()
  {
    charac = readImage("PlayerCharacter.png");
  
    addKeyListener(this);
    setFocusable(true);
  }
  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.drawImage(charac, x, y, null);
  }
  
  /* Combine the timer idea and the keyboard input idea. This way, we move at
     a consistent rate. Make sure to call this method with panel.mainLoop()
     at the end of the main method in GraphicsMainExample.java!
     
     Notice how this has the same 3 parts as the animation loop:
      1. Change some variable(s) representing information about what to draw
         as needed,
      2. Wait a fixed amount of time to keep a consistent frame rate,
      3. Redraw the screen,
   */
  public void mainLoop()
  {
    while(true)
    { 
  
    
      try
      {
        Thread.sleep(20);
      }
      catch(Exception e){e.printStackTrace();}
      
      repaint();
    }
  }
  
  /* Required methods for KeyListener */
  public void keyPressed(KeyEvent e)
  {
    int code = e.getKeyCode();
    
    // We have moved the code that changes the position of the square to the
    // mainLoop method. So, all we have to do here is set xSpeed and ySpeed.
        
    if(code == KeyEvent.VK_D)
    {
      x += 20;
    }
    else if(code == KeyEvent.VK_W)
    {
      y -= 20;
    }
    else if(code == KeyEvent.VK_S)
    {
      y += 20;
    }
    else if(code == KeyEvent.VK_A)
    {
      x -= 20;
    }
  }
  
  // Make sure to set the speed back to 0 when we release the key
  public void keyReleased(KeyEvent e)
  {
    int code = e.getKeyCode();
    
    // Notice how we can combine the cases here
   }
  public void keyTyped(KeyEvent e)
  {
  }
  
  public static BufferedImage readImage(String infile)
  {
    try
    {
      BufferedImage ret = ImageIO.read(new File(infile));
      return ret;
    }
    catch(Exception e){System.out.println(e.getMessage()); return null;}
  }

}