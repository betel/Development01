package basics04;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Timing_notes {

	/**四角形の初期位置*/
	float x = 400, y = 300;
	/**四角形の初期回転位置*/
	float rotation = 0;
	/**最終フレームの時間*/
	long lastFrame;
	/**FPSの値*/
	int fps;
	/**最後のfps*/
	long lastFPS;

	public void start(){
		try{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}

		//初期化
		initGL();	//OpenGLの初期化
		getDelta(); //最終フレームを初期化するため、ループ前に1回だけ呼び出す
		lastFPS = getTime();	//fpsタイマーを初期化するためにループ前に呼び出す

		//メインループ
		while(!Display.isCloseRequested()){
			int delta = getDelta();

			update(delta);
			renderGL();

			Display.update();
			Display.sync(60);	//fpsを60に固定
		}
		Display.destroy();
	}

	//
	public void update(int delta){
		/*
		 * 以下の0.15f、0.35fという数値が小さいほど移動・回転時に遅くなめらかに動く。
		 * 速さと思って良い。
		 */
		//四角形の回転
		rotation += 0.1f * delta;
		//キーボードで四角形の移動ができるようにする
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))	x -= 0.35f * delta;
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))	x += 0.35f * delta;
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))		y += 0.35f * delta;
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))	y -= 0.35f * delta;
		//四角形が画面外にでないように制限する
		if(x<0) x = 0;
		if(x>800) x = 800;
		if(y<0) y = 0;
		if(y>600) y = 600;

		updateFPS();
	}

	//最終フレームから何ミリ秒経ったかを計算する(1フレームにかかった時間とも言える)
	public int getDelta(){
		long time = getTime();
		int delta = (int)(time - lastFrame);
		lastFrame = time;

		return delta;
	}

	//システムの時刻をミリ秒単位で取得
	public long getTime(){
		return (Sys.getTime() * 1000)	/ Sys.getTimerResolution();
	}

	//FPSタイマーを更新する
	public void updateFPS(){
		//1秒経過したら
		if(getTime() - lastFPS > 1000){
			Display.setTitle("FPS: " + fps);	//タイトルバーにFPSを表示
			fps = 0;			//fpsを初期化
			lastFPS += 1000;	//1秒経過したことを記録する。
		}
		fps++;	//上のif文が実行されるまで（1秒経つまで）フレーム数をカウントする
	}

	//OpenGL初期化をメソッドにまとめたもの。
	public void initGL(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,800,0,600,1,-1);
		glMatrixMode(GL_MODELVIEW);
	}

	//描画
	public void renderGL(){
		//スクリーンのクリア
		glClear(GL_COLOR_BUFFER_BIT);
		//色指定
		glColor3f(1.0f,0.3f,0.6f);

		//四角形の描画
		glPushMatrix();
			//座標系の平行移動
			glTranslatef(x,y,0);			//座標の原点がキー入力に追随する
			glRotatef(rotation,0f,0f,1f);	//Z軸を中心にrotation度だけ回転させる
			glTranslatef(-x,-y,0);

			//頂点の指定
			glBegin(GL_QUADS);
				glVertex2f(x-20,y-20);
				glVertex2f(x+20,y-20);
				glVertex2f(x+20,y+20);
				glVertex2d(x-20,y+20);
			glEnd();

		glPopMatrix();
	}

	//実行
	public static void main (String[] args){
		new Timing_notes().start();
	}
}
