// class definition and inheritance
public class DemoScene extends GameScene {
    // constructor method
    DemoScene(){
        // running constructor of the parent class
        super();
    }
    // overriding the update method of te parent
    @Override
    public void update(){
        System.out.println("Hi! :)");
    }
}
