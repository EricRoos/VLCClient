import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;


public class VLCKeyListener implements KeyListener {

	private boolean isFastForwarding = false;
	private EmbeddedMediaPlayer player;
	private Thread fastForwardThread;
	
	public VLCKeyListener(EmbeddedMediaPlayer player) {
		super();
		this.player = player;
	}

	public void keyPressed(KeyEvent e) {
		final char key = e.getKeyChar();
		switch(key){
			case 'f' : 
				isFastForwarding = true;
				fastForwardThread = new Thread(new Runnable(){
					public void run() {
						while(isFastForwarding){
							player.setTime(player.getTime() + 10);
						}
						
					}
				});
				fastForwardThread.start();
				break;
		}	
	}

	public void keyReleased(KeyEvent e) {
		char key = e.getKeyChar();
		switch(key){
			case 'f' : 
				isFastForwarding = false;
				try {
					fastForwardThread.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Got key release");
				break;
		}
	}

	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		if(key != 'f'){
			if(isFastForwarding){
				e.setKeyChar('f');
				keyReleased(e);
			}
		}
		System.out.println("Typed: " +key);
		switch(key){
			case 'p':
				//pause
				if(player.isPlaying()){
					player.pause();
				}else{
					player.play();
				}
				
				break;
		}
		
	}

}
