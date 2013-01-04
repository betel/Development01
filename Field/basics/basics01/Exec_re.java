package basics01;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Exec_re {
	int width	= 100;
	int height	= 100;
	
	public void start(){
		try{
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
			return;
		}
		
		while(!Display.isCloseRequested()){
			Display.update();
		}
		
		Display.destroy();
	}
	
	public static void main(String[] args){
		new Exec_re().start();
	}
}
