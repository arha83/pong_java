import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import javax.swing.JPanel;

public class Main{
    public static void main(String args[]){
        SimpleGameWindow sgw= new SimpleGameWindow(800, 600, "woooooop");

        // loading the image
        Image img= Toolkit.getDefaultToolkit().getImage("images/troll.png");
        // waiting for image to be loaded
        MediaTracker mediaTracker= new MediaTracker(new JPanel());
        mediaTracker.addImage(img, 0);
        try{mediaTracker.waitForAll();}
        catch(InterruptedException e){e.printStackTrace();}
        // drawing the image
        sgw.drawImage(10, 10, img);
    }
}
