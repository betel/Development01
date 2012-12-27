package basics04;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * assignment01:
 * GL11のstaticインポートを使用せずにコードを書いてみる
 * assignment02:
 * 三角形のオブジェクトを描画する。任意のボタンで回転できるようにする。
 * assignment03:
 * オブジェクトの中心位置を取得してタイトルバーに表示してみる
 * @author betel
 *
 */

public class Timing_rev01 {

	int	width	=	800;
	int height	=	600;
	long lastFPS;
	long lastFrameTime;
	int fps;
	float x = 400, y = 300;
	float rotation = 0;

	public void start(){
		//ウィンドウの生成
		try{
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}

		//初期化
		initGL();
		getDelta();
		lastFPS = getTime();

		//メインループ
		while(!Display.isCloseRequested()){
			int delta = getDelta();
			update(delta);
			render();

			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}

	//正確な時刻をミリ秒で取得
	public long getTime(){
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	//時刻の差分(整数)を取得
	public int getDelta(){
		long time	=	getTime();
		int delta	=	(int)(time - lastFrameTime);
		lastFrameTime	=	time;
		return delta;
	}
	//オブジェクトの回転・移動・キー操作
	public void update(int delta){

		if(Keyboard.isKeyDown(Keyboard.KEY_R))	rotation += 0.5 * delta;
		if(Keyboard.isKeyDown(Keyboard.KEY_E))	rotation -= 0.5 * delta;
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) 	x -= 0.35f * delta;
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))	x += 0.35f * delta;
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))		y += 0.35f * delta;
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))	y -= 0.35f * delta;

		if(x<0) x=0;
		if(x>800) x=800;
		if(y<0) y=0;
		if(y>600) y=600;

		//updateFPS();
		showPlace();
	}
	//FPSのカウント
	public void updateFPS(){
		if((getTime() - lastFPS)>1000){
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	//オブジェクトの位置をタイトルバーに表示する
	public void showPlace(){
		Display.setTitle("(" + (int)x + "," + (int)y + ")");
	}
	//OpenGLの初期化
	public void initGL(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	//描画
	public void render(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glColor3f(0.1f, 0.5f, 0.3f);

		GL11.glPushMatrix();

			GL11.glTranslatef(x, y, 0);
			GL11.glRotatef(rotation, 0, 0, 1f);
			GL11.glTranslatef(-x, -y, 0);

			GL11.glBegin(GL11.GL_TRIANGLES);
				GL11.glVertex2d(x-50, y-50 * 1/Math.sqrt(3));
				GL11.glVertex2d(x+50, y-50 * 1/Math.sqrt(3));
				GL11.glVertex2d(x, y+50 * 2*Math.sqrt(3)/3);
				//GL11.glVertex2d(x-20, y+20);
			GL11.glEnd();
		GL11.glPopMatrix();
	}
	//実行
	public static void main(String[] args){
		new Timing_rev01().start();
	}

}
