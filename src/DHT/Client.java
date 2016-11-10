package DHT;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {



	//sends information to the given node
	public boolean SendMessage(String nodeHostName, String nodeString, Object obj){
		
		
		try{
			Socket nodeSocket = null;
			ObjectOutputStream out = null;
			int node = Integer.parseInt(nodeString);
			
			//establish communication
			try {
				nodeSocket = new Socket(nodeHostName, node);
				
				out = new ObjectOutputStream(nodeSocket.getOutputStream());
			} catch (UnknownHostException e) {
				System.err.println("Don't know about host: " + nodeHostName);
				return false;
			} catch (IOException e) {
				System.err.println("Couldn't get I/O for the connection to: " + nodeHostName);
				return false;
			}
			//sends information
			System.out.println("doing stuff: aka sending information");
			
			out.writeObject(obj);
			out.flush();
			
			
			//close communication
			out.close();
			nodeSocket.close();
		} catch (Exception e){
			System.err.print(e.getMessage());
			return false;
		}
		return true;
		
	}


}