import myGame.SimpleGameWindow;
import scenes.DemoScene;

public class Main{
    public static void main(String args[]){
        // game window and game scene
        SimpleGameWindow sgw= new SimpleGameWindow(800, 600, "woooooop");
        DemoScene ds= new DemoScene();
        // game loop
        while(true){
            // clearing and drawing
            sgw.clear();
            sgw.drawGameScene(ds, true);
            // updating physics
            ds.update();
            // delay
            try{
                Thread.sleep(50);
            } catch(InterruptedException e){}
        }
    }
}
