public class Player extends Collisable
{
  public int health = 1;
  public int damage = 1;
  
  // Constructor
  Player(int Health, int Damage, int x, int width, int height)
  {
    super(x, 800, width, height);
    health = Health;
    damage = Damage;
  } 
  
}
