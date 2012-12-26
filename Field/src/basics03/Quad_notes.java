package basics03;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
/**
 * 
 * GL11をstaticインポートする
 * 描画メソッドをrender()とする。
 * 
 *
 */

public class Quad_notes {
	
	public void start(){
		//ウィンドウの生成
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		//OpenGLの初期化
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,800,0,600,1,-1);
		glMatrixMode(GL_MODELVIEW);
		
		//メインループ
		while(!Display.isCloseRequested()){
			render();
			Display.update();
		}
		
		//ウィンドウの破棄
		Display.destroy();
	}
	//描画メソッド
	public void render(){
		//スクリーンのクリア
		glClear(GL_COLOR_BUFFER_BIT);
		//色指定
		glColor3f(0.2f,0.5f,1.0f);
		//描画部分
		glBegin(GL_QUADS);
			glVertex2f(100,100);
			glVertex2f(100+100,100);
			glVertex2f(100+100,100+100);
			glVertex2f(100,100+100);
		glEnd();	
	}
	
	//実行
	public static void main(String[] args){
		new Quad_notes().start();
	}
}
