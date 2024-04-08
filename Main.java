import myGame.SimpleGameWindow;
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
            sgw.drawGameScene(cp, true);
            // updating physics
            cp.update(sgw.getKey());
            // delay
            try{
                Thread.sleep(10);
            } catch(InterruptedException e){}
        }
    }
}
