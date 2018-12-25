package tetris_1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SendThread extends Thread {
	private Socket socket;
	private byte[] data = new byte[22 * 12];
	private MainBoard _mypanel = new MainBoard();

	public SendThread(Socket _socket, MainBoard mypanel) {
		socket = _socket;
		_mypanel = mypanel;
		for (int i = 0; i < 22 * 12; i++) {
			data[i] = (byte) mypanel.board[i / 12][i % 12];
		}
	}

	public void run() {
		super.run();
		try {
			OutputStream output = socket.getOutputStream();
			PrintWriter sendWriter = new PrintWriter(output);

			while (true) {
				for (int i = 0; i < 22 * 12; i++) {
					data[i] = (byte) _mypanel.board[i / 12][i % 12];
				}
				
				output.write(data);
				sendWriter.flush();
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