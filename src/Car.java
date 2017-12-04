import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Car extends GameObject {
	private Image image;
	private Scene scene;
	private Game game;
	int width, height, x, y, difficulty, speed, lane;
	private String name;
	Random rand;
	double rotation;
	AffineTransform tx;
	
	public void tick() {
		this.x -= (int) (this.speed*this.scene.scaleX) + 1;
		
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
		tx = new AffineTransform();
		tx.scale(this.scene.scaleX, this.scene.scaleY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		g.drawImage(op.filter((BufferedImage) image, null), this.x, this.y, null);
	}
	
	public Car(double x, double y, int lane, int difficulty, Scene scene, Game game) {
		this.x = (int) x;
		this.y = (int) y;
		this.scene = scene;
		this.lane = lane;
		this.game = game;
		this.difficulty = difficulty;
		rand = new Random();
		int r = rand.nextInt(4);
		switch(r) {
		case 0: this.name = "car1.png";
				break;
		case 1: this.name = "car2.png";
				break;
		case 2: this.name = "car3.png";
				break;
		case 3: this.name = "car4.png";
				break;
		}
		this.speed = 2;
		
		try {
			image = ImageIO.read(new File("res/" + this.name));
		} catch (IOException e) {
			image = null;
		}
	}
}
