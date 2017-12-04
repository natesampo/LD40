import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Axe extends GameObject {
	private Image image;
	private Scene scene;
	private Game game;
	int width, height, x, y, difficulty, speed;
	private String name;
	double rotation;
	
	public void tick() {
		this.rotation -= 0.1*(this.difficulty/1.5);
		this.x -= (int) (this.speed*this.scene.scaleX);
		
		this.height = (int) (image.getHeight(null)*this.scene.scaleY);
		this.width = (int) (image.getWidth(null)*this.scene.scaleX);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int n) {
		this.x = n;
	}
	
	public void setY(int n) {
		this.y = n;
	}
	
	public int getImgX() {
		return this.width;
	}
	
	public int getImgY() {
		return this.height;
	}
	
	public void render(Graphics2D g) {
		double locationX = (this.width*this.scene.scaleX)/(2*this.scene.scaleX);
		double locationY = (this.height*this.scene.scaleY)/(2*this.scene.scaleY);
		AffineTransform tx = AffineTransform.getRotateInstance(this.rotation, locationX, locationY);
		tx.scale(this.scene.scaleX, this.scene.scaleY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		g.drawImage(op.filter((BufferedImage) image, null), this.x, this.y, null);
	}
	
	public Axe(double x, double y, int difficulty, Scene scene, Game game) {
		this.x = (int) x;
		this.y = (int) y;
		this.scene = scene;
		this.game = game;
		this.difficulty = difficulty;
		this.speed = 6;
		
		try {
			image = ImageIO.read(new File("res/axe.png"));
		} catch (IOException e) {
			image = null;
		}
	}
}
