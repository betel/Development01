package simfield;

import org.lwjgl.opengl.GL11;

public class LifeBlue extends LifeRed{

	public LifeBlue(float x, float y){
		super(x,y);
		super.freq = 2000;
		super.velocity = 0.01f;
		super.side = 20/2;
	}
	@Override
	public void setLifeColor(){
		GL11.glColor3f(0.1f, 0.1f, 0.8f);
	}
}
