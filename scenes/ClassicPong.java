package scenes;

import java.util.Random;

import Chai.*;

import java.lang.Math;
// class declaration
public class ClassicPong extends GameScene{
    // game scene data
    private int sceneOriginX, sceneOriginY;
    private int sceneWidth, sceneHeight;
    private int leftPlayerScore= 0;
    private int rightPlayerScore= 0;
    private boolean gameStarted= false;
    private boolean gameOver= false;
    // game objects
    GameObject leftPaddle;
    GameObject rightPaddle;
    GameObject ball;
    GameObject leftScore;
    GameObject rightScore;
    GameObject dialog;
    // sounds
    AudioPlayer theme;
    AudioPlayer paddleCollisionSound;
    AudioPlayer gameOverSound;
    // constructor
    public ClassicPong(int x, int y, int w, int h){
        super();
        sceneOriginX= x;
        sceneOriginY= y;
        sceneWidth= w;
        sceneHeight= h;
        // initializing sounds
        theme= new AudioPlayer("./assets/sfx/theme 1.wav");
        paddleCollisionSound= new AudioPlayer("./assets/sfx/boop.wav");
        gameOverSound= new AudioPlayer("./assets/sfx/game over.wav");
        theme.initAudioStream();
        paddleCollisionSound.initAudioStream();
        gameOverSound.initAudioStream();
        // sprites
        Sprite leftPaddleSprite= new Sprite("./assets/animations/pong paddle", 9, 0, 0);
        Sprite rightPaddleSprite= new Sprite("./assets/animations/pong paddle", 9, 0, 0);
        Sprite ballSprite= new Sprite("./assets/animations/pong ball", 9, 0, 0);
        Sprite leftScoreSprite= new Sprite("./assets/animations/score", 11, 0, 0);
        Sprite rightScoreSprite= new Sprite("./assets/animations/score", 11, 0, 0);
        Sprite dialogSprite= new Sprite("./assets/animations/dialog 1", 4, 0, 0);
        // hitboxes
        Hitbox leftPaddleHitbox= new Hitbox(-12, -64, 24, 128);
        Hitbox rightPaddleHitbox= new Hitbox(-12, -64, 24, 128);
        Hitbox ballHitbox= new Hitbox(-32, -32, 64, 64);
        Hitbox leftScoreHitbox= new Hitbox(-16, -16, 0, 0);
        Hitbox rightScoreHitbox= new Hitbox(-16, -16, 0, 0);
        Hitbox dialogHitbox= new Hitbox(x, y, w, h);
        // game objects
        leftPaddle= new GameObject(leftPaddleSprite, leftPaddleHitbox, 0, 0, 0, 0, 0, 0);
        rightPaddle= new GameObject(rightPaddleSprite, rightPaddleHitbox, 0, 0, 0, 0, 0, 0);
        ball= new GameObject(ballSprite, ballHitbox, 0, 0, 0, 0, 0, 0);
        leftScore= new GameObject(leftScoreSprite, leftScoreHitbox, 0, 0, 0, 0, 0, 0);
        rightScore= new GameObject(rightScoreSprite, rightScoreHitbox, 0, 0, 0, 0, 0, 0);
        dialog= new GameObject(dialogSprite, dialogHitbox, 0, 0, 0, 0, 0, 0);
        // setting initial positions
        leftPaddle.setXY(x + 12, h/2);
        rightPaddle.setXY(w - 12, h/2);
        ball.setXY(w/2 + x, h/2 + y);
        leftScore.setXY(x + w/2 - 32 , 20);
        rightScore.setXY(x + w/2 + 32, 20);
        dialog.setXY(x + w/2, y + h/2);
        // adding objects to list
        addObject(leftScore);
        addObject(rightScore);
        addObject(ball);
        addObject(leftPaddle);
        addObject(rightPaddle);
        addObject(dialog);
    }
    // set a random speed for ball
    public void setRandomBallSpeed(){
        Random random= new Random();
        float theta= random.nextFloat(0, 2*3.1416f);
        float length= random.nextFloat(7.0f, 10.0f);
        float vx= (float)Math.cos(theta) * length;
        float vy= (float)Math.sin(theta) * length;
        ball.setVelocity(vx, vy);
    }

