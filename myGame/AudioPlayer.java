package myGame;

// importing packages
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

public class AudioPlayer {
    // class properties
    private AudioInputStream audioInputStream= null;
    private Clip clip= null;
    private String path;
    // constructor only sets the file path
    public AudioPlayer(String path){
        this.path= path;
    }
    // this method initializes the audioInput stream and clip properties
    // return true if operation was successful
    public boolean initAudioStream(){
        // release clip resources
        if(clip != null){
            clip.stop();
            clip.close();
            clip= null;
        }
        try{
            audioInputStream= AudioSystem.getAudioInputStream(new File(path));
            clip= AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch(IOException e){
            return false;
        } catch(UnsupportedAudioFileException e){
            return false;
        } catch(LineUnavailableException e){
            return false;
        }
        return true;
    }
    // this method plays the audio
    public void play(){
        clip.start();
    }
    // this method pauses the audio
    public void pause(){
        clip.stop();
    }
    // this method plays audio on loop
    public void loop(){
        clip.loop(-1);
    }
    // this method restarts the audio
    public void restart(){
        clip.setMicrosecondPosition(0);
    }
}
