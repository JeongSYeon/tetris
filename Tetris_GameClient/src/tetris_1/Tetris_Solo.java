package tetris_1;

import java.awt.event.KeyEvent;
import java.io.File;

//메인 테트리스 게임을 받아와 hole 키를 추가, 고스트모드도 추가 
public class Tetris_Solo extends Tetris {

	NextblockBoard _holdboard;

	private boolean isholdOn = false; // hold board 에 블록이 올려져있는지 없는지 상태
	private int holdcount = 3; // hold 아이템은 3번까지 사용 가능 하다.

	// 생성자 파라미터로 매번 값을 바꾸어줄 3개의 패널을 입력 받는다.
	public Tetris_Solo(MainBoard panel, NextblockBoard nextblockboard, NextblockBoard holdboard) {
		super(panel, nextblockboard);
		_holdboard = holdboard;

	}

	// 메소드 정의 : 고스트 블록은 먼저 내려가 있어야 한다. 다른 블록을 만날때까지 
	// 파라미터 정의 : 고스트 블록을 받는다
	// 메소드 하는 일 : 고스트 블록을 파라미터로 받아, 밑으로 내릴 수 있는지 확인한다.
	public boolean isGhostPossible(Blocks block) {
		boolean flag = true;
		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			int blocknumber = _panel.board[block.oneblock[i].getRow() + 1][block.oneblock[i].getCol()];
			if (blocknumber > 0 && blocknumber < 9) {
				flag = false;
			}
		}
		return flag;
	}

	// 메소드 정의 : 고스트 모드 추가
	// 불리는 시점 : 매 순간 키입력을 받을때마다 그려 주어야 한다.
	// 메소드 하는 일 :
	// 1. 현재의 블록을 고스트 블록으로 추가한다.
	// 2. 고스트 블록을 불투명 하게 한다.
	// 3. 현재 블록 위치를 고스트 블록 위치와 동일 하게 한다.
	// 4. 고스트 블록을 밑으로 내리기 전 현재 블록을 0으로 해둔다.
	// 5. 고스트 블록을 다른 블록을 만날때까지 내린다.
	// 6. 현재 블록과 , 고스트 블록을 화면에 보여 준다.
	
	// 7. 고스트 블록이 전의 값과 겹치지 않게 지워 주어야 한다. (1번 이전에 해야 하는일) 
	public void ghostpiece() {
		
		boolean ghostdelete = true;	// 고스트 블록이 이동할때 지워 주어야할 블록인지 확인 하는 경우 필요 하다 
		// 고스트 블록 안보이게해 주어야 한다.  고스트 블록이 비어있지 않다면
		if (ghostblock != null) {
			for (int i = 0; i < Blocks.BLOCKNUM; i++) {
				if (_panel.board[ghostblock.oneblock[i].getRow()][ghostblock.oneblock[i]
						.getCol()] != ghostblock.blockNum) {
					ghostdelete = false;
				}
			if (ghostdelete) {
					_panel.board[ghostblock.oneblock[i].getRow()][ghostblock.oneblock[i].getCol()] = 0;
				}
			ghostdelete = true;
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		ghostblock = addBlockList(currentblock.blockNum); // 고스트 블록은 현재의 블록이 내려간형태 이다.
		ghostblock.blockNum += 8; // 고스트 블록은 현재 블록과 다르게 불투명 하다.

		// 현재 블록의 위치를 고스트 블록 위치로 입력한다.
		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			ghostblock.oneblock[i].set_RC(currentblock.oneblock[i].getRow(), currentblock.oneblock[i].getCol());
		}
		// 현재 블록을 0으로 잠시 지정
		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i].getCol()] = 0;
		}
		// 고스트 블록을 밑으로 내린다.
		while (isGhostPossible(ghostblock)) {
			ghostblock.DownKey();
		}
		// 고스트 블록을 보이게
		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			_panel.board[ghostblock.oneblock[i].getRow()][ghostblock.oneblock[i].getCol()] = ghostblock.blockNum;
		}
		// 현재블록을 다시 보이게
		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i].getCol()] = currentblock.blockNum;
		}
	}

	// 메소드 정의 : hold 키를 입력 했을때 벌어지는 이벤트
	// 메소드 하는 일 :
	// 홀드 패널이 비어있는 경우
	// 홀트 패널에 블록이 차있는 경우
	// 위의 두가지로 나누어 액션을 처리
	public void holdKeyAction() {
		// 홀드 카운 터 -1 ;
		// hold board 에 블록이 있다면 -> hold 블록 current 블록으로 이동 -> 기존 current 블록 삭제
		// -> 게임 계속 진행
		if (isholdOn) {
			holdcount--;
			for (int i = 0; i < Blocks.BLOCKNUM; i++) {
				_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i].getCol()] = 0;
			}
			currentblock = holdblock;
			setholdBlockPanel();
			isholdOn = false;

		} else {
			// hold board 에 블록이 없다면 -> current 블록을 hold 블록에 보드 이동 -> next블록을
			// current 블록으로 이동 -> 게임 계속 진행
			holdblock = currentblock;
			for (int i = 0; i < Blocks.BLOCKNUM; i++) {
				_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i].getCol()] = 0;
			}
			currentblock = nextblock;

			nextblock = addBlockList(r.nextInt(7) + 1);
			setNextBlockPanel();
			// nextblock.setCurrentBlocks();
			setholdBlockPanel();
			isholdOn = true;
		}
	}

	// 메소드 정의 : hold 블록을 갱신 해주는 메소드
	// 메소드 하는 일 : 전에 있던 블록을 지우고 위치에 맞춰 홀드 블록을 다시 그려 준다.
	public void setholdBlockPanel() {
		for (int i = 1; i < 5; i++)
			for (int j = 1; j < 5; j++)
				_holdboard.board[i][j] = 0;
		if (!isholdOn) {
			holdblock = addBlockList(holdblock.blockNum);
			for (int i = 0; i < Blocks.BLOCKNUM; i++) {
				int x = holdblock.oneblock[i].getRow();
				int y = holdblock.oneblock[i].getCol();
				int num = holdblock.blockNum;
				if (num == 1) {
					_holdboard.board[x + 1][y - 3] = holdblock.blockNum;
				} else if (num == 2) {
					_holdboard.board[x + 1][y - 2] = holdblock.blockNum;
				} else if (num == 3) {
					_holdboard.board[x + 1][y - 2] = holdblock.blockNum;
				} else if (num == 4) {
					_holdboard.board[x + 1][y - 2] = holdblock.blockNum;
				} else if (num == 5) {
					_holdboard.board[x + 1][y - 3] = holdblock.blockNum;
				} else if (num == 6) {
					_holdboard.board[x + 1][y - 3] = holdblock.blockNum;
				} else if (num == 7) {
					_holdboard.board[x + 1][y - 2] = holdblock.blockNum;
				}
			}
		}
	}

	// h 키를입력 했을때 엑션 처리
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		if (e.getKeyCode() == KeyEvent.VK_H) {
			m.PlaySound(new File("src//music//pip.wav"));

			if (holdcount > 0)
				holdKeyAction();
		}
		if(!endcount)
			ghostpiece();
	}

}
