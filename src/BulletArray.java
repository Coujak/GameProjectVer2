import java.awt.*;
import java.util.ArrayList;

public class BulletArray {

    private ArrayList<Bullet> bulletArray = new ArrayList<>();
    private Bullet bullet;

    private final int maxBullets = 10;


    public void checkCollision() {
        for (int i=0; i < bulletArray.size(); i++) {
            if (bullet.position_Y <= 0) {
                bulletArray.remove(i);
            }
        }
    }

    public void move() {
        for (int i = 0; i < bulletArray.size(); i++) {
            bullet = bulletArray.get(i);
            bullet.move();
        }
        checkCollision();
    }

    public void paint(Graphics graphics) {
        for (int i = 0; i < bulletArray.size(); i++) {
            bullet = bulletArray.get(i);
            bullet.paint(graphics);
        }
    }

    public void addBullet(Bullet bullet) {
        bulletArray.add(bullet);

    }

    public ArrayList<Bullet> getBulletArray() {
        return bulletArray;
    }

    public int getMaxBullets() {
        return maxBullets;
    }


}
