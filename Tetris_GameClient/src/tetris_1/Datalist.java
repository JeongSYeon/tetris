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

	// 각각 이름과 점수를 담기 위한 어레이 리스트
	private ArrayList<String> strlist = new ArrayList<String>();
	private ArrayList<String> scorelist = new ArrayList<String>();

	// 각각 이름 , 점수를 파일 을 불러 온다.
	private File _name = new File("src/RankText/Name.file");
	private File _score = new File("src/RankText/score.file");

	// 파라미터가 없다면 읽기만 한다.
	public Datalist() {
		FileRead();
	}

	// 파라미터가 있다면 쓰고 읽는다.
	public Datalist(String str, int score) {
		FileWrite(str, score);
		FileRead();
	}

	// 메소드 정의 : 파일의 점수를 확인 오름 차순으로  
	// 파라미터 정의 : n , s 각각 파일에 저장돤 이름과 배열 문자열 이다. 
	// 메소드 하는 일 : 이름과 배열 문자열을 파일 한줄 한줄 읽을때마다 이 메소드를 실행한다. 
	// 				파일 의 이름, 배열을 리스트에 내림차순으로 입력한다. 이 정렬을 통해 탑 10 을 추출 할 수 있다. 
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

	// 메소드 정의 : 이름과 점수를 파일에 입력한다.
	// 메소드 하는 일 : 이름과 점수를 버퍼에 담아 파일에 입력후 라인을 한칸 내려준다. 파일을 닫으면 끝
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

	// 메소드 정의 : 점수와 이름 파일을 읽는다. 
	// 메소드 하는 일 : 버퍼리터에 파일을 열고 라인이 비어있지 않을때까지 읽는다. 한줄 읽을때마다 sort메소드를 이용하여 배열에 넣어준다. 마지막으로 파일을 닫는다.
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
	
	// 메소드 정의 : 이름 리스트 반환
	public ArrayList<String> getnameList() {
		return strlist;
	}

	// 메소드 정의 : 점수 리스트 반환
	public ArrayList<String> getscoreList() {
		return scorelist;
	}

}