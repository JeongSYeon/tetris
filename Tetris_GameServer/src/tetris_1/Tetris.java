package tetris_1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Random;

import javax.swing.JLabel;

public class Tetris implements KeyListener, Runnable {

	// ���� ���� �ٸ� ȿ������ ������ �ִ�.
	protected Music m = new Music();

	// ��ŷ ������ �����̴�. 10��ȿ� ��� �� Ȯ�� �� ��ŷ�� ��� ���� ������ �����Ѵ�.
	protected Datalist data = new Datalist();

	protected MainBoard _panel;
	protected NextblockBoard _nextblockboard;
	protected Thread gamethread;
	protected Random r = new Random();

	protected Blocks currentblock;	// ������ ���
	protected Blocks nextblock;		// ������ ���
	protected Blocks holdblock; 	// hold ���
	protected Blocks ghostblock;	// ��Ʈ ���

	protected boolean isDownPossible = true; 	// ������ �� �ִ��� Ȯ���ϴ� ����
	protected boolean isDeleteLine; 			// ������ ������ �ִ��� Ȯ���ϴ� ����
	protected boolean endcount;					// ������ �������� Ȯ���ϴ� ����
	protected boolean isCombo = false; 			// �޺��� ��� �Ǵ� ����
	
	protected int score;
	protected int combo;
	protected int line;
	protected int speed;

	JLabel scorelabel = new JLabel("" + getScore());
	JLabel combolabel = new JLabel("" + getCombo());
	JLabel linelabel = new JLabel("" + getLine());

	// ������
	// ������ ���� �Ǹ� ù���� ��Ϸ������� ������ �ش�.
	// ����� �����ð� ���� �����ִ� �����带 �����Ѵ�.
	public Tetris(MainBoard panel, NextblockBoard nextblockboard) {

		this._nextblockboard = nextblockboard;
		this._panel = panel;

		score = 0;
		combo = 0;
		line = 0;
		speed = 1000;
		endcount = false;

		currentblock = addBlockList(r.nextInt(7) + 1); // ���� ����� ���� ������ ����. 
		setCurrentPanel();								//ȭ�鿡 ����� �����ش�.

		gamethread = new Thread(this);					//������ ���� 
		gamethread.start();								//������ ���� 
	}

