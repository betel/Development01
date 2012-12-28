package simfield;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Base {

	private int freq	=	100;	//更新頻度(ミリ秒)
	private int width	=	800;	//ウィンドウのサイズ
	private int height	=	600;
	private float x		=	400;	//オブジェクトの初期位置
	private float y		=	300;
	long lastFrameTime;
	long lastTime;

	public void start(){
		//ウィンドウの生成
		try{
			Display.setDisplayMode(new DisplayMode(width,height));
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
		Life01 life2 = new Life01(x+50,y);

		//メインループ
		while(!Display.isCloseRequested()){

			renderLife(life1);
			renderLife(life2);

			Display.update();	//オンスクリーンに反映
			Display.sync(60);	//FPSを60に固定
		}
	}


	//描画処理
	void renderLife(Life01 life){
		int delta = getDelta();

		if((getTime()-lastTime) > freq){
			life.updateLife(delta,width,height);
			life.render();
			lastTime += freq;	//speed秒経過したことを記録する
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
