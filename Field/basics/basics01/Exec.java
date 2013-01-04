package basics01;

//ウィンドウの生成

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Exec {
	int width	= 300;
	int height	= 200;
	
	public void start(){
		//ウィンドウのサイズを指定して生成する
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
		} catch (LWJGLException e) {	//DisplayModeが存在しない時の例外処理
			e.printStackTrace();
			return;
		}
		//ループ処理。closeボタンが押されるまで
		while(!Display.isCloseRequested()){
			Display.update();
		}
		//ウィンドウの破棄
		Display.destroy();
	}
	//実行
	public static void main(String[] args){
		new Exec().start();
	}
}
