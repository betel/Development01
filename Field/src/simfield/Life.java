package simfield;

import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

public class Life {

	protected	int freq = 150;
	protected	float	velocity = 0.05f;
	protected	int	side = 10/2;
	private		float	x,y;
	private int		direction;
	private long	lastFrameTime;
	private long	lastTime;

	public Life(float x, float y){	//コンストラクタ
		this.x = x;
		this.y = y;
		getDelta();
		lastTime = getTime();
	}

	//メソッド
	//描画
	public void render(){

		setLifeColor();

		//GL11.glPushMatrix();
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2f(x-side, y-side);
				GL11.glVertex2f(x+side, y-side);
				GL11.glVertex2f(x+side, y+side);
				GL11.glVertex2f(x-side, y+side);
			GL11.glEnd();
		//GL11.glPopMatrix();

	}
	//ライフの色を決める
	public void setLifeColor(){
		GL11.glColor4f(0.1f, 0.8f, 0.1f, 0.9f);
	}
	//ライフの向かう方向（1,2,3,4,5,6,7,8の8方向。0,9-14は移動しない）
	public void judgeDirection(){
		direction = (int)(15*Math.random());

	}
	//移動（Baseクラスからデルタ時間を取得）
	public void updateLife(int delta){

		if(direction==0||direction==9||direction==10
				||direction==11||direction==12||direction==13
				||direction==14) return;
		if(direction==1){ x -= velocity*delta; }
		if(direction==2){ x -= velocity*delta; y += velocity*delta; }
		if(direction==3){ y += velocity*delta; }
		if(direction==4){ x += velocity*delta; y += velocity*delta; }
		if(direction==5){ x += velocity*delta; }
		if(direction==6){ x += velocity*delta; y -= velocity*delta; }
		if(direction==7){ y -= velocity*delta; }
		if(direction==8){ x -= velocity*delta; y -= velocity*delta; }

		//移動範囲の制限
		if(x<0) x=0;
		if(x>Base.WIDTH)  x=Base.WIDTH;
		if(y<0) y=0;
		if(y>Base.HEIGHT) y=Base.HEIGHT;
	}
	//正確な時刻を得る
	public long getTime(){
		return (Sys.getTime() * 1000 / Sys.getTimerResolution());
	}
	//デルタ時間を得る
	public int getDelta(){
		long	time	=	getTime();
		int		delta	=	(int)(time -lastFrameTime);
		lastFrameTime	=	time;
		return delta;
	}
	//ライフのアニメーション
	public void renderLife(){
		int delta = getDelta();

		if((getTime() - lastTime) > freq){	//freqミリ秒経ったら
			judgeDirection();				//方向を決めて
			lastTime += freq;				//freqミリ秒経ったことを記録する
		}
		updateLife(delta);					//ライフの移動
		render();							//移動の描画
	}
}
