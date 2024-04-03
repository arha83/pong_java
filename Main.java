public class Main{
    public static void main(String args[]){
        AudioPlayer ap1= new AudioPlayer("./assets/sfx/demo.wav");
        ap1.initAudioStream();
        ap1.loop();
    }
}
