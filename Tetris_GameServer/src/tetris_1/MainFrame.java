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

	private MainBoard mainboard = new MainBoard(); // 메인게임이 돌아가는 게임 패널
	private NextblockBoard nextblokcboard = new NextblockBoard(); // 게임 중 다음 블록을
																	// 보여주는 패널
	private NextblockBoard holdboard = new NextblockBoard();
	private Tetris t = new Tetris_Solo(mainboard, nextblokcboard,holdboard); // 메인 게임 연산 .
	
	// 생성자
	public MainFrame() {

		setVisible(true);

		// 이 변수로 frame class 에서 이미지를 변환한다
		isMainFrame = true;

		// 뒤로가기 버튼 추가
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

		// 메뉴바 추가
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

		// 다음 블록 라벨 추가
		Next.setBounds(800, 70, 150, 100);
		add(Next);
		// 스코어 라벨 추가
		Score.setBounds(100, 100, 200, 100);
		add(Score);
		// 콤보 라벨 추가
		Combo.setBounds(100, 250, 200, 100);
		add(Combo);
		// 삭제한 줄수 라인 라벨 추가
		Line.setBounds(100, 400, 200, 100);
		add(Line);
		Guard.setBounds(490, 100, 300, 25);
		add(Guard);

		// 점수를 게임중 갱신하여 프레임에 보여준다
		t.scorelabel.setBounds(100, 150, 200, 100);
		t.scorelabel.setForeground(new Color(255, 255, 255));
		t.scorelabel.setFont(new Font("고딕", Font.BOLD, 40));
		add(t.scorelabel);

		// 콤보를 게임중 갱신하여 프레임에 보여준다
		t.combolabel.setBounds(100, 300, 200, 100);
		t.combolabel.setForeground(new Color(255, 255, 255));
		t.combolabel.setFont(new Font("고딕", Font.BOLD, 40));
		add(t.combolabel);

		// 삭제한 줄수를 게임중 갱신하여 프레임에 보여준다
		t.linelabel.setBounds(100, 450, 200, 100);
		t.linelabel.setForeground(new Color(255, 255, 255));
		t.linelabel.setFont(new Font("고딕", Font.BOLD, 40));
		add(t.linelabel);

		// 메인 게임 패널 추가
		mainboard.addKeyListener(t);
		mainboard.requestFocus();
		mainboard.setFocusable(true);
		mainboard.setBounds(490, 100, 300, 600);
		add(mainboard);
		// 다음 블록을 보여주는 패넬 추가
		nextblokcboard.setBounds(800, 150, 150, 150);
		add(nextblokcboard);
		holdboard.setBounds(800, 400, 150, 150);
		add(holdboard);
	}
}
