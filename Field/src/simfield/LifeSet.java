package simfield;

import java.util.ArrayList;

public class LifeSet {

	private ArrayList<Life> aSetOfLife;

	public LifeSet(Life life,int numberOfLife){	//コンストラクタ
		aSetOfLife = new ArrayList<Life>();
		initLifeSet(life,numberOfLife);
	}

	private void initLifeSet(Life life,int numberOfLife){
		aSetOfLife.clear();
		int n = numberOfLife;
		for(int i=0; i<n; i++){
			life = new Life(i*3 + 200,300);
			aSetOfLife.add(life);
		}
	}
	public ArrayList<Life> getArray(){
		return aSetOfLife;
	}
}
