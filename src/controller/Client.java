package controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Client {
	MessageController controller;
	public Client(MessageController controller){
		this.controller = controller;
		
		start();
	}
	private void start() {
		while(true){
			if(!controller.sendQueue.isEmpty()){
				sendDatagram(controller.sendQueue.pop());
			}
		}
		
	}
	private void sendDatagram(DatagramPacket datagram) {
		DatagramSocket clientSocket = null;
		try{
			clientSocket = new DatagramSocket(8765);
			
			clientSocket.send(datagram);
			
		} catch(IOException e){
			System.out.println("Error");
			System.exit(1);
		} finally{
			clientSocket.close();
		}
		
	}
}
