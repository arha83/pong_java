
public class Main{
    public static void main(String args[]){
        SimpleGameWindow sgw= new SimpleGameWindow(800, 600, "woooooop");
        // defining the sprite with 24 frames with center of (200,250)
        Sprite sprite= new Sprite("assets/animations/demo", 24, 200, 250);

        while(true){
            // draw the sprite and change to next frame
            sgw.drawSprite(sprite);
            sprite.next();
            // small delay
            try{
                Thread.sleep(30);
            } catch(InterruptedException e){};
        }
    }
}
