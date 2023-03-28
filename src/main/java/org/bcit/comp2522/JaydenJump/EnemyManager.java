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
  private final ArrayList<Enemy> enemies;
  private final PApplet sketch;
  private final float spawnRate;
  private int spawnCounter;
  PImage image;


  public EnemyManager(PApplet sketch, float spawnRate, PImage img) {
    this.enemies = new ArrayList<>();
    this.sketch = sketch;
    this.spawnRate = spawnRate;
    this.spawnCounter = 0;
    this.image = img;
  }

  public void update() {

    spawnCounter++;
    if (spawnCounter >= spawnRate) {
      spawnEnemy();
      spawnCounter = 0;
    }

    System.out.println(Game.getPlayer().getLives());

    for (int i = enemies.size() - 1; i >= 0; i--) {
      Enemy enemy = enemies.get(i);
      enemy.update();

      // Check for collision with player
      if (enemy.collides(Game.getPlayer())) {
        Game.getPlayer().setLives(Game.getPlayer().getLives() - 1);
        enemies.remove(enemy);
        enemy.update();
        System.out.println(Game.getPlayer().getLives());
      }

      if(enemy.collides(Game.getPlayer().getProjectile())){
        enemies.remove(enemy);
        enemy.update();
      }

    }

  }

  public void draw() {
    for (Enemy enemy : enemies) {
      enemy.draw();
    }
  }

  private void spawnEnemy() {
    int width = 50;
    int height = 50;
    int health = 1;
    int speed = 5;

    // generate a random number between 0 and 1
    float randomNum = sketch.random(100);

    // check if the random number is less than the spawn rate
    if (randomNum < spawnRate) {
      float x = sketch.random(sketch.width - width);
      float y = 0;
      Enemy enemy = new Enemy(x, y, 0, 0, health, speed, width, height, image, sketch);
      enemies.add(enemy);
    }
  }

  public ArrayList<Enemy> getEnemies() {
    return enemies;
  }
}
