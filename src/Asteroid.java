import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Asteroid extends GameObject {
	private Image image, image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11;
	private Scene scene;
	private Game game;
	int width, height, x, y, difficulty, speed, animate, aniLength;
	long timer;
	double rotation, rotSpeed, locationX, locationY, sc;
	AffineTransform tx, as;
	AffineTransformOp op, op1;
	Random rand;
	
	public void explode() {
		if(animate==0) {
			this.timer = java.lang.System.currentTimeMillis();
			this.speed = 0;
			this.animate = 1;
			this.rotSpeed = 0;
		}
	}
	
	public void tick() {
		this.rotation -= rotSpeed*(this.difficulty/1.5);
		this.y += (int) (this.speed*this.scene.scaleY);
		
		this.width = (int) (image.getWidth(null)*this.scene.scaleX);
		this.height = (int) (image.getHeight(null)*this.scene.scaleY);
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
		switch(animate) {
			case 0: locationX = (this.width*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (this.height*this.scene.scaleY)/(2*this.scene.scaleY);
					tx = AffineTransform.getRotateInstance(this.rotation, locationX, locationY);
					tx.scale(this.scene.scaleX, this.scene.scaleY);
					op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					g.drawImage(op.filter((BufferedImage) image, null), this.x, this.y, null);
					break;
			case 1: locationX = (this.width*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (this.height*this.scene.scaleY)/(2*this.scene.scaleY);
					tx = AffineTransform.getRotateInstance(this.rotation, locationX, locationY);
					tx.scale(this.scene.scaleX, this.scene.scaleY);
					op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					locationX = (image1.getWidth(null)*this.scene.scaleX*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (image1.getHeight(null)*this.scene.scaleY*this.scene.scaleY)/(2*this.scene.scaleY);
					as = new AffineTransform();
					as.scale(this.scene.scaleX, this.scene.scaleY);
					op1 = new AffineTransformOp(as, AffineTransformOp.TYPE_BILINEAR);
					g.drawImage(op.filter((BufferedImage) image, null), this.x, this.y, null);
					g.drawImage(op1.filter((BufferedImage) image1, null), (int) (this.x + 20*this.scene.scaleX + this.width/2 - (image1.getWidth(null)*this.scene.scaleX)/2), (int) (this.y + this.height/2 - (image1.getHeight(null)*this.scene.scaleY)/2), null);
					if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
						timer = java.lang.System.currentTimeMillis();
						animate = 2;
					}
					break;
			case 2: locationX = (this.width*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (this.height*this.scene.scaleY)/(2*this.scene.scaleY);
					tx = AffineTransform.getRotateInstance(this.rotation, locationX, locationY);
					tx.scale(this.scene.scaleX, this.scene.scaleY);
					op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					locationX = (image1.getWidth(null)*this.scene.scaleX*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (image1.getHeight(null)*this.scene.scaleY*this.scene.scaleY)/(2*this.scene.scaleY);
					as = new AffineTransform();
					as.scale(this.scene.scaleX, this.scene.scaleY);
					op1 = new AffineTransformOp(as, AffineTransformOp.TYPE_BILINEAR);
					g.drawImage(op.filter((BufferedImage) image, null), this.x, this.y, null);
					g.drawImage(op1.filter((BufferedImage) image2, null), (int) (this.x + 20*this.scene.scaleX + this.width/2 - (image1.getWidth(null)*this.scene.scaleX)/2), (int) (this.y + this.height/2 - (image1.getHeight(null)*this.scene.scaleY)/2), null);
					if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
						timer = java.lang.System.currentTimeMillis();
						animate = 3;
					}
					break;
			case 3: locationX = (this.width*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (this.height*this.scene.scaleY)/(2*this.scene.scaleY);
					tx = AffineTransform.getRotateInstance(this.rotation, locationX, locationY);
					tx.scale(this.scene.scaleX, this.scene.scaleY);
					op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					locationX = (image1.getWidth(null)*this.scene.scaleX*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (image1.getHeight(null)*this.scene.scaleY*this.scene.scaleY)/(2*this.scene.scaleY);
					as = new AffineTransform();
					as.scale(this.scene.scaleX, this.scene.scaleY);
					op1 = new AffineTransformOp(as, AffineTransformOp.TYPE_BILINEAR);
					g.drawImage(op.filter((BufferedImage) image, null), this.x, this.y, null);
					g.drawImage(op1.filter((BufferedImage) image3, null), (int) (this.x + 20*this.scene.scaleX + this.width/2 - (image1.getWidth(null)*this.scene.scaleX)/2), (int) (this.y + this.height/2 - (image1.getHeight(null)*this.scene.scaleY)/2), null);
					if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
						timer = java.lang.System.currentTimeMillis();
						animate = 4;
					}
					break;
			case 4: locationX = (this.width*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (this.height*this.scene.scaleY)/(2*this.scene.scaleY);
					tx = AffineTransform.getRotateInstance(this.rotation, locationX, locationY);
					tx.scale(this.scene.scaleX, this.scene.scaleY);
					op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					locationX = (image1.getWidth(null)*this.scene.scaleX*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (image1.getHeight(null)*this.scene.scaleY*this.scene.scaleY)/(2*this.scene.scaleY);
					as = new AffineTransform();
					as.scale(this.scene.scaleX, this.scene.scaleY);
					op1 = new AffineTransformOp(as, AffineTransformOp.TYPE_BILINEAR);
					g.drawImage(op.filter((BufferedImage) image, null), this.x, this.y, null);
					g.drawImage(op1.filter((BufferedImage) image4, null), (int) (this.x + 20*this.scene.scaleX + this.width/2 - (image1.getWidth(null)*this.scene.scaleX)/2), (int) (this.y + this.height/2 - (image1.getHeight(null)*this.scene.scaleY)/2), null);
					if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
						timer = java.lang.System.currentTimeMillis();
						animate = 5;
					}
					break;
			case 5: locationX = (this.width*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (this.height*this.scene.scaleY)/(2*this.scene.scaleY);
					tx = AffineTransform.getRotateInstance(this.rotation, locationX, locationY);
					tx.scale(this.scene.scaleX, this.scene.scaleY);
					op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					locationX = (image1.getWidth(null)*this.scene.scaleX*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (image1.getHeight(null)*this.scene.scaleY*this.scene.scaleY)/(2*this.scene.scaleY);
					as = new AffineTransform();
					as.scale(this.scene.scaleX, this.scene.scaleY);
					op1 = new AffineTransformOp(as, AffineTransformOp.TYPE_BILINEAR);
					g.drawImage(op.filter((BufferedImage) image, null), this.x, this.y, null);
					g.drawImage(op1.filter((BufferedImage) image5, null), (int) (this.x + 20*this.scene.scaleX + this.width/2 - (image1.getWidth(null)*this.scene.scaleX)/2), (int) (this.y + this.height/2 - (image1.getHeight(null)*this.scene.scaleY)/2), null);
					if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
						timer = java.lang.System.currentTimeMillis();
						animate = 6;
					}
					break;
			case 6: locationX = (this.width*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (this.height*this.scene.scaleY)/(2*this.scene.scaleY);
					tx = AffineTransform.getRotateInstance(this.rotation, locationX, locationY);
					tx.scale(this.scene.scaleX, this.scene.scaleY);
					op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					locationX = (image1.getWidth(null)*this.scene.scaleX*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (image1.getHeight(null)*this.scene.scaleY*this.scene.scaleY)/(2*this.scene.scaleY);
					as = new AffineTransform();
					as.scale(this.scene.scaleX, this.scene.scaleY);
					op1 = new AffineTransformOp(as, AffineTransformOp.TYPE_BILINEAR);
					g.drawImage(op.filter((BufferedImage) image, null), this.x, this.y, null);
					g.drawImage(op1.filter((BufferedImage) image6, null), (int) (this.x + 20*this.scene.scaleX + this.width/2 - (image1.getWidth(null)*this.scene.scaleX)/2), (int) (this.y + this.height/2 - (image1.getHeight(null)*this.scene.scaleY)/2), null);
					if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
						timer = java.lang.System.currentTimeMillis();
						animate = 7;
					}
					break;
			case 7: locationX = (this.width*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (this.height*this.scene.scaleY)/(2*this.scene.scaleY);
					tx = AffineTransform.getRotateInstance(this.rotation, locationX, locationY);
					tx.scale(this.scene.scaleX, this.scene.scaleY);
					op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					locationX = (image1.getWidth(null)*this.scene.scaleX*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (image1.getHeight(null)*this.scene.scaleY*this.scene.scaleY)/(2*this.scene.scaleY);
					as = new AffineTransform();
					as.scale(this.scene.scaleX, this.scene.scaleY);
					op1 = new AffineTransformOp(as, AffineTransformOp.TYPE_BILINEAR);
					g.drawImage(op.filter((BufferedImage) image, null), this.x, this.y, null);
					g.drawImage(op1.filter((BufferedImage) image7, null), (int) (this.x + 20*this.scene.scaleX + this.width/2 - (image1.getWidth(null)*this.scene.scaleX)/2), (int) (this.y + this.height/2 - (image1.getHeight(null)*this.scene.scaleY)/2), null);
					if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
						timer = java.lang.System.currentTimeMillis();
						animate = 8;
					}
					break;
			case 8: locationX = (this.width*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (this.height*this.scene.scaleY)/(2*this.scene.scaleY);
					tx = AffineTransform.getRotateInstance(this.rotation, locationX, locationY);
					tx.scale(this.scene.scaleX, this.scene.scaleY);
					op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					locationX = (image1.getWidth(null)*this.scene.scaleX*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (image1.getHeight(null)*this.scene.scaleY*this.scene.scaleY)/(2*this.scene.scaleY);
					as = new AffineTransform();
					as.scale(this.scene.scaleX, this.scene.scaleY);
					op1 = new AffineTransformOp(as, AffineTransformOp.TYPE_BILINEAR);
					g.drawImage(op1.filter((BufferedImage) image8, null), (int) (this.x + 20*this.scene.scaleX + this.width/2 - (image1.getWidth(null)*this.scene.scaleX)/2), (int) (this.y + this.height/2 - (image1.getHeight(null)*this.scene.scaleY)/2), null);
					if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
						timer = java.lang.System.currentTimeMillis();
						animate = 9;
					}
					break;
			case 9: locationX = (this.width*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (this.height*this.scene.scaleY)/(2*this.scene.scaleY);
					tx = AffineTransform.getRotateInstance(this.rotation, locationX, locationY);
					tx.scale(this.scene.scaleX, this.scene.scaleY);
					op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					locationX = (image1.getWidth(null)*this.scene.scaleX*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (image1.getHeight(null)*this.scene.scaleY*this.scene.scaleY)/(2*this.scene.scaleY);
					as = new AffineTransform();
					as.scale(this.scene.scaleX, this.scene.scaleY);
					op1 = new AffineTransformOp(as, AffineTransformOp.TYPE_BILINEAR);
					g.drawImage(op1.filter((BufferedImage) image9, null), (int) (this.x + 20*this.scene.scaleX + this.width/2 - (image1.getWidth(null)*this.scene.scaleX)/2), (int) (this.y + this.height/2 - (image1.getHeight(null)*this.scene.scaleY)/2), null);
					if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
						timer = java.lang.System.currentTimeMillis();
						animate = 10;
					}
					break;
			case 10: locationX = (this.width*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (this.height*this.scene.scaleY)/(2*this.scene.scaleY);
					tx = AffineTransform.getRotateInstance(this.rotation, locationX, locationY);
					tx.scale(this.scene.scaleX, this.scene.scaleY);
					op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					locationX = (image1.getWidth(null)*this.scene.scaleX*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (image1.getHeight(null)*this.scene.scaleY*this.scene.scaleY)/(2*this.scene.scaleY);
					as = new AffineTransform();
					as.scale(this.scene.scaleX, this.scene.scaleY);
					op1 = new AffineTransformOp(as, AffineTransformOp.TYPE_BILINEAR);
					g.drawImage(op1.filter((BufferedImage) image10, null), (int) (this.x + 20*this.scene.scaleX + this.width/2 - (image1.getWidth(null)*this.scene.scaleX)/2), (int) (this.y + this.height/2 - (image1.getHeight(null)*this.scene.scaleY)/2), null);
					if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
						timer = java.lang.System.currentTimeMillis();
						animate = 11;
					}
					break;
			case 11: locationX = (this.width*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (this.height*this.scene.scaleY)/(2*this.scene.scaleY);
					tx = AffineTransform.getRotateInstance(this.rotation, locationX, locationY);
					tx.scale(this.scene.scaleX, this.scene.scaleY);
					op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					locationX = (image1.getWidth(null)*this.scene.scaleX*this.scene.scaleX)/(2*this.scene.scaleX);
					locationY = (image1.getHeight(null)*this.scene.scaleY*this.scene.scaleY)/(2*this.scene.scaleY);
					as = new AffineTransform();
					as.scale(this.scene.scaleX, this.scene.scaleY);
					op1 = new AffineTransformOp(as, AffineTransformOp.TYPE_BILINEAR);
					g.drawImage(op1.filter((BufferedImage) image11, null), (int) (this.x + 20*this.scene.scaleX + this.width/2 - (image1.getWidth(null)*this.scene.scaleX)/2), (int) (this.y + this.height/2 - (image1.getHeight(null)*this.scene.scaleY)/2), null);
					if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
						timer = java.lang.System.currentTimeMillis();
						animate = 12;
					}
					break;
			case 12: this.scene.objects.remove(this);
					break;
		}
	}
	
	public Asteroid(double x, double y, int difficulty, Scene scene, Game game) {
		this.x = (int) x;
		this.y = (int) y;
		this.scene = scene;
		this.game = game;
		this.difficulty = difficulty;
		this.speed = 3;
		this.animate = 0;
		this.aniLength = 40;
		this.sc = 0.5;
		rand = new Random();
		this.rotSpeed = (rand.nextInt(200) + 20) * 0.001;
		if(rand.nextInt(2)==1) {
			this.rotSpeed *= -1;
		}

		try {
			image = ImageIO.read(new File("res/asteroid.png"));
			image1 = ImageIO.read(new File("res/explosion00.png"));
			image2 = ImageIO.read(new File("res/explosion0.png"));
			image3 = ImageIO.read(new File("res/explosion1.png"));
			image4 = ImageIO.read(new File("res/explosion2.png"));
			image5 = ImageIO.read(new File("res/explosion3.png"));
			image6 = ImageIO.read(new File("res/explosion4.png"));
			image7 = ImageIO.read(new File("res/explosion5.png"));
			image8 = ImageIO.read(new File("res/explosion6.png"));
			image9 = ImageIO.read(new File("res/explosion7.png"));
			image10 = ImageIO.read(new File("res/explosion8.png"));
			image11 = ImageIO.read(new File("res/explosion9.png"));
		} catch (IOException e) {
			image = null;
			image1 = null;
			image2 = null;
			image3 = null;
			image4 = null;
			image5 = null;
			image6 = null;
			image7 = null;
			image8 = null;
			image9 = null;
			image10 = null;
			image11 = null;
		}
	}
}
