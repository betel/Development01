package simfield;


import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;
/*
 * 受け取った文字列を指定した色と座標で描画する
 */

public class DrawTTF {
	private TrueTypeFont font;
	private boolean antiAlias = true;

	public DrawTTF(){
		init();
	}

	/*
	 * This method should be called once out of loop.
	 */
	public void init(){
		//Load font from ttfFile
		try{
			InputStream inputStream = ResourceLoader.getResourceAsStream("resSample/UbuntuMono-B.ttf");

			Font awtfont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtfont = awtfont.deriveFont(24f);
			font	= new TrueTypeFont(awtfont, antiAlias);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/*
	 * This method will be used in "Main" loop.
	 */
	public void drawStr(int x,int y,String str,Color c){
		Color.white.bind();
		font.drawString(x, y, str, c);
	}
}
