package basics03;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Quad_rev01 {
	public void start(){
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.setTitle("Draw a Quad");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		//init OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,800,0,600,1,-1);
		glMatrixMode(GL_MODELVIEW);
		
		//mainLoop
		while(!Display.isCloseRequested()){
			render();
			Display.update();
		}
		Display.destroy();
	}
	
	public void render(){
		glClear(GL_COLOR_BUFFER_BIT);
		glColor3f(0.44f,0.8f,0.1f);
		glBegin(GL_QUADS);
			glVertex2f(100,100);
			glVertex2f(100+100,100);
			glVertex2f(100+100,100+100);
			glVertex2f(100,100+100);
		glEnd();
	}
	
	public static void main(String[] args){
		new Quad_rev01().start();
	}
}
