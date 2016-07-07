package gui.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientConnection {
	
	public void sendToServer(List<SendData> dataToSend) throws InterruptedException {
		ConnectionThread a= new ConnectionThread(dataToSend);
		a.start();
		a.join();
	}
	
	private class ConnectionThread extends Thread {
		
		private final List<SendData> dataToSend;
		
		public ConnectionThread(List<SendData> dataToSend){
			this.dataToSend = dataToSend;
		}
		
		@Override
		public void run() {
			int PORT_NUMBER = 44532;
			
			Socket server;
			try {
				server = new Socket("127.0.0.1",PORT_NUMBER);
				
				try (ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream()); ObjectInputStream in = new ObjectInputStream(server.getInputStream());) {
					for (SendData dts : dataToSend) {
						out.writeObject(dts.getOperation());
						out.writeObject(dts.getOperand());
						out.writeObject(dts.getObject());
						
						dts.setReturnValue(in.readObject());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				server.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}