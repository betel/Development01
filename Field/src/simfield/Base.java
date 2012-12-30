package simfield;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Base {

	static final int FREQ	=	200;	//更新頻度(ミリ秒)
	static final int WIDTH	=	800;	//ウィンドウのサイズ
	static final int HEIGHT	=	600;
	private float x		=	400;		//オブジェクトの初期位置
	private float y		=	300;
	private long lastFrameTime;
	private long lastTime;

	public void start(){
		//ウィンドウの生成
		try{
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			Display.setTitle("SimField");
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}

		//初期化
		initGL();
		getDelta();
		lastTime = getTime();
		Life01 life1 = new Life01(x,y);	//ライフの配置

		//メインループ
		while(!Display.isCloseRequested()){

			renderLife(life1);

			Display.update();	//オンスクリーンに反映
			Display.sync(60);	//FPSを60に固定
		}
	}

	//描画処理
	void renderLife(Life01 life){
		int delta = getDelta();

		if((getTime()-lastTime) > FREQ){
			life.judgeDirection();
			lastTime += FREQ;	//freq秒経過したことを記録する
		}
		life.updateLife(delta);
		life.render();
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
