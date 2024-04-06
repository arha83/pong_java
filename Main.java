public class Main{
    public static void main(String args[]){
        SimpleGameWindow sgw= new SimpleGameWindow(800, 600, "woooooop");
        GameScene gs= new GameScene(){
            @Override
            public void update(){
                System.out.println("Hi!");
            }
        };
    }
}
