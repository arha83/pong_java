package scenes;

import myGame.*;
import java.util.Random;
import java.lang.Math;
// class declaration
public class ClassicPong extends GameScene{
    // game scene data
    private int sceneOriginX, sceneOriginY;
    private int sceneWidth, sceneHeight;
    // game objects
    GameObject leftPaddle;
    GameObject rightPaddle;
    GameObject ball;
    // constructor
    public ClassicPong(int x, int y, int w, int h){
        super();
        sceneOriginX= x;
        sceneOriginY= y;
        sceneWidth= w;
        sceneHeight= h;
        // sprites
        Sprite leftPaddleSprite= new Sprite("./assets/animations/pong paddle", 9, 0, 0);
        Sprite rightPaddleSprite= new Sprite("./assets/animations/pong paddle", 9, 0, 0);
        Sprite ballSprite= new Sprite("./assets/animations/pong ball", 9, 0, 0);
        // hitboxes
        Hitbox leftPaddleHitbox= new Hitbox(-12, -64, 24, 128);
        Hitbox rightPaddleHitbox= new Hitbox(-12, -64, 24, 128);
        Hitbox ballHitbox= new Hitbox(-32, -32, 64, 64);
        // game objects
        leftPaddle= new GameObject(leftPaddleSprite, leftPaddleHitbox, 0, 0, 0, 0, 0, 0);
        rightPaddle= new GameObject(rightPaddleSprite, rightPaddleHitbox, 0, 0, 0, 0, 0, 0);
        ball= new GameObject(ballSprite, ballHitbox, 0, 0, 0, 0, 0, 0);
        // setting initial positions
        leftPaddle.setXY(x + 12, h/2);
        rightPaddle.setXY(w - 12, h/2);
        ball.setXY(w/2, h/2);
        // adding objects to list
        addObject(ball);
        addObject(leftPaddle);
        addObject(rightPaddle);
    }
    // set a random speed for ball
    public void setRandomBallSpeed(){
        Random random= new Random();
        float theta= random.nextFloat(0, 2*3.1416f);
        float length= random.nextFloat(1.0f, 5.0f);
        float vx= (float)Math.cos(theta) * length;
        float vy= (float)Math.sin(theta) * length;
        ball.setVelocity(vx, vy);
    }

    public void update(Character key){
        // ball-wall collisions
        if(ball.getY() >= sceneHeight || ball.getY() <= sceneOriginY){
            ball.setVelocity(ball.getVX(), -ball.getVY());
        }
        // ball-paddle collisions
        if(ball.collidesWith(leftPaddle)){
            // set velocity to positive value.
            ball.setVelocity(Math.abs(ball.getVX()), ball.getVY());
        }
        if(ball.collidesWith(rightPaddle)){
            // set velocity to negative value.
            ball.setVelocity(-Math.abs(ball.getVX()), ball.getVY());
        }
        // move paddles based on keyPress
        if(key != null){
            switch(key.charValue()){
                case 'r':
                    leftPaddle.setXY(leftPaddle.getX(), leftPaddle.getY()-10);
                    break;
                case 'f':
                    leftPaddle.setXY(leftPaddle.getX(), leftPaddle.getY()+10);
                    break;
                case 'u':
                    rightPaddle.setXY(rightPaddle.getX(), rightPaddle.getY()-10);
                    break;
                case 'j':
                    rightPaddle.setXY(rightPaddle.getX(), rightPaddle.getY()+10);
                    break;
            }
        }
        // don't let the paddles move outside the scene
        if(leftPaddle.getY() - leftPaddle.getHitbox().getHeight()/2 <= sceneOriginY)
            leftPaddle.setXY(leftPaddle.getX(), sceneOriginY + leftPaddle.getHitbox().getHeight()/2);
        if(leftPaddle.getY() + leftPaddle.getHitbox().getHeight()/2  >= sceneOriginY + sceneHeight)
            leftPaddle.setXY(leftPaddle.getX(), sceneOriginY + sceneHeight - leftPaddle.getHitbox().getHeight()/2);
        if(rightPaddle.getY() - rightPaddle.getHitbox().getHeight()/2 <= sceneOriginY)
            rightPaddle.setXY(rightPaddle.getX(), sceneOriginY + rightPaddle.getHitbox().getHeight()/2);
        if(rightPaddle.getY() + rightPaddle.getHitbox().getHeight()/2  >= sceneOriginY + sceneHeight)
            rightPaddle.setXY(rightPaddle.getX(), sceneOriginY + sceneHeight - rightPaddle.getHitbox().getHeight()/2);
        // updating the objects
        ball.update();
    }

}
