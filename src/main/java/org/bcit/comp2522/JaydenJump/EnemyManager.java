package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

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
   * constructor for the enemy manager class.
   *
   * @param sketch the sketch for the enemy manager
   * @param spawnRate the spawnrate for the enemies
   * @param img the image for the enemies
   */
  public EnemyManager(PApplet sketch, float spawnRate, PImage img) {
    this.enemies = new ArrayList<>();
    this.sketch = sketch;
    this.spawnRate = spawnRate;
    this.spawnCounter = 0;
    this.image = img;
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
      Enemy enemy = new Enemy(x, y, 0, 0, health, speed, width, height, image, sketch);
      enemies.add(enemy);
    }
  }

  /**
   * getter for the arraylist of enemies.
   *
   * @return the array list of enemies
   */
  public ArrayList<Enemy> getEnemies() {
    return enemies;
  }
}
