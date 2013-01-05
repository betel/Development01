package simfield;



import org.lwjgl.Sys;
import org.newdawn.slick.Color;

public class Status {

	public	int		fps;
	private long	lastFPS;
	DrawTTF draw;
	String str;

	public void initStatus(){
		lastFPS = getTime();
		draw = new DrawTTF();
		draw.initResource();
	}

	public long getTime(){
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	public void updateFPS(){
		if(getTime() - lastFPS > 1000){
			str = "FPS: " + Integer.toString(fps);
			draw.renderStr(100, 100, str, Color.green);

			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
}
