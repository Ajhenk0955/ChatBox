package backend;

import java.util.List;

/*
 * Controller for the program
 * UI should use this to do everything
 * program logic should be a black box
 */
public class ChatController {

	private Message head;
	private Message tail;
	
	private List<Message> subHeads;
	
	/*
	 * send a message object
	 * TODO
	 */
	boolean send(Message message){
		return false;
		
	}

	/*
	 * retrieve messages always while connected
	 */
	void retrieveMessages(){
		
	}
	
	/*
	 * load stored messages, ask storage manager
	 */
	boolean loadMessages(){
		return false;
	}

	public Message getHead() {
		return head;
	}

	public void setHead(Message head) {
		this.head = head;
	}

	public Message getTail() {
		return tail;
	}

	public void setTail(Message tail) {
		this.tail = tail;
	}

	public List<Message> getSubHeads() {
		return subHeads;
	}

	public void setSubHeads(List<Message> subHeads) {
		this.subHeads = subHeads;
	}
	
	
}
