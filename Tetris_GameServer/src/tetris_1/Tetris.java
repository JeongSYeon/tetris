package tetris_1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Random;

import javax.swing.JLabel;

public class Tetris implements KeyListener, Runnable {

	// 각각 서로 다른 효과음을 가지고 있다.
	protected Music m = new Music();

	// 랭킹 데이터 변수이다. 10등안에 드는 지 확인 후 랭킹을 등록 할지 안할지 결정한다.
	protected Datalist data = new Datalist();

	protected MainBoard _panel;
	protected NextblockBoard _nextblockboard;
	protected Thread gamethread;
	protected Random r = new Random();

	protected Blocks currentblock;	// 현재의 블록
	protected Blocks nextblock;		// 다음의 블록
	protected Blocks holdblock; 	// hold 블록
	protected Blocks ghostblock;	// 고스트 블록

	protected boolean isDownPossible = true; 	// 내려갈 수 있는지 확인하는 변수
	protected boolean isDeleteLine; 			// 삭제될 라인이 있는지 확인하는 변수
	protected boolean endcount;					// 게임이 끝났는지 확인하는 변수
	protected boolean isCombo = false; 			// 콤보에 사용 되는 변수
	
	protected int score;
	protected int combo;
	protected int line;
	protected int speed;

	JLabel scorelabel = new JLabel("" + getScore());
	JLabel combolabel = new JLabel("" + getCombo());
	JLabel linelabel = new JLabel("" + getLine());

	// 생성자
	// 게임이 시작 되면 첫번쨰 블록랜덤으로 생성해 준다.
	// 블록을 일정시간 마다 내려주는 스레드를 생성한다.
	public Tetris(MainBoard panel, NextblockBoard nextblockboard) {

		this._nextblockboard = nextblockboard;
		this._panel = panel;

		score = 0;
		combo = 0;
		line = 0;
		speed = 1000;
		endcount = false;

		currentblock = addBlockList(r.nextInt(7) + 1); // 현재 블록을 랜덤 값으로 생성. 
		setCurrentPanel();								//화면에 블록을 보여준다.

		gamethread = new Thread(this);					//스레드 생성 
		gamethread.start();								//스레드 시작 
	}

