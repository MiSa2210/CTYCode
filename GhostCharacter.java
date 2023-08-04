public class GhostCharacter extends Collisable
{ 

  public int speed;
  public int direction; 

  public int health = 1;
  public int damage = 1;
  
  // Constructor
  GhostCharacter(int Health, int Damage, int x, int speed, int Width, int Height, int direction)
  {
    super(x, 825, Width, Height); 
    this.health = Health;
    this.damage = Damage;
    this.speed = speed;
    this.direction = direction; 
  } 
  
  public void Change()
  {
      if(direction == 1 && x > 1920)
      {
         direction = direction * -1;
      }
      else if(direction == -1 && x < 50)
      {
         direction = direction * -1;
      }



  }
  
  public void Move()
  {
      x += (speed * direction); 
      
     
 
  }
  

}
