package serverCommands;

import java.net.Socket;

public interface Command {
	public Object handleClient(Socket client);
}