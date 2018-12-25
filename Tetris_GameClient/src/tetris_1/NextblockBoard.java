package tetris_1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

// 메인 게임 패널과 매우 흡사하다. ( 모르는게 있다면 매인 패널 주석을 보면 된다 .)
public class NextblockBoard extends JPanel{
	
	private Image[] images = new Image[9];
	private Toolkit toolkit = getToolkit();

	int[][] board = new int[6][6];
	{
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (j == 0 ||j == 5 || i == 0 || i == 5) {
					board[i][j] = 8;
				} else {
					board[i][j] = 0;
				}
			}
		}
	}

	public NextblockBoard() {
		for (int i = 0; i < 9; i++)
			images[i] = toolkit.getImage("src/image/MainBoard" + (i + 1) + ".png");
	}

	public int getX(int col) {
		return col * (getWidth()) / 6;
	}

	public int getY(int row) {
		return row * (getHeight()) / 6;
	}

	public void drawCell(Graphics g, int col, int row) {
		g.drawImage(images[board[row][col]], getX(col), getY(row), (getWidth()) / 6, (getHeight()) / 6, this);
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		for (int row = 0; row < 6; ++row)
			for (int col = 0; col < 6; ++col)
				drawCell(g, row, col);
	}

}
