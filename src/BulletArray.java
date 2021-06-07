import java.awt.*;


public class BulletArray {

    private int maxBullets = 10;
    private int countBullets = 0;
    private Bullet[] bulletArray = new Bullet[maxBullets];

    public void addBullet(Bullet bullet) {
        bulletArray[countBullets] = bullet;
        countBullets++;
    }

    public void removeBullet(int index) {
        if (countBullets > 0) {
            for (int i = index; i < countBullets-1; i++) {
                bulletArray[i] = bulletArray[i+1];
            }
            bulletArray[countBullets - 1] = null;
            countBullets--;
        }
    }

    public void checkCollision() {
        for (int i = 0; i < countBullets; i++) {
            if (bulletArray[i].position_Y <= 0) {
                removeBullet(i);
            }
        }
    }

    public void move() {
        for (int i = 0; i < countBullets; i++) {
            bulletArray[i].move();
        }
        checkCollision();
    }

    public void paint(Graphics graphics) {
        for (int i = 0; i < countBullets; i++) {
            bulletArray[i].paint(graphics);
        }
    }

    public int getMaxBullets() {
        return maxBullets;
    }

    public Bullet[] getBulletArray() {
        return bulletArray;
    }

    public int getCountBullets() {
        return countBullets;
    }
}
