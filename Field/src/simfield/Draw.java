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
		initDraw();
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
	public void render(int x, int y, String str, Color c){
		Color.white.bind();
		font.drawString(x, y, str, c);
	}
}
