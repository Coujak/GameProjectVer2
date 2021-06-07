import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameWindow extends JPanel implements KeyListener, MouseListener {
    private Player player;
    private EnemyArray enemyArray;
    private BulletArray bulletArray;


    private int score = 0, maxEnemies = 1;

    public GameWindow() {
        JFrame gameFrame = new JFrame("Shooting Game");

        player = new Player(Dimensions.PLAYER_X , Dimensions.PLAYER_Y);
        enemyArray = new EnemyArray(player, maxEnemies);
        bulletArray = new BulletArray();
        gameFrame.setSize(Dimensions.WINDOW_WIDTH, Dimensions.WINDOW_HEIGHT);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.setContentPane(this);
        gameFrame.setVisible(true);
        gameFrame.setFocusable(true);
        JOptionPane.showMessageDialog(null, "Use W,A,S,D or the arrow keys to move," +
                                                            " Shoot falling enemies with space key or mouse button","Instructions", JOptionPane.PLAIN_MESSAGE);
        gameFrame.addKeyListener(this);
        gameFrame.addMouseListener(this);
        gameThread();
    }

    public void gameThread() {
        new Thread(() -> {
            while (true) {

                if (score/300 > maxEnemies) {
                    enemyArray.setMaxEnemies(++maxEnemies);
                }
                checkCollision();
                player.movement();
                enemyArray.createEnemies();
                enemyArray.moveEnemy();
                bulletArray.move();
                repaint();

                try {
                    Thread.sleep(30);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }).start();
    }

    public void checkCollision() {
        for (int i = 0; i < bulletArray.getBulletArray().size(); i++) {
            if (enemyArray.checkBulletCollision(bulletArray.getBulletArray().get(i))) {
                bulletArray.getBulletArray().remove(i);
                score+=100;
            }
        }
    }



    public static void main(String[] args) {
        new GameWindow();
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);

        ImageIcon imagePath = new ImageIcon(getClass().getResource("background.jpg"));
        Image backgroundImage = imagePath.getImage();
        graphics.drawImage(backgroundImage, 0, 0, Dimensions.WINDOW_WIDTH, Dimensions.WINDOW_HEIGHT, this);

        this.player.paint(graphics);
        this.enemyArray.paint(graphics);
        this.bulletArray.paint(graphics);

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.drawString("Score: "+score, Dimensions.WINDOW_WIDTH-150, 30);
        graphics.drawString("Bullets Remaining: "+(bulletArray.getMaxBullets()-bulletArray.getBulletArray().size()), 20, 100);
    }

    int keyPress = 0; // to shot only twice instead of no end
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);

        if (e.getKeyCode() == KeyEvent.VK_SPACE)
          if (bulletArray.getBulletArray().size() < bulletArray.getMaxBullets())
                bulletArray.addBullet(new Bullet(player.position_X+player.getPlayerWidth()/2, player.position_Y));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            if (bulletArray.getBulletArray().size() < bulletArray.getMaxBullets())
                bulletArray.addBullet(new Bullet(player.position_X+player.getPlayerWidth()/2, player.position_Y));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
