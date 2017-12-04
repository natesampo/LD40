import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class KeyInput extends KeyAdapter{
	private Game game;
	private int key;

	public KeyInput(Game game){
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e) {
		this.key = e.getKeyCode();
		this.game.keyPress(this.key);
	}
	
	public void keyReleased(KeyEvent e) {
		this.key = e.getKeyCode();
		this.game.keyRel(this.key);
	}
}