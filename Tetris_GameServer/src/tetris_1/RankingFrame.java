package tetris_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.Iterator;

import javax.swing.JLabel;

public class RankingFrame extends Frame {

	private Datalist data = new Datalist();								//��ŷ ������ �� �ҷ��´�
	private JLabel[] rank = new JLabel[10];								//1~10 �� �� 
	private JLabel[] name = new JLabel[data.getnameList().size()];		//10����� �̸� ��ŷ ��
	private JLabel[] score = new JLabel[data.getscoreList().size()];	//10����� ���� ��ŷ �� 
	private String text;												//��� ���� ���ڿ����� ������ ���� 

	public RankingFrame() {

		setVisible(true);

		// �� ������ frame class ���� �̹����� ��ȯ�Ѵ�
		isRankingFrame = true;

		//�ڷΰ��� ��ư �߰�
		Return.setBounds(900, 510, 400, 120);
		Return.setBorderPainted(false);
		Return.setContentAreaFilled(false);
		Return.setFocusPainted(false);
		Return.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				m.PlaySound(new File("src//music//button.wav"));
				Return.setIcon(returnOn);
			}

			public void mouseExited(MouseEvent e) {
				Return.setIcon(returnOff);
			}

			public void mouseClicked(MouseEvent e) {
				dispose();
				new FirstFrame();
			}
		});
		add(Return);

		//�޴��� �߰� 
		Menu.setBounds(0, 0, 1280, 720);
		Menu.setBorderPainted(false);
		Menu.setContentAreaFilled(false);
		Menu.setFocusPainted(false);
		Menu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				screenX = e.getX();
				screenY = e.getY();
			}
		});
		Menu.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - screenX, y - screenY);
			}

		});
		add(Menu);

		//1~10 ���� �� �߰�
		for (int i = 0; i < 10; i++) {
			if(i<9)
			text ="  "+(i+1)+"  �� ";
			else 
				text = ""+(i+1)+"  �� ";
				
			rank[i] = new JLabel(text);
		}
		for (int i = 0; i < 10; i++) {
			rank[i].setBounds(160, 190+(i*40), 400, 100);
			rank[i].setForeground(new Color(255, 255, 255));
			rank[i].setFont(new Font("���", Font.BOLD, 25));
			add(rank[i]);
		}
		
		// player name �� �߰� 
		for (int i = 0; i < data.getnameList().size() && i<10; i++) {
			text = data.getnameList().get(i);
			name[i] = new JLabel(text);
		}
		for (int i = 0; i < data.getnameList().size()&& i<10; i++) {
			name[i].setBounds(370, 190+(i*40), 400, 100);
			name[i].setForeground(new Color(255, 255, 255));
			name[i].setFont(new Font("���", Font.BOLD, 25));
			add(name[i]);
		}
		
		// score �� �߰� 
		for (int i = 0; i < data.getscoreList().size()&& i<10; i++) {
			text = data.getscoreList().get(i);
			score[i] = new JLabel(text);
		}
		for (int i = 0; i < data.getscoreList().size()&& i<10; i++) {
			score[i].setBounds(810, 190+(i*40), 400, 100);
			score[i].setForeground(new Color(255, 255, 255));
			score[i].setFont(new Font("���", Font.BOLD, 25));
			add(score[i]);
		}
		
	}

}
