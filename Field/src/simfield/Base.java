package simfield;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Base {

	static final int WIDTH	=	800;	//ウィンドウのサイズ
	static final int HEIGHT	=	600;
	private float x		=	400;		//オブジェクトの初期位置
	private float y		=	300;
	private long lastFrameTime;
	private ArrayList<Life> life;

	//コンストラクタ
	public Base(){
		getDelta();
		life = new ArrayList<Life>();

	}

	/**
	public void createLifeSet(){
		for(int i=0; i<)
	}
	**/
	public void start(){
		//ウィンドウの生成
		try{
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			//Display.setTitle("SimField");
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}
		initGL();
		//fortest
		Life life1 = new Life(x,y);	//ライフの配置
		Life life2 = new Life(x+100,y+100);

		//メインループ
		while(!Display.isCloseRequested()){
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			life1.renderLife();
			life2.renderLife();
			//forTest
			Display.setTitle("l1-x=" + (int)life1.x + ",\t l1y=" + (int)life1.y);
			Display.update();	//オンスクリーンに反映
			Display.sync(60);	//FPSを60に固定
		}
	}
	//正確な時刻を得る
	public long getTime(){
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	//デルタ時間を得る
	public int getDelta(){
		long	time	=	getTime();
		int		delta	=	(int)(time - lastFrameTime);
		lastFrameTime	=	time;

		return delta;
	}
	//OpenGLの初期化
	void initGL(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
}
