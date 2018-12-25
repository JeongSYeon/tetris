package tetris_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Datalist {

	// ���� �̸��� ������ ��� ���� ��� ����Ʈ
	private ArrayList<String> strlist = new ArrayList<String>();
	private ArrayList<String> scorelist = new ArrayList<String>();

	// ���� �̸� , ������ ���� �� �ҷ� �´�.
	private File _name = new File("src/RankText/Name.file");
	private File _score = new File("src/RankText/score.file");

	// �Ķ���Ͱ� ���ٸ� �б⸸ �Ѵ�.
	public Datalist() {
		FileRead();
	}

	// �Ķ���Ͱ� �ִٸ� ���� �д´�.
	public Datalist(String str, int score) {
		FileWrite(str, score);
		FileRead();
	}

	// �޼ҵ� ���� : ������ ������ Ȯ�� ���� ��������  
	// �Ķ���� ���� : n , s ���� ���Ͽ� ����� �̸��� �迭 ���ڿ� �̴�. 
	// �޼ҵ� �ϴ� �� : �̸��� �迭 ���ڿ��� ���� ���� ���� ���������� �� �޼ҵ带 �����Ѵ�. 
	// 				���� �� �̸�, �迭�� ����Ʈ�� ������������ �Է��Ѵ�. �� ������ ���� ž 10 �� ���� �� �� �ִ�. 
	public void sort(String n, String s) {
		for (int i = 0; i < scorelist.size() + 1; i++) {
			if (scorelist.isEmpty()) {
				scorelist.add(s);
				strlist.add(n);
				return;
			} else if (i == scorelist.size()) {
				scorelist.add(s);
				strlist.add(n);
				return;
			} else if (Integer.parseInt(scorelist.get(i)) < Integer.parseInt(s)) {
				scorelist.add(i, s);
				strlist.add(i, n);
				return;
			}
		}
	}

	// �޼ҵ� ���� : �̸��� ������ ���Ͽ� �Է��Ѵ�.
	// �޼ҵ� �ϴ� �� : �̸��� ������ ���ۿ� ��� ���Ͽ� �Է��� ������ ��ĭ �����ش�. ������ ������ ��
	public void FileWrite(String str, int score) {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(_name, true));
			BufferedWriter writer1 = new BufferedWriter(new FileWriter(_score, true));

			writer.write(str);
			writer.newLine();
			writer1.write("" + score);
			writer1.newLine();

			writer.close();
			writer1.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �޼ҵ� ���� : ������ �̸� ������ �д´�. 
	// �޼ҵ� �ϴ� �� : ���۸��Ϳ� ������ ���� ������ ������� ���������� �д´�. ���� ���������� sort�޼ҵ带 �̿��Ͽ� �迭�� �־��ش�. ���������� ������ �ݴ´�.
	public void FileRead() {

		String tempstr;
		String tempsco;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(_name));
			BufferedReader reader1 = new BufferedReader(new FileReader(_score));

			while ((tempstr = reader.readLine()) != null) {
				tempsco = reader1.readLine();
				sort(tempstr, tempsco);

			}

			reader.close();
			reader1.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// �޼ҵ� ���� : �̸� ����Ʈ ��ȯ
	public ArrayList<String> getnameList() {
		return strlist;
	}

	// �޼ҵ� ���� : ���� ����Ʈ ��ȯ
	public ArrayList<String> getscoreList() {
		return scorelist;
	}

}