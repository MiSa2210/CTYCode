public class Collisable
{
  int x;
  int y;
  int width;
  int height;
  
  public Collisable(int nx, int ny, int width, int height)
  {
    x = nx;
    y = ny;
    this.width = width;
    this.height = height;

  }
  
  
  /* Return true if the point is contained inside the rectangle for this
     Collisable, false otherwise.
     FILL THIS IN!
   */
  public boolean containsPoint(int pointX, int pointY)
  {
    return pointX > x &&  pointX < x + width && pointY > y &&  pointY < y + width;
  }
  
  /* Return true if the rectangles for this Collisable and the parameter other
     are intersecting, false otherwise.
     FILL THIS IN!
   */
  public boolean intersects(Collisable other)
  {
    return x + width > other.x && x < other.x + other.width && y + height > other.y && y < other.y + other.height ;
  }
  
  /* Movement methods for our square.
     We will modify this in a future file to stop our player square from being
     able to run into other squares.
   */
 }