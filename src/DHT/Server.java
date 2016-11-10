package DHT;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

	protected Socket clientSocket;
	private DistributedHashTable dht;
	
	public Server(DistributedHashTable distributedHashTable) {
		
		dht = distributedHashTable;
		
		ServerSocket serverSocket = null;
		
		try{
			serverSocket = new ServerSocket(10007);
			System.out.println("Connection socket created");
			try{
				while(true){
					System.out.println("Waiting for connection");
					new Server(serverSocket.accept());
				}
			} catch(IOException e){
				System.err.println("Accept failed.");
				System.exit(1);
			}
		} catch (IOException e){
			System.err.println("Could not listen on port: 10007.");
			System.exit(1);
		}finally{
			try{
				serverSocket.close();
			} catch(IOException e){
				System.err.println("Could not close port: 10007");
				System.exit(1);
			}
		}	
	}
	
	private Server(Socket clientSoc){
		clientSocket = clientSoc;
		start();
	}
	

	//Accepts 1 object, then sends object to controller, then closes
	public void run(){
		System.out.println("New communication thread started");
		
		try{
		
			ObjectOutputStream out = new ObjectOutputStream(
						clientSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(
						clientSocket.getInputStream());
	
			Object obj = null;
			try{
				obj = in.readObject();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			System.out.println("server recieved object ");
			//return current dht as courtesy
			out.writeObject(dht.getNodesDHT());
			out.flush();
			
			//Notify dht controller
			//barbershop?
			dht.updateDHT(obj);
			//time to close :)
			
			out.close();
			in.close();
			clientSocket.close();
			
		} catch (IOException e){
			System.err.println("Problem with Communication server");
			System.exit(1);
		}
	}
}
