import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JComponent;
import java.util.HashMap;


public class JGameObject extends JComponent {

  private HashMap<String, Animation> animations = new HashMap<>();
  public Animation currAnim;
  public Point position;
  public Dimension size;
  public int rotation = 0;
  private AffineTransform at;


  public JGameObject(Point position, Dimension size){
  	setDoubleBuffered(true);
  	this.position = position;
  	this.size = size;
  	this.setBounds();
  	JGameObjectRenderer.addGo(this);
  }
  public JGameObject(Point position){
  	this(position, new Dimension(50, 50));
  }

  public JGameObject(){
    this(new Point(0, 0));
  }

  protected void addAnimation(Animation animation, String animationName){
  	animations.put(animationName, animation);
  }

  private void setBounds(){
  	super.setBounds(position.x - size.width/2, position.y - size.height/2, size.width, size.height);
  }

  protected void playAnimation(String animationName){
  	if(animations.get(animationName) != null){
  		currAnim = animations.get(animationName);
  		size = new Dimension(currAnim.frameWidth, currAnim.frameHeight);
  		this.setBounds();
  	}
  }

  public void paintComponent(Graphics g){
  	super.paintComponent(g);
  	Graphics2D g2d = (Graphics2D)g;
	at = new AffineTransform();
	at.translate(size.width/2, size.height/2);
	at.rotate(rotation);
	at.translate(-size.width/2, -size.height/2);
	g2d.drawImage(currAnim.getNextSprite(), at, null);
  }

  //TODO: IMPLEMENTAR A CLASSE E INTERFACE DE SCRIPT
  
}
