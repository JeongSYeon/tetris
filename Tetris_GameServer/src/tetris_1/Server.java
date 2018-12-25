package tetris_1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket s_socket;
	private Socket c_socket;
	private MainBoard _mypanel;
	private MainBoard _yourpanel;

	public Server(MainBoard mypanel,MainBoard yourpanel) {
		
		_mypanel = mypanel;
		_yourpanel = yourpanel;
		
		try {
			s_socket = new ServerSocket(8888);
			c_socket = s_socket.accept();
			
			ReceiveThread receive = new ReceiveThread(c_socket,_yourpanel);
			SendThread send = new SendThread(c_socket, _mypanel);

			send.start();
			receive.start();
			
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}
