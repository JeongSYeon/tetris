package tetris_1;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//�������, ȿ������ ��� �ϴ� Ŭ���� ������ ��� 
public class Music extends Thread {

	private File Clap;

	public Music() {
		Clap = new File("src//music//bgm.wav");
	}

	// �޼ҵ� ���� : 1�� �Ͼ�� ȿ������ ��� �����ش�
	// �Ķ���� ���� : Sound -> ����� ������ ��θ��� �޴´�
	// �޼ҵ� �ϴ� �� : ����� ������ ��θ� �޾� ����� Ŭ�� ��ü�� �޾� ������ ��� ���� �ش�.
	public void PlaySound(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound)); 	// ����� ������ Ŭ�� ��ü�� �����
			clip.start();										// ������ ���
		} catch (Exception e) {

		}
	}

	// �޼ҵ� ���� : ��������� ���� ������ �����ش�. 
	// �Ķ���� ���� : Sound -> ����� ������ ��θ��� �޴´�
	// �޼ҵ� �ϴ� �� : ����� ������ ��θ� �޾� ����� Ŭ�� ��ü�� �޾� ������ ��� ���� �ش�.
	public void PlaySoundloop(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
			clip.loop(clip.LOOP_CONTINUOUSLY); // LOOP_CONTINUOUSLY �� ���� ������ ����
												// ���� �����ش�
		} catch (Exception e) {

		}
	}

	// ��������� ������� �����ش�.
	public void run() { 
		try {
			PlaySoundloop(Clap);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}