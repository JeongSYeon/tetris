package tetris_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

public class BattleFrame extends Frame{

	private MainBoard myboard = new MainBoard();					// 메인게임이 돌아가는 게임 패널
	private MainBoard yourboard = new MainBoard(); 					// 메인게임이 돌아가는 게임 패널 ( 상대방 )
	private NextblockBoard mynextblokcboard = new NextblockBoard(); // 게임 중 다음 블록을 보여주는 패널
	private NextblockBoard yournextblokcboard = new NextblockBoard(); // 상대방 게임 중 다음 블록을 보여주는 패널
	
	private Tetris t = new Tetris(myboard, mynextblokcboard); // 메인 게임 연산 .
	
	private Server s = new Server(myboard,yourboard);	// 서버 역할을한다. 
	
	// 생성자
	public BattleFrame() {

		setVisible(true);

		// 이 변수로 frame class 에서 이미지를 변환한다
		isMainFrame = true;

		// 뒤로가기 버튼 추가
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
		Next.setBounds(490, 100, 150, 100);
		add(Next);
		//맨위 블록 라인을 가려준다. 
		Guard.setBounds(90, 100, 300, 25);
		add(Guard);
		// 상대방 맨위 블록 라인을 가려준다. 
		yourGuard.setBounds(90, 100, 300, 25);
		add(yourGuard);
		
		// 메인 게임 패널 추가
		myboard.addKeyListener(t);
		myboard.requestFocus();
		myboard.setFocusable(true);
		myboard.setBounds(90, 100, 300, 600);
		add(myboard);
		// 다음 블록을 보여주는 패넬 추가
		mynextblokcboard.setBounds(490, 200, 150, 150);
		add(mynextblokcboard);
		//상대의 게임 패널 추가 
		yourboard.setBounds(890, 100, 300, 600);
		add(yourboard);
		yournextblokcboard.setBounds(640, 200, 150, 150);
		add(yournextblokcboard);
	}
}
