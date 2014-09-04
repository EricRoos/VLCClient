package Network;

import vlc.Player;
import messages.Ack;
import messages.CommandMessage;
import messages.Message;

public class MessageHandler {
	
	public Message handle(Message msg){
		if(msg instanceof CommandMessage){
			return handleCommandMessage((CommandMessage)msg);
		}
		return null;
	}

	private Message handleCommandMessage(CommandMessage msg) {
		System.out.println(msg.toString());
		if(msg.getCommand().compareTo("play") == 0){
			if(msg.getArgument().length() == 0){
				System.out.println("Playing again");
				Player.play(null);
			}else{
				Player.play(msg.getArgument());
			}
		}
		if(msg.getCommand().compareTo("pause") == 0){
			Player.pause();
		}
		return new Ack();
	}
}
