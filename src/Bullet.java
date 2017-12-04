import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet {
	public int x, y, vel;
	private Scene scene;
	
	public void tick() {
		this.y -= vel;
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.red);
		g.fillRect(this.x, this.y, (int) (12*this.scene.scaleX), (int) (48*this.scene.scaleY));
	}
	
	public Bullet(int x, int y, int vel, Scene scene) {
		this.x = x;
		this.y = y;
		this.vel = vel;
		this.scene = scene;
	}
}
