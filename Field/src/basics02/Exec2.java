package basics02;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Assignment01:
 * マウスが右クリックされた位置を検出して表示する。
 * Assignment02:
 * Aが押されたら"A Pressed"と表示し、
 * Bが離れたら"B Released"と表示する。
 */

public class Exec2 {
	int w = 300;
	int h = 200;
	public void start(){
		try{
			Display.setDisplayMode(new DisplayMode(w,h));
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}
		while(!Display.isCloseRequested()){
			pollInput();
			Display.update();
		}
		Display.destroy();
	}
	
	public void pollInput(){
		int x = Mouse.getX();
		int y = Mouse.getY();
		if(Mouse.isButtonDown(1)){
			System.out.printf("Mouse Right Clicked @ (%d,%d)\n",x,y);
		}
		
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState()){
				if(Keyboard.getEventKey() == Keyboard.KEY_A){
					System.out.println("A Pressed");
				}
			}else{
				if(Keyboard.getEventKey() == Keyboard.KEY_B){
					System.out.println("B Released");
				}
			}
		}
	}
	
	public static void main(String[] args){
		new Exec2().start();
	}
}
