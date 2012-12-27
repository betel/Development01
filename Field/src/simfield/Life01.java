package simfield;

import org.lwjgl.opengl.GL11;

public class Life01 {

	private float x,y;

	//コンストラクタ
	public Life01(float x, float y){
		this.x = x;
		this.y = y;
	}

	//メソッド
	//描画
	public void render(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glColor3f(0.1f, 0.4f, 0.1f);

		GL11.glPushMatrix();
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f(x-10, y-10);
				GL11.glVertex2f(x+10, y-20);
				GL11.glVertex2f(x+10, y+10);
				GL11.glVertex2f(x-10, y+10);
			GL11.glEnd();
		GL11.glPopMatrix();
	}
	//ライフの向かう方向（0,1,2,3の4方向）
	public int getDirection(){
		return (int)(4*Math.random());
	}
}
