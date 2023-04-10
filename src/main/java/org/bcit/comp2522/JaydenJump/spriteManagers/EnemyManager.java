package org.bcit.comp2522.JaydenJump.spriteManagers;

import java.util.ArrayList;
import java.util.Iterator;
import org.bcit.comp2522.JaydenJump.Game;
import org.bcit.comp2522.JaydenJump.Level;
import org.bcit.comp2522.JaydenJump.gameUI.MenuManager;
import org.bcit.comp2522.JaydenJump.sprites.Enemy;
import org.bcit.comp2522.JaydenJump.sprites.Player;
import processing.core.PApplet;

/**
 * Enemy manager class to manage the enemies.
 *
 * @author Ravdeep Aulakh
 * @version 1.0
 */
public class EnemyManager {

  /**
   * the arraylist of enemies in the game.
   */
  private final ArrayList<Enemy> enemies;

  /**
   * the sketch for the enemy manager.
   */
  private final PApplet sketch;

  /**
   * the spawn rate of enemies.
   */
  private final float spawnRate;

  /**
   * the spawn counter for enemies.
   */
  private int spawnCounter;

  /**
   * The instance of the EnemyManager class.
   */
  private static EnemyManager instance;

  /**
   * game instance.
   */
  private Game game;

  /**
   * the level number.
   */
  private int levelNum;

  /**
   * constants.
   */
  private static final int ENEMYWIDTH = 60;
  private static final int ENEMYHEIGHT = 60;
  private static final int ENEMYHEALTH = 1;
  private static final int ENEMYSPEED = 5;


  /**
   * constructor for the enemy manager class.
   *
   * @param level the spawnrate for the enemies
   * @param game the game object
   */
  private EnemyManager(Level level, Game game) {
    this.enemies = new ArrayList<>();
    this.sketch = MenuManager.getInstance();
    this.spawnRate = level.getSpawnRate();
    this.spawnCounter = 0;
    this.game = game;
    this.levelNum = level.getLevelNumber();
  }

  /**
   * Returns the instance of the EnemyManager class.
   *
   * @param level the spawnrate for the enemies
   * @param game the game object
   * @return the instance of the EnemyManager class
   */
  public static EnemyManager getInstance(Level level, Game game) {
    if (instance == null || instance.levelNum != level.getLevelNumber()) {
      instance = new EnemyManager(level, game);
    }
    return instance;
  }

  /**
   * update method for the enemy manager class.
   */
  public void update() {

    spawnCounter++;
    if (spawnCounter >= spawnRate) {
      spawnEnemy();
      spawnCounter = 0;
    }

    for (int i = enemies.size() - 1; i >= 0; i--) {
      Enemy enemy = enemies.get(i);
      enemy.update();

      if (enemy.collides(Player.getInstance().getProjectile())) {
        enemies.remove(enemy);
        enemy.update();
      }

    }

  }

  /**
   * draw method for the enemy manager class.
   */
  public void draw() {
    for (Enemy enemy : enemies) {
      enemy.draw();
    }
  }

  /**
   * Method to spawn enemies.
   */
  private void spawnEnemy() {
    int width = ENEMYWIDTH;
    int height = ENEMYHEIGHT;
    int health = ENEMYHEALTH;
    int speed = ENEMYSPEED;


    float randomNum = sketch.random(100);


    if (randomNum < spawnRate) {
      float x = sketch.random(sketch.width - width);
      float y = 0;
      Enemy enemy = new Enemy(x, y, 0, 0, health, speed, width, height);
      enemies.add(enemy);
    }
  }

  /**
   * Handle collision between enemy and player.
   */
  public void checkCollision() {
    Iterator<Enemy> enemyIterator = enemies.iterator();
    while (enemyIterator.hasNext()) {
      Enemy enemy = enemyIterator.next();
      if (enemy.collides(Player.getInstance())) {
        game.setLives(game.getLives() - 1);
        if (game.getLives() == 0) {
          BossManager.getInstance().setIsAlive(false);
          BossManager.getInstance().setBossHealth(3);
          game.endGame();
        }
        enemyIterator.remove();
      }
    }
  }

}
