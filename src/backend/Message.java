package backend;

/*
 * all chat should be in the form of the message object
 * messages should be part of a linked list, for now
 * contain references to micro groupings
 */
public class Message {

	private Message before;
	
	private Message after;
	
	private Account sender;
	
	private String text;
	
	private Message microBefore;
	
	private Message microAfter;

	public Message getBefore() {
		return before;
	}

	public void setBefore(Message before) {
		this.before = before;
	}

	public Message getAfter() {
		return after;
	}

	public void setAfter(Message after) {
		this.after = after;
	}

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Message getMicroBefore() {
		return microBefore;
	}

	public void setMicroBefore(Message microBefore) {
		this.microBefore = microBefore;
	}

	public Message getMicroAfter() {
		return microAfter;
	}

	public void setMicroAfter(Message microAfter) {
		this.microAfter = microAfter;
	}
	
	
}
