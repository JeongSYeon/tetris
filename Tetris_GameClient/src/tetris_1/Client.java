package tetris_1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
	private Socket c_socket;
	private MainBoard _mypanel;
	private MainBoard _yourpanel;

	public Client(MainBoard mypanel,MainBoard yourpanel) {
		
		_mypanel = mypanel;
		_yourpanel = yourpanel;
		
		try {
			c_socket = new Socket("192.168.0.4", 8888);
			
			ReceiveThread receive = new ReceiveThread(c_socket,_yourpanel);
			SendThread send = new SendThread(c_socket, _mypanel);

			send.start();
			receive.start();
			
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}
