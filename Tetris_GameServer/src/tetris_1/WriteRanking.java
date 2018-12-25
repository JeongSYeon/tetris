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

//���ӿ����� �Ǹ� �ߴ� ���̾� �α� ( ��� ) 
//������ ������ 2���� ȭ���� ���  
//�ϳ��� 10��ȿ� ����� ��� 
//�ϳ��� 10��ȿ� ���� ������ ��� 
public class WriteRanking extends JDialog {
	
	private Music m = new Music();

	private int WIDHT = 500, HEIGHT = 350; 	// ȭ�� ���� ����
	private JTextField textname; 			// 10��ȿ� ����ٸ� ������ ������ �ؽ�Ʈ�� ������� ���̵� �Է� �޴´�.
	private JLabel label; 					// 10��ȿ� ���� ���ߴٸ� ���� ���.
	private JLabel gameover; 				// game over ��
	private JButton textbutton; 			// ��ư�� �����ٸ� �Է¹��� �ؽ�Ʈ�� ��ŷ ȭ������ �Ѿ��. or 10���ο� ���� ���ߴٸ� �׳� ��ŷ ȭ������ �Ѿ��
	private JPanel panel; 					// ������Ʈ�� ���� �г�

	// �Ķ���Ͱ� ���� ������ .
	// �Ҹ��� �ñ� : ������ �������� ������ 10��ȿ� ���� ���Ѵٸ� .
	public WriteRanking() {
		panel = new JPanel();
		label = new JLabel(" 10�� �ȿ� ���� ���߽��ϴ� . ");
		gameover = new JLabel("Game Over");
		textbutton = new JButton("ok");

		gameover.setBounds(150, 30, 400, 100);
		gameover.setForeground(new Color(255, 255, 255));
		gameover.setFont(new Font("���", Font.BOLD, 40));
		panel.add(gameover);

		label.setBounds(100, 100, 400, 100);
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("���", Font.BOLD, 25));
		panel.add(label);

		// ��ư ���콺 �̺�Ʈ
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
		textbutton.setFont(new Font("���", Font.BOLD, 50));
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

	// �Ķ���Ͱ� �ִ� ������ .
	// �Ҹ��� �ñ� : ������ �������� ������ 10�� �ȿ� ��ٸ� .
	// s : ������ ������ score �� �޾ƿ´�.
	public WriteRanking(int score) {
		panel = new JPanel();
		textname = new JTextField("Write your name ");
		gameover = new JLabel("Game Over");
		textbutton = new JButton(" ��ŷ��� �ϱ� ");

		gameover.setBounds(130, 30, 400, 100);
		gameover.setForeground(new Color(255, 255, 255));
		gameover.setFont(new Font("���", Font.BOLD, 40));
		panel.add(gameover);

		textname.setBounds(90, 130, 300, 50);
		textname.setFont(new Font("���", Font.BOLD, 25));
		panel.add(textname);

		// ��ư ���콺 �̺�Ʈ
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
				new Datalist(textname.getText(),score); 				//���⼭ �����͸� ���Ͽ� �Է��Ѵ�. 
			}
		});
		textbutton.setBounds(50, 200, 400, 100);
		textbutton.setForeground(new Color(255, 255, 255));
		textbutton.setFont(new Font("���", Font.BOLD, 50));
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
