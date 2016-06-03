package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection extends Thread {

	/* Variables */
	private Socket socket;

	/* Constructor */
	public Connection(Socket socket) {
		this.socket = socket;
	}

	/* Overrides */
	@Override
	public void run() {

		try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());) {
			
			while(true) {
				
				Operation operation = (Operation) in.readObject();
				
				switch (operation) {
					case ADD_PLAYER:
						break;
					case CREATE_GROUPS:
						break;
				}
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.run();
	}

}