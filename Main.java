import java.awt.Color;

public class Main{
    public static void main(String args[]){
        SimpleGameWindow sgw= new SimpleGameWindow(800, 600, "woooooop");

        sgw.drawSimpleCircle(200, 100, 40, new Color(0, 255, 255));
        sgw.clear();
        sgw.drawSimpleCircle(400, 100, 30, new Color(255, 0, 255));
    }
}
