package tetris_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainBoard extends JPanel { // 테트리스 게임을 할 수 있는 메인 보드판

	private Image[] images = new Image[16];
	private Toolkit toolkit = getToolkit();

	//2차원 보드판이다 이 보드의 값을 이용하여 이미지를 쉽게 변환해준다.
	int[][] board = new int[22][12];
	{
		for (int i = 0; i < 22; i++) {
			for (int j = 0; j < 12; j++) {
				if (j == 0 || j == 11 || i == 21) {
					board[i][j] = 8;
				} else {
					board[i][j] = 0;
				}
			}
		}
	}

	// 생성자 : 이미지를 이미지 배열에 저장한다.
	// 0 은 공백 을 가리킨다. 
	// 1~7 은 각각 7가지 블록을 가리킨다.
	// 8 은 움직을수 없는 벽( 회색 블록 ) 을 가리킨다
	// 9~16 은 1~7 각각 블록의 그림자를 구현한 불투명 블록들이다.
	public MainBoard() {
		for (int i = 0; i < 16; i++)
			images[i] = toolkit.getImage("src/image/MainBoard" + (i + 1) + ".png");
	}

	// 메소드 정의 : 게임 보드판을 나누어 지정 할 수 있게 한다
	// 파라미터 정의 : col 가로 몇번째 를 가리키는지
	// 메소드 하는 일 : 패널의 가로 길이를 받아와 메인 게임 가로 칸 12로 나누어 col 값이 가리킬수 있도록 한다.
	public int getX(int col) {
		return col * (getWidth()) / 12;
	}

	// 메소드 정의 : 게임 보드판을 나누어 지정 할 수 있게 한다
	// 파라미터 정의 : row 세로 몇번째 를 가리키는지
	// 메소드 하는 일 : 패널의 세로 길이를 받아와 메인 게임 세로 칸 22로 나누어 row 값이 가리킬수 있도록 한다.
	public int getY(int row) {
		return row * (getHeight()) / 22;
	}

	// 메소드 정의 : 페인트 함수에서 이 메소드가 불린다. 계속 패널을 변환 시켜준다
	// 파라미터 정의 : col row 는 각각 가로 세로의 몇번쨰를 가리키는지 
	// 메소드 하는 일 : 2차원 보드 배열의 값이 바뀔때마다 각각의 2차원 패널 배열에 그림을 다시 그려준다.  
	public void drawCell(Graphics g, int col, int row) {
		g.drawImage(images[board[row][col]], getX(col), getY(row), (getWidth()) / 12, (getHeight()) / 22, this);
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight()); 
		for (int row = 0; row < 12; ++row)
			for (int col = 0; col < 22; ++col)
				drawCell(g, row, col);
	}
}