    public void update(Character key){
        // if game is not started, perform appropriate operations and leave the method (using return;)
        if(!gameStarted){
            // stop theme sound
            theme.pause();
            // game over dialog; show announcement and reset game. Otherwise, show the pause dialog
            if(gameOver){
                theme.pause();
                if(leftPlayerScore > rightPlayerScore) dialog.setIndex(2);
                else dialog.setIndex(3);
            } else dialog.setIndex(1);
            // start/resume game
            if(key != null && key == ' '){
                gameStarted= true;
                // start theme sound
                theme.loop();
                // resetting if the game has ended
                if(gameOver){
                    // resetting
                    leftPlayerScore= 0;
                    rightPlayerScore= 0;
                    leftPaddle.setXY(sceneOriginX + 12, sceneHeight/2);
                    rightPaddle.setXY(sceneWidth - 12, sceneHeight/2);
                    ball.setXY(sceneWidth/2 + sceneOriginX, sceneHeight/2 + sceneOriginY);
                    leftScore.setXY(sceneOriginX + sceneWidth/2 - 32 , 20);
                    rightScore.setXY(sceneOriginX + sceneWidth/2 + 32, 20);
                    dialog.setXY(sceneOriginX + sceneWidth/2, sceneOriginY + sceneHeight/2);
                    gameOver= false;
                }
                dialog.setIndex(0);
            }
            return;
        }
        // ball-wall collisions
        if(ball.getY() >= sceneHeight || ball.getY() <= sceneOriginY){
            ball.setVelocity(ball.getVX(), -ball.getVY());
        }
        // ball-paddle collisions
        float theta, velocityLength, relation;
        if(ball.collidesWith(leftPaddle)){
            // calculate reflection angle and scale it so it won't be too vertical
            relation= (2*(ball.getY()-leftPaddle.getY())) / (ball.getHitbox().getHeight() + leftPaddle.getHitbox().getHeight());
            // checking if relation is out of bound
            if(relation > 1) relation= 1;
            if(relation < -1) relation= -1;
            theta= (float) Math.asin(relation);
            theta*= 0.8;
            // calculate current velocity vector length
            velocityLength= (float) Math.sqrt(ball.getVX()*ball.getVX() + ball.getVY()*ball.getVY());
            // reflection x factor is positive and calculate y reflection factor
            ball.setVelocity((float) (velocityLength * Math.cos(theta)), (float) (velocityLength * Math.sin(theta)));
            // play sound
            paddleCollisionSound.restart();
            paddleCollisionSound.play();
        }
        if(ball.collidesWith(rightPaddle)){
            // calculate reflection angle and scale it so it won't be too vertical
            relation= (2*(ball.getY()-rightPaddle.getY())) / (ball.getHitbox().getHeight() + rightPaddle.getHitbox().getHeight());
            // checking if relation is out of bound
            if(relation > 1) relation= 1;
            if(relation < -1) relation= -1;
            theta= (float) Math.asin(relation);
            theta*= 0.8;
            // calculate current velocity vector length
            velocityLength= (float) Math.sqrt(ball.getVX()*ball.getVX() + ball.getVY()*ball.getVY());
            // reflection x factor is negative and calculate y reflection factor
            ball.setVelocity((float) (-velocityLength * Math.cos(theta)), (float) (velocityLength * Math.sin(theta)));
            // play sound
            paddleCollisionSound.restart();
            paddleCollisionSound.play();
        }
        // all out of the scene
        if(ball.getX() <= sceneOriginX - ball.getHitbox().getWidth()){ // right player scored
            rightPlayerScore++;
            ball.setXY(sceneOriginX + sceneWidth/2, sceneOriginY + sceneHeight/2);
            setRandomBallSpeed();
        }
        if(ball.getX() >= sceneOriginX + sceneWidth + ball.getHitbox().getWidth()){ // left player scored
            leftPlayerScore++;
            ball.setXY(sceneOriginX + sceneWidth/2, sceneOriginY + sceneHeight/2);
            setRandomBallSpeed();
        }
        // set game over flag if a player score is 10
        if(leftPlayerScore == 10 || rightPlayerScore == 10){
            gameOver= true;
            gameStarted= false;
            gameOverSound.restart();
            gameOverSound.play();
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
                case ' ':
                    gameStarted= false;
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
        leftScore.setIndex(leftPlayerScore);
        rightScore.setIndex(rightPlayerScore);
        ball.update();
    }

}
