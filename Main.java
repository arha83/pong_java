import java.awt.Color;

public class Main{
    public static void main(String args[]){
        SimpleGameWindow sgw= new SimpleGameWindow(800, 600, "woooooop");

        sgw.drawLine(10, 20, 50, 200, new Color(0,0,255));
    }
}
