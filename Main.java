public class Main{
    public static void main(String args[]){
        SimpleGameWindow sgw= new SimpleGameWindow(800, 600, "woooooop");

        Character input= null;
        while(input == null){
            input= sgw.getKey();
            // wait a bit
            try{
                Thread.sleep(1);
            } catch(InterruptedException e){};
        }
        // doing whatever I want
        System.out.println(input);
    }
}
