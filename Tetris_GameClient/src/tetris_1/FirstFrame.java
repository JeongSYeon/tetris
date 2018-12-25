package tetris_1;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

public class FirstFrame extends Frame {

	FirstFrame() {

		setVisible(true);
		isFirstFrame = true;		// �� ������ frame class ���� �̹����� ��ȯ�Ѵ�

		// start��ư �߰�
		Start.setBounds(440, 310, 400, 120);
		Start.setBorderPainted(false);
		Start.setContentAreaFilled(false);
		Start.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				m.PlaySound(new File("src//music//button.wav"));
				Start.setIcon(StartOn);
			}

			public void mouseExited(MouseEvent e) {
				Start.setIcon(StartOff);
			}

			public void mouseClicked(MouseEvent e) {
				Start.setVisible(false);
				Ranking.setVisible(false);
				Exit.setVisible(false);
				stage.setVisible(true);
				time.setVisible(true);
				vs1.setVisible(true);
				Return.setVisible(true);
			}
		});
		add(Start);

		// Ranking��ư
		Ranking.setBounds(440, 430, 400, 120);
		Ranking.setBorderPainted(false);
		Ranking.setContentAreaFilled(false);
		Ranking.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				m.PlaySound(new File("src//music//button.wav"));
				Ranking.setIcon(RankingOn);
			}

			public void mouseExited(MouseEvent e) {
				Ranking.setIcon(RankingOff);
			}

			public void mouseClicked(MouseEvent e) {
				dispose();
				new RankingFrame();
			}
		});
		add(Ranking);

		// �����ư
		Exit.setBounds(440, 550, 400, 120);
		Exit.setBorderPainted(false);
		Exit.setContentAreaFilled(false);
		Exit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				m.PlaySound(new File("src//music//button.wav"));
				Exit.setIcon(ExitOn);
			}

			public void mouseExited(MouseEvent e) {
				Exit.setIcon(ExitOff);
			}

			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		add(Exit);

		// ȥ���ϱ� ���� ���
		stage.setBounds(440, 310, 400, 120);
		stage.setBorderPainted(false);
		stage.setContentAreaFilled(false);
		stage.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				m.PlaySound(new File("src//music//button.wav"));
				stage.setIcon(stageOn);
			}

			public void mouseExited(MouseEvent e) {
				stage.setIcon(stageOff);
			}

			public void mouseClicked(MouseEvent e) {
				dispose();
				new MainFrame();
			}
		});
		add(stage);
		stage.setVisible(false);

		// Ÿ�� ���
		time.setBounds(440, 430, 400, 120);
		time.setBorderPainted(false);
		time.setContentAreaFilled(false);
		time.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				m.PlaySound(new File("src//music//button.wav"));
				time.setIcon(timeOn);
			}

			public void mouseExited(MouseEvent e) {
				time.setIcon(timeOff);
			}

			public void mouseClicked(MouseEvent e) {
				/*
				 * Ÿ�� ���� ���� ���� ���� �̱��� 
				 */ 
			}
		});
		add(time);
		time.setVisible(false);

		// �������
		vs1.setBounds(480, 550, 400, 120);
		vs1.setBorderPainted(false);
		vs1.setContentAreaFilled(false);
		vs1.setFocusPainted(false);
		vs1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				m.PlaySound(new File("src//music//button.wav"));
				vs1.setIcon(vs1On);
			}

			public void mouseExited(MouseEvent e) {
				vs1.setIcon(vs1Off);
			}

			public void mouseClicked(MouseEvent e) {
				dispose();
				new BattleFrame();
			}
		});
		add(vs1);
		vs1.setVisible(false);
		
		// �ڷΰ��� ��ư �߰�
		Return.setBounds(900, 510, 400, 120);
		Return.setBorderPainted(false);
		Return.setContentAreaFilled(false);

		Return.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				m.PlaySound(new File("src//music//button.wav"));
				Return.setIcon(returnOn);
			}

			public void mouseExited(MouseEvent e) {
				Return.setIcon(returnOff);
			}

			public void mouseClicked(MouseEvent e) {
				Start.setVisible(true);
				Ranking.setVisible(true);
				Exit.setVisible(true);
				stage.setVisible(false);
				time.setVisible(false);
				vs1.setVisible(false);
				Return.setVisible(false);
			}
		});
		add(Return);
		Return.setVisible(false);
		
		// �޴��� �߰�
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
	}
}
