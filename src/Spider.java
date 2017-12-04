import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spider extends GameObject {
	private Image image1, image2, image3, image4, image5;
	private Scene scene;
	private Game game;
	int width, height, x, y, difficulty, speed, animate, aniLength, aniPause;
	long timer, deathTimer;
	private String name;
	double rotation;
	float opacity;
	
	public void tick() {
		this.rotation -= 0.1*(this.difficulty/1.5);
		this.x += (int) (this.speed*this.scene.scaleX);
		
		this.height = (int) (image1.getHeight(null)*this.scene.scaleY);
		this.width = (int) (image1.getWidth(null)*this.scene.scaleX);
		
		if(this.x > this.scene.x + Game.WIDTH*this.scene.scaleX) {
			//System.out.println("YOU LOSE");
			this.scene.damage();
			this.scene.objects.remove(this);
		}
	}
	
	public void crush() {
		if(animate < 5) {
			timer = java.lang.System.currentTimeMillis();
			animate = 5;
			this.speed = 0;
		}
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
		AffineTransform tx = new AffineTransform();
		tx.scale(this.scene.scaleX, this.scene.scaleY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		switch(animate) {
			case 1: g.drawImage(op.filter((BufferedImage) image1, null), this.x, this.y, null);
					if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
						timer = java.lang.System.currentTimeMillis();
						animate = 2;
					}
					break;
			case 2: g.drawImage(op.filter((BufferedImage) image2, null), this.x, this.y, null);
					if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
						timer = java.lang.System.currentTimeMillis();
						animate = 3;
					}
					break;
			case 3: g.drawImage(op.filter((BufferedImage) image3, null), this.x, this.y, null);
					if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
						timer = java.lang.System.currentTimeMillis();
						animate = 4;
					}
					break;
			case 4: g.drawImage(op.filter((BufferedImage) image4, null), this.x, this.y, null);
					if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
						timer = java.lang.System.currentTimeMillis();
						animate = 1;
					}
					break;
			case 5: opacity = 1f;
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
					g.drawImage(op.filter((BufferedImage) image5, null), this.x, this.y, null);
					if(java.lang.System.currentTimeMillis() - timer > this.deathTimer) {
						timer = java.lang.System.currentTimeMillis();
						animate = 6;
					}
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
					break;
			case 6: opacity = 0.8f;
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
					g.drawImage(op.filter((BufferedImage) image5, null), this.x, this.y, null);
					if(java.lang.System.currentTimeMillis() - timer > this.deathTimer) {
						timer = java.lang.System.currentTimeMillis();
						animate = 7;
					}
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
					break;
			case 7: opacity = 0.5f;
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
					g.drawImage(op.filter((BufferedImage) image5, null), this.x, this.y, null);
					if(java.lang.System.currentTimeMillis() - timer > this.deathTimer) {
						timer = java.lang.System.currentTimeMillis();
						animate = 8;
					}
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
					break;
			case 8: opacity = 0.2f;
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
					g.drawImage(op.filter((BufferedImage) image5, null), this.x, this.y, null);
					if(java.lang.System.currentTimeMillis() - timer > this.deathTimer) {
						timer = java.lang.System.currentTimeMillis();
						animate = 9;
					}
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
					break;
			case 9: opacity = 0.1f;
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
					g.drawImage(op.filter((BufferedImage) image5, null), this.x, this.y, null);
					if(java.lang.System.currentTimeMillis() - timer > this.deathTimer) {
						timer = java.lang.System.currentTimeMillis();
						this.scene.objects.remove(this);
					}
					g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
					break;
		}
	}
	
	public Spider(double x, double y, int difficulty, Scene scene, Game game) {
		this.x = (int) x;
		this.y = (int) y;
		this.scene = scene;
		this.game = game;
		this.difficulty = difficulty;
		this.speed = 7;
		this.aniLength = 100;
		this.aniPause = this.aniLength*4;
		this.deathTimer = 100;
		animate = 1;
		
		try {
			image1 = ImageIO.read(new File("res/spider1.png"));
			image2 = ImageIO.read(new File("res/spider2.png"));
			image3 = ImageIO.read(new File("res/spider3.png"));
			image4 = ImageIO.read(new File("res/spider4.png"));
			image5 = ImageIO.read(new File("res/spidercrush.png"));
		} catch (IOException e) {
			image1 = null;
			image2 = null;
			image3 = null;
			image4 = null;
			image5 = null;
		}
	}
}
