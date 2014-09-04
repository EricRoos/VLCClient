package messages;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CommandMessage extends Message {
	
	private String command;
	private String argument;
	
	

	public CommandMessage(String command, String argument) {
		super();
		this.command = command;
		this.argument = argument;
	}
	
	public CommandMessage(InputStream inputStream) throws IOException {
		command = Message.readString(inputStream);
		argument = Message.readString(inputStream);
	}

	@Override
	public void encode(OutputStream stream) throws IOException{
		super.encode(stream);
		this.write_string(stream, command);
		this.write_string(stream, argument);
	}

	public String getCommand() {
		return command;
	}



	public void setCommand(String command) {
		this.command = command;
	}



	public String getArgument() {
		return argument;
	}



	public void setArgument(String argument) {
		this.argument = argument;
	}



	@Override
	public String getPrefix() {
		return "COMMAND";
	}

	@Override
	public String toString() {
		return "CommandMessage [command=" + command + ", argument=" + argument
				+ "]";
	}

	
}
