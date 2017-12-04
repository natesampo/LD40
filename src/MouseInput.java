import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	private Game game;
	
	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		this.game.mouse(mouseX, mouseY);
	}
	
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		this.game.mousePress(mouseX, mouseY);
	}
	
	public MouseInput(Game game) {
		this.game = game;
	}
}
