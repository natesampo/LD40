import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Scene {
	int x, y, minigame, difficulty, key, cooldown, cdTimer, timer, damageTimer, dmg, progresstotal, sceneGap;
	double scaleX, scaleY, axeWait, asteroidWait, spiderWait, progress, progresstick;
	long sceneTimer;
	private Game game;
	public boolean act, done, played;
	private GameObject tempObject;
	private Bullet tempBullet;
	public LinkedList<GameObject> objects = new LinkedList<GameObject>();
	public ArrayList<Boolean> lanes;
	public ArrayList<Integer> laneTimers;
	private Rectangle a, p, c, b;
	Random rand;
	
	public void damage() {
		this.damageTimer = dmg;
		this.game.hp -= 2;
	}
	
	public void pressAction() {
		((Player) this.objects.get(0)).pressAction();
	}
	
	public void relAction() {
		((Player) this.objects.get(0)).relAction();
	}
	
	public void more(int n) {
		switch(n) {
			case 2: switch(this.minigame) {
						case 1: ((Player) this.objects.get(0)).setX((int) (this.x + ((Game.WIDTH * this.scaleX)/2) - (100*this.scaleX)));
								((Player) this.objects.get(0)).setY((int) ((int)this.y + ((Game.HEIGHT * this.scaleY)/2) - (100*this.scaleY)));
								if(this.objects.size() > 1) {
									((Axe) this.objects.get(1)).setX((int) (((Axe) this.objects.get(1)).getX() * this.scaleX));
									((Axe) this.objects.get(1)).setY((int) (((Axe) this.objects.get(1)).getY() * this.scaleY) + Game.HEIGHT/4);
								}
								break;
						case 2: ((Player) this.objects.get(0)).setX((int) (this.x + ((Game.WIDTH * this.scaleX)/2) - (100*this.scaleX)));
								((Player) this.objects.get(0)).setY((int) (((Player) this.objects.get(0)).getY() * this.scaleY) + Game.HEIGHT/4);
								for(int i=1;i<this.objects.size();i++) {
									((Car) this.objects.get(i)).setX((int) (((Car) this.objects.get(i)).getX() * this.scaleX));
									((Car) this.objects.get(i)).setY((int) (((Car) this.objects.get(i)).getY() * this.scaleY) + Game.HEIGHT/4);
								}
								break;
						case 3: ((Player) this.objects.get(0)).setX((int) (this.x + ((Game.WIDTH * this.scaleX)/2) - (100*this.scaleX)));
								((Player) this.objects.get(0)).setY((int) (this.y + (Game.HEIGHT * this.scaleY) - (280*this.scaleY)));
								for(int i=1;i<this.objects.size();i++) {
									((Asteroid) this.objects.get(i)).setX((int) (((Asteroid) this.objects.get(i)).getX() * this.scaleX));
									((Asteroid) this.objects.get(i)).setY((int) (((Asteroid) this.objects.get(i)).getY() * this.scaleY) + Game.HEIGHT/4);
								}
								break;
						case 4: ((Player) this.objects.get(0)).setX((int) ((int) (((Player) this.objects.get(0)).getX()) * this.scaleX));
								((Player) this.objects.get(0)).setY((int) (this.y + (Game.HEIGHT * this.scaleY) - (1273*this.scaleY)));
								for(int i=1;i<this.objects.size();i++) {
									((Spider) this.objects.get(i)).setX((int) (((Spider) this.objects.get(i)).getX() * this.scaleX));
									((Spider) this.objects.get(i)).setY((int) (((Spider) this.objects.get(i)).getY() * this.scaleY) + Game.HEIGHT/4);
								}
								break;
					}
					break;
			case 3: switch(this.minigame) {
						case 1: ((Player) this.objects.get(0)).setY((int) (((Player) this.objects.get(0)).getY()) - Game.HEIGHT/4);
								if(this.objects.size() > 1) {
									((Axe) this.objects.get(1)).setY((int) (((Axe) this.objects.get(1)).getY()) - Game.HEIGHT/4);
								}
								break;
						case 2: ((Player) this.objects.get(0)).setY((int) (((Player) this.objects.get(0)).getY()) - Game.HEIGHT/4);
								for(int i=1;i<this.objects.size();i++) {
									((Car) this.objects.get(i)).setY((int) (((Car) this.objects.get(i)).getY()) + Game.HEIGHT/4);
								}
								break;
						case 3: ((Player) this.objects.get(0)).setY((int) (((Player) this.objects.get(0)).getY()) - Game.HEIGHT/4);
								for(int i=1;i<this.objects.size();i++) {
									((Asteroid) this.objects.get(i)).setY((int) (((Asteroid) this.objects.get(i)).getY()) + Game.HEIGHT/4);
								}
								break;
						case 4: ((Player) this.objects.get(0)).setY((int) (((Player) this.objects.get(0)).getY()) - Game.HEIGHT/4);
								for(int i=1;i<this.objects.size();i++) {
									((Spider) this.objects.get(i)).setY((int) (((Spider) this.objects.get(i)).getY()) + Game.HEIGHT/4);
								}
								break;
					}
					break;
			case 4: switch(this.minigame) {
						case 1: ((Player) this.objects.get(0)).setX((int) (((Player) this.objects.get(0)).getX()) - Game.WIDTH/4);
								if(this.objects.size() > 1) {
									((Axe) this.objects.get(1)).setX((int) (((Axe) this.objects.get(1)).getX()) - Game.WIDTH/4);
								}
								break;
						case 2: ((Player) this.objects.get(0)).setX((int) (((Player) this.objects.get(0)).getX()) - Game.WIDTH/4);
								for(int i=1;i<this.objects.size();i++) {
									((Car) this.objects.get(i)).setX((int) (((Car) this.objects.get(i)).getX()) - Game.WIDTH/4);
								}
								break;
						case 3: ((Player) this.objects.get(0)).setX((int) (((Player) this.objects.get(0)).getX()) - Game.WIDTH/4);
								for(int i=1;i<this.objects.size();i++) {
									((Asteroid) this.objects.get(i)).setX((int) (((Asteroid) this.objects.get(i)).getX()) - Game.WIDTH/4);
								}
								break;
						case 4: ((Player) this.objects.get(0)).setX((int) (((Player) this.objects.get(0)).getX()) - Game.WIDTH/4);
								for(int i=1;i<this.objects.size();i++) {
									((Spider) this.objects.get(i)).setX((int) (((Spider) this.objects.get(i)).getX()) - Game.WIDTH/4);
								}
								break;
					}
					break;
			case 5: switch(this.minigame) {
						case 1: ((Player) this.objects.get(0)).setX((int) (this.x + ((Game.WIDTH * this.scaleX)/2) - (100*this.scaleX)));
								((Player) this.objects.get(0)).setY((int) (this.y + ((Game.HEIGHT * this.scaleY)/2) - (100*this.scaleY)));
								if(this.objects.size() > 1) {
									((Axe) this.objects.get(1)).setX((int) (((Axe) this.objects.get(1)).getX()*this.scaleX));
									((Axe) this.objects.get(1)).setY((int) (this.y + ((Game.HEIGHT * this.scaleY)/2) - (30 * this.scaleY)));
								}
								break;
						case 2: ((Player) this.objects.get(0)).setX((int) (((Player) this.objects.get(0)).getX()*0.66666666666667));
								((Player) this.objects.get(0)).setY((int) (this.y + ((Game.HEIGHT * this.scaleY)/2) - (96*this.scaleY)));
								for(int i=1;i<this.objects.size();i++) {
									((Car) this.objects.get(1)).setX((int) (((Car) this.objects.get(i)).getX()*this.scaleX));
									((Car) this.objects.get(1)).setY((int) (((Game.HEIGHT * this.scaleY)/2) + ((220 - 424*(i/2)) * this.scaleY) + ((this.y + ((Player) this.objects.get(0)).offset + 24 * this.scaleY) - 432*1*this.scaleY)%(864*this.scaleY)));
								}
								break;
						case 3: ((Player) this.objects.get(0)).setX((int) (this.x + ((Game.WIDTH * this.scaleX)/2) - (100*this.scaleX)));
								((Player) this.objects.get(0)).setY((int) (this.y + (Game.HEIGHT * this.scaleY) - (280*this.scaleY)));
								for(int i=1;i<this.objects.size();i++) {
									((Asteroid) this.objects.get(1)).setX((int) (((Asteroid) this.objects.get(i)).getX()*this.scaleX));
									((Asteroid) this.objects.get(i)).setY((int) (((Asteroid) this.objects.get(i)).getY()*this.scaleY));
								}
								break;
						case 4: ((Player) this.objects.get(0)).setX((int) (this.x + ((Game.WIDTH * this.scaleX)/2) - (440*this.scaleX)));
								((Player) this.objects.get(0)).setY((int) (this.y + (Game.HEIGHT * this.scaleY) - (1273*this.scaleY)));
								if(this.objects.size() > 1) {
									((Spider) this.objects.get(1)).setX((int) (((Spider) this.objects.get(1)).getX()*this.scaleX));
									((Spider) this.objects.get(1)).setY((int) (((Spider) this.objects.get(1)).getY()*this.scaleY));
								}
								break;
					}
					break;
			case 10: switch(this.minigame) {
						case 1: ((Player) this.objects.get(0)).setX((int) (this.x + ((Game.WIDTH * this.scaleX)/2) - (100*this.scaleX)));
								((Player) this.objects.get(0)).setY((int) (this.y + ((Game.HEIGHT * this.scaleY)/2) - (100*this.scaleY)));
								if(this.objects.size() > 1) {
									((Axe) this.objects.get(1)).setX((int) (((Axe) this.objects.get(1)).getX()*this.scaleX));
									((Axe) this.objects.get(1)).setY((int) (this.y + ((Game.HEIGHT * this.scaleY)/2) - (30 * this.scaleY)));
								}
								break;
						case 2: ((Player) this.objects.get(0)).setX((int) (((Player) this.objects.get(0)).getX()*0.75));
								((Player) this.objects.get(0)).setY((int) (this.y + ((Game.HEIGHT * this.scaleY)/2) - (96*this.scaleY)));
								for(int i=1;i<this.objects.size();i++) {
									((Car) this.objects.get(1)).setX((int) (((Car) this.objects.get(i)).getX()*this.scaleX));
									((Car) this.objects.get(1)).setY((int) (((Game.HEIGHT * this.scaleY)/2) + ((220 - 424*(i/2)) * this.scaleY) + ((this.y + ((Player) this.objects.get(0)).offset + 24 * this.scaleY) - 432*1*this.scaleY)%(864*this.scaleY)));
								}
								break;
						case 3: ((Player) this.objects.get(0)).setX((int) (this.x + ((Game.WIDTH * this.scaleX)/2) - (100*this.scaleX)));
								((Player) this.objects.get(0)).setY((int) (this.y + (Game.HEIGHT * this.scaleY) - (280*this.scaleY)));
								for(int i=1;i<this.objects.size();i++) {
									((Asteroid) this.objects.get(1)).setX((int) (((Asteroid) this.objects.get(i)).getX()*this.scaleX));
									((Asteroid) this.objects.get(i)).setY((int) (((Asteroid) this.objects.get(i)).getY()*this.scaleY));
								}
								break;
						case 4: ((Player) this.objects.get(0)).setX((int) (this.x + ((Game.WIDTH * this.scaleX)/2) - (440*this.scaleX)));
								((Player) this.objects.get(0)).setY((int) (this.y + (Game.HEIGHT * this.scaleY) - (1273*this.scaleY)));
								if(this.objects.size() > 1) {
									((Spider) this.objects.get(1)).setX((int) (((Spider) this.objects.get(1)).getX()*this.scaleX));
									((Spider) this.objects.get(1)).setY((int) (((Spider) this.objects.get(1)).getY()*this.scaleY));
								}
								break;
					}
					break;
		}
	}
	
	public void tick() {
		if(this == this.game.scenes.get(0)) {
			switch(this.game.diff) {
				case 2: if(this.game.scenes.size() < 4) {
							if(java.lang.System.currentTimeMillis() - this.sceneTimer > this.sceneGap) {
								this.game.addScene();
								this.sceneTimer = java.lang.System.currentTimeMillis();
							}
						}
						break;
				case 3: if(this.game.scenes.size() < 9) {
							if(java.lang.System.currentTimeMillis() - this.sceneTimer > this.sceneGap) {
								this.game.addScene();
								this.sceneTimer = java.lang.System.currentTimeMillis();
							}
						}
						break;
				case 4: if(this.game.scenes.size() < 16) {
							if(java.lang.System.currentTimeMillis() - this.sceneTimer > this.sceneGap) {
								this.game.addScene();
								this.sceneTimer = java.lang.System.currentTimeMillis();
							}
						}
						break;
			}
		}
		
		switch(this.minigame) {
			case 1: if(java.lang.System.currentTimeMillis() % this.axeWait <= 100 && this.objects.size()==1) {
						this.addObject(new Axe(this.x + Game.WIDTH * this.scaleX, this.y + ((Game.HEIGHT * this.scaleY)/2) - (30 * this.scaleY), this.difficulty, this, this.game));
					}
					for(int i=0;i<this.objects.size();i++) {
						tempObject = this.objects.get(i);
						if(tempObject instanceof Axe) {
							if(((Axe) tempObject).getX() + ((Axe) tempObject).getImgX() < this.x || ((Axe) tempObject).getY() > this.y + (Game.HEIGHT*this.scaleY)) {
								this.removeObject(tempObject);
							} else {
								tempObject.tick();
							}
						} else {
							tempObject.tick();
						}
					}
					if(this.objects.size() > 1) {
						a = new Rectangle((int) (((Axe) this.objects.get(1)).getX()+(30*this.scaleX)), (int) (((Axe) this.objects.get(1)).getY()+(12*this.scaleY)), (int) (((Axe) this.objects.get(1)).getImgX()-(60*this.scaleX)), (int) (((Axe) this.objects.get(1)).getImgY()-(10*this.scaleY)));
						p = new Rectangle((int) (((Player) this.objects.get(0)).getX()+(20*this.scaleX)), (int) (((Player) this.objects.get(0)).getY()+(6*this.scaleY) - ((Player) this.objects.get(0)).offset), (int) (((Player) this.objects.get(0)).getImgX()-(40*this.scaleX)), (int) (((Player) this.objects.get(0)).getImgY()-(12*this.scaleY)));
						if(a.intersects(p)) {
							//System.out.println("YOU LOSE");
							this.damage();
							this.objects.remove(1);
							AudioPlayer.getSound("hurt").play();
						}
					}
					break;
			case 2: for(int i=0;i<this.lanes.size();i++) {
						if(java.lang.System.currentTimeMillis() % this.laneTimers.get(i) <= 100 && !this.lanes.get(i)) {
							if(i%2==0) {
								this.addObject(new Car(this.x + Game.WIDTH * this.scaleX, ((Game.HEIGHT * this.scaleY)/2) + ((220 - 424*(i/2)) * this.scaleY) + ((this.y + ((Player) this.objects.get(0)).offset + 24 * this.scaleY) - 432*1*this.scaleY)%(864*this.scaleY), i, this.difficulty, this, this.game));
								this.lanes.set(i, true);
							} else {
								this.addObject(new Car(this.x + Game.WIDTH * this.scaleX, ((Game.HEIGHT * this.scaleY)/2) + ((52 - 424*((i-1)/2)) * this.scaleY) + ((this.y + ((Player) this.objects.get(0)).offset + 24 * this.scaleY) - 432*1*this.scaleY)%(864*this.scaleY), i, this.difficulty, this, this.game));
								this.lanes.set(i, true);
							}
						}
					}
					for(int i=0;i<this.objects.size();i++) {
						tempObject = this.objects.get(i);
						if(tempObject instanceof Car) {
							if(((Car) tempObject).getX() + ((Car) tempObject).getImgX() < this.x || ((Car) tempObject).getY() > this.y + (Game.HEIGHT*this.scaleY)) {
								this.lanes.set(((Car) tempObject).lane, false);
								this.removeObject(tempObject);
							} else {
								tempObject.tick();
							}
						} else {
							tempObject.tick();
						}
					}
					if(((Player) this.objects.get(0)).offset % (216*this.scaleY) == 0 && !this.act) {
						this.act = true;
					}
					p = new Rectangle((int) (this.x + ((Game.WIDTH * this.scaleX)/2) - (62*this.scaleX)), (int) (this.y + ((Game.HEIGHT * this.scaleY)/2) + (36*this.scaleY)), (int) (124*this.scaleX), (int) (182*this.scaleY));
					for(int i=1;i<this.objects.size();i++) {
						c = new Rectangle((int) (((Car) this.objects.get(i)).getX()+(30*this.scaleX)), (int) (((Car) this.objects.get(i)).getY()+(48*this.scaleY)), (int) (((Car) this.objects.get(i)).getImgX()-(60*this.scaleX)), (int) (94*this.scaleY));
						if(c.intersects(p)) {
							//System.out.println("YOU LOSE");
							this.damage();
							this.lanes.set(((Car) this.objects.get(i)).lane, false);
							this.objects.remove(i);
							AudioPlayer.getSound("hurt").play();
						}
					}
					break;
			case 3: if(this.progress <= this.progresstotal + 500*this.progresstick) {
						this.progress += this.progresstick;
					} else if(!done) {
						this.game.win = true;
						done = true;
					}
					if(Math.abs(this.progress - this.progresstotal) < 3*this.progresstick) {
						for(int i=1;i<this.objects.size();i++) {
							((Asteroid) this.objects.get(i)).explode();
						}
					} else if(this.progress < this.progresstotal + 300*this.progresstick && this.progress > this.progresstotal + 150*this.progresstick)  {
						((Player) this.objects.get(0)).y -= 0.1;
					} else if(this.progress >= this.progresstotal + 300*this.progresstick) {
						((Player) this.objects.get(0)).y -= 4;
					}
					if(this.cdTimer > 0) {
						this.cdTimer -= 1;
					}
					if(this.progress < this.progresstotal) {
						if(rand.nextInt(150) == 1) {
							if(rand.nextInt(2) == 0) {
								this.addObject(new Asteroid(this.x + rand.nextInt(250)*this.scaleX, this.y - 200*this.scaleY, this.difficulty, this, this.game));
							} else {
								this.addObject(new Asteroid(this.x + (Game.WIDTH * this.scaleX) - rand.nextInt(400)*this.scaleX, this.y - 200*this.scaleY, this.difficulty, this, this.game));
							}
						}
						if(java.lang.System.currentTimeMillis() % this.asteroidWait <= 20 && this.cdTimer == 0) {
							if(rand.nextInt(20) == 1) {
								int r = rand.nextInt(3);
								if(r==0) {
									this.addObject(new Asteroid(this.x + (Game.WIDTH * this.scaleX)/2 + 20*this.scaleX, this.y - 200*this.scaleY, this.difficulty, this, this.game));
									this.cdTimer = this.cooldown;
								} else if(r==1) {
									this.addObject(new Asteroid(this.x + (Game.WIDTH * this.scaleX)/2 - 180*this.scaleX, this.y - 200*this.scaleY, this.difficulty, this, this.game));
									this.cdTimer = this.cooldown;
								} else {
									this.addObject(new Asteroid(this.x + (Game.WIDTH * this.scaleX)/2 + 20*this.scaleX, this.y - 200*this.scaleY, this.difficulty, this, this.game));
									this.addObject(new Asteroid(this.x + (Game.WIDTH * this.scaleX)/2 - 180*this.scaleX, this.y - 200*this.scaleY, this.difficulty, this, this.game));
									this.cdTimer = this.cooldown;
								}
							}
						}
					}
					for(int i=0;i<this.objects.size();i++) {
						tempObject = this.objects.get(i);
						if(tempObject instanceof Asteroid) {
							if(((Asteroid) tempObject).getX() + ((Asteroid) tempObject).getImgX() < this.x || ((Asteroid) tempObject).getY() > this.y + (Game.HEIGHT*this.scaleY)) {
								this.removeObject(tempObject);
							} else {
								tempObject.tick();
							}
						} else {
							tempObject.tick();
						}
					}
					p = new Rectangle((int) (((Player) this.objects.get(0)).getX()+(20*this.scaleX)), (int) (((Player) this.objects.get(0)).getY()+(28*this.scaleY)), (int) (((Player) this.objects.get(0)).getImgX()-(48*this.scaleX)), (int) (((Player) this.objects.get(0)).getImgY()-(12*this.scaleY)));
					for(int i=1;i<this.objects.size();i++) {
						a = new Rectangle((int) (((Asteroid) this.objects.get(i)).getX()+(30*this.scaleX)), (int) (((Asteroid) this.objects.get(i)).getY()+(12*this.scaleY)), (int) (((Asteroid) this.objects.get(i)).getImgX()-(60*this.scaleX)), (int) (((Asteroid) this.objects.get(i)).getImgY()-(20*this.scaleY)));
						if(a.intersects(p)) {
							//System.out.println("YOU LOSE");
							this.damage();
							this.objects.remove(i);
							AudioPlayer.getSound("hurt").play();
						}
						for(int j=0;j<((Player) this.objects.get(0)).bullets.size();j++) {
							tempBullet = ((Player) this.objects.get(0)).bullets.get(j);
							b = new Rectangle(tempBullet.x, (int) (tempBullet.y + 36*this.scaleY), (int) (12*this.scaleX), (int) (12*this.scaleY));
							if(a.intersects(b)) {
								((Player) this.objects.get(0)).bullets.remove(tempBullet);
								((Asteroid) this.objects.get(i)).explode();
							}
						}
					}
					break;
			case 4: if(java.lang.System.currentTimeMillis() % this.spiderWait <= 100 && this.objects.size()==1) {
						this.addObject(new Spider(this.x - 600*this.scaleX, this.y + ((Game.HEIGHT * this.scaleY)/2) - (70 * this.scaleY), this.difficulty, this, this.game));
					}
					for(int i=0;i<this.objects.size();i++) {
						tempObject = this.objects.get(i);
						if(tempObject instanceof Axe) {
							if(((Axe) tempObject).getX() + ((Axe) tempObject).getImgX() < this.x || ((Axe) tempObject).getY() > this.y + (Game.HEIGHT*this.scaleY)) {
								this.removeObject(tempObject);
							} else {
								tempObject.tick();
							}
						} else {
							tempObject.tick();
						}
					}
					p = new Rectangle((int) (((Player) this.objects.get(0)).getX()+(100*this.scaleX)), (int) (((Player) this.objects.get(0)).getY()+(10*this.scaleY) + ((Player) this.objects.get(0)).offset), (int) (((Player) this.objects.get(0)).getImgX()-(300*this.scaleX)), (int) (((Player) this.objects.get(0)).getImgY()-(12*this.scaleY)));
					if(this.objects.size() > 1) {
						a = new Rectangle((int) (((Spider) this.objects.get(1)).getX()+(150*this.scaleX)), (int) (((Spider) this.objects.get(1)).getY()+(65*this.scaleY)), (int) (((Spider) this.objects.get(1)).getImgX()-(300*this.scaleX)), (int) (((Spider) this.objects.get(1)).getImgY()-(100*this.scaleY)));
						if(a.intersects(p)) {
							//System.out.println("CRUSHED");
							((Spider) this.objects.get(1)).crush();
							if(this.played) {
								AudioPlayer.getSound("stomp").play();
								this.played = false;
							}
						}
					}
					break;
		}
	}
	
	public void render(Graphics2D g) {
		g.setClip(new Rectangle(this.x, this.y, (int) (Game.WIDTH*this.scaleX), (int) (Game.HEIGHT*this.scaleY)));
		
		g.setColor(Color.white);
		g.fillRect(this.x, this.y, (int) (Game.WIDTH * this.scaleX), (int) (Game.HEIGHT * this.scaleY));
		
		switch(this.minigame) {
			case 1: for(int i=0;i<this.objects.size();i++) {
						tempObject = this.objects.get(i);
						tempObject.render(g);
					}
					g.setColor(Color.darkGray);
					g.fillRect(this.x, (int) (((Player) this.objects.get(0)).getY() + ((Player) this.objects.get(0)).getImgY()), (int) (Game.WIDTH * this.scaleX), Game.HEIGHT - ((int) (((Player) this.objects.get(0)).getY() + ((Player) this.objects.get(0)).getImgY())));
					if(a!=null && this.game.debug) {
						g.setColor(Color.black);
						g.draw(a);
						g.draw(p);
					}
					break;
			case 2: for(int i=0;i<this.objects.size();i++) {
						tempObject = this.objects.get(i);
						tempObject.render(g);
					}
					if(p!=null && this.game.debug) {
						for(int i=1;i<this.objects.size();i++) {
							c = new Rectangle((int) (((Car) this.objects.get(i)).getX()+(30*this.scaleX)), (int) (((Car) this.objects.get(i)).getY()+(48*this.scaleY)), (int) (((Car) this.objects.get(i)).getImgX()-(60*this.scaleX)), (int) (94*this.scaleY));
							g.setColor(Color.white);
							g.draw(c);
						}
						g.draw(p);
					}
					break;
			case 3:	g.setColor(Color.black);
					g.fillRect(this.x, this.y, (int) (Game.WIDTH*this.scaleX), (int) (Game.HEIGHT*this.scaleY));
					for(int i=0;i<this.objects.size();i++) {
						tempObject = this.objects.get(i);
						tempObject.render(g);
					}
					if(a!=null && this.game.debug) {
						for(int i=1;i<this.objects.size();i++) {
							a = new Rectangle((int) (((Asteroid) this.objects.get(i)).getX()+(30*this.scaleX)), (int) (((Asteroid) this.objects.get(i)).getY()+(12*this.scaleY)), (int) (((Asteroid) this.objects.get(i)).getImgX()-(60*this.scaleX)), (int) (((Asteroid) this.objects.get(i)).getImgY()-(20*this.scaleY)));
							g.setColor(Color.white);
							g.draw(a);
						}
						g.setColor(Color.blue);
						g.draw(p);
					}
					g.setColor(Color.red);
					g.setStroke(new BasicStroke((float) (4*this.scaleX)));
					g.drawRect((int) (this.x + Game.WIDTH*this.scaleX*0.96), (int) (this.y + Game.HEIGHT*this.scaleY*0.2), (int) (Game.WIDTH*this.scaleX*0.015), (int) (Game.HEIGHT*this.scaleY*0.6));
					g.setStroke(new BasicStroke(1));
					g.setColor(Color.cyan);
					g.fillRect((int) (this.x + Game.WIDTH*this.scaleX*0.96 + 1), (int) (this.y + Game.HEIGHT*this.scaleY*0.2 + 1), (int) (Game.WIDTH*this.scaleX*0.015 - 2), (int) (Game.HEIGHT*this.scaleY*0.6 - 2));
					g.setColor(Color.blue);
					g.fillRect((int) (this.x + Game.WIDTH*this.scaleX*0.96 + 1), (int) (this.y + Game.HEIGHT*this.scaleY*0.2 + 1), (int) (Game.WIDTH*this.scaleX*0.015 - 2), (int) (((this.progresstotal-this.progress)*(Game.HEIGHT*this.scaleY*0.6 - 2))/this.progresstotal));
					break;
			case 4: g.setColor(Color.white);
					g.fillRect(this.x, this.y, (int) (Game.WIDTH * this.scaleX), (int) (Game.HEIGHT * this.scaleY));
					g.setColor(Color.darkGray);
					g.fillRect(this.x, this.y + (int) (Game.HEIGHT*this.scaleY - 245*this.scaleY), (int) (Game.WIDTH*this.scaleX), (int) (250*this.scaleY));
					g.setColor(Color.lightGray);
					g.fillRect(this.x, this.y + (int) (Game.HEIGHT*this.scaleY - 330*this.scaleY), (int) (Game.WIDTH*this.scaleX), (int) (85*this.scaleY));
					g.setColor(new Color(10, 10, 10, 100));
					g.fillOval((int) (this.x + (Game.WIDTH*this.scaleX)/4 - 50*this.scaleX), this.y + (int) (Game.HEIGHT*this.scaleY - 310*this.scaleY), (int) ((Game.WIDTH*this.scaleX)/2 + 100*this.scaleX), (int) (50*this.scaleY));
					for(int i=1;i<this.objects.size();i++) {
						tempObject = this.objects.get(i);
						tempObject.render(g);
					}
					this.objects.get(0).render(g);
					if(this.game.debug) {
						for(int i=1;i<this.objects.size();i++) {
							a = new Rectangle((int) (((Spider) this.objects.get(i)).getX()+(150*this.scaleX)), (int) (((Spider) this.objects.get(i)).getY()+(65*this.scaleY)), (int) (((Spider) this.objects.get(i)).getImgX()-(300*this.scaleX)), (int) (((Spider) this.objects.get(i)).getImgY()-(100*this.scaleY)));
							g.setColor(Color.red);
							g.draw(a);
						}
						p = new Rectangle((int) (((Player) this.objects.get(0)).getX()+(100*this.scaleX)), (int) (((Player) this.objects.get(0)).getY()+(10*this.scaleY) + ((Player) this.objects.get(0)).offset), (int) (((Player) this.objects.get(0)).getImgX()-(300*this.scaleX)), (int) (((Player) this.objects.get(0)).getImgY()-(12*this.scaleY)));
						g.setColor(Color.blue);
						g.draw(p);
					}
					break;
		}
		if(this.damageTimer > 0) {
			this.damageTimer -= 1;
			g.setColor(new Color(255, 0, 0, (255 * this.damageTimer) / this.dmg));
			g.fillRect(this.x, this.y, (int) (Game.WIDTH*this.scaleX), (int) (Game.HEIGHT*this.scaleY));
		}
	}
	
	public Scene(int x, int y, double scaleX, double scaleY, int difficulty, int minigame, int key, Game game) {
		this.x = x;
		this.y = y;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.minigame = minigame;
		this.game = game;
		this.difficulty = difficulty;
		this.key = key;
		this.played = true;
		this.dmg = 70;
		this.damageTimer = 0;
		this.sceneTimer = java.lang.System.currentTimeMillis();
		rand = new Random();
		this.sceneGap = rand.nextInt(5000) + 5000;
		done = false;
		
		switch(this.minigame) {
			case 1: this.axeWait = (rand.nextInt(1000) + 2500)/difficulty;
					this.addObject(new Player(this.x + ((Game.WIDTH * this.scaleX)/2) - (100*this.scaleX), this.y + ((Game.HEIGHT * this.scaleY)/2) - (100*this.scaleY), this.minigame, this, this.game));
					break;
			case 2: ArrayList<Boolean> lanes = new ArrayList<Boolean>();
					this.lanes = lanes;
					ArrayList<Integer> laneTimers = new ArrayList<Integer>();
					this.laneTimers = laneTimers;
					for(int i=0;i<10;i++) {
						this.lanes.add(false);
						this.laneTimers.add((rand.nextInt(2000) + 10000)/difficulty);
					}
					this.act = true;
					this.addObject(new Player(this.x + ((Game.WIDTH * this.scaleX)/2) - (180*this.scaleX), this.y + ((Game.HEIGHT * this.scaleY)/2) - (96*this.scaleY), this.minigame, this, this.game));
					break;
			case 3: this.progresstotal = 100;
					this.progress = 0;
					this.progresstick = 0.025;
					this.cooldown = 100;
					this.cdTimer = this.cooldown;
					this.asteroidWait = (rand.nextInt(50) + 50)/difficulty;
					this.addObject(new Player(this.x + ((Game.WIDTH * this.scaleX)/2) - (100*this.scaleX), this.y + (Game.HEIGHT * this.scaleY) - (280*this.scaleY), this.minigame, this, this.game));
					break;
			case 4: this.spiderWait = (rand.nextInt(1000) + 5000)/difficulty;
					this.addObject(new Player(this.x + ((Game.WIDTH * this.scaleX)/2) - (440*this.scaleX), this.y + (Game.HEIGHT * this.scaleY) - (1273*this.scaleY), this.minigame, this, this.game));
					//this.addObject(new Spider(this.x - 600*this.scaleX, this.y + ((Game.HEIGHT * this.scaleY)/2) - (70 * this.scaleY), this.difficulty, this, this.game));
					break;
		}
	}
	
	public void addObject(GameObject obj) {
		objects.add(obj);
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
	}
}
