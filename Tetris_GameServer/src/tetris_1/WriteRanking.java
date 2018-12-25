package tetris_1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//게임오버가 되면 뜨는 다이어 로그 ( 모달 ) 
//게임이 끝나면 2가지 화면이 뜬다  
//하나는 10등안에 들었을 경우 
//하나는 10등안에 들지 못했을 경우 
public class WriteRanking extends JDialog {
	
	private Music m = new Music();

	private int WIDHT = 500, HEIGHT = 350; 	// 화면 가로 세로
	private JTextField textname; 			// 10등안에 들었다면 게임이 끝나고 텍스트에 사용자의 아이디를 입력 받는다.
	private JLabel label; 					// 10등안에 들지 못했다면 라벨이 뜬다.
	private JLabel gameover; 				// game over 라벨
	private JButton textbutton; 			// 버튼을 누른다면 입력받은 텍스트가 랭킹 화면으로 넘어간다. or 10등인에 들지 못했다면 그냥 랭킹 화면으로 넘어간다
	private JPanel panel; 					// 컴포넌트를 담을 패널

	// 파라미터가 없는 생성자 .
	// 불리는 시기 : 게임이 끝났을때 점수가 10등안에 들지 못한다면 .
	public WriteRanking() {
		panel = new JPanel();
		label = new JLabel(" 10등 안에 들지 못했습니다 . ");
		gameover = new JLabel("Game Over");
		textbutton = new JButton("ok");

		gameover.setBounds(150, 30, 400, 100);
		gameover.setForeground(new Color(255, 255, 255));
		gameover.setFont(new Font("고딕", Font.BOLD, 40));
		panel.add(gameover);

		label.setBounds(100, 100, 400, 100);
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("고딕", Font.BOLD, 25));
		panel.add(label);

		// 버튼 마우스 이벤트
		textbutton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				m.PlaySound(new File("src//music//button.wav"));
				textbutton.setForeground(new Color(100, 100, 100));
			}

			public void mouseExited(MouseEvent e) {
				textbutton.setForeground(new Color(255, 255, 255));
			}

			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		textbutton.setBounds(200, 200, 100, 50);
		textbutton.setForeground(new Color(255, 255, 255));
		textbutton.setFont(new Font("고딕", Font.BOLD, 50));
		textbutton.setBorderPainted(false);
		textbutton.setContentAreaFilled(false);
		textbutton.setFocusPainted(false);
		panel.add(textbutton);

		panel.setLayout(null);
		panel.setBackground(Color.black);

		this.add(panel);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();

		this.setTitle("Game Over");
		this.setSize(WIDHT, HEIGHT);
		this.setLocation(screenSize.width / 2 - (WIDHT / 2), screenSize.height / 2 - (HEIGHT / 2));
		this.setModal(true);
		this.setVisible(true);
	}

	// 파라미터가 있는 생성자 .
	// 불리는 시기 : 게임이 끝났을때 점수가 10등 안에 든다면 .
	// s : 게임이 끝나면 score 를 받아온다.
	public WriteRanking(int score) {
		panel = new JPanel();
		textname = new JTextField("Write your name ");
		gameover = new JLabel("Game Over");
		textbutton = new JButton(" 랭킹등록 하기 ");

		gameover.setBounds(130, 30, 400, 100);
		gameover.setForeground(new Color(255, 255, 255));
		gameover.setFont(new Font("고딕", Font.BOLD, 40));
		panel.add(gameover);

		textname.setBounds(90, 130, 300, 50);
		textname.setFont(new Font("고딕", Font.BOLD, 25));
		panel.add(textname);

		// 버튼 마우스 이벤트
		textbutton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				m.PlaySound(new File("src//music//button.wav"));
				textbutton.setForeground(new Color(100, 100, 100));
			}

			public void mouseExited(MouseEvent e) {
				textbutton.setForeground(new Color(255, 255, 255));
			}

			public void mouseClicked(MouseEvent e) {
				dispose();
				new Datalist(textname.getText(),score); 				//여기서 데이터를 파일에 입력한다. 
			}
		});
		textbutton.setBounds(50, 200, 400, 100);
		textbutton.setForeground(new Color(255, 255, 255));
		textbutton.setFont(new Font("고딕", Font.BOLD, 50));
		textbutton.setBorderPainted(false);
		textbutton.setContentAreaFilled(false);
		textbutton.setFocusPainted(false);
		panel.add(textbutton);

		panel.setLayout(null);
		panel.setBackground(Color.black);

		this.add(panel);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();

		this.setTitle("Game Over ( Ranking )");
		this.setSize(WIDHT, HEIGHT);
		this.setLocation(screenSize.width / 2 - (WIDHT / 2), screenSize.height / 2 - (HEIGHT / 2));
		this.setModal(true);
		this.setVisible(true);
	}

}
