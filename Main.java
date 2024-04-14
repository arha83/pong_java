import Chai.SimpleGameWindow;
import scenes.ClassicPong;

public class Main{
    public static void main(String args[]){
        // game window and game scene
        SimpleGameWindow sgw= new SimpleGameWindow(800, 600, "woooooop");
        ClassicPong cp= new ClassicPong(3, 0, 800-20, 600);
        cp.setRandomBallSpeed();
        // game loop
        while(true){
            // clearing and drawing
            sgw.clear();
            sgw.drawGameScene(cp, false);
            // getting typed key and exiting if it is 'q'
            Character input= sgw.getKey();
            if(input != null && input == 'q') break;
            // updating physics
            cp.update(input);
            // delay
            try{
                Thread.sleep(10);
            } catch(InterruptedException e){}
        }
        sgw.close();
    }
}
