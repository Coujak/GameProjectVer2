import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GameObject {

    private ImageIcon playerImagePath = new ImageIcon(getClass().getResource("player.png"));
    private Image playerImage = playerImagePath.getImage();
    private int playerWidth = playerImagePath.getIconWidth();
    private int playerHeight = playerImagePath.getIconHeight();
    private int playerHealth = 3, velocity_X = 0, velocity_Y = 0;

    public Player(int position_X, int position_Y) {
        super(position_X, position_Y);
    }

    public void movement() {
        checkBorderCollision();
        position_X+=velocity_X;
        position_Y+=velocity_Y;

        if(playerHealth == 0) {
            JOptionPane.showMessageDialog(null, "You're out of life, game will exit now","Game Over", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }

    }

    public void checkBorderCollision() {
        if (position_X + velocity_X < 0) {
            velocity_X = 0;
            position_Y = 0;
        }
        else if (position_X + velocity_Y < 0) {
            velocity_Y = 0;
            position_Y = 0;
        }
        else if (position_X + playerWidth + velocity_X > Dimensions.WINDOW_WIDTH-11) {
            velocity_X = 0;
            position_X = Dimensions.WINDOW_WIDTH-11 - playerWidth ;
        }
        else if (position_Y + playerHeight + velocity_Y > Dimensions.WINDOW_HEIGHT-30) {
            velocity_Y = 0;
            position_Y = Dimensions.WINDOW_HEIGHT-30 - playerHeight;
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: case KeyEvent.VK_W:
                velocity_Y = -5;
                break;
            case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
                velocity_Y = 5;
                break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_A:
                velocity_X = -5;
                break;
            case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
                velocity_X = 5;
                break;

        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: case KeyEvent.VK_W:
            case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
                velocity_Y = 0;
                break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_A:
            case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
                velocity_X = 0;
                break;
        }
    }


    public void paint(Graphics graphics) {
        graphics.drawImage(playerImage, position_X, position_Y, playerWidth, playerHeight, null);
        graphics.drawRect(position_X, position_Y, playerWidth,playerHeight);

        ImageIcon imagePath = new ImageIcon(getClass().getResource("hearts.png"));
        Image heartsImage = imagePath.getImage();

        for (int i=0, distance = 20; i < playerHealth; i++, distance+=imagePath.getIconWidth()+5)
            graphics.drawImage(heartsImage, distance, 20, null);
    }

    public int getPlayerWidth() {
        return playerWidth;
    }

    public void setPlayerWidth(int playerWidth) {
        this.playerWidth = playerWidth;
    }

    public int getPlayerHeight() {
        return playerHeight;
    }

    public void setPlayerHeight(int playerHeight) {
        this.playerHeight = playerHeight;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }
}
