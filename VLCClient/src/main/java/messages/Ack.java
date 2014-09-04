package messages;

public class Ack extends Message {

	@Override
	public String getPrefix() {
		return "ACK";
	}

}
