package simfield;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Base {

	static final int WIDTH	=	800;	//ウィンドウのサイズ
	static final int HEIGHT	=	600;
	private int		numberOfLife	= 20;
	private int 	numberOfRed		= 5;
	private float	x		=	400;		//オブジェクトの初期位置
	private float	y		=	300;
	private ArrayList<Life> life,lifeRed;

	public Base(){	//コンストラクタ
		life = new ArrayList<Life>();
		lifeRed = new ArrayList<Life>();
		createLifeSet(numberOfLife,numberOfRed);
	}

	public void start(){
		//ウィンドウの生成
		try{
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			//Display.setTitle("SimField");
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}

		initGL();

		//メインループ
		while(!Display.isCloseRequested()){
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			for(Life l : life){
				l.renderLife();
			}
			for(Life lr : lifeRed){
				lr.renderLife();
			}

			Display.update();	//オンスクリーンに反映
			Display.sync(60);	//FPSを60に固定
		}
	}
	//OpenGLの初期化
	void initGL(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	//指定された個数分だけライフを生成してArrayListに追加
	public void createLifeSet(int numberOfLife,int numberOfRed){
		int m = numberOfLife;
		int n = numberOfRed;
		for(int i=0; i<m; i++){
			life.add(new Life(x,y));
		}
		for(int i=0; i<n; i++){
			lifeRed.add(new LifeRed(x,y));
		}
	}
}
