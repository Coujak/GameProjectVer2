import javax.swing.*;
import java.awt.*;

public class Bullet extends GameObject {

    private ImageIcon bulletImagePath = new ImageIcon(getClass().getResource("bullet.png"));
    private Image bulletImage = bulletImagePath.getImage();
    private int bulletWidth = bulletImagePath.getIconWidth();
    private int bulletHeight = bulletImagePath.getIconHeight();

    public Bullet(int position_X, int position_Y) {
        super(position_X, position_Y);
    }

    public void move() {
        position_Y -= 20;
    }


    public void paint(Graphics graphics) {
        graphics.drawImage(bulletImage, position_X, position_Y, null);
    }

    public int getBulletWidth() {
        return bulletWidth;
    }

    public void setBulletWidth(int bulletWidth) {
        this.bulletWidth = bulletWidth;
    }

    public int getBulletHeight() {
        return bulletHeight;
    }

    public void setBulletHeight(int bulletHeight) {
        this.bulletHeight = bulletHeight;
    }


}
