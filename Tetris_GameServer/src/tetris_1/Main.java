package tetris_1;

import java.io.File;

public class Main {

	final static int WIDTH = 1280;
	final static int HEIGHT = 720;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FirstFrame();	//첫번째 화면 띄어준다 

		Music m = new Music(); // 배경음
		m.run();

	}

}
