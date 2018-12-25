package tetris_1;

public class Blocks {

	final static int BLOCKNUM = 4; // ��� ����� 4���� ���� ����� ������ ����
	OneBlock[] oneblock; // ���� ��� �迭
	protected int blockNum; // ������ ��ϵ��� ���� �ĺ� ��ȣ�� �־���. ����� ������ �ֱ� ���ؼ� �Ǵ� � �������
							// Ȯ�� �ϴ� ���
	protected boolean rotateflag; // rotateNum == 2 �� ��� �Ǵ� �Ҹ��� ����. ȸ���� 2������ ��쿡
									// true ������ ��Ȳ�� T F ���� �־���.
	protected int rotateNum;
	// ��ϴ� ȸ�� �� 1�� or 2 �� or 4��
	// ���簢�� ���( O )�� ȸ������ �������� 1����
	// ������ 3�� ���(I Z S)�� ȸ���ؼ� ������ ����� 2����
	// ������ 3�� ��� (L J T)�� ȸ���ؼ� ������ ����� 4���� �̴�.

	// ������
	public Blocks() {
		oneblock = new OneBlock[BLOCKNUM]; // ���� ��� 4�� ����
	}

	// �޼ҵ� ���� : ���� ���ӿ��� ����� �̵� �ϱ��� ��ϵ��� ��� ������ ���� �ص� �� Ȯ�� �Ѵ�. 
	// �޼ҵ� �ϴ� �� : ��� 4�� �迭�� ��ȯ �� �ش�
	public OneBlock[] CloneBlock() {
		OneBlock[] temp = new OneBlock[BLOCKNUM];
		for (int i = 0; i < BLOCKNUM; i++) {
			temp[i] = new OneBlock(this.oneblock[i].getRow(), this.oneblock[i].getCol());
		}
		return temp;
	}

	// �޼ҵ� ���� : ��� �ð� ���� ȸ�� �̵�
	// �Ķ���� ���� : num ������ rotateNum �� �޴´� .(���� ���𿡼� �ּ����� ���� )  
	// �޼ҵ� �ϴ� �� : ���� ����� x,y ��ǥ�� ���� ���� ��ϵ��� ��ǥ ���� �ٲپ��ش�. 
	// x,y ��ǥ�� 0,0���� �̵� -> (x,y)�� (-y ,x) �� �ٲپ��ָ� ���� �ð� ���� ȸ���� �����ϴ�. �ݽð� ������ �ݴ�� 
	public void UpKey(int num) {
		int x = oneblock[0].getRow();
		int y = oneblock[0].getCol();

		if (num == 1) {

		} else if (num == 2) {
			if (rotateflag) {
				for (int i = 0; i < BLOCKNUM; i++) {
					oneblock[i].set_RC(-(oneblock[i].getCol() - y) + x, oneblock[i].getRow() - x + y);
				}
				rotateflag = false;
			} else {
				for (int i = 0; i < BLOCKNUM; i++) {
					oneblock[i].set_RC(oneblock[i].getCol() - y + x, -(oneblock[i].getRow() - x) + y);
				}
				rotateflag = true;
			}
		} else if (num == 4) {
			for (int i = 0; i < BLOCKNUM; i++) {
				oneblock[i].set_RC(oneblock[i].getCol() - y + x, -(oneblock[i].getRow() - x) + y);
			}
		}
	}

	// �޼ҵ� ���� : ��� �Ʒ��� ��ĭ �̵�
	// �޼ҵ� �ϴ� �� : ���� ����� x,y ��ǥ�� ���� ���� ��ϵ��� ��ǥ ���� �ٲپ��ش�.
	public void DownKey() {
		for (int i = 0; i < BLOCKNUM; i++) {
			oneblock[i].set_RC(oneblock[i].getRow() + 1, oneblock[i].getCol());
		}
	}

	// �޼ҵ� ���� : ��� ���� ��ĭ �̵�
	// �޼ҵ� �ϴ� �� : ���� ����� x,y ��ǥ�� ���� ���� ��ϵ��� ��ǥ ���� �ٲپ��ش�.
	public void LeftKey() {
		for (int i = 0; i < BLOCKNUM; i++) {
			oneblock[i].set_RC(oneblock[i].getRow(), oneblock[i].getCol() - 1);
		}
	}

	// �޼ҵ� ���� : ��� ������ ��ĭ �̵�
	// �޼ҵ� �ϴ� �� : ���� ����� x,y ��ǥ�� ���� ���� ��ϵ��� ��ǥ ���� �ٲپ��ش�.
	public void RightKey() {
		for (int i = 0; i < BLOCKNUM; i++) {
			oneblock[i].set_RC(oneblock[i].getRow(), oneblock[i].getCol() + 1);
		}
	}

}

