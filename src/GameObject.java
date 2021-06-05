import java.awt.*;

public class GameObject {
    public int position_X, position_Y;

    public GameObject(int position_X, int position_Y) {
        this.position_X = position_X;
        this.position_Y = position_Y;

    }

    public Rectangle getBounds(int width, int height) {
        return new Rectangle(position_X, position_Y, width, height);
    }
}
