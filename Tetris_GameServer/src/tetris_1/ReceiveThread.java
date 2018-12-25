package tetris_1;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReceiveThread extends Thread {
	private Socket _socket;
	private InputStream input;
	private byte[] data = new byte[22*12];
	private MainBoard _yourpanel;
	
	public ReceiveThread(Socket socket,MainBoard yourpanel) {
		_socket = socket;
		_yourpanel = yourpanel;
	}

	public void run() {
		super.run();

		try {
			input = _socket.getInputStream();

			while (true) {
				input.read(data);
				
				if (input == null) {
					break;
				} else {
					for (int i = 0; i < 22*12; i++) {
						_yourpanel.board[i/12][i%12]  = data[i];
					}
				}
				sleep(500);
			}
		} catch (IOException e) {
			e.printStackTrace();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}