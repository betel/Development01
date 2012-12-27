package simfield;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Base {

	private int width	=	800;	//ウィンドウのサイズ
	private int height	=	600;
	private int x		=	400;	//オブジェクトの初期位置
	private int y		=	300;

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

		//メインループ
		while(!Display.isCloseRequested()){

			renderLife();
			Display.update();	//オンスクリーンに反映
			Display.sync(60);	//FPSを60に固定
		}
	}

	//OpenGLの初期化
	void initGL(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	//描画処理
	void renderLife(){
		Life01 life01 = new Life01(x,y);
		life01.render();
	}

}
