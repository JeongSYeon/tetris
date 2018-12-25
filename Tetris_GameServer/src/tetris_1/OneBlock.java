package tetris_1;

public class OneBlock {
	private int _row;		//메인 게임 보드의 x 좌표
	private int _col;		//메인 게임 보드의 y 좌표

	OneBlock(int row, int col){
		this._row = row;
		this._col = col;
	}
	
	//x좌표 반환
	public int getRow(){
		return _row;
	}
	
	//y좌표 반환
	public int getCol(){
		return _col;
	}
	
	//x,y 좌표 입력 
	public void set_RC(int row, int col) {
		_row = row;
		_col = col;
	}
}
