import java.awt.Color;

public class Main{
    public static void main(String args[]){
        SimpleGameWindow sgw= new SimpleGameWindow(800, 600, "woooooop");
        
        int x= 400, y= 300;
        while (true) {
            sgw.clear();
            sgw.drawSimpleCircle(x, y, 30, new Color(0, 255, 255));

            delay(1);
        }
    }

    private static void delay(int time){
        try{
            Thread.sleep(50);
        } catch(InterruptedException e){}
    }
}
