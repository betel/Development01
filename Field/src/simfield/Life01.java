package simfield;

import org.lwjgl.opengl.GL11;

public class Life01 {

	private float x,y;
	private float velocity = 0.03f;
	int direction;

	//コンストラクタ
	public Life01(float x, float y){
		this.x = x;
		this.y = y;
	}

	//メソッド
	//描画
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
	//ライフの向かう方向（1,2,3,4の4方向。0は移動しない）
	public void judgeDirection(){
		direction = (int)(5*Math.random());
	}
	//移動（Baseクラスからデルタ時間を取得）
	public void updateLife(int delta){

		if(direction==0) return;
		if(direction==1) x -= velocity*delta;
		if(direction==2) y += velocity*delta;
		if(direction==3) x += velocity*delta;
		if(direction==4) y -= velocity*delta;

		//移動範囲の制限
		if(x<0) x=0;
		if(x>Base.WIDTH)  x=Base.WIDTH;
		if(y<0) y=0;
		if(y>Base.HEIGHT) y=Base.HEIGHT;
	}
}
