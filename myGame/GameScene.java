package myGame;

import java.util.ArrayList;
// class definition
public class GameScene {
    // list of game object
    ArrayList<GameObject> objects;
    // simple constructor
    public GameScene(){
        objects= new ArrayList<GameObject>();
    }
    // this method add an instance of GameObject class to is list.
    public void addObject(GameObject go){
        objects.add(go);
    }
    // this method should be overridden
    public void update(){}
}
