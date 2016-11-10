package controller;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Hashtable;
import java.util.Stack;

public class MessageController {

	Stack<DatagramPacket> messages = new Stack<DatagramPacket>();
	Stack<DatagramPacket> sendQueue = new Stack<DatagramPacket>();
	Server server;
	Client client;
	
	String publicKey = "xxxxxxxxxxxxx";
	String privateKey = "xxxxxxxxxxxxxx";
	
	//DHT stuff
	Hashtable<String, String> dht = new Hashtable<String, String>();
	
	public MessageController(){
		server = new Server(this);
		client = new Client(this);
	
		start();
	}
	
	//receives messages
	//directs received messages
	//sends messages
	//handles dht

	private void start() {
		while(true){
			if(!messages.isEmpty()){
				processMessage(messages.pop());
			}
		}
		
	}
	
	//processes messages
	private void processMessage(DatagramPacket datagram) {
		byte[] data = datagram.getData();
		String type = ""; 
		type += data[0] + data[1] + data[2] + data[3];
		//determines what to do for specific commands
		switch(type){
			case "0000" :
					break;
			case "1111" :
				//send dht
				sendDHT(datagram);
				break;
			default:
				break;
		}
		
	}
	
	//sends the dht to the requesting node
	private void sendDHT(DatagramPacket datagram) {
		InetAddress address = datagram.getAddress();
		int port = datagram.getPort();
		String data = "";
		FileOutputStream output;
		ObjectOutputStream objectOutput;
		
		//attempts to serialize the dht TODO what about size
		try{
			output = new FileOutputStream(data);
			objectOutput = new ObjectOutputStream(output);
			objectOutput.writeObject(dht);
			
			output.close();
		}catch(Exception e){
			System.err.print(e.getMessage());
		}
		
		DatagramPacket send = new DatagramPacket(data.getBytes(), data.getBytes().length, address, port);
		
		sendQueue.push(send);
		
		
	}
	
}
