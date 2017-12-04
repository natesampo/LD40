import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static void load() {
		try {
			soundMap.put("stomp", new Sound("res/stomp.wav"));
			soundMap.put("shoot", new Sound("res/shoot.wav"));
			soundMap.put("select", new Sound("res/select.wav"));
			soundMap.put("hurt", new Sound("res/hurt.wav"));
			soundMap.put("jump", new Sound("res/jump.wav"));
			soundMap.put("explode", new Sound("res/explode.wav"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
}
