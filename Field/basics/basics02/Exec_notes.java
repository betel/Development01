package basics02;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Exec_notes {

	int width = 300;
	int height = 200;

	public void start(){
		//ウィンドウの生成
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);	//returnでも良い？
		}
		//ループ
		while(!Display.isCloseRequested()){
			pollInput();
			Display.update();
		}
		//ウィンドウの破棄
		Display.destroy();
	}

	//ボタン類のアクション
	public void pollInput(){
		//マウスカーソルの位置を取得
		int x = Mouse.getX();
		int y = Mouse.getY();
		//左クリックした位置を表示する
		if(Mouse.isButtonDown(0)){
			System.out.printf("LeftClick ON @(%d,%d)\n",x,y);
		}

		while(Keyboard.next()){
			//getEventKeyState()は、ボタンが押されたらtrue,離したらfalseを返す
			if(Keyboard.getEventKeyState()){	//とあるキーが押された時
				//もしそのキーがAであったら
				if(Keyboard.getEventKey() == Keyboard.KEY_A){
					System.out.println("A Key Pressed");
				}
			}else{								//とあるキーが離れた時
				//もしそのキーがAであったら
				if(Keyboard.getEventKey() == Keyboard.KEY_A){
					System.out.println("A Key Released");
				}
			}
		}

	}

	//実行
	public static void main(String[] args){
		new Exec_notes().start();
	}
}
