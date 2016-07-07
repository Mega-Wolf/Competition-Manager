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
			System.out.println("Created new one");
		}
		
		@Override
		public void run() {
			int PORT_NUMBER = 44532;
			
			Socket server;
			try {
				server = new Socket("127.0.0.1",PORT_NUMBER);
				
				try (ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream()); ObjectInputStream in = new ObjectInputStream(server.getInputStream());) {
					for (SendData dts : dataToSend) {
						System.out.println(dts.getOperation());
						out.writeObject(dts.getOperation());
						System.out.println(dts.getOperand());
						out.writeObject(dts.getOperand());
						System.out.println(dts.getObject());
						out.writeObject(dts.getObject());
						
						
						dts.setReturnValue(in.readObject());
						System.out.println(dts.getReturnValue());
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				server.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}