package tetris_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends Frame {

	private MainBoard mainboard = new MainBoard(); // ���ΰ����� ���ư��� ���� �г�
	private NextblockBoard nextblokcboard = new NextblockBoard(); // ���� �� ���� �����
																	// �����ִ� �г�
	private NextblockBoard holdboard = new NextblockBoard();
	private Tetris t = new Tetris_Solo(mainboard, nextblokcboard,holdboard); // ���� ���� ���� .
	
	// ������
	public MainFrame() {

		setVisible(true);

		// �� ������ frame class ���� �̹����� ��ȯ�Ѵ�
		isMainFrame = true;

		// �ڷΰ��� ��ư �߰�
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

		// �޴��� �߰�
		Menu.setBounds(0, 0, 1280, 720);
		Menu.setBorderPainted(false);
		Menu.setContentAreaFilled(false);
		Menu.setFocusPainted(false);
		Menu.setFocusable(false);
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

		// ���� ��� �� �߰�
		Next.setBounds(800, 70, 150, 100);
		add(Next);
		// ���ھ� �� �߰�
		Score.setBounds(100, 100, 200, 100);
		add(Score);
		// �޺� �� �߰�
		Combo.setBounds(100, 250, 200, 100);
		add(Combo);
		// ������ �ټ� ���� �� �߰�
		Line.setBounds(100, 400, 200, 100);
		add(Line);
		Guard.setBounds(490, 100, 300, 25);
		add(Guard);

		// ������ ������ �����Ͽ� �����ӿ� �����ش�
		t.scorelabel.setBounds(100, 150, 200, 100);
		t.scorelabel.setForeground(new Color(255, 255, 255));
		t.scorelabel.setFont(new Font("���", Font.BOLD, 40));
		add(t.scorelabel);

		// �޺��� ������ �����Ͽ� �����ӿ� �����ش�
		t.combolabel.setBounds(100, 300, 200, 100);
		t.combolabel.setForeground(new Color(255, 255, 255));
		t.combolabel.setFont(new Font("���", Font.BOLD, 40));
		add(t.combolabel);

		// ������ �ټ��� ������ �����Ͽ� �����ӿ� �����ش�
		t.linelabel.setBounds(100, 450, 200, 100);
		t.linelabel.setForeground(new Color(255, 255, 255));
		t.linelabel.setFont(new Font("���", Font.BOLD, 40));
		add(t.linelabel);

		// ���� ���� �г� �߰�
		mainboard.addKeyListener(t);
		mainboard.requestFocus();
		mainboard.setFocusable(true);
		mainboard.setBounds(490, 100, 300, 600);
		add(mainboard);
		// ���� ����� �����ִ� �г� �߰�
		nextblokcboard.setBounds(800, 150, 150, 150);
		add(nextblokcboard);
		holdboard.setBounds(800, 400, 150, 150);
		add(holdboard);
	}
}
