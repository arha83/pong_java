package Chai;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Sprite {
    private Image frames[]; // image array
    private int n; // number of frames
    private int x, y; // drawing coordinates
    private int index; // index of current frame
    
    // class constructor
    // parameters:
    //   path       -> path of directory which the frames are stored in
    //   n          -> number of frames in the directory
    //   x          -> x coordinate of the sprite
    //   y          -> y coordinate of the sprite
    public Sprite(String path, int n, int x, int y){
        // loading the images and wait to be loaded
        frames= new Image[n]; // array definition
        MediaTracker mediaTracker= new MediaTracker(new JPanel());
        for(int i=0; i < n; i++){
            Image img= Toolkit.getDefaultToolkit().getImage(path + "/" + i + ".png");
            mediaTracker.addImage(img, i);
            frames[i]= img;
        }
        // waiting
        try{mediaTracker.waitForAll();}
        catch(InterruptedException e){e.printStackTrace();}
        // setting class properties values
        this.n= n;
        this.x= x;
        this.y= y;
        index= 0; // initial index is 0
    }
    
    // these methods get and set x and y
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x= x;
    }
    public void setY(int y){
        this.y= y;
    }
    
    // this method returns current frame
    public Image getFrame(){
        return frames[index];
    }

    // these methods get, set, or change the frame
    public int getIndex(){
        return index;
    }
    public void setIndex(int index){
        this.index= index;
    }
    public void next(){
        index++;
        // if index is on the last frame, start over the animation (set index to 0)
        if(index >= n) index= 0;
    }
}
