package simfield;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

/*
 * 受け取った文字列をTTFを使用して描画する
 * Colorクラスをawt,lwjgl,slic2dのどれにするかでバグが出るかも
 * InputStreamのクラスもチェック。
 */
public class Draw {
	private TrueTypeFont font;
	private boolean antiAlias = true;

	public Draw(){
		//initGL();
		initDraw();
	}

	public void render(int x, int y, String str, Color c){
		Color.white.bind();
		font.drawString(x, y, str, c);
	}
	
	public void initDraw(){
		InputStream input = ResourceLoader.getResourceAsStream("res/UbuntuMono.ttf");

		try {
			Font awtfont = Font.createFont(Font.TRUETYPE_FONT, input);
			awtfont =	awtfont.deriveFont(24f);
			font	=	new TrueTypeFont(awtfont, antiAlias);
		} catch (FontFormatException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	/*
	public void initGL(){
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		GL11.glClearDepth(1);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_COLOR);

	}
	*/
}
