public class GameObject {
    private Sprite sprite;
    private Hitbox hitbox;
    private float x; // x factor of the position
    private float y; // y factor of the position
    private float vx; // x factor of the velocity
    private float vy; // y factor of the velocity
    // constructor
    public GameObject(Sprite spr, Hitbox hb, float x, float y, float vx, float vy){
        sprite= spr;
        hitbox= hb;
        this.x= x;
        this.y= y;
        this.vx= vx;
        this.vy= vy;
    }
    // getters and setters
    public Sprite getSprite(){
        return sprite;
    }
    public Hitbox getHitbox(){
        return hitbox;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public float getVX(){
        return vx;
    }
    public float getVY(){
        return vy;
    }
    // this method should change position of object, sprite, and hitbox
    public void setXY(float x, float y){
        this.x= x;
        this.y= y;
        // position of sprite is integer; so we cast x and y to int
        sprite.setX((int) x);
        sprite.setY((int) y);
        // position of hitbox is integer and it is on the top left corner of it; So we calculate its center and cast it to int
        hitbox.setX((int) (x - hitbox.getWidth()/2));
        hitbox.setY((int) (y - hitbox.getHeight()/2));
    }
    public void setVelocity(float vx, float vy){
        this.vx= vx;
        this.vy= vy;
    }
    // update the position
    public void update(){
        setXY(x+vx, y+vy);
    }
}