// ���簢�� ����� ���
class OBlock extends Blocks {
	// ��ϵ��� �ʱⰪ ����
	public OBlock() {
		super();
		rotateNum = 1;
		blockNum = 1;
		// ������ ����� �⺻ ��� ��ġ ( �� ó�� ����� ������ ��� �־�� �ϴ� ����� ��ġ ) �� ������ �ִ�.
		oneblock[0] = new OneBlock(1, 5);
		oneblock[1] = new OneBlock(2, 5);
		oneblock[2] = new OneBlock(1, 6);
		oneblock[3] = new OneBlock(2, 6);
	}
}

// ���� ����� ���
class IBlock extends Blocks {
	// ��ϵ��� �ʱⰪ ����
	public IBlock() {
		super();
		rotateNum = 2;
		blockNum = 2;
		rotateflag = true;
		// ������ ����� �⺻ ��� ��ġ ( �� ó�� ����� ������ ��� �־�� �ϴ� ����� ��ġ ) �� ������ �ִ�.
		oneblock[0] = new OneBlock(1, 5);
		oneblock[1] = new OneBlock(1, 3);
		oneblock[2] = new OneBlock(1, 4);
		oneblock[3] = new OneBlock(1, 6);
	}
}

// s ����� ���
class SBlock extends Blocks {
	// ��ϵ��� �ʱⰪ ����
	public SBlock() {
		super();
		rotateNum = 2;
		blockNum = 3;
		rotateflag = true;
		// ������ ����� �⺻ ��� ��ġ ( �� ó�� ����� ������ ��� �־�� �ϴ� ����� ��ġ ) �� ������ �ִ�.
		oneblock[0] = new OneBlock(1, 5);
		oneblock[1] = new OneBlock(2, 5);
		oneblock[2] = new OneBlock(1, 6);
		oneblock[3] = new OneBlock(2, 4);
	}
}

// z ����� ���
class ZBlock extends Blocks {
	// ��ϵ��� �ʱⰪ ����
	public ZBlock() {
		super();
		rotateNum = 2;
		blockNum = 4;
		rotateflag = true;
		// ������ ����� �⺻ ��� ��ġ ( �� ó�� ����� ������ ��� �־�� �ϴ� ����� ��ġ ) �� ������ �ִ�.
		oneblock[0] = new OneBlock(1, 5);
		oneblock[1] = new OneBlock(2, 5);
		oneblock[2] = new OneBlock(1, 4);
		oneblock[3] = new OneBlock(2, 6);
	}
}

// l����� ���
class LBlock extends Blocks {
	// ��ϵ��� �ʱⰪ ����
	public LBlock() {
		super();
		rotateNum = 4;
		blockNum = 5;
		// ������ ����� �⺻ ��� ��ġ ( �� ó�� ����� ������ ��� �־�� �ϴ� ����� ��ġ ) �� ������ �ִ�.
		oneblock[0] = new OneBlock(1, 5);
		oneblock[1] = new OneBlock(1, 4);
		oneblock[2] = new OneBlock(1, 6);
		oneblock[3] = new OneBlock(2, 4);
	}
}

// J����� ���
class JBlock extends Blocks {
	// ��ϵ��� �ʱⰪ ����
	public JBlock() {
		super();
		rotateNum = 4;
		blockNum = 6;
		// ������ ����� �⺻ ��� ��ġ ( �� ó�� ����� ������ ��� �־�� �ϴ� ����� ��ġ ) �� ������ �ִ�.
		oneblock[0] = new OneBlock(1, 5);
		oneblock[1] = new OneBlock(1, 4);
		oneblock[2] = new OneBlock(1, 6);
		oneblock[3] = new OneBlock(2, 6);
	}
}

// T ����� ���
class TBlock extends Blocks {
	// ��ϵ��� �ʱⰪ ����
	public TBlock() {
		super();
		rotateNum = 4;
		blockNum = 7;
		// ������ ����� �⺻ ��� ��ġ ( �� ó�� ����� ������ ��� �־�� �ϴ� ����� ��ġ ) �� ������ �ִ�.
		oneblock[0] = new OneBlock(1, 5);
		oneblock[1] = new OneBlock(1, 4);
		oneblock[2] = new OneBlock(1, 6);
		oneblock[3] = new OneBlock(2, 5);
	}
}