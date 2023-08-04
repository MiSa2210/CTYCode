/* This class contains an example of how to load an image from a file in Java */
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 
import java.awt.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList; 
import java.awt.Font;

public class LoadImages extends JPanel implements KeyListener

{ 

  BufferedImage image;
  private int x;
  private int speed;
  private int delay = 10;
  BufferedImage imgChar; 
  BufferedImage imgFlip; 
  BufferedImage charac;
  BufferedImage characFlip; 
  int ghostMovement = 4; 
  int health = 10;
  
  int Speed = 0;
  int mode = 4; 
 
  int po = 1; 
  int Jumper = 0;  
  Player player;
  Magic magic; 
  RightBorder right; 
  LeftBorder left; 
  int score = 0; 
  
  ArrayList<GhostCharacter> ghosts = new ArrayList<GhostCharacter>();
  int time = 0;
  int waves = 0;  
  
  int jumpTime = 0; 
  boolean J = true; 
  int cooldown = 0; 
  
  boolean S = true; 
  
  int direction = 1; 
  int mp;
  
  boolean alive = true; 
  int playerKilla = 0;  
 

  // Since we are now storing data, we should have a constructor to help us
  // initialize that data.
  public LoadImages(String filename)
  {
    image = readImage(filename);
    imgChar = readImage("GhostCharacter.png");
    imgFlip = readImage("GhostCharacterFlipped.png");
    charac = readImage("PlayerCharacter.png");
    
    player = new Player(100, 10, 600, charac.getWidth(), charac.getHeight());
    magic = new Magic(-1000, 800, 100, 20); 
    right = new RightBorder();
    left = new LeftBorder(); 

   
    
    characFlip = readImage("PlayerCharacterFlipped.png");
    addKeyListener(this);
    setFocusable(true);

    ghosts.add(new GhostCharacter(10, 10, 0, ghostMovement, imgChar.getWidth(), imgChar.getHeight(), direction));
    ghosts.add(new GhostCharacter(10, 10, 1820, ghostMovement, imgFlip.getWidth(), imgFlip.getHeight(), (direction * - 1)));

 
      
  }
  public void mainLoop()
  {
    
    while(alive == true)
    {
      // Not great style to say while(true), but we'll talk about alternatives
      // later. For now, this is our main way to have a persistent program.
      
      
        // Wait an amount of time in between frames
        try
        {
          Thread.sleep(delay);
        }catch(Exception e){e.printStackTrace();}
        
      for(int k = 0; k < ghosts.size(); k++)
      {
         (ghosts.get(k)).Change();   
         (ghosts.get(k)).Move(); 
          // built-in to JPanel
         
        
      }
      for(int m  = 0; m < ghosts.size(); m++)
      {
         if(player.intersects(ghosts.get(m)))
            {
               Speed = Speed * -1;
               (ghosts.get(m)).speed = 0; 
               playerKilla++;
               
               if(playerKilla % 20 == 0)
               { 
                  health -- ; 
               }

            }
            else
            {
               (ghosts.get(m)).speed = ghostMovement;
            } 
            
            if(player.intersects(right)|| player.intersects(left))
            {
              Speed = Speed * -1; 
            }
         if(magic.intersects(ghosts.get(m)))
         {
            (ghosts.get(m)).health = (ghosts.get(m)).health - 1;
            
            if((ghosts.get(m)).health <= 0)
            {
               ghosts.remove(ghosts.get(m));
               m --; 
               score++; 
            }
         }
      }
       
 
         // Redraw the screen
         player.x += Speed;
         repaint();

      time++;
      waves++;
      if(time == 200)
      {
         for(int er = 0; er < mode; er++)
         {
            ghosts.add(new GhostCharacter(10, 10, 0 + (er*100), ghostMovement,imgFlip.getWidth(), imgFlip.getHeight(), (direction))); 
            ghosts.add(new GhostCharacter(10, 10, 1820 - (er*100), (-1) * ghostMovement,imgFlip.getWidth(), imgFlip.getHeight(), ((-1) * direction))); 
         }  

         time = 0; 
         
      }
      if(waves % 1000 == 0)
      {
            ghostMovement = ghostMovement + 2; 
      }
      if(player.y == 400)
      {
         jumpTime++; 
      }
      if(jumpTime == 3)
      {
         J = false; 
         jumpTime = 0; 
      }
      
      if(J == false)
      {
         cooldown++;
      }
      if(cooldown == 50)
      {
         J = true;
         cooldown = 0; 
      }
      if(health <= 0)
      {
         alive = false; 
      }  
      
    }
    
    System.exit(0); 
  }


  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    
      g.setColor(Color.cyan);

    
    // image, x coordinate, y coordinate, null
    // (for our purposes in this course, we will always make the last argument
    //  null, but of course it has a purpose)
    g.drawImage(image, 0, 0, null);

    
    if(po == 1)
    {
      g.drawImage(charac, player.x, player.y, null);
      if(mp == 1)
      {
         g.fillRect(player.x + charac.getWidth(), player.y + 100, 250, 20); 
      }
    }
    else
    {
      g.drawImage(characFlip, player.x, player.y, null);
      
      if(mp == 1)
      {
         g.fillRect(player.x - 250, player.y + 100, 250, 20); 
     
      }      
    }
    
    
    for(int i = 0; i < ghosts.size(); i++)
    {
      if((ghosts.get(i)).direction == 1)
      {
         g.drawImage(imgChar, (ghosts.get(i).x), 825, null);

         
      }
      else if ((ghosts.get(i)).direction == -1)
      {
         g.drawImage(imgFlip, (ghosts.get(i).x), 825, null);
      }
    }
    if(health > 0)
    { 
      g.setColor(Color.red);
      g.fillRect(460,50, (health*100), 50); 
      g.setColor(Color.black);
      g.drawRect(460, 50, 1000, 50);
    }
    
