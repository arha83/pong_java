public class Hitbox {
    private int x; // top left corner x
    private int y; // top left corner y
    private int width; // width (obviously... 😐)
    private int height; // height (obviously... 😐)
    // constructor method.
    // parameters: explain themselves
    public Hitbox(int x, int y, int w, int h){
        this.x= x;
        this.y= y;
        width= w;
        height= h;
    }
    // getters and setters
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void setX(int x){
        this.x= x;
    }
    public void setY(int y){
        this.y= y;
    }
    public void setWidth(int w){
        width= w;
    }
    public void setHeight(int h){
        height= h;
    }
    // collision detection
    boolean collidesWith(Hitbox h){
        // x <= (h.x + h.width) && (x + width) >= h.x)    -> vertical intersection
        // y <= (h.y + h.height) && (y + height) >= h.y   -> horizontal intersection
        return (x <= (h.x + h.width) && (x + width) >= h.x) && (y <= (h.y + h.height) && (y + height) >= h.y);
    }
}
