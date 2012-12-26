package simfield;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Panel {

	private int width	=	400;	//ウィンドウのサイズ
	private int height	=	300;

	public void start(){
		//ウィンドウの生成
		try{
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.setTitle("SimField");
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
			return;
		}

		try{
			//OpenGLの設定
			glEnable(GL_CULL_FACE);	//ポリゴンの片面表示有効化
			glCullFace(GL_BACK);	//表のみ表示

			/**
			//設定切り替え
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0,width,0,height,0,0);	//視体積の指定
			//設定切り替え
			glMatrixMode(GL_MODELVIEW);
			**/

			//ループ
			while(!Display.isCloseRequested()){
				glClear(GL_COLOR_BUFFER_BIT);	//画面初期化
				Display.update();				//オンスクリーンに反映
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			Display.destroy();
		}
	}

	public static void main(String[] args){
		new Panel().start();
	}
}
