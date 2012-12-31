package simfield;

import org.lwjgl.opengl.GL11;

public class LifeRed extends Life{

	public LifeRed(float x, float y){	//コンストラクタ
		super(x,y);
	}
	@Override
	public void setLifeColor(){
		GL11.glColor3f(0.8f, 0.1f, 0.1f);
	}
}
