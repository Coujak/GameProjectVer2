import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Dimensions {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //private static final ImageIcon bulletImagePath = new ImageIcon(getClass().getResource("player.jpg"));

    public static final int WINDOW_WIDTH = (int)(screenSize.getWidth()/2);
    public static final int WINDOW_HEIGHT = (int)(screenSize.getHeight()/2);

    public static final int PLAYER_X = WINDOW_WIDTH/2;
    public static final int PLAYER_Y = WINDOW_HEIGHT - 100;

   /* public static final Image BULLET_IMAGE = bulletImagePath.getImage();
    public static final int BULLET_WIDTH = bulletImagePath.getIconWidth();
    public static final int BULLET_HEIGHT = bulletImagePath.getIconHeight(); */

}
