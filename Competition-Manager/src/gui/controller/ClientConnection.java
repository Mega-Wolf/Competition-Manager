package gui.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import network.CompetitionServer;

public class ClientConnection {
	
	private static Logger log = LogManager.getLogger(ClientConnection.class);
	
	public void sendToServer(List<SendData> dataToSend) throws InterruptedException {
		ConnectionThread a= new ConnectionThread(dataToSend);
		log.debug("Started");
		a.start();
		
		a.join();
		log.debug("Joined");
	}
	
	private class ConnectionThread extends Thread {
		
		private final List<SendData> dataToSend;
		
		public ConnectionThread(List<SendData> dataToSend){
			this.dataToSend = dataToSend;
			log.debug("Created new one");
		}
		
		@Override
		public void run() {
			int PORT_NUMBER = 44532;
			
			Socket server;
			try {
				server = new Socket("127.0.0.1",PORT_NUMBER);
				
				try (ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream()); ObjectInputStream in = new ObjectInputStream(server.getInputStream());) {
					for (SendData dts : dataToSend) {
						log.debug(dts.getOperation());
						out.writeObject(dts.getOperation());
						log.debug(dts.getOperand());
						out.writeObject(dts.getOperand());
						log.debug(dts.getObject());
						out.writeObject(dts.getObject());
						
						
						dts.setReturnValue(in.readObject());
						log.debug(dts.getReturnValue());
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