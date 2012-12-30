package simfield;

import org.lwjgl.opengl.GL11;

public class Life02 extends Life{

	private float x,y;

	public Life02(float x, float y){
		super(x,y);
		this.x = x;
		this.y = y;
	}
	public void render(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glColor3f(0.1f, 0.8f, 0.1f);

		GL11.glPushMatrix();
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f(x-10, y-10);
				GL11.glVertex2f(x+10, y-10);
				GL11.glVertex2f(x+10, y+10);
				GL11.glVertex2f(x-10, y+10);
			GL11.glEnd();
		GL11.glPopMatrix();
	}
}
