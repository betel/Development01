package slick2dBasics01;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class LoadingImage_notes {
	//イメージを保持する変数
	private Texture texture;
	/*
	 * 開始
	 */
	public void start(){
		initGL(800,600);	//OpenGLの初期化設定
		init();				//テクスチャの読み込み

		while(!Display.isCloseRequested()){
			glClear(GL_COLOR_BUFFER_BIT);
			render();

			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	/*
	 * OpenGLのみのパート
	 */
	public void initGL(int width, int height){
		try{
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
			Display.setVSyncEnabled(true);		//垂直同期を有効化
		}catch(LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}
		glEnable(GL_TEXTURE_2D);				//2Dテクスチャマッピングを有効化
		glClearColor(0.0f, 0.8f, 0.0f, 0.0f);	//初期の背景色を設定
		//アルファブレンドを有効化
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		/*
		 * ビューポート（描画範囲のようなもの）の設定。
		 * (0,0,width,height)とすると一番左下からウィンドウの幅の範囲でで描画する
		 */
		glViewport(0, 0, width, height);
		glMatrixMode(GL_MODELVIEW);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, 1, -1);	//視体積の設定
		glMatrixMode(GL_MODELVIEW);
	}

	/*
	 * 以下、Slick2dが関わるパート
	 */
	public void init(){
		try{
			//PNGファイルからテクスチャをロードする
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resSample/image.png"));

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	/*
	 * イメージを貼り付ける「四角形の板」を描いてテクスチャを貼る
	 */
	public void render(){
		Color.white.bind();		//板にデフォルトの色をセットする
		texture.bind();			//GL11.glBind(texture.getTextureID())でも可

		glBegin(GL_QUADS);		//四角形を描く。
			/*
			 * テクスチャ座標の指定。1を3に設定すると1/3になる
			 * イメージの角(0,0)に「イメージを貼る板の角(100,100)」を対応させる。
			 */
			glTexCoord2f(0,0);
			glVertex2f(100, 100);	//
			glTexCoord2f(1, 0);
			glVertex2f(100+texture.getTextureWidth(), 100);
			glTexCoord2f(1, 1);
			glVertex2f(100+texture.getTextureWidth(), 100+texture.getTextureHeight());
			glTexCoord2f(0, 1);
			glVertex2f(100, 100+texture.getTextureHeight());
		glEnd();
	}
	/*
	 * MainClass
	 */
	public static void main(String[] args){
		new LoadingImage_notes().start();
	}
}