     g.setFont(new Font("Arial", Font.PLAIN, 36));
    drawFancyString(g, "Score: " + score, 85, 100, new Color(0xffff));
      
    
  }

  /* Read the image with the specified file name and return it as a BufferedImage. */
  public static BufferedImage readImage(String infile)
  {
    try
    {
      BufferedImage ret = ImageIO.read(new File(infile));
      return ret;
    }
    catch(Exception e){System.out.println(e.getMessage()); return null;}
  }
    public void keyPressed(KeyEvent e)
  {
    int code = e.getKeyCode();
    
    // We have moved the code that changes the position of the square to the
    // mainLoop method. So, all we have to do here is set xSpeed and ySpeed.
        
    if(code == KeyEvent.VK_D)
    {
         Speed = 10;
         po = 1;   
    }

    else if(code == KeyEvent.VK_A)
    {
         Speed = -10;
         po = 0; 
       
    }
    else if(code == KeyEvent.VK_W)
    {
      if(J == true)
      {
         player.y = 400;
      }
      else
      {
         player.y = 800; 
         
      }
     
    }
    else if(code == KeyEvent.VK_SPACE)
    {
      mp = 1; 
      if(po == 1)
      {
         magic = new Magic(player.x + charac.getWidth(), player.y + 100, 250, 20);

      }
      else if (po == 0)
      {
       magic = new Magic(player.x - 250, player.y + 100, 250, 20);


      }

    }

  }
  
  // Make sure to set the speed back to 0 when we release the key
  public void keyReleased(KeyEvent e)
  {
  Speed = 0; 
  Jumper = 0;
  player.y = 800;
  mp = 0;  
  magic = new Magic(-1000,800, 100, 20);
 
   }
  public void keyTyped(KeyEvent e)
  {
  }
  
    public void drawFancyString(Graphics g, String text, int x, int y, Color c)
  {
    g.setColor(Color.BLACK);
    for(int i=0; i<3; i+=2)
    {
      for(int j=0; j<3; j+=2)
      {
        g.drawString(text, x + i, y + j);
      }
    }
    g.setColor(c);
    g.drawString(text, x + 1, y + 1);
  }
  
  
  
}
