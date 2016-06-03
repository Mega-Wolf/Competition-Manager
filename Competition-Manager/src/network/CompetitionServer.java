package network;

import java.io.IOException;
import java.net.ServerSocket;

public class CompetitionServer {

	private static final int PORT_NUMBER = 44532;

	public CompetitionServer() {

		try (ServerSocket server = new ServerSocket(PORT_NUMBER)) {

			while(true) {
				new Connection(server.accept()).start();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}