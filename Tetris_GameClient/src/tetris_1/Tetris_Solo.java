package tetris_1;

import java.awt.event.KeyEvent;
import java.io.File;

//���� ��Ʈ���� ������ �޾ƿ� hole Ű�� �߰�, ��Ʈ��嵵 �߰� 
public class Tetris_Solo extends Tetris {

	NextblockBoard _holdboard;

	private boolean isholdOn = false; // hold board �� ����� �÷����ִ��� ������ ����
	private int holdcount = 3; // hold �������� 3������ ��� ���� �ϴ�.

	// ������ �Ķ���ͷ� �Ź� ���� �ٲپ��� 3���� �г��� �Է� �޴´�.
	public Tetris_Solo(MainBoard panel, NextblockBoard nextblockboard, NextblockBoard holdboard) {
		super(panel, nextblockboard);
		_holdboard = holdboard;

	}

	// �޼ҵ� ���� : ��Ʈ ����� ���� ������ �־�� �Ѵ�. �ٸ� ����� ���������� 
	// �Ķ���� ���� : ��Ʈ ����� �޴´�
	// �޼ҵ� �ϴ� �� : ��Ʈ ����� �Ķ���ͷ� �޾�, ������ ���� �� �ִ��� Ȯ���Ѵ�.
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

	// �޼ҵ� ���� : ��Ʈ ��� �߰�
	// �Ҹ��� ���� : �� ���� Ű�Է��� ���������� �׷� �־�� �Ѵ�.
	// �޼ҵ� �ϴ� �� :
	// 1. ������ ����� ��Ʈ ������� �߰��Ѵ�.
	// 2. ��Ʈ ����� ������ �ϰ� �Ѵ�.
	// 3. ���� ��� ��ġ�� ��Ʈ ��� ��ġ�� ���� �ϰ� �Ѵ�.
	// 4. ��Ʈ ����� ������ ������ �� ���� ����� 0���� �صд�.
	// 5. ��Ʈ ����� �ٸ� ����� ���������� ������.
	// 6. ���� ��ϰ� , ��Ʈ ����� ȭ�鿡 ���� �ش�.
	
	// 7. ��Ʈ ����� ���� ���� ��ġ�� �ʰ� ���� �־�� �Ѵ�. (1�� ������ �ؾ� �ϴ���) 
	public void ghostpiece() {
		
		boolean ghostdelete = true;	// ��Ʈ ����� �̵��Ҷ� ���� �־���� ������� Ȯ�� �ϴ� ��� �ʿ� �ϴ� 
		// ��Ʈ ��� �Ⱥ��̰��� �־�� �Ѵ�.  ��Ʈ ����� ������� �ʴٸ�
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
		ghostblock = addBlockList(currentblock.blockNum); // ��Ʈ ����� ������ ����� ���������� �̴�.
		ghostblock.blockNum += 8; // ��Ʈ ����� ���� ��ϰ� �ٸ��� ������ �ϴ�.

		// ���� ����� ��ġ�� ��Ʈ ��� ��ġ�� �Է��Ѵ�.
		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			ghostblock.oneblock[i].set_RC(currentblock.oneblock[i].getRow(), currentblock.oneblock[i].getCol());
		}
		// ���� ����� 0���� ��� ����
		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i].getCol()] = 0;
		}
		// ��Ʈ ����� ������ ������.
		while (isGhostPossible(ghostblock)) {
			ghostblock.DownKey();
		}
		// ��Ʈ ����� ���̰�
		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			_panel.board[ghostblock.oneblock[i].getRow()][ghostblock.oneblock[i].getCol()] = ghostblock.blockNum;
		}
		// �������� �ٽ� ���̰�
		for (int i = 0; i < Blocks.BLOCKNUM; i++) {
			_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i].getCol()] = currentblock.blockNum;
		}
	}

	// �޼ҵ� ���� : hold Ű�� �Է� ������ �������� �̺�Ʈ
	// �޼ҵ� �ϴ� �� :
	// Ȧ�� �г��� ����ִ� ���
	// ȦƮ �гο� ����� ���ִ� ���
	// ���� �ΰ����� ������ �׼��� ó��
	public void holdKeyAction() {
		// Ȧ�� ī�� �� -1 ;
		// hold board �� ����� �ִٸ� -> hold ��� current ������� �̵� -> ���� current ��� ����
		// -> ���� ��� ����
		if (isholdOn) {
			holdcount--;
			for (int i = 0; i < Blocks.BLOCKNUM; i++) {
				_panel.board[currentblock.oneblock[i].getRow()][currentblock.oneblock[i].getCol()] = 0;
			}
			currentblock = holdblock;
			setholdBlockPanel();
			isholdOn = false;

		} else {
			// hold board �� ����� ���ٸ� -> current ����� hold ��Ͽ� ���� �̵� -> next�����
			// current ������� �̵� -> ���� ��� ����
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

	// �޼ҵ� ���� : hold ����� ���� ���ִ� �޼ҵ�
	// �޼ҵ� �ϴ� �� : ���� �ִ� ����� ����� ��ġ�� ���� Ȧ�� ����� �ٽ� �׷� �ش�.
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

	// h Ű���Է� ������ ���� ó��
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
