import java.awt.*;
import java.util.Random;

public class EnemyArray {

    private Player player;
    private int maxEnemies, countEnemy = 0;
    private int score;
    private Enemy[] enemyArray;
    private Random random = new Random();

    public EnemyArray(Player player, int score, int maxEnemies) {
        this.player = player;
        this.score = score;
        this.maxEnemies = maxEnemies;
        this.enemyArray = new Enemy[maxEnemies];
    }

    public void addEnemy() {
        if(countEnemy == maxEnemies) {
            growSize();
        }

        if (score/600 == maxEnemies)
            countEnemy++;

        enemyArray[countEnemy] = new Enemy(this.random.nextInt(Dimensions.WINDOW_WIDTH - 75), 0);
        countEnemy++;

    }

    public void removeEnemy(int index) {
        if (countEnemy > 0) {
            for (int i=index; i < countEnemy-1; i++) {
                enemyArray[i] = enemyArray[i+1];
            }
            enemyArray[countEnemy - 1] = null;
            countEnemy--;
        }
    }

    public void growSize() {
        Enemy[] tempArray = null;
        if (countEnemy == maxEnemies) {
            tempArray = new Enemy[maxEnemies*2];
            for (int i = 0; i < maxEnemies; i++) {
                tempArray[i] = enemyArray[i];
            }
        }
        enemyArray = tempArray;
        maxEnemies++;
    }

    public void moveEnemy() {
            for (int i = 0; i < countEnemy; i++) {
                enemyArray[i].move();
            }
            checkPlayerCollision();
    }

    public void checkPlayerCollision() {
        for (int i = 0; i < countEnemy; i++) {
            if (enemyArray[i].position_Y >= Dimensions.WINDOW_HEIGHT - enemyArray[i].getEnemyHeight() - 30) {
                removeEnemy(i);
                player.setPlayerHealth(player.getPlayerHealth() - 1);
                if (player.getPlayerHealth() > 0) {
                    addEnemy();
                }
            }

            if (enemyArray[i].getBounds(enemyArray[i].getEnemyWidth(), enemyArray[i].getEnemyHeight()).intersects(player.getBounds(player.getPlayerWidth(), player.getPlayerHeight()))) {
                removeEnemy(i);
                player.setPlayerHealth(player.getPlayerHealth() - 1);
                if (player.getPlayerHealth() > 0) {
                    addEnemy();
                }
            }
        }
    }

    public boolean checkBulletCollision(Bullet bullet) {
        for (int i=0; i < countEnemy; i++) {
            if (enemyArray[i].getBounds(enemyArray[i].getEnemyWidth(), enemyArray[i].getEnemyHeight()).intersects(bullet.getBounds(bullet.getBulletWidth(), bullet.getBulletHeight()))) {
                removeEnemy(i);
                if (player.getPlayerHealth() > 0)
                    addEnemy();
                return true;
            }
        }
        return false;
    }

    public void paint(Graphics graphics) {
        for (int i = 0; i < countEnemy; i++) {
            enemyArray[i].paint(graphics);
        }
    }

    public void setMaxEnemies(int maxEnemies) {
        this.maxEnemies = maxEnemies;
    }
}
