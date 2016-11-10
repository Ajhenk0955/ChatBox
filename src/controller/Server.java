package controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Server {

	DatagramSocket serverSocket;
	MessageController controller;
	
	public Server(MessageController controller) {
		this.controller = controller;
		
		try{
			serverSocket = new DatagramSocket(9876);
			System.out.println("Datagram socket created");
			
			//sending && receiving
			byte[] receiveData = new byte[1024];
			
			//Accepting datagrams
			while(true){
				receiveData = new byte[1024];
				
				DatagramPacket receivePacket = 
						new DatagramPacket(receiveData, receiveData.length);
				
				System.out.println("Waiting for datagram packet");
				
				serverSocket.receive(receivePacket);
				
				//add to the stack for the controller to process
				controller.messages.push(receivePacket);
				
			}

		} catch (IOException e){
			System.err.println("Could not listen on port: 9876.");
			System.exit(1);
		}finally{
			serverSocket.close();
		}	
	}

}

