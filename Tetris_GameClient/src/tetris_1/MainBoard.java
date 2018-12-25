package tetris_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainBoard extends JPanel { // ��Ʈ���� ������ �� �� �ִ� ���� ������

	private Image[] images = new Image[16];
	private Toolkit toolkit = getToolkit();

	//2���� �������̴� �� ������ ���� �̿��Ͽ� �̹����� ���� ��ȯ���ش�.
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

	// ������ : �̹����� �̹��� �迭�� �����Ѵ�.
	// 0 �� ���� �� ����Ų��. 
	// 1~7 �� ���� 7���� ����� ����Ų��.
	// 8 �� �������� ���� ��( ȸ�� ��� ) �� ����Ų��
	// 9~16 �� 1~7 ���� ����� �׸��ڸ� ������ ������ ��ϵ��̴�.
	public MainBoard() {
		for (int i = 0; i < 16; i++)
			images[i] = toolkit.getImage("src/image/MainBoard" + (i + 1) + ".png");
	}

	// �޼ҵ� ���� : ���� �������� ������ ���� �� �� �ְ� �Ѵ�
	// �Ķ���� ���� : col ���� ���° �� ����Ű����
	// �޼ҵ� �ϴ� �� : �г��� ���� ���̸� �޾ƿ� ���� ���� ���� ĭ 12�� ������ col ���� ����ų�� �ֵ��� �Ѵ�.
	public int getX(int col) {
		return col * (getWidth()) / 12;
	}

	// �޼ҵ� ���� : ���� �������� ������ ���� �� �� �ְ� �Ѵ�
	// �Ķ���� ���� : row ���� ���° �� ����Ű����
	// �޼ҵ� �ϴ� �� : �г��� ���� ���̸� �޾ƿ� ���� ���� ���� ĭ 22�� ������ row ���� ����ų�� �ֵ��� �Ѵ�.
	public int getY(int row) {
		return row * (getHeight()) / 22;
	}

	// �޼ҵ� ���� : ����Ʈ �Լ����� �� �޼ҵ尡 �Ҹ���. ��� �г��� ��ȯ �����ش�
	// �Ķ���� ���� : col row �� ���� ���� ������ ������� ����Ű���� 
	// �޼ҵ� �ϴ� �� : 2���� ���� �迭�� ���� �ٲ𶧸��� ������ 2���� �г� �迭�� �׸��� �ٽ� �׷��ش�.  
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
