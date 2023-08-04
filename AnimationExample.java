/* Basic example of how to do animation.

   Make sure to call panel.mainLoop() in your main method!
 */
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


public class AnimationExample extends JPanel
{
  private int x;
  private int speed;
  private int delay = 10;
  BufferedImage imgChar; 

  public AnimationExample()
  {
    x = 50;
    speed = 3;
  }
  
  /* Update the image and redraw the screen */
  public void mainLoop()
  {
    img = readImage("GhostCharacter.png");
    while(true)
    {
      // Not great style to say while(true), but we'll talk about alternatives
      // later. For now, this is our main way to have a persistent program.
      while(x < 600)
      {
      
        // Wait an amount of time in between frames
        try
        {
          Thread.sleep(delay);
        }catch(Exception e){e.printStackTrace();}
        
        // Make a change
        x+=speed;
        
        // Redraw the screen
        repaint(); // built-in to JPanel
      }
      while(x > speed)
      {
        try{Thread.sleep(delay);}catch(Exception e){e.printStackTrace();}
        x-=speed;
        repaint(); // built-in to JPanel
      }
    }
  }
  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    
    g.setColor(new Color(x/3, 80, 80));
     
    
    // Make sure something in your paintComponent method is linked to a variable!
    g.drawImage(imgChar, x, 825, null);
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
