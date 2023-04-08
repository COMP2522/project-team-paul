package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Iterator;

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
   * the image for the enemies.
   */
  PImage image;

  /**
   * The instance of the EnemyManager class.
   */
  private static EnemyManager instance;


  /**
   * constructor for the enemy manager class.
   *
   * @param level the spawnrate for the enemies
   * @param img the image for the enemies
   */
  private EnemyManager(Level level, PImage img) {
    this.enemies = new ArrayList<>();
    this.sketch = MenuManager.getInstance();
    this.spawnRate = level.getSpawnRate();
    this.spawnCounter = 0;
    this.image = img;
  }

  /**
   * Returns the instance of the EnemyManager class.
   *
   * @param level the spawnrate for the enemies
   * @param img the image for the enemies
   * @return the instance of the EnemyManager class
   */
  public static EnemyManager getInstance(Level level, PImage img) {
    if (instance == null) {
      instance = new EnemyManager(level, img);
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
    int width = 60;
    int height = 60;
    int health = 1;
    int speed = 5;


    float randomNum = sketch.random(100);


    if (randomNum < spawnRate) {
      float x = sketch.random(sketch.width - width);
      float y = 0;
      Enemy enemy = new Enemy(x, y, 0, 0, health, speed, width, height, image);
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
        Game.setLives(Game.getLives() - 1);
        if (Game.getLives() == 0) {
          BossManager.getInstance().setIsAlive(false);
          BossManager.getInstance().setBossHealth(3);
          Game.endGame();
        }
        enemyIterator.remove();
      }
    }
  }

}
