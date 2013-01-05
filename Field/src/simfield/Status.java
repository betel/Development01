package simfield;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;

public class Status {

	public	int		fps;
	private long	lastFPS;
	DrawTTF ttf;

	public Status(){		//コンストラクタ
		lastFPS = getTime();
		ttf = new DrawTTF();
	}

	public long getTime(){
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	public void updateFPS(){
		if(getTime() - lastFPS > 1000){
			String str = "FPS: " + Integer.toString(fps);
			ttf.drawStr(100, 100, str, Color.green);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
}
