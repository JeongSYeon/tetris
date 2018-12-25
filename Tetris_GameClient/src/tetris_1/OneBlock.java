package tetris_1;

public class OneBlock {
	private int _row;		//���� ���� ������ x ��ǥ
	private int _col;		//���� ���� ������ y ��ǥ

	OneBlock(int row, int col){
		this._row = row;
		this._col = col;
	}
	
	//x��ǥ ��ȯ
	public int getRow(){
		return _row;
	}
	
	//y��ǥ ��ȯ
	public int getCol(){
		return _col;
	}
	
	//x,y ��ǥ �Է� 
	public void set_RC(int row, int col) {
		_row = row;
		_col = col;
	}
}
