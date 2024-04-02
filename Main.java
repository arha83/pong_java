import java.awt.Color;

public class Main{
    public static void main(String args[]){
        SimpleGameWindow sgw= new SimpleGameWindow(800, 600, "woooooop");
        // sprite and hitbox
        Sprite spr= new Sprite("./assets/animations/troll", 1, 400, 300);
        Hitbox hb= new Hitbox(350, 250, 100, 100);
        // game object
        GameObject object= new GameObject(spr, hb, 400, 300);
        // drawing
        sgw.drawGameObject(object, true);
    }
}