	// 메소드 정의 : 블록 세팅 
	// 메소드 하는 일 : 게임이 끝나야 하는지 확인후 끝났다면 gameEnd 함수를 부른다. 그렇지 않다면다음 블록을 게임 보드에 적재 , 다음 블록을 생성 해둔다. 
	public void setCurrentPanel() {

		//기본 블록 위치 확인
		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			if (_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i].getCol()] != 0) {
					gameEnd();
			}
		}

		// 패널에 현재 블록 적재 
		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i].getCol()] = currentblock.blockNum;
		}
		//다음 블록 생성 .
		nextblock = addBlockList(r.nextInt(7) + 1);
		//다음 블록을 화면에 보여준다.
		setNextBlockPanel();
	}

	// 메소드 정의 : 새로운 블록 생성 
	// 파라미터 정의 : 랜덤 값 입력 받는다.
	// 메소드 하는 일 : 입력 받은 랜덤 값을 확인하여 다른 블록을 생성  
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

	// 메소드 정의 : 다음 블록 패널을 그려준다
	// 메소드 하는 일 : 전에 있던 블록을 지우고 위치에 맞춰 다음블록을 다시 그려 준다. 현재 블록이 바뀌는 시점 마다 불린다.
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

	// 메소드 정의 : 삭제할 라인을 체크 한다. 
	// 메소드 하는 일 : 
	// 패널을 모두 확인후 지우는 줄이 있다면 delete 함수를 부른다.
	// 여기서 스코어 , 콤보, 삭제 라인 에 필요한 변수들로 계산 .
	public void FullLineCheck() {

		//아래 변수는 콤보 에 사용된다.
		//연속 삭제시 콤보가 늘어나고 연속 삭제가 끈기면 0이 된다. 
		boolean deleteLineMinOne = false;		
		int linecount = 0;

		for (int i = 0; i < 21; i++) {			//한줄 마다 검사
			isDeleteLine = true;
			for (int j = 1; j < 11; j++) {
				if (_panel.board[i][j] == 0) {		//여기서 한칸이라도 공백이라면 지울 라인이 없다. 
					isDeleteLine = false;
				}
			}
			if (isDeleteLine) {			//확인하여 지울게있다면  i 번째 라인을 삭제 .
				DeleteLine(i);
				setLine();				// 라인이 삭제 되었으므로  라인 라벨을 바꿔준다. 
				linecount++;
				m.PlaySound(new File("src//music//boom.wav"));	//라인 삭제 효과음 
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

	// 메소드 정의 : 라인 삭제를 한다
	// 파라미터 정의 : n 번째 라인을 삭제 
	// 메소드 하는 일 : 라인을 삭제, 그 후 위에 있는 라인을 아래로땡겨준다.
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

	// 메소드 정의 : 블록 이동이 가능한지 확인한다.
	// 파라미터 정의 : 블록을 입력 받아 이동이 가능한지 확인 
	// 메소드 하는 일 : 확인 하여 boolean 값을 반환한다.
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

	// 메소드 정의 : 블록 내리기 연산
	// 메소드 하는 일 : 
	// 1. 현재 블록위치를 다른 블록 변수에 저장 한다. 위치만 바꾸어 주고, 화면에 띄우진 않았다.
	// 2. 블록을 일단 옮긴다. 
	// 3. 옮긴 블록에 다른 블록이 없다면 화면에 뛰어주고 끝.
	// 4. 블록을 옮길 수 없다면 더이상 내려갈수 없으니.
	// 5. 위에서 위치시킨 블록을 다시 현재 위치로 옮겨준후 , 
	// 6. 라인 체크를 하고 끝내준다.
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

	// 메소드 정의 : 블록 회전 연산
	// 메소드 하는 일 : 
	// 1. 현재 블록위치를 다른 블록 변수에 저장 한다. 위치만 바꾸어 주고, 화면에 띄우진 않았다.
	// 2. 블록을 일단 옮긴다. 
	// 3. 옮긴 블록에 다른 블록이 없다면 화면에 뛰어주고 끝.
	// 4. 블록을 옮길 수 없다면 블록을 다시 현재 위치로 옮겨준후 끝낸다.
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

	// 메소드 정의 : 블록 왼쪽 이동 연산
	// 메소드 하는 일 : 
	// 1. 현재 블록위치를 다른 블록 변수에 저장 한다. 위치만 바꾸어 주고, 화면에 띄우진 않았다.
	// 2. 블록을 일단 옮긴다. 
	// 3. 옮긴 블록에 다른 블록이 없다면 화면에 뛰어주고 끝.
	// 4. 블록을 옮길 수 없다면 블록을 다시 현재 위치로 옮겨준후 끝낸다.
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

	// 메소드 정의 : 블록 오른쪽 이동 연산
	// 메소드 하는 일 : 
	// 1. 현재 블록위치를 다른 블록 변수에 저장 한다. 위치만 바꾸어 주고, 화면에 띄우진 않았다.
	// 2. 블록을 일단 옮긴다. 
	// 3. 옮긴 블록에 다른 블록이 없다면 화면에 뛰어주고 끝.
	// 4. 블록을 옮길 수 없다면 블록을 다시 현재 위치로 옮겨준후 끝낸다.
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

	// 키입력을 받는다.
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
			//스페이스바 연산은 내려갈 곳이 없을때까지 블록을 내려주면 된다.
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
				
				// 게임이 끝났을때 흑백 화면으로 면들어주고 랭킹 등록 화면이 뜬다.
				if (endcount) {
					for (int i = 0; i < 21; i++) {
						for (int j = 0; j < 11; j++)
							if (_panel.board[i][j] != 0) {
								_panel.board[i][j] = 8;
							}
						Thread.sleep(70);
					}
					m.PlaySound(new File("src//music//gameEnd.wav"));
					
					//10등안에 들었을 경우 
					//파일에 10등까지 없다면 그냥 파일 저장 
					if (data.getscoreList().size() < 10) {
						new WriteRanking(score);	
					} 
					//파일에 10이 넘는다면 top 10보다 큰점 수인지 확인 후 저장 
					else if (Integer.parseInt(data.getscoreList().get(9)) < score) {
						new WriteRanking(score);
					} 
					//아니라면 그냥 게임 종료창이 뜬다. 
					else {
						new WriteRanking();
					}
					gamethread.stop();
				}
				
				// 게임이 끝나지 않았다면 계속 블록을 한칸씩 내려주는 일을 한다.
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

	// 메소드 정의 : 점수 라벨을 갱신해준다 . 현제 콤보와 점수를 합쳐준 스코어로 갱신
	// 파라미터 정의 : 삭제 라인 수와 콤보 
	// 메소드 하는 일 : 파라미터를 이용해 각각 다른 값들은 입력 받는다. 
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

	// 메소드 정의 : 콤보 값 갱신 
	// 파라미터 정의 : 콤보 였는지
	// 메소드 하는 일 : 맞다면 + 1 아니라면 0 
	public void setCombo(boolean isCombo) {
		if (isCombo == true) {
			combo++;
		} else
			combo = 0;

		combolabel.setText("" + getCombo());
	}

	// 메소드 정의 : 트린 라인수 갱신
	// 메소드 하는 일 : 라인 ++ 
	public void setLine() {
		line++;
		linelabel.setText("" + getLine());
	}

	// 메소드 정의 : 블록이 내려오는 속도 조절
	// 파라미터 정의 : 삭제 라인 수를 이용
	// 메소드 하는 일 : 10칸 마다 속도가 1단계씩 빨라진다. 
	// 아직 미구현 -> 게임 재미를 위해 90 라인이 넘어간다면 다른 이벤트 발생   
	public void setSpeed(int line) {
		if (line < 90)
			speed = 1000 - ((int) (line / 10)) * 100;
		else {
			// 90라인이 넘어가면
		}
	}
}
