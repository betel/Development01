package simfield;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

public class Status {

	public	int		fps;
	private long	lastFPS;

	public Status(){		//コンストラクタ
		lastFPS = getTime();
	}

	public long getTime(){
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	public void updateFPS(){
		if(getTime() - lastFPS > 1000){
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
}
