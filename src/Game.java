import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -6748925586576993018L;
	public static final int WIDTH = 1024, HEIGHT = 768;
	private MouseInput mouseInput;
	private KeyInput keyInput;
	public boolean running, win, lose;
	public LinkedList<Scene> scenes = new LinkedList<Scene>();
	private Thread thread;
	private Scene tempScene;
	public boolean debug;
	public int hp, hptotal, menu, diff, r;
	Random rand;
	Image image, image1, image2, image3, image4, image5, image6;

	public void mouse(int mouseX, int mouseY) {
		if(this.menu!=0) {
			if(mouseX > 360 && mouseX < 624 && mouseY > 415 && mouseY < 480) {
				this.menu=2;
			} else if(mouseX > 360 && mouseX < 624 && mouseY > 480 && mouseY < 548) {
				this.menu=3;
			} else if(mouseX > 360 && mouseX < 624 && mouseY > 548 && mouseY < 617) {
				this.menu=4;
			} else if(mouseX > 360 && mouseX < 624 && mouseY > 617 && mouseY < 690) {
				this.menu=5;
			} else {
				this.menu=1;
			}
		}
	}
	
	public void mousePress(int mouseX, int mouseY) {
		if(this.menu > 1) {
			this.diff = this.menu-1;
			this.addScene();
			this.menu = 0;
		}
	}
	
	public void keyPress(int keya) {
		if(keya==47) {
			this.debug = !this.debug;
		} else if(keya==77 && (this.win || this.lose)) {
			this.win = false;
			this.lose = false;
			this.hp = this.hptotal;
			this.menu = 1;
		} else if(keya==67 && this.win) {
			this.win = false;
		} else if(keya==10 && this.menu>1) {
			this.diff = this.menu-1;
			this.addScene();
			this.menu = 0;
		} else {
			for(int i=0;i<this.scenes.size();i++) {
				tempScene = this.scenes.get(i);
				System.out.println(keya);
				if(tempScene.key == keya) {
					tempScene.pressAction();
				}
			}
		}
	}
	
	public void keyRel(int keya) {
		for(int i=0;i<this.scenes.size();i++) {
			tempScene = this.scenes.get(i);
			//System.out.println(keya);
			if(tempScene.key == keya) {
				tempScene.relAction();
			}
		}
	}
	
	public void addScene() {
		boolean stop = false;
		if(this.scenes.size() > 0) {
			switch(this.scenes.size()+1) {
				case 2: this.scenes.get(0).scaleX = 0.5;
						this.scenes.get(0).scaleY = 0.5;
						this.scenes.get(0).y = (int) (HEIGHT/4);
						r = rand.nextInt(3);
						switch(r) {
							case 0: this.scenes.add(new Scene((int) (WIDTH/2), (int) (HEIGHT/4), 0.5, 0.5, 1, 1, 87, this));
									break;
							case 1: this.scenes.add(new Scene((int) (WIDTH/2), (int) (HEIGHT/4), 0.5, 0.5, 1, 4, 87, this));
									break;
							case 2: this.scenes.add(new Scene((int) (WIDTH/2), (int) (HEIGHT/4), 0.5, 0.5, 1, 2, 87, this));
						}
						this.scenes.get(0).more(this.scenes.size());
						stop = true;
						break;
				case 3: if(!stop) {
							for(int i=0;i<this.scenes.size();i++) {
								tempScene = this.scenes.get(i);
								tempScene.y = 0;
								tempScene.more(this.scenes.size()+1);
							}
							r = rand.nextInt(3);
							switch(r) {
								case 0: this.scenes.add(new Scene((int) (WIDTH)/4, (int) (HEIGHT/2), 0.5, 0.5, 1, 1, 69, this));
										break;
								case 1: this.scenes.add(new Scene((int) (WIDTH)/4, (int) (HEIGHT/2), 0.5, 0.5, 1, 4, 69, this));
										break;
								case 2: this.scenes.add(new Scene((int) (WIDTH)/4, (int) (HEIGHT/2), 0.5, 0.5, 1, 2, 69, this));
							}
							stop = true;
						}
						break;
				case 4: if(!stop) {
							this.scenes.get(2).x = 0;
							if(this.diff==2) {
								this.scenes.add(new Scene((int) (WIDTH/2), (int) (HEIGHT/2), 0.5, 0.5, 1, 3, 82, this));
							} else {
								r = rand.nextInt(3);
								switch(r) {
									case 0: this.scenes.add(new Scene((int) (WIDTH/2), (int) (HEIGHT/2), 0.5, 0.5, 1, 1, 82, this));
											break;
									case 1: this.scenes.add(new Scene((int) (WIDTH/2), (int) (HEIGHT/2), 0.5, 0.5, 1, 4, 82, this));
											break;
									case 2: this.scenes.add(new Scene((int) (WIDTH/2), (int) (HEIGHT/2), 0.5, 0.5, 1, 2, 82, this));
								}
							}
							this.scenes.get(2).more(this.scenes.size());
							stop = true;
						}
						break;
				case 5: if(!stop) {
							for(int i=0;i<this.scenes.size();i++) {
								tempScene = this.scenes.get(i);
								tempScene.scaleX = 0.3333333333333;
								tempScene.scaleY = 0.3333333333333;
								tempScene.x = (int) (tempScene.x * 0.6666666666667);
								tempScene.y = (int) (tempScene.y * 0.6666666666667);
								tempScene.more(this.scenes.size()+1);
							}
							r = rand.nextInt(3);
							switch(r) {
								case 0: this.scenes.add(new Scene(0, (int) (2*HEIGHT/3), 0.3333333333333, 0.3333333333333, 1, 1, 84, this));
										break;
								case 1: this.scenes.add(new Scene(0, (int) (2*HEIGHT/3), 0.3333333333333, 0.3333333333333, 1, 4, 84, this));
										break;
								case 2: this.scenes.add(new Scene(0, (int) (2*HEIGHT/3), 0.3333333333333, 0.3333333333333, 1, 2, 84, this));
							}
							stop = true;
						}
						break;
				case 6: if(!stop) {
							r = rand.nextInt(3);
							switch(r) {
								case 0: this.scenes.add(new Scene((int) (WIDTH/3), (int) (2*HEIGHT/3), 0.3333333333333, 0.3333333333333, 1, 1, 89, this));
										break;
								case 1: this.scenes.add(new Scene((int) (WIDTH/3), (int) (2*HEIGHT/3), 0.3333333333333, 0.3333333333333, 1, 4, 89, this));
										break;
								case 2: this.scenes.add(new Scene((int) (WIDTH/3), (int) (2*HEIGHT/3), 0.3333333333333, 0.3333333333333, 1, 2, 89, this));
							}
							stop = true;
						}
						break;
				case 7: if(!stop) {
							r = rand.nextInt(3);
							switch(r) {
								case 0: this.scenes.add(new Scene((int) (2*WIDTH/3), (int) (2*HEIGHT/3), 0.3333333333333, 0.3333333333333, 1, 1, 85, this));
										break;
								case 1: this.scenes.add(new Scene((int) (2*WIDTH/3), (int) (2*HEIGHT/3), 0.3333333333333, 0.3333333333333, 1, 4, 85, this));
										break;
								case 2: this.scenes.add(new Scene((int) (2*WIDTH/3), (int) (2*HEIGHT/3), 0.3333333333333, 0.3333333333333, 1, 2, 85, this));
							}
							stop = true;
						}
						break;
				case 8: if(!stop) {
							r = rand.nextInt(3);
							switch(r) {
								case 0: this.scenes.add(new Scene((int) (2*WIDTH/3), (int) (HEIGHT/3), 0.3333333333333, 0.3333333333333, 1, 1, 73, this));
										break;
								case 1: this.scenes.add(new Scene((int) (2*WIDTH/3), (int) (HEIGHT/3), 0.3333333333333, 0.3333333333333, 1, 4, 73, this));
										break;
								case 2: this.scenes.add(new Scene((int) (2*WIDTH/3), (int) (HEIGHT/3), 0.3333333333333, 0.3333333333333, 1, 2, 73, this));
							}
							stop = true;
						}
						break;
				case 9: if(!stop) {
							if(this.diff==3) {
								this.scenes.add(new Scene((int) (2*WIDTH/3), 0, 0.3333333333333, 0.3333333333333, 1, 3, 79, this));
							} else {
								r = rand.nextInt(3);
								switch(r) {
									case 0: this.scenes.add(new Scene((int) (2*WIDTH/3), 0, 0.3333333333333, 0.3333333333333, 1, 1, 79, this));
											break;
									case 1: this.scenes.add(new Scene((int) (2*WIDTH/3), 0, 0.3333333333333, 0.3333333333333, 1, 4, 79, this));
											break;
									case 2: this.scenes.add(new Scene((int) (2*WIDTH/3), 0, 0.3333333333333, 0.3333333333333, 1, 2, 79, this));
								}
							}
							stop = true;
						}
						break;
				case 10: if(!stop) {
							for(int i=0;i<this.scenes.size();i++) {
								tempScene = this.scenes.get(i);
								tempScene.scaleX = 0.25;
								tempScene.scaleY = 0.25;
								tempScene.x = (int) (tempScene.x * 0.75);
								tempScene.y = (int) (tempScene.y * 0.75);
								tempScene.more(this.scenes.size()+1);
							}
							r = rand.nextInt(3);
							switch(r) {
								case 0: this.scenes.add(new Scene((int) (3*WIDTH/4), 0, 0.25, 0.25, 1, 1, 80, this));
										break;
								case 1: this.scenes.add(new Scene((int) (3*WIDTH/4), 0, 0.25, 0.25, 1, 4, 80, this));
										break;
								case 2: this.scenes.add(new Scene((int) (3*WIDTH/4), 0, 0.25, 0.25, 1, 2, 80, this));
							}
							stop = true;
						}
						break;
				case 11: if(!stop) {
							r = rand.nextInt(3);
							switch(r) {
								case 0: this.scenes.add(new Scene((int) (3*WIDTH/4), (int) (HEIGHT/4), 0.25, 0.25, 1, 1, 65, this));
										break;
								case 1: this.scenes.add(new Scene((int) (3*WIDTH/4), (int) (HEIGHT/4), 0.25, 0.25, 1, 4, 65, this));
										break;
								case 2: this.scenes.add(new Scene((int) (3*WIDTH/4), (int) (HEIGHT/4), 0.25, 0.25, 1, 2, 65, this));
							}
							 stop = true;
						 }
						 break;
				case 12: if(!stop) {
							r = rand.nextInt(3);
							switch(r) {
								case 0: this.scenes.add(new Scene((int) (3*WIDTH/4), (int) (2*HEIGHT/4), 0.25, 0.25, 1, 1, 83, this));
										break;
								case 1: this.scenes.add(new Scene((int) (3*WIDTH/4), (int) (2*HEIGHT/4), 0.25, 0.25, 1, 4, 83, this));
										break;
								case 2: this.scenes.add(new Scene((int) (3*WIDTH/4), (int) (2*HEIGHT/4), 0.25, 0.25, 1, 2, 83, this));
							}
							 stop = true;
						 }
						 break;
				case 13: if(!stop) {
							r = rand.nextInt(3);
							switch(r) {
								case 0: this.scenes.add(new Scene((int) (3*WIDTH/4), (int) (3*HEIGHT/4), 0.25, 0.25, 1, 1, 68, this));
										break;
								case 1: this.scenes.add(new Scene((int) (3*WIDTH/4), (int) (3*HEIGHT/4), 0.25, 0.25, 1, 4, 68, this));
										break;
								case 2: this.scenes.add(new Scene((int) (3*WIDTH/4), (int) (3*HEIGHT/4), 0.25, 0.25, 1, 2, 68, this));
							}
							 stop = true;
						 }
						 break;
				case 14: if(!stop) {
							r = rand.nextInt(3);
							switch(r) {
								case 0: this.scenes.add(new Scene((int) (2*WIDTH/4), (int) (3*HEIGHT/4), 0.25, 0.25, 1, 1, 70, this));
										break;
								case 1: this.scenes.add(new Scene((int) (2*WIDTH/4), (int) (3*HEIGHT/4), 0.25, 0.25, 1, 4, 70, this));
										break;
								case 2: this.scenes.add(new Scene((int) (2*WIDTH/4), (int) (3*HEIGHT/4), 0.25, 0.25, 1, 2, 70, this));
							}
							 stop = true;
						 }
						 break;
				case 15: if(!stop) {
							r = rand.nextInt(3);
							switch(r) {
								case 0: this.scenes.add(new Scene((int) (1*WIDTH/4), (int) (3*HEIGHT/4), 0.25, 0.25, 1, 1, 71, this));
										break;
								case 1: this.scenes.add(new Scene((int) (1*WIDTH/4), (int) (3*HEIGHT/4), 0.25, 0.25, 1, 4, 71, this));
										break;
								case 2: this.scenes.add(new Scene((int) (1*WIDTH/4), (int) (3*HEIGHT/4), 0.25, 0.25, 1, 2, 71, this));
							}
							 stop = true;
						 }
						 break;
				case 16: if(!stop) {
							 this.scenes.add(new Scene(0, (int) (3*HEIGHT/4), 0.25, 0.25, 1, 3, 72, this));
							 stop = true;
						 }
						 break;
			}
		} else if(this.diff==1) {
			this.scenes.add(new Scene(0, 0, 1, 1, 1, 3, 81, this));
		} else {
			r = rand.nextInt(3);
			switch(r) {
				case 0: this.scenes.add(new Scene(0, 0, 1, 1, 1, 1, 81, this));
						break;
				case 1: this.scenes.add(new Scene(0, 0, 1, 1, 1, 2, 81, this));
						break;
				case 2: this.scenes.add(new Scene(0, 0, 1, 1, 1, 4, 81, this));
			}
		}
	}
	
	public void tick() {
		if(this.hp > 0 && !this.win && this.menu==0) {
			for(int i=0;i<this.scenes.size();i++) {
				tempScene = this.scenes.get(i);
				tempScene.tick();
			}
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		if(this.hp > 0 && !this.win && this.menu==0) {
			g.setColor(Color.lightGray);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			for(int i=0;i<this.scenes.size();i++) {
				tempScene = this.scenes.get(i);
				tempScene.render(g);
			}
			
			g.setClip(0, 0, WIDTH, HEIGHT);
	
			g.setColor(Color.black);
			g.fillRect((int) (WIDTH/5), (int) (HEIGHT/50), (int) (WIDTH*0.6), (int) (HEIGHT/100));
			g.fillRect((int) (WIDTH/5), (int) (HEIGHT/50 + HEIGHT/16), (int) (WIDTH*0.6), (int) (HEIGHT/100));
			g.fillRect((int) (WIDTH/5), (int) (HEIGHT/50), (int) (HEIGHT/100), HEIGHT/16);
			g.fillRect((int) (WIDTH/5 + WIDTH*0.6 - HEIGHT/100), (int) (HEIGHT/50), (int) (HEIGHT/100), HEIGHT/16);
			g.setColor(Color.red);
			g.fillRect((int) (WIDTH/5 + HEIGHT/100), (int) (HEIGHT/50 + HEIGHT/100), (int) (WIDTH*0.6 - HEIGHT/50 + WIDTH/1000), (int) (HEIGHT/16 - HEIGHT/100));
			g.setColor(Color.green);
			g.fillRect((int) (WIDTH/5 + HEIGHT/100), (int) (HEIGHT/50 + HEIGHT/100), (int) ((this.hp*(WIDTH*0.6 - HEIGHT/50 + WIDTH/1000))/this.hptotal), (int) (HEIGHT/16 - HEIGHT/100));
		} else if(this.hp <= 0) {
			this.lose = true;
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.drawImage(image, 0, 0, null);
		} else if(this.win) {
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.drawImage(image1, 0, 0, null);
		} else if(this.menu==1) {
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.drawImage(image2, 0, 0, null);
		} else if(this.menu==2) {
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.drawImage(image3, 0, 0, null);
		} else if(this.menu==3) {
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.drawImage(image4, 0, 0, null);
		} else if(this.menu==4) {
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.drawImage(image5, 0, 0, null);
		} else if(this.menu==5) {
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.drawImage(image6, 0, 0, null);
		}
		g.dispose();
		bs.show();
	}
	
	public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
        	long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1) {
            	tick();
            	delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000) {
            	timer += 1000;
            	//System.out.println("FPS: "+ frames);
            	frames = 0;
            }
        }
        stop();
    }
	
	public Game() {
		new Window(WIDTH, HEIGHT, "Game", this);
		keyInput = new KeyInput(this);
		mouseInput = new MouseInput(this);
		this.addMouseListener(mouseInput);
		this.addKeyListener(keyInput);
		this.addMouseMotionListener(mouseInput);
		this.menu = 1;
		this.hptotal = 100;
		this.win = false;
		this.lose = false;
		this.diff = 0;
		this.hp = hptotal;
		rand = new Random();
		try {
			image = ImageIO.read(new File("res/youlose.png"));
			image1 = ImageIO.read(new File("res/youwin.png"));
			image2 = ImageIO.read(new File("res/menu.png"));
			image3 = ImageIO.read(new File("res/menu1.png"));
			image4 = ImageIO.read(new File("res/menu2.png"));
			image5 = ImageIO.read(new File("res/menu3.png"));
			image6 = ImageIO.read(new File("res/menu4.png"));
		} catch (IOException e) {
			image = null;
			image1 = null;
			image2 = null;
			image3 = null;
			image4 = null;
			image5 = null;
			image6 = null;
		}
		this.debug = false;
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		new Game();
	}
	
}
