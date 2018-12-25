package tetris_1;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//배경음악, 효과음을 재생 하는 클래스 스레드 상속 
public class Music extends Thread {

	private File Clap;

	public Music() {
		Clap = new File("src//music//bgm.wav");
	}

	// 메소드 정의 : 1번 일어나는 효과음을 재생 시켜준다
	// 파라미터 정의 : Sound -> 오디오 파일의 경로명을 받는다
	// 메소드 하는 일 : 오디오 파일의 경로를 받아 오디오 클립 객체로 받아 음악을 재생 시켜 준다.
	public void PlaySound(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound)); 	// 오디오 파일을 클립 객체로 만들고
			clip.start();										// 음악을 재생
		} catch (Exception e) {

		}
	}

	// 메소드 정의 : 배경음악을 무한 루프로 돌려준다. 
	// 파라미터 정의 : Sound -> 오디오 파일의 경로명을 받는다
	// 메소드 하는 일 : 오디오 파일의 경로를 받아 오디오 클립 객체로 받아 음악을 재생 시켜 준다.
	public void PlaySoundloop(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
			clip.loop(clip.LOOP_CONTINUOUSLY); // LOOP_CONTINUOUSLY 를 통해 음악을 무한
												// 루프 시켜준다
		} catch (Exception e) {

		}
	}

	// 배경음악은 스레드로 돌려준다.
	public void run() { 
		try {
			PlaySoundloop(Clap);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}