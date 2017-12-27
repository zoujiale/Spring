package zou.common.vo;

import lombok.Data;

@Data
public class Message {
	 public Message(String message, Boolean state) {
		super();
		this.message = message;
		this.state = state;
	}
	private String message;
	 private Boolean state;
	public Message() {
		super();
	}
}
