import java.awt.Color;

public class Main{
    public static void main(String args[]){
        SimpleGameWindow sgw= new SimpleGameWindow(800, 600, "woooooop");

        sgw.drawRectangle(10, 10, 40, 30, new Color(255, 0, 0));
        sgw.drawFillRectangle(70, 10, 40, 30, new Color(0, 255, 0));
    }
}
