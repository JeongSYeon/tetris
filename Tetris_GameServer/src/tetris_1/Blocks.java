package tetris_1;

public class Blocks {

	final static int BLOCKNUM = 4; // 모든 블록은 4개의 작은 블록이 합쳐진 형태
	OneBlock[] oneblock; // 작은 블록 배열
	protected int blockNum; // 각각의 블록들은 고유 식별 변호를 주었다. 블록의 색상을 주기 위해서 또는 어떤 블록인지
							// 확인 하는 경우
	protected boolean rotateflag; // rotateNum == 2 에 사용 되는 불리안 변수. 회전이 2가지인 경우에
									// true 각각의 상황을 T F 값을 주었다.
	protected int rotateNum;
	// 블록당 회전 수 1개 or 2 개 or 4개
	// 정사각형 블록( O )은 회전하지 않음으로 1가지
	// 나머지 3개 블록(I Z S)은 회전해서 나오는 모양이 2가지
	// 나머지 3개 블록 (L J T)은 회전해서 나오는 모양이 4가지 이다.

	// 생성자
	public Blocks() {
		oneblock = new OneBlock[BLOCKNUM]; // 작은 블록 4개 생성
	}

	// 메소드 정의 : 메인 게임에서 블록을 이동 하기전 블록들을 잠시 변수에 저장 해둔 후 확인 한다. 
	// 메소드 하는 일 : 블록 4개 배열을 반환 해 준다
	public OneBlock[] CloneBlock() {
		OneBlock[] temp = new OneBlock[BLOCKNUM];
		for (int i = 0; i < BLOCKNUM; i++) {
			temp[i] = new OneBlock(this.oneblock[i].getRow(), this.oneblock[i].getCol());
		}
		return temp;
	}

	// 메소드 정의 : 블록 시계 방향 회전 이동
	// 파라미터 정의 : num 값으로 rotateNum 을 받는다 .(변수 선언에서 주석으로 설명 )  
	// 메소드 하는 일 : 각각 블록의 x,y 좌표를 가진 작은 블록들의 좌표 값을 바꾸어준다. 
	// x,y 좌표를 0,0으로 이동 -> (x,y)를 (-y ,x) 로 바꾸어주면 쉽게 시계 방향 회전이 가능하다. 반시계 방향은 반대로 
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

	// 메소드 정의 : 블록 아래쪽 한칸 이동
	// 메소드 하는 일 : 각각 블록의 x,y 좌표를 가진 작은 블록들의 좌표 값을 바꾸어준다.
	public void DownKey() {
		for (int i = 0; i < BLOCKNUM; i++) {
			oneblock[i].set_RC(oneblock[i].getRow() + 1, oneblock[i].getCol());
		}
	}

	// 메소드 정의 : 블록 왼쪽 한칸 이동
	// 메소드 하는 일 : 각각 블록의 x,y 좌표를 가진 작은 블록들의 좌표 값을 바꾸어준다.
	public void LeftKey() {
		for (int i = 0; i < BLOCKNUM; i++) {
			oneblock[i].set_RC(oneblock[i].getRow(), oneblock[i].getCol() - 1);
		}
	}

	// 메소드 정의 : 블록 오른쪽 한칸 이동
	// 메소드 하는 일 : 각각 블록의 x,y 좌표를 가진 작은 블록들의 좌표 값을 바꾸어준다.
	public void RightKey() {
		for (int i = 0; i < BLOCKNUM; i++) {
			oneblock[i].set_RC(oneblock[i].getRow(), oneblock[i].getCol() + 1);
		}
	}

}

// 정사각형 모양의 블록
class OBlock extends Blocks {
	// 블록들의 초기값 설정
	public OBlock() {
		super();
		rotateNum = 1;
		blockNum = 1;
		// 각각의 블록은 기본 블록 위치 ( 맨 처음 블록이 나왔을 경우 있어야 하는 블록의 위치 ) 를 가지고 있다.
		oneblock[0] = new OneBlock(1, 5);
		oneblock[1] = new OneBlock(2, 5);
		oneblock[2] = new OneBlock(1, 6);
		oneblock[3] = new OneBlock(2, 6);
	}
}

