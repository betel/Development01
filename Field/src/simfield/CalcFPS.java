package simfield;

/*
 * getFPS()でFPSの値を一秒ごとに返す。
 * getFPSString()で文字列にして返す。
 */
import org.lwjgl.Sys;

public class CalcFPS {

	private int		fps;
	private int		count;
	private long	lastFPS;

	public CalcFPS(){
		initFPS();
	}

	public void initFPS(){
		lastFPS = getTime();
	}
	public long getTime(){
		return (Sys.getTime() * 1000)/Sys.getTimerResolution();
	}

	public int getFPS(){

		if(getTime() - lastFPS > 1000){
			fps = count;
			count = 0;
			lastFPS += 1000;
			return fps;
		}
		count++;
		return fps;
	}

	public String getFPSString(){
		return "FPS: " + Integer.toString(getFPS());
	}
}
