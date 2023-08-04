import javax.swing.JFrame;
import java.util.Scanner; 
public class FinalProectMain
{
   public static void main(String[] args)
   {
      Scanner chooser = new Scanner(System.in); 
      // background
      JFrame frame = new JFrame();
      frame.setSize(1920,1089);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      LoadImages panel = new LoadImages("Background.jpg");
      
      System.out.println("What level would you like to play at, easy, medium, hard, or impossible?"); 
      String mode = chooser.nextLine();
      if(mode.charAt(0) == 'e' || mode.charAt(0) == 'E')
      {
         panel.mode = 2; 
      }
      else if(mode.charAt(0) == 'm' || mode.charAt(0) == 'M')
      {
         panel.mode = 3; 
         
      }
      else if(mode.charAt(0) == 'h' || mode.charAt(0) == 'H')
      {
         panel.mode = 4; 
         
      }
      else if(mode.charAt(0) == 'i' || mode.charAt(0) == 'I')
      {
         panel.mode = 5; 
         
      }
      
      System.out.println("Would you like to play one-heart or regular mode? (answer One Heart or Regular)");  
      String answer = chooser.nextLine();
      
      if(answer.charAt(0) == 'O' || answer.charAt(0) == 'o')
      {
         panel.health = 1;
      }
      else
      {
         panel.health = 10; 
      }
      
      frame.add(panel);
      frame.setVisible(true);
      
      panel.mainLoop();
      
      
   }
}