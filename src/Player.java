import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Player extends GameObject {
	private Image image1, image2, image3, image4, image5;
	public int minigame, width, height, x, y, animate, aniLength, aniPause, offset, aniTimes, tempTimes, cooldown, cdtimer;
	public long timer;
	private boolean down, released;
	private double vel;
	private Game game;
	private Scene scene;
	private GameObject tempObject;
	public ArrayList<Point2D> stars;
	public LinkedList<Bullet> bullets;
	Random rand;
	
	public void pressAction() {
		switch(this.minigame) {
			case 1: if(this.offset == 0) {
						this.vel = 11*this.scene.scaleY;
						AudioPlayer.getSound("jump").play();
					}
					break;
			case 2: if(this.offset % (216*this.scene.scaleY) < 1) {
						this.vel = 6*this.scene.scaleY;
						this.offset += this.vel;
						timer = java.lang.System.currentTimeMillis();
						animate = 1;
						this.scene.act = false;
					}
					break;
			case 3: if(this.cdtimer==0 && this.released) {
						this.released = false;
						this.cdtimer = this.cooldown;
						AudioPlayer.getSound("shoot").play();
						bullets.add(new Bullet((int) (this.x + 24 * this.scene.scaleX), (int) (this.y + 100 * this.scene.scaleY), (int) (16*this.scene.scaleY), this.scene));
						bullets.add(new Bullet((int) (this.x + 144 * this.scene.scaleX), (int) (this.y + 100 * this.scene.scaleY), (int) (16*this.scene.scaleY), this.scene));
					}
					break;
			case 4: if(this.offset == 0 && this.released) {
						this.vel = 25*this.scene.scaleY;
						this.offset += this.vel;
						this.down = true;
						this.released = false;
					}
					break;
		}
	}
	
	public void relAction() {
		this.released = true;
	}
	
	public void tick() {
		switch(this.minigame) {
			case 1: if(this.offset <= 0 && this.vel < 0) {
						this.offset = 0;
						this.vel = 0;
					} else {
						this.vel -= 0.2*this.scene.scaleY;
					}
					this.offset += vel;
					this.height = (int) (image1.getHeight(null)*this.scene.scaleY);
					this.width = (int) (image1.getWidth(null)*this.scene.scaleX);
					break;
			case 2: if(this.offset % (216*this.scene.scaleY) < 1 && this.offset > 0) {
						this.vel = 0;
					}
					this.offset += this.vel;
					this.height = (int) (image1.getHeight(null)*this.scene.scaleY);
					this.width = (int) (image1.getWidth(null)*this.scene.scaleX);
					for(int i=1;i<this.scene.objects.size();i++) {
						tempObject = this.scene.objects.get(i);
						((Car) tempObject).setY((int) (((Car) tempObject).getY() + this.vel));
					}
					break;
			case 3: for(int i=0;i<this.stars.size();i++) {
						if(this.stars.get(i).getY() > Game.HEIGHT) {
							this.stars.remove(i);
							this.stars.add(new Point2D.Double(rand.nextInt(Game.WIDTH), -2 - rand.nextInt(18)));
						}
						this.stars.get(i).setLocation(this.stars.get(i).getX(), this.stars.get(i).getY() + this.vel*this.scene.scaleY);
					}
					if(this.cdtimer > 0) {
						cdtimer -= 1;
					}
					for(int i=0;i<this.bullets.size();i++) {
						this.bullets.get(i).tick();
						if(this.bullets.get(i).y < (this.scene.y - 50)) {
							this.bullets.remove(this.bullets.get(i));
						}
					}
					this.height = (int) (image1.getHeight(null)*this.scene.scaleY);
					this.width = (int) (image1.getWidth(null)*this.scene.scaleX);
					break;
			case 4: if(this.down) {
						this.offset += this.vel;
					} else {
						this.offset -= this.vel;
					}
					if(this.offset >= Game.HEIGHT*this.scene.scaleY - 370*this.scene.scaleY) {
						this.down = false;
					} else if(this.offset < 0) {
						this.offset = 0;
						this.vel = 0;
					}
					this.height = (int) (image1.getHeight(null)*this.scene.scaleY);
					this.width = (int) (image1.getWidth(null)*this.scene.scaleX);
					break;
		}
	}
	
	public Player(double x, double y, int minigame, Scene scene, Game game) {
		this.minigame = minigame;
		this.scene = scene;
		this.game = game;
		this.x = (int) x;
		this.y = (int) y;
		this.released = true;
		
		switch(this.minigame) {
			case 1: this.aniLength = 120;
					this.aniPause = 1000;
					this.offset = 0;
					this.vel = 0;
					
					try {
						image1 = ImageIO.read(new File("res/player1.png"));
						image2 = ImageIO.read(new File("res/player2.png"));
						image3 = ImageIO.read(new File("res/player3.png"));
						image4 = ImageIO.read(new File("res/player4.png"));
					} catch (IOException e) {
						image1 = null;
						image2 = null;
						image3 = null;
						image4 = null;
					}
					break;
			case 2: this.aniLength = 80;
					this.offset = 432;
					this.vel = 0;
					this.aniTimes = 1;
					this.tempTimes = aniTimes;
					this.aniPause = this.aniLength*4*this.aniTimes;

					try {
						image1 = ImageIO.read(new File("res/chicken1.png"));
						image2 = ImageIO.read(new File("res/chicken2.png"));
						image3 = ImageIO.read(new File("res/chicken3.png"));
						image4 = ImageIO.read(new File("res/road1.png"));
						image5 = ImageIO.read(new File("res/road2.png"));
					} catch (IOException e) {
						image1 = null;
						image2 = null;
						image3 = null;
						image4 = null;
						image5 = null;
					}
					break;
			case 3: this.offset = 0;
					this.vel = 2;
					this.cooldown = 45;
					this.cdtimer = 0;
					ArrayList<Point2D> stars = new ArrayList<Point2D>();
					LinkedList<Bullet> bullets = new LinkedList<Bullet>();
					this.bullets = bullets;
					this.stars = stars;
					rand = new Random();
					for(int i=0;i<50;i++) {
						this.stars.add(new Point2D.Double(rand.nextInt(Game.WIDTH), rand.nextInt(Game.HEIGHT)));
					}
					
					try {
						image1 = ImageIO.read(new File("res/spaceship.png"));
					} catch (IOException e) {
						image1 = null;
					}
					break;
			case 4: this.offset = 0;
					this.vel = 0;
					this.down = false;
					try {
						image1 = ImageIO.read(new File("res/boot.png"));
					} catch (IOException e) {
						image1 = null;
					}
					break;
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
		switch(this.minigame) {
			case 1: AffineTransform tx = new AffineTransform();
					tx.scale(this.scene.scaleX, this.scene.scaleY);
					AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					
					if(java.lang.System.currentTimeMillis() % this.aniPause <= 100 && animate==0 && offset==0) {
						animate = 1;
						timer = java.lang.System.currentTimeMillis();
					}
					
					if(animate>0 && this.offset == 0) {
						switch(animate) {
							case 1: g.drawImage(op.filter((BufferedImage) image2, null), this.x, this.y, null);
									if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
										timer = java.lang.System.currentTimeMillis();
										animate = 2;
									}
									break;
							case 2: g.drawImage(op.filter((BufferedImage) image3, null), this.x, this.y, null);
									if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
										timer = java.lang.System.currentTimeMillis();
										animate = 3;
									}
									break;
							case 3: g.drawImage(op.filter((BufferedImage) image4, null), this.x, this.y, null);
									if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
										animate = 0;
									}
									break;
						}
					} else {
						g.drawImage(op.filter((BufferedImage) image1, null), this.x, this.y - this.offset, null);
					}
					break;
			case 2: AffineTransform tx1 = new AffineTransform();
					tx1.scale(this.scene.scaleX, this.scene.scaleY);
					AffineTransformOp op1 = new AffineTransformOp(tx1, AffineTransformOp.TYPE_BILINEAR);
					Color myDarkGray = new Color(82, 83, 95);
					Color myLightGray = new Color(99, 100, 116);
					Color myYellow = new Color(217, 206, 0);
					g.setColor(myDarkGray);
					g.fillRect(this.scene.x, this.scene.y, (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY));
					for(int i=0;i<5;i++) {
						g.setColor(myDarkGray);
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset - 24 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 48 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 72 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 144 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 168 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 192 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 216 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 240 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 312 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 336 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 384 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.setColor(myYellow);
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 24 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 360 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 192 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (72 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 144 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 192 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (144 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 360 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 192 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (144 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 576 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 192 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (144 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 790 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 192 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (144 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 1006 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 192 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (144 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.setColor(myLightGray);
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 96 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 120 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 264 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 288 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (Game.WIDTH * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 72 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (72 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 144 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 72 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (180 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 396 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 72 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (216 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 720 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 72 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (72 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 900 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 72 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (218 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 144 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (108 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 216 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 144 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (144 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 468 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 144 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (432 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 972 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 144 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (144 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 108 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 240 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (180 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 396 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 240 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (216 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 684 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 240 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (108 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 936 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 240 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (180 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect(this.scene.x, (int) (this.scene.y + ((this.offset + 312 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (180 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 360 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 312 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (144 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 576 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 312 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (298 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
						g.fillRect((int) (this.scene.x + 900 * this.scene.scaleX), (int) (this.scene.y + ((this.offset + 312 * this.scene.scaleY) - 432*i*this.scene.scaleY)%(864*this.scene.scaleY)), (int) (144 * this.scene.scaleX), (int) (24 * this.scene.scaleY + 1));
					}
					
					//g.drawImage(image5, this.scene.x, this.scene.y + this.offset - 539, null);
					
					if(animate>0) {
						switch(animate) {
							case 1: g.drawImage(op1.filter((BufferedImage) image2, null), this.x, this.y, null);
									if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
										timer = java.lang.System.currentTimeMillis();
										animate = 2;
									}
									break;
							case 2: g.drawImage(op1.filter((BufferedImage) image1, null), this.x, this.y, null);
									if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
										timer = java.lang.System.currentTimeMillis();
										animate = 3;
									}
									break;
							case 3: g.drawImage(op1.filter((BufferedImage) image3, null), this.x, this.y, null);
									if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
										timer = java.lang.System.currentTimeMillis();
										animate = 4;
									}
									break;
							case 4: g.drawImage(op1.filter((BufferedImage) image1, null), this.x, this.y, null);
									if(java.lang.System.currentTimeMillis() - timer > this.aniLength) {
										if(this.tempTimes == 0) {
											timer = java.lang.System.currentTimeMillis();
											animate = 0;
											this.tempTimes = this.aniTimes;
										} else {
											timer = java.lang.System.currentTimeMillis();
											animate = 1;
											this.tempTimes -= 1;
										}
									}
									break;
						}
					} else {
						g.drawImage(op1.filter((BufferedImage) image1, null), this.x, this.y, null);
					}
					break;
			case 3: for(int i=0;i<this.stars.size();i++) {
						g.setColor(Color.white);
						g.drawRect((int) (this.scene.x + this.stars.get(i).getX() * this.scene.scaleX), (int) (this.scene.y + this.stars.get(i).getY() * this.scene.scaleY), 2, 2);
					}
					for(int i=0;i<this.bullets.size();i++) {
						this.bullets.get(i).render(g);
					}
					AffineTransform tx2 = new AffineTransform();
					tx2.scale(this.scene.scaleX, this.scene.scaleY);
					AffineTransformOp op2 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
					g.drawImage(op2.filter((BufferedImage) image1, null), this.x, this.y - this.offset, null);
					break;
			case 4: AffineTransform tx3 = new AffineTransform();
					tx3.scale(this.scene.scaleX, this.scene.scaleY);
					AffineTransformOp op3 = new AffineTransformOp(tx3, AffineTransformOp.TYPE_BILINEAR);
					g.drawImage(op3.filter((BufferedImage) image1, null), this.x, this.y + this.offset, null);
					break;
		}
	}	
}