	// �޼ҵ� ���� : ��� ���� 
	// �޼ҵ� �ϴ� �� : ������ ������ �ϴ��� Ȯ���� �����ٸ� gameEnd �Լ��� �θ���. �׷��� �ʴٸ���� ����� ���� ���忡 ���� , ���� ����� ���� �صд�. 
	public void setCurrentPanel() {

		//�⺻ ��� ��ġ Ȯ��
		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			if (_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i].getCol()] != 0) {
					gameEnd();
			}
		}

		// �гο� ���� ��� ���� 
		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i].getCol()] = currentblock.blockNum;
		}
		//���� ��� ���� .
		nextblock = addBlockList(r.nextInt(7) + 1);
		//���� ����� ȭ�鿡 �����ش�.
		setNextBlockPanel();
	}

	// �޼ҵ� ���� : ���ο� ��� ���� 
	// �Ķ���� ���� : ���� �� �Է� �޴´�.
	// �޼ҵ� �ϴ� �� : �Է� ���� ���� ���� Ȯ���Ͽ� �ٸ� ����� ����  
	public Blocks addBlockList(int n) {
		Blocks temp = null;
		if (n == 1) {
			temp = new OBlock();
		} else if (n == 2) {
			temp = new IBlock();
		} else if (n == 3) {
			temp = new SBlock();
		} else if (n == 4) {
			temp = new ZBlock();
		} else if (n == 5) {
			temp = new LBlock();
		} else if (n == 6) {
			temp = new JBlock();
		} else if (n == 7) {
			temp = new TBlock();
		}
		return temp;
	}

	// �޼ҵ� ���� : ���� ��� �г��� �׷��ش�
	// �޼ҵ� �ϴ� �� : ���� �ִ� ����� ����� ��ġ�� ���� ��������� �ٽ� �׷� �ش�. ���� ����� �ٲ�� ���� ���� �Ҹ���.
	public void setNextBlockPanel() {
		for (int i = 1; i < 5; i++)
			for (int j = 1; j < 5; j++)
				_nextblockboard.board[i][j] = 0;

		for (int i = 0; i < Blocks.BLOCKNUM; i++) {

			int x = nextblock.oneblock[i].getRow();
			int y = nextblock.oneblock[i].getCol();
			int num = nextblock.blockNum;
			
			if (num == 1) {
				_nextblockboard.board[x + 1][y - 3] = num;
			} else if (num == 2) {
				_nextblockboard.board[x + 1][y - 2] = num;
			} else if (num == 3) {
				_nextblockboard.board[x + 1][y - 2] = num;
			} else if (num == 4) {
				_nextblockboard.board[x + 1][y - 2] = num;
			} else if (num == 5) {
				_nextblockboard.board[x + 1][y - 3] = num;
			} else if (num == 6) {
				_nextblockboard.board[x + 1][y - 3] = num;
			} else if (num == 7) {
				_nextblockboard.board[x + 1][y - 2] = num;
			}
		}
	}

	// �޼ҵ� ���� : ������ ������ üũ �Ѵ�. 
	// �޼ҵ� �ϴ� �� : 
	// �г��� ��� Ȯ���� ����� ���� �ִٸ� delete �Լ��� �θ���.
	// ���⼭ ���ھ� , �޺�, ���� ���� �� �ʿ��� ������� ��� .
	public void FullLineCheck() {

		//�Ʒ� ������ �޺� �� ���ȴ�.
		//���� ������ �޺��� �þ�� ���� ������ ����� 0�� �ȴ�. 
		boolean deleteLineMinOne = false;		
		int linecount = 0;

		for (int i = 0; i < 21; i++) {			//���� ���� �˻�
			isDeleteLine = true;
			for (int j = 1; j < 11; j++) {
				if (_panel.board[i][j] == 0) {		//���⼭ ��ĭ�̶� �����̶�� ���� ������ ����. 
					isDeleteLine = false;
				}
			}
			if (isDeleteLine) {			//Ȯ���Ͽ� ������ִٸ�  i ��° ������ ���� .
				DeleteLine(i);
				setLine();				// ������ ���� �Ǿ����Ƿ�  ���� ���� �ٲ��ش�. 
				linecount++;
				m.PlaySound(new File("src//music//boom.wav"));	//���� ���� ȿ���� 
				deleteLineMinOne = true;
			}
		}
		setSpeed(getLine());
		setScore(linecount, getCombo());

		if (deleteLineMinOne) {
			setCombo(isCombo);
			isCombo = true;
		} else {
			isCombo = false;
			setCombo(isCombo);
		}

		currentblock = nextblock;
		setCurrentPanel();
	}

	// �޼ҵ� ���� : ���� ������ �Ѵ�
	// �Ķ���� ���� : n ��° ������ ���� 
	// �޼ҵ� �ϴ� �� : ������ ����, �� �� ���� �ִ� ������ �Ʒ��ζ����ش�.
	public void DeleteLine(int n) {
		for (int j = 1; j < 11; j++) {
			_panel.board[n][j] = 0;
		}
		for (int i = n; i > 0; i--) {
			for (int j = 1; j < 11; j++) {
				_panel.board[i][j] = _panel.board[i - 1][j];
			}
		}
	}

	// �޼ҵ� ���� : ��� �̵��� �������� Ȯ���Ѵ�.
	// �Ķ���� ���� : ����� �Է� �޾� �̵��� �������� Ȯ�� 
	// �޼ҵ� �ϴ� �� : Ȯ�� �Ͽ� boolean ���� ��ȯ�Ѵ�.
	public boolean isMovePossible(Blocks block) {
		boolean flag = true;
		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			int blocknumber = _panel.board[block.oneblock[i].getRow()][block.oneblock[i].getCol()];
			if (blocknumber > 0 && blocknumber < 9) {
				flag = false;
			}
		}
		return flag;
	}

	// �޼ҵ� ���� : ��� ������ ����
	// �޼ҵ� �ϴ� �� : 
	// 1. ���� �����ġ�� �ٸ� ��� ������ ���� �Ѵ�. ��ġ�� �ٲپ� �ְ�, ȭ�鿡 ����� �ʾҴ�.
	// 2. ����� �ϴ� �ű��. 
	// 3. �ű� ��Ͽ� �ٸ� ����� ���ٸ� ȭ�鿡 �پ��ְ� ��.
	// 4. ����� �ű� �� ���ٸ� ���̻� �������� ������.
	// 5. ������ ��ġ��Ų ����� �ٽ� ���� ��ġ�� �Ű����� , 
	// 6. ���� üũ�� �ϰ� �����ش�.
	public void Down() {

		OneBlock[] tempblock = new OneBlock[Blocks.BLOCKNUM];
		tempblock = currentblock.CloneBlock();

		currentblock.DownKey();

		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			_panel.board[tempblock[i].getRow()][tempblock[i].getCol()] = 0;
		}
		isDownPossible = isMovePossible(currentblock);
		if (isMovePossible(currentblock)) {
			for (int i = 0; i < Blocks.BLOCKNUM; i++) {
				_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i]
						.getCol()] = currentblock.blockNum;
			}
		} else {
			for (int i = 0; i < Blocks.BLOCKNUM; i++) {
				currentblock.oneblock[i].set_RC(tempblock[i].getRow(), tempblock[i].getCol());
				_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i]
						.getCol()] = currentblock.blockNum;
			}
			FullLineCheck();
		}
	}

	// �޼ҵ� ���� : ��� ȸ�� ����
	// �޼ҵ� �ϴ� �� : 
	// 1. ���� �����ġ�� �ٸ� ��� ������ ���� �Ѵ�. ��ġ�� �ٲپ� �ְ�, ȭ�鿡 ����� �ʾҴ�.
	// 2. ����� �ϴ� �ű��. 
	// 3. �ű� ��Ͽ� �ٸ� ����� ���ٸ� ȭ�鿡 �پ��ְ� ��.
	// 4. ����� �ű� �� ���ٸ� ����� �ٽ� ���� ��ġ�� �Ű����� ������.
	public void Up() {
		OneBlock[] tempblock = new OneBlock[Blocks.BLOCKNUM];
		tempblock = currentblock.CloneBlock();

		currentblock.UpKey(currentblock.rotateNum);

		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			_panel.board[tempblock[i].getRow()][tempblock[i].getCol()] = 0;
		}
		if (isMovePossible(currentblock)) {
			for (int i = 0; i < Blocks.BLOCKNUM; i++) {
				_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i]
						.getCol()] = currentblock.blockNum;
			}
		} else {
			for (int i = 0; i < Blocks.BLOCKNUM; i++) {
				currentblock.oneblock[i].set_RC(tempblock[i].getRow(), tempblock[i].getCol());
				_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i]
						.getCol()] = currentblock.blockNum;
			}
		}

	}

	// �޼ҵ� ���� : ��� ���� �̵� ����
	// �޼ҵ� �ϴ� �� : 
	// 1. ���� �����ġ�� �ٸ� ��� ������ ���� �Ѵ�. ��ġ�� �ٲپ� �ְ�, ȭ�鿡 ����� �ʾҴ�.
	// 2. ����� �ϴ� �ű��. 
	// 3. �ű� ��Ͽ� �ٸ� ����� ���ٸ� ȭ�鿡 �پ��ְ� ��.
	// 4. ����� �ű� �� ���ٸ� ����� �ٽ� ���� ��ġ�� �Ű����� ������.
	public void Left() {

		OneBlock[] tempblock = new OneBlock[Blocks.BLOCKNUM];
		tempblock = currentblock.CloneBlock();

		currentblock.LeftKey();

		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			_panel.board[tempblock[i].getRow()][tempblock[i].getCol()] = 0;
		}
		if (isMovePossible(currentblock)) {
			for (int i = 0; i < Blocks.BLOCKNUM; i++) {
				_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i]
						.getCol()] = currentblock.blockNum;
			}
		} else {
			for (int i = 0; i < Blocks.BLOCKNUM; i++) {
				currentblock.oneblock[i].set_RC(tempblock[i].getRow(), tempblock[i].getCol());
				_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i]
						.getCol()] = currentblock.blockNum;
			}
		}
	}

	// �޼ҵ� ���� : ��� ������ �̵� ����
	// �޼ҵ� �ϴ� �� : 
	// 1. ���� �����ġ�� �ٸ� ��� ������ ���� �Ѵ�. ��ġ�� �ٲپ� �ְ�, ȭ�鿡 ����� �ʾҴ�.
	// 2. ����� �ϴ� �ű��. 
	// 3. �ű� ��Ͽ� �ٸ� ����� ���ٸ� ȭ�鿡 �پ��ְ� ��.
	// 4. ����� �ű� �� ���ٸ� ����� �ٽ� ���� ��ġ�� �Ű����� ������.
	public void Right() {

		OneBlock[] tempblock = new OneBlock[Blocks.BLOCKNUM];
		tempblock = currentblock.CloneBlock();

		currentblock.RightKey();

		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			_panel.board[tempblock[i].getRow()][tempblock[i].getCol()] = 0;
		}
		if (isMovePossible(currentblock)) {
			for (int i = 0; i < Blocks.BLOCKNUM; i++) {
				_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i]
						.getCol()] = currentblock.blockNum;
			}
		} else {
			for (int i = 0; i < Blocks.BLOCKNUM; i++) {
				currentblock.oneblock[i].set_RC(tempblock[i].getRow(), tempblock[i].getCol());
				_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i]
						.getCol()] = currentblock.blockNum;
			}
		}
	}

	// Ű�Է��� �޴´�.
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (isDownPossible) {
				Up();
				m.PlaySound(new File("src//music//pip.wav"));
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (isDownPossible)
				Down();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (isDownPossible)
				Left();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (isDownPossible)
				Right();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			//�����̽��� ������ ������ ���� ���������� ����� �����ָ� �ȴ�.
			while (isDownPossible) {
				 Down();
			}
			if(!endcount) isDownPossible = true;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void gameEnd() {
		endcount = true; 
	}

	//
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				
				// ������ �������� ��� ȭ������ �����ְ� ��ŷ ��� ȭ���� ���.
				if (endcount) {
					for (int i = 0; i < 21; i++) {
						for (int j = 0; j < 11; j++)
							if (_panel.board[i][j] != 0) {
								_panel.board[i][j] = 8;
							}
						Thread.sleep(70);
					}
					m.PlaySound(new File("src//music//gameEnd.wav"));
					
					//10��ȿ� ����� ��� 
					//���Ͽ� 10����� ���ٸ� �׳� ���� ���� 
					if (data.getscoreList().size() < 10) {
						new WriteRanking(score);	
					} 
					//���Ͽ� 10�� �Ѵ´ٸ� top 10���� ū�� ������ Ȯ�� �� ���� 
					else if (Integer.parseInt(data.getscoreList().get(9)) < score) {
						new WriteRanking(score);
					} 
					//�ƴ϶�� �׳� ���� ����â�� ���. 
					else {
						new WriteRanking();
					}
					gamethread.stop();
				}
				
				// ������ ������ �ʾҴٸ� ��� ����� ��ĭ�� �����ִ� ���� �Ѵ�.
				else {
					//Thread.sleep(speed);
					//Down();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getScore() {
		return score;
	}

	public int getCombo() {
		return combo;
	}

	public int getLine() {
		return line;
	}

	// �޼ҵ� ���� : ���� ���� �������ش� . ���� �޺��� ������ ������ ���ھ�� ����
	// �Ķ���� ���� : ���� ���� ���� �޺� 
	// �޼ҵ� �ϴ� �� : �Ķ���͸� �̿��� ���� �ٸ� ������ �Է� �޴´�. 
	public void setScore(int linecount, int combo) {
		if (linecount == 1) {
			score = score + (100 * (combo + 1));
		} else if (linecount == 2) {
			score = score + (250 * (combo + 1));
		} else if (linecount == 3) {
			score = score + (500 * (combo + 1));
		} else if (linecount == 4) {
			score = score + (1000 * (combo + 1));
		}
		scorelabel.setText("" + getScore());
	}

	// �޼ҵ� ���� : �޺� �� ���� 
	// �Ķ���� ���� : �޺� ������
	// �޼ҵ� �ϴ� �� : �´ٸ� + 1 �ƴ϶�� 0 
	public void setCombo(boolean isCombo) {
		if (isCombo == true) {
			combo++;
		} else
			combo = 0;

		combolabel.setText("" + getCombo());
	}

	// �޼ҵ� ���� : Ʈ�� ���μ� ����
	// �޼ҵ� �ϴ� �� : ���� ++ 
	public void setLine() {
		line++;
		linelabel.setText("" + getLine());
	}

	// �޼ҵ� ���� : ����� �������� �ӵ� ����
	// �Ķ���� ���� : ���� ���� ���� �̿�
	// �޼ҵ� �ϴ� �� : 10ĭ ���� �ӵ��� 1�ܰ辿 ��������. 
	// ���� �̱��� -> ���� ��̸� ���� 90 ������ �Ѿ�ٸ� �ٸ� �̺�Ʈ �߻�   
	public void setSpeed(int line) {
		if (line < 90)
			speed = 1000 - ((int) (line / 10)) * 100;
		else {
			// 90������ �Ѿ��
		}
	}
}
