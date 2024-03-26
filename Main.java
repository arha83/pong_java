import java.awt.Color;

public class Main{
    public static void main(String args[]){
        SimpleGameWindow sgw= new SimpleGameWindow(800, 600, "woooooop");

        sgw.drawSimpleCircle(200, 100, 40, new Color(0, 255, 255));
    }
}
