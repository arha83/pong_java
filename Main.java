import java.awt.Color;

public class Main{
    public static void main(String args[]){
        SimpleGameWindow sgw= new SimpleGameWindow(800, 600, "woooooop");
        // defining the hitboxes
        Hitbox fixed= new Hitbox(320, 240, 160, 120);
        Hitbox moving= new Hitbox(0, 200, 80, 80);

        while(true){
            // clear the frame
            sgw.clear();
            // draw the hitboxes based on collision
            sgw.drawHitbox(fixed, new Color(255, 255, 0));
            if(moving.collidesWith(fixed)) sgw.drawHitbox(moving, new Color(255, 0, 255));
            else sgw.drawHitbox(moving, new Color(0, 255, 255));
            // move the moving hitbox
            moving.setX(moving.getX()+1);
            // small delay
            try{
                Thread.sleep(30);
            } catch(InterruptedException e){};
        }
    }
}
