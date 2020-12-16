package management;

public class MessageEvent {
	private Event event;
	private String message;
	
	public MessageEvent(Event event, String message) {
		this.event = event;
		this.message = message;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
