package DHT;
import java.util.Hashtable;
import java.util.List;

/*
 * everyone should hold a piece of the DHT
 * DHT holds a map of IDs and Addresses/Ports
 * dht key should be encrypted by part of the unique friend ID
 * value should also be encrypted by the ID
 * if someone has the friend ID they should be able to connect
 */
public class DistributedHashTable {
	private Hashtable<String, String> nodesDHT;
	public List<String[]> dhtSources;
	private Server server;
	
	DistributedHashTable(){
		server = new Server(this);
	}

	//checks if current dht has the key/value
	//if not, request dht hashes from other nodes
	public boolean getNodesDHT(String comm){
		if (nodesDHT.containsKey(comm)){
			return true;
		} else {
			//it's almost recursive
			//ask other machines for the table
			requestCommNode();
			//expect a response in a bit TODO
			System.out.println("request to network is sent, try again shortly");
			return false;
		}
	}
	public Hashtable<String, String> getNodesDHT(){
		return nodesDHT;
	}

	//request from other machines in the network to return the dht
	private boolean requestCommNode() {
		
		Client client = new Client();
		//using list of dhtSources that we have, ask if they have it.
		for(String node[] : dhtSources){
			//request value
			//assuming server has started
			client.SendMessage(node[0], node[1], nodesDHT);
		}
		return false;
	}
	
	//updates DHT when received info from server
	public boolean updateDHT(Object obj){
		try{
			Hashtable<String, String> dht = (Hashtable<String,String>) obj;
			//tries to put the unchecked received object to the dht TODO may need to verify
			nodesDHT.putAll(dht);
		}catch(Exception e){
			System.err.print(e.getMessage());
			return false;
		}
		return true;
	}

	
}