// 일자 모양의 블록
class IBlock extends Blocks {
	// 블록들의 초기값 설정
	public IBlock() {
		super();
		rotateNum = 2;
		blockNum = 2;
		rotateflag = true;
		// 각각의 블록은 기본 블록 위치 ( 맨 처음 블록이 나왔을 경우 있어야 하는 블록의 위치 ) 를 가지고 있다.
		oneblock[0] = new OneBlock(1, 5);
		oneblock[1] = new OneBlock(1, 3);
		oneblock[2] = new OneBlock(1, 4);
		oneblock[3] = new OneBlock(1, 6);
	}
}

// s 모양의 블록
class SBlock extends Blocks {
	// 블록들의 초기값 설정
	public SBlock() {
		super();
		rotateNum = 2;
		blockNum = 3;
		rotateflag = true;
		// 각각의 블록은 기본 블록 위치 ( 맨 처음 블록이 나왔을 경우 있어야 하는 블록의 위치 ) 를 가지고 있다.
		oneblock[0] = new OneBlock(1, 5);
		oneblock[1] = new OneBlock(2, 5);
		oneblock[2] = new OneBlock(1, 6);
		oneblock[3] = new OneBlock(2, 4);
	}
}

// z 모양의 블록
class ZBlock extends Blocks {
	// 블록들의 초기값 설정
	public ZBlock() {
		super();
		rotateNum = 2;
		blockNum = 4;
		rotateflag = true;
		// 각각의 블록은 기본 블록 위치 ( 맨 처음 블록이 나왔을 경우 있어야 하는 블록의 위치 ) 를 가지고 있다.
		oneblock[0] = new OneBlock(1, 5);
		oneblock[1] = new OneBlock(2, 5);
		oneblock[2] = new OneBlock(1, 4);
		oneblock[3] = new OneBlock(2, 6);
	}
}

// l모양의 블록
class LBlock extends Blocks {
	// 블록들의 초기값 설정
	public LBlock() {
		super();
		rotateNum = 4;
		blockNum = 5;
		// 각각의 블록은 기본 블록 위치 ( 맨 처음 블록이 나왔을 경우 있어야 하는 블록의 위치 ) 를 가지고 있다.
		oneblock[0] = new OneBlock(1, 5);
		oneblock[1] = new OneBlock(1, 4);
		oneblock[2] = new OneBlock(1, 6);
		oneblock[3] = new OneBlock(2, 4);
	}
}

// J모양의 블록
class JBlock extends Blocks {
	// 블록들의 초기값 설정
	public JBlock() {
		super();
		rotateNum = 4;
		blockNum = 6;
		// 각각의 블록은 기본 블록 위치 ( 맨 처음 블록이 나왔을 경우 있어야 하는 블록의 위치 ) 를 가지고 있다.
		oneblock[0] = new OneBlock(1, 5);
		oneblock[1] = new OneBlock(1, 4);
		oneblock[2] = new OneBlock(1, 6);
		oneblock[3] = new OneBlock(2, 6);
	}
}

// T 모양의 블록
class TBlock extends Blocks {
	// 블록들의 초기값 설정
	public TBlock() {
		super();
		rotateNum = 4;
		blockNum = 7;
		// 각각의 블록은 기본 블록 위치 ( 맨 처음 블록이 나왔을 경우 있어야 하는 블록의 위치 ) 를 가지고 있다.
		oneblock[0] = new OneBlock(1, 5);
		oneblock[1] = new OneBlock(1, 4);
		oneblock[2] = new OneBlock(1, 6);
		oneblock[3] = new OneBlock(2, 5);
	}
}