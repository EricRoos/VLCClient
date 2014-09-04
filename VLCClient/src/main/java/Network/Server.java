package Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import vlc.Player;
import messages.CommandMessage;
import messages.Message;

public class Server {
	private ServerSocket sock;
	
	public Server(int port) throws IOException{
		sock = new ServerSocket(port);
		sock.setReuseAddress(true);
		//sock.setSoTimeout(5000);
	}
	
	public void run(){
		while(true){
			Socket client = null;
			MessageHandler hndlr = new MessageHandler();
			Player.init();
			while(true){
				try {
					if(client == null){
						client = sock.accept();
						client.setSoTimeout(5000);
						System.out.println("Accepted");
					}
					Message msg = Message.decode(client.getInputStream());
					if(msg == null){
						throw new IOException("Bad Message");
					}
					Message response = hndlr.handle(msg);
					if(response != null){
						response.encode(client.getOutputStream());
					}
				}catch (IOException e) {
					try {
						System.out.println("Closing socket and opening a new one");
						if(client != null){
							client.close();
						}
						client = null;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		Server server = new Server(4000);
		server.run();
		
	}
}
