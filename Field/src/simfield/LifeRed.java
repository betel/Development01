package simfield;

import org.lwjgl.opengl.GL11;

public class LifeRed extends Life{

	public LifeRed(float x, float y){	//コンストラクタ
		super(x,y);
		super.freq 	= 220;
		super.velocity = 0.3f;
		super.side	=	8/2;
	}
	@Override
	public void setLifeColor(){
		GL11.glColor3f(0.8f, 0.1f, 0.1f);
	}
}