package vlc;



import java.awt.Frame;

import javax.swing.JFrame;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class Player {
	
	private static EmbeddedMediaPlayer player;
	
	public static void init(){
		NativeLibrary.addSearchPath(
	                RuntimeUtil.getLibVlcLibraryName(), "/Applications/VLC.app/Contents/MacOS/lib"
	            );
	    Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(),LibVlc.class);
        JFrame frame = new JFrame("vlcj Tutorial");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);  
        EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        player = mediaPlayerComponent.getMediaPlayer();
        frame.setContentPane(mediaPlayerComponent);
        frame.setLocation(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
	}
	public static void play(String path){
		if(path != null){
			player.playMedia(path);
		}else{
			player.play();
		}
	}
	
	public static void stop(){
		System.out.println("Got Stop");
	}
	
	public static void pause(){
		player.pause();
	}
	


}
