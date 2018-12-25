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

	//���� ���۸��� ���ؼ� ����ϴ� �� ���� 
	protected Image BG_Image;
	protected Graphics BG_Graphic;

	// ���� ó�� , ���ΰ��� , ��ŷ ȭ���� ���
	protected Image First_BG = new ImageIcon(Main.class.getResource("../image/FirstBG.jpg")).getImage();
	protected Image Main_BG = new ImageIcon(Main.class.getResource("../image/MainBG.jpg")).getImage();
	protected Image Ranking_BG = new ImageIcon(Main.class.getResource("../image/RankingBG.jpg")).getImage();

	// ���۹�ư, ��ŷ��ư, �����ư 1,2 , �ڷΰ��� �̹���
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

	// �������, ����, �޺�, ���� �̹���
	protected ImageIcon Menu_BG = new ImageIcon(Main.class.getResource("../image/MenuBG.png"));
	protected ImageIcon Nextimg = new ImageIcon(Main.class.getResource("../image/next.png"));
	protected ImageIcon Scoreimg = new ImageIcon(Main.class.getResource("../image/score.png"));
	protected ImageIcon Comboimg = new ImageIcon(Main.class.getResource("../image/combo.png"));
	protected ImageIcon Lineimg = new ImageIcon(Main.class.getResource("../image/Line.png"));
	protected ImageIcon Guardimg = new ImageIcon(Main.class.getResource("../image/guard.png"));

	// ��ư
	protected JButton Start = new JButton(StartOff);
	protected JButton Ranking = new JButton(RankingOff);
	protected JButton Exit = new JButton(ExitOff);
	protected JButton exit = new JButton(exitOff);
	protected JButton Return = new JButton(returnOff);
	protected JButton Menu = new JButton(Menu_BG);
	protected JButton stage = new JButton(stageOff);
	protected JButton time = new JButton(timeOff);
	protected JButton vs1 = new JButton(vs1Off);

	// ��
	protected JLabel Next = new JLabel(Nextimg);
	protected JLabel Score = new JLabel(Scoreimg);
	protected JLabel Combo = new JLabel(Comboimg);
	protected JLabel Line = new JLabel(Lineimg);
	protected JLabel Guard = new JLabel(Guardimg);
	protected JLabel yourGuard = new JLabel(Guardimg);

	// ������ �������� true �� ���ϸ� ����� �ٲ�� ��������.
	protected boolean isFirstFrame = false;
	protected boolean isMainFrame = false;
	protected boolean isRankingFrame = false;

	// ���콺�� x,y ���� �ޱ� ����
	protected int screenX;
	protected int screenY;

	//��� �� �⺻ �������� ��� �޴´�. �� �����ӿ��� ���������� ���� ��ư�� �� �ִ�. 
	public Frame() {
		setUndecorated(true);				//�������� �⺻ ���ڵ��� ���� ���ش�			
		setTitle("Tetris Game");			//Ÿ��Ʋ 
		setSize(Main.WIDTH, Main.HEIGHT);	//���� 1280 ���� 720 ����  
		setResizable(false);				//ȭ�� ũ�� ���� �Ұ�
		setLocationRelativeTo(null);		//������ â�� ȭ�� ����� ����.		
		setLayout(null);					//���� �ƿ� ���� , �������� ������ ��ư,�󺧵��� ��ġ �����ش� 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//���� �� ��� �ݾ��ִ� ��ɾ�
		setBackground(new Color(0, 0, 0, 0));			//ȭ���� �����ϰ� ������ ��� ȭ���� �־��ش�. 

		// ��� �����ӿ� �ִ� ���� ��ư
		exit.setBounds(1250, 0, 30, 30);		//��ġ ���� 
		exit.setBorderPainted(false);			//��ư �׵θ� ���ֱ� 
		exit.setContentAreaFilled(false);		//��ư ��� ���ֱ� 
		exit.setFocusable(false);				//���� ���ӿ��� Ű�Է��� �ޱ����� ��Ŀ�� ���� 
		//���� ��ư ���콺 �׼� 
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

	// �޼ҵ� ���� : ���� ���۸� �̿��� ���ȭ�� �׷��ֱ� 
	public void paint(Graphics g) {
		BG_Image = createImage(Main.WIDTH, Main.HEIGHT);	//1280 720 ũ���� �̹����� ������ �̹��� ������ �־� �ش�.
		BG_Graphic = BG_Image.getGraphics();				//�̹����� �׷��� ��ü�� ���� ������ �־��ش�.
		paintGraphic(BG_Graphic);							//�� �Լ����� �׸��� �׷� �ش�. 
		g.drawImage(BG_Image, 0, 0, null);
	}

	// �޼ҵ� ���� : ȭ���� ����ش� 
	// �޼ҵ� �ϴ� �� : �̹��� ������ Ȯ���� � ȭ���� ������ִ��� Ȯ���� �ٲ��ش�. 
	public void paintGraphic(Graphics g) {
		if (isFirstFrame)
			g.drawImage(First_BG, 0, 0, null);
		else if (isMainFrame)
			g.drawImage(Main_BG, 0, 0, null);
		else if (isRankingFrame)
			g.drawImage(Ranking_BG, 0, 0, null);
		paintComponents(g);	//�̹����� ������ ��ư���� ȭ�鿡 �׷��ִ� ���� 
		this.repaint();
	}
}
