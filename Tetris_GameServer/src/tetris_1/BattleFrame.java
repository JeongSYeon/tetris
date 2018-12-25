package tetris_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

public class BattleFrame extends Frame{

	private MainBoard myboard = new MainBoard();					// ���ΰ����� ���ư��� ���� �г�
	private MainBoard yourboard = new MainBoard(); 					// ���ΰ����� ���ư��� ���� �г� ( ���� )
	private NextblockBoard mynextblokcboard = new NextblockBoard(); // ���� �� ���� ����� �����ִ� �г�
	private NextblockBoard yournextblokcboard = new NextblockBoard(); // ���� ���� �� ���� ����� �����ִ� �г�
	
	private Tetris t = new Tetris(myboard, mynextblokcboard); // ���� ���� ���� .
	
	private Server s = new Server(myboard,yourboard);	// ���� �������Ѵ�. 
	
	// ������
	public BattleFrame() {

		setVisible(true);

		// �� ������ frame class ���� �̹����� ��ȯ�Ѵ�
		isMainFrame = true;

		// �ڷΰ��� ��ư �߰�
		Return.setBounds(500, 510, 400, 120);
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
		Next.setBounds(490, 100, 150, 100);
		add(Next);
		//���� ��� ������ �����ش�. 
		Guard.setBounds(90, 100, 300, 25);
		add(Guard);
		// ���� ���� ��� ������ �����ش�. 
		yourGuard.setBounds(90, 100, 300, 25);
		add(yourGuard);
		
		// ���� ���� �г� �߰�
		myboard.addKeyListener(t);
		myboard.requestFocus();
		myboard.setFocusable(true);
		myboard.setBounds(90, 100, 300, 600);
		add(myboard);
		// ���� ����� �����ִ� �г� �߰�
		mynextblokcboard.setBounds(490, 200, 150, 150);
		add(mynextblokcboard);
		//����� ���� �г� �߰� 
		yourboard.setBounds(890, 100, 300, 600);
		add(yourboard);
		yournextblokcboard.setBounds(640, 200, 150, 150);
		add(yournextblokcboard);
	}
}
