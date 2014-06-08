package karataparty.sumobird;

/**
 * Created by the karatekidz on 30/03/14.
 */
public class Pipe {
    private int x;
    private int gapY;
    private int gapHeight;
    private int width;

    public Pipe(int gapY){
        this.x = 0;
        this.gapY = gapY;
        this.width = 50;
        this.gapHeight = 150;
    }

    public int getX() {
        return x;
    }
    public int getGapY() {
        return gapY;
    }
    public int getWidth() {
        return width;
    }
    public int getGapHeight() {
        return gapHeight;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setGapY(int gapY) {
        this.gapY = gapY;
    }

    public void moveX(int x){
        this.x += x;
    }

}
