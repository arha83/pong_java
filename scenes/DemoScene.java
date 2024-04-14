package scenes;

import java.util.Random;

import Chai.*;
// class definition and inheritance
public class DemoScene extends GameScene {
    // declaring scene objects
    public GameObject ball;
    // constructor method
    public DemoScene(){
        // running constructor of the parent class
        super();
        // initializing scene object (only a ball)
        Sprite ballSprite= new Sprite("./assets/animations/glowing ball", 22, 0, 0);
        Hitbox ballHitbox= new Hitbox(-40, -40, 80, 80);
        ball= new GameObject(ballSprite, ballHitbox, 0, 0, 0, 0, 0, 0);
        // setting ball position to center and setting a random velocity to it
        ball.setXY(400, 300);
        ball.setVelocity(new Random().nextFloat() * 10 - 5, new Random().nextFloat() * 10 - 5);
        // adding ball to objects list so it will be drawn in the window
        addObject(ball);
    }
    // overriding the update method of te parent
    @Override
    public void update(){
        // game mechanics here
        // bounce ball if it hits the edges
        if(ball.getX() <= 40 || ball.getX() >= 800-40) ball.setVelocity(-ball.getVX(), ball.getVY());
        if(ball.getY() <= 40 || ball.getY() >= 600-40) ball.setVelocity(ball.getVX(), -ball.getVY());
        // draw the ball
        ball.update();
        ball.next();
    }
}
