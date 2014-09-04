package messages;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public abstract class Message {
	
	public final static String PLAY = "play";
	public final static String FAST_FORWARD="fast_forward";
	public final static String PAUSE = "pause";
	
	public abstract String getPrefix();
	
	public void encode(OutputStream stream) throws IOException{
		stream.write(getPrefix().getBytes());
		stream.write(" ".getBytes());
	}
	
	protected void write_string(OutputStream stream,String str) throws IOException{
		stream.write(String.valueOf(str.length()).getBytes());
		stream.write(" ".getBytes());
		stream.write(str.getBytes());
	}
	
	public static void main(String[] args) throws IOException{
		CommandMessage message = new CommandMessage(Message.PLAY,"/var/test.mp4");
		message.encode(System.out);
	}
	
	public static String readString(InputStream inputStream) throws IOException{
		int size = Integer.parseInt(readToken(inputStream));
		byte[] bytes = new byte[size];
		inputStream.read(bytes);
		return new String(bytes);
	}
	public static String readToken(InputStream inputStream) throws IOException{
		int sought = (int)' ';
		int read = -1;
		StringBuilder bldr = new StringBuilder();
		do{
			read = inputStream.read();
			if(read != -1){
				bldr.append((char)read);
			}
		}while(read != -1 && sought != read );
		return bldr.toString().trim();
	}
	
	public static Message decode(InputStream inputStream) throws IOException{
		String messageName = Message.readToken(inputStream);
		if(messageName.compareTo("COMMAND") == 0){
			return new CommandMessage(inputStream);
		}
		return null;
	}
	
	public Message reply(Message msg){
		if(msg instanceof CommandMessage){
			CommandMessage cmd = (CommandMessage)msg;
		}
		return null;
	}
}
