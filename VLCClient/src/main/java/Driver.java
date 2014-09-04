import java.awt.Frame;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;


public class Driver {
    public static void main(String[] args) throws IOException {
        NativeLibrary.addSearchPath(
                RuntimeUtil.getLibVlcLibraryName(), "/Applications/VLC.app/Contents/MacOS/lib"
            );
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(),LibVlc.class);
        Scanner scn = new Scanner(System.in);
        String path = new FileDriver().run("/Users/Eric/Downloads",scn);
        scn.close();
        
        JFrame frame = new JFrame("vlcj Tutorial");
        frame.setExtendedState(Frame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);  
        EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        EmbeddedMediaPlayer player = mediaPlayerComponent.getMediaPlayer();
        frame.setContentPane(mediaPlayerComponent);
        frame.addKeyListener(new VLCKeyListener(player));
        
        frame.setLocation(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        

        //player.playMedia(path);
       
    }
}
