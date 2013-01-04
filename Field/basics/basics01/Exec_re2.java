package basics01;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Exec_re2 {
	int width = 400;
	int height = 300;

	public void start(){
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
		} catch (LWJGLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		while(!Display.isCloseRequested()){
			Display.update();
		}
		Display.destroy();
	}

	public static void main(String[] args){
		new Exec().start();
	}
}
