package tetris_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame {

	Music m = new Music();

	//더블 버퍼링을 위해서 사용하는 두 변수 
	protected Image BG_Image;
	protected Graphics BG_Graphic;

	// 각각 처음 , 메인게임 , 랭킹 화면의 배경
	protected Image First_BG = new ImageIcon(Main.class.getResource("../image/FirstBG.jpg")).getImage();
	protected Image Main_BG = new ImageIcon(Main.class.getResource("../image/MainBG.jpg")).getImage();
	protected Image Ranking_BG = new ImageIcon(Main.class.getResource("../image/RankingBG.jpg")).getImage();

	// 시작버튼, 랭킹버튼, 종료버튼 1,2 , 뒤로가기 이미지
	protected ImageIcon StartOff = new ImageIcon(Main.class.getResource("../image/start.png"));
	protected ImageIcon StartOn = new ImageIcon(Main.class.getResource("../image/startOn.png"));
	protected ImageIcon RankingOff = new ImageIcon(Main.class.getResource("../image/Ranking.png"));
	protected ImageIcon RankingOn = new ImageIcon(Main.class.getResource("../image/RankingOn.png"));
	protected ImageIcon ExitOff = new ImageIcon(Main.class.getResource("../image/Exit.png"));
	protected ImageIcon ExitOn = new ImageIcon(Main.class.getResource("../image/exitOn.png"));
	protected ImageIcon exitOff = new ImageIcon(Main.class.getResource("../image/exit2.png"));
	protected ImageIcon exitOn = new ImageIcon(Main.class.getResource("../image/ExitOn2.png"));
	protected ImageIcon returnOff = new ImageIcon(Main.class.getResource("../image/return.png"));
	protected ImageIcon returnOn = new ImageIcon(Main.class.getResource("../image/returnOn.png"));
	protected ImageIcon stageOff = new ImageIcon(Main.class.getResource("../image/stage.png"));
	protected ImageIcon stageOn = new ImageIcon(Main.class.getResource("../image/stageOn.png"));
	protected ImageIcon timeOff = new ImageIcon(Main.class.getResource("../image/time.png"));
	protected ImageIcon timeOn = new ImageIcon(Main.class.getResource("../image/timeOn.png"));
	protected ImageIcon vs1Off = new ImageIcon(Main.class.getResource("../image/1vs1.png"));
	protected ImageIcon vs1On = new ImageIcon(Main.class.getResource("../image/1vs1On.png"));

	// 다음블록, 점수, 콤보, 라인 이미지
	protected ImageIcon Menu_BG = new ImageIcon(Main.class.getResource("../image/MenuBG.png"));
	protected ImageIcon Nextimg = new ImageIcon(Main.class.getResource("../image/next.png"));
	protected ImageIcon Scoreimg = new ImageIcon(Main.class.getResource("../image/score.png"));
	protected ImageIcon Comboimg = new ImageIcon(Main.class.getResource("../image/combo.png"));
	protected ImageIcon Lineimg = new ImageIcon(Main.class.getResource("../image/Line.png"));
	protected ImageIcon Guardimg = new ImageIcon(Main.class.getResource("../image/guard.png"));

	// 버튼
	protected JButton Start = new JButton(StartOff);
	protected JButton Ranking = new JButton(RankingOff);
	protected JButton Exit = new JButton(ExitOff);
	protected JButton exit = new JButton(exitOff);
	protected JButton Return = new JButton(returnOff);
	protected JButton Menu = new JButton(Menu_BG);
	protected JButton stage = new JButton(stageOff);
	protected JButton time = new JButton(timeOff);
	protected JButton vs1 = new JButton(vs1Off);

	// 라벨
	protected JLabel Next = new JLabel(Nextimg);
	protected JLabel Score = new JLabel(Scoreimg);
	protected JLabel Combo = new JLabel(Comboimg);
	protected JLabel Line = new JLabel(Lineimg);
	protected JLabel Guard = new JLabel(Guardimg);
	protected JLabel yourGuard = new JLabel(Guardimg);

	// 각각의 변수들이 true 로 변하면 배경이 바뀌어 보여진다.
	protected boolean isFirstFrame = false;
	protected boolean isMainFrame = false;
	protected boolean isRankingFrame = false;

	// 마우스의 x,y 축을 받기 위해
	protected int screenX;
	protected int screenY;

	//모두 이 기본 프레임을 상속 받는다. 이 프레임에는 공통적으로 들어가는 버튼이 들어가 있다. 
	public Frame() {
		setUndecorated(true);				//프레임의 기본 데코들을 삭제 해준다			
		setTitle("Tetris Game");			//타이틀 
		setSize(Main.WIDTH, Main.HEIGHT);	//가로 1280 세로 720 게임  
		setResizable(false);				//화면 크기 변경 불가
		setLocationRelativeTo(null);		//윈도우 창을 화면 가운데로 띄운다.		
		setLayout(null);					//레이 아웃 제거 , 수동으로 일일이 버튼,라벨들을 위치 시켜준다 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//종료 할 경우 닫아주는 명령어
		setBackground(new Color(0, 0, 0, 0));			//화면을 투명하게 해준후 배경 화면을 넣어준다. 

		// 모든 프레임에 있는 종료 버튼
		exit.setBounds(1250, 0, 30, 30);		//위치 설정 
		exit.setBorderPainted(false);			//버튼 테두리 없애기 
		exit.setContentAreaFilled(false);		//버튼 배경 없애기 
		exit.setFocusable(false);				//메인 게임에서 키입력을 받기위해 포커스 제거 
		//각각 버튼 마우스 액션 
		exit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				exit.setIcon(exitOn);
			}

			public void mouseExited(MouseEvent e) {
				exit.setIcon(exitOff);
			}

			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		add(exit);

	}

	// 메소드 정의 : 더블 버퍼를 이용한 배경화면 그려주기 
	public void paint(Graphics g) {
		BG_Image = createImage(Main.WIDTH, Main.HEIGHT);	//1280 720 크기의 이미지를 생성해 이미지 변수에 넣어 준다.
		BG_Graphic = BG_Image.getGraphics();				//이미지의 그래픽 객체를 얻어와 변수에 넣어준다.
		paintGraphic(BG_Graphic);							//이 함수에서 그림을 그려 준다. 
		g.drawImage(BG_Image, 0, 0, null);
	}

	// 메소드 정의 : 화면을 띄어준다 
	// 메소드 하는 일 : 이미지 변수를 확인해 어떤 화면이 띄어져있는지 확인후 바꿔준다. 
	public void paintGraphic(Graphics g) {
		if (isFirstFrame)
			g.drawImage(First_BG, 0, 0, null);
		else if (isMainFrame)
			g.drawImage(Main_BG, 0, 0, null);
		else if (isRankingFrame)
			g.drawImage(Ranking_BG, 0, 0, null);
		paintComponents(g);	//이미지를 제외한 버튼들을 화면에 그려주는 역할 
		this.repaint();
	}
}
