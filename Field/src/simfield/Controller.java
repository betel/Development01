package simfield;

public class Controller{
	public boolean flg;

	public void start(){
		Base base = new Base();
		Thread t = new Thread(base);
		if(flg){
			t.start();
		}
	}
}
