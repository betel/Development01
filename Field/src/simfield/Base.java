package simfield;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Base{

	static final int WIDTH	=	800;	//ウィンドウのサイズ
	static final int HEIGHT	=	600;
	static int		numberOfLife	= 30;
	static int	 	numberOfRed		= 20;
	static int		numberOfBlue	= 5;
	private float	x		=	400;		//オブジェクトの初期位置
	private float	y		=	300;
	public Status status;
	//private Life life,lifeRed,lifeBlue;
	private ArrayList<Life> life,lifeRed,lifeBlue;
	private LifeSet lifeSet,lifeRedSet,lifeBlueSet;

	public Base(){	//コンストラクタ
		/**
		lifeSet = new LifeSet(life,numberOfLife);
		lifeRedSet = new LifeSet(lifeRed,numberOfRed);
		lifeBlueSet = new LifeSet(lifeBlue,numberOfBlue);
		**/
		life = new ArrayList<Life>();
		lifeRed = new ArrayList<Life>();
		lifeBlue = new ArrayList<Life>();
		status = new Status();
		createLifeSet(numberOfLife,numberOfRed,numberOfBlue);
	}

	public void start(){

		//init();
		initGL(WIDTH,HEIGHT);

		//メインループ
		while(!Display.isCloseRequested()){
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			/**
			for(Life l : lifeSet.getArray()){
				l.renderLife();
			}
			for(Life lr: lifeRedSet.getArray()){
				lr.renderLife();
			}
			for(Life lb: lifeBlueSet.getArray()){
				lb.renderLife();
			}
			**/
			for(Life l : life){
				l.renderLife();
			}
			for(Life lr: lifeRed){
				lr.renderLife();
			}
			for(Life lb: lifeBlue){
				lb.renderLife();
			}
			status.updateFPS();
			Display.update();	//オンスクリーンに反映
			Display.sync(100);	//FPSを60に固定
		}
		Display.destroy();
	}
	/**初期化
	public void init(){

	}
	*/

	//OpenGL関係の初期化
	void initGL(int width, int height){
		//ウィンドウの生成
		try{
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.setTitle("SimField");
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		GL11.glClearDepth(1);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glViewport(0, 0, width, height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, 0, height, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	//指定された分だけライフを生成する
	/*
	 * これは別のクラスで行ったほうがいいかも
	 */
	public void createLifeSet(int numberOfLife,int numberOfRed,int numberOfBlue){
		for(int i=0; i<numberOfLife; i++){
			life.add(new Life(x,y));
		}
		for(int i=0; i<numberOfRed; i++){
			lifeRed.add(new LifeRed(x,y));
		}
		for(int i=0; i<numberOfBlue; i++){
			lifeBlue.add(new LifeBlue(x,y));
		}
	}
}
