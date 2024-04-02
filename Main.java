public class Main{
    public static void main(String args[]){
        SimpleGameWindow sgw= new SimpleGameWindow(800, 600, "woooooop");
        // sprite and hitbox
        Sprite spr= new Sprite("./assets/animations/troll", 1, 400, 300);
        Hitbox hb= new Hitbox(350, 250, 100, 100);
        // game object with initial velocity of (4, 3)
        GameObject object= new GameObject(spr, hb, 400, 300, 4, 3);
        object.setXY(0, 0);
        // updating the object position in a loop and drawing it
        while(true){
            // draw
            sgw.clear();
            sgw.drawGameObject(object, true);
            // update
            object.update();
            // small delay
            try{
                Thread.sleep(10);
            } catch(InterruptedException e){}
        }
    }
}
