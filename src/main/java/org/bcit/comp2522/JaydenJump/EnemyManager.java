package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class EnemyManager {
  private final ArrayList<Enemy> enemies;
  private final PApplet sketch;
  private final int spawnRate;
  private int spawnCounter;

  public EnemyManager(PApplet sketch, int spawnRate) {
    this.enemies = new ArrayList<>();
    this.sketch = sketch;
    this.spawnRate = spawnRate;
    this.spawnCounter = 0;
  }

  public void update() {
    spawnCounter++;
    if (spawnCounter >= spawnRate) {
      spawnEnemy();
      spawnCounter = 0;
    }

    for (int i = enemies.size() - 1; i >= 0; i--) {
      Enemy enemy = enemies.get(i);
      enemy.update();
      if (enemy.isToBeRemoved()) {
        enemies.remove(i);
      }
    }
  }

  public void draw() {
    for (Enemy enemy : enemies) {
      enemy.draw();
    }
  }

  private void spawnEnemy() {
    PImage image = sketch.loadImage("./Images/enemy.png");
    int width = 50;
    int height = 50;
    float x = sketch.random(sketch.width - width);
    float y = 0;
    int health = 1;
    int speed = 5;
    Enemy enemy = new Enemy(x, y, 0, 0, health, speed, width, height, image, sketch);
    enemies.add(enemy);
  }

}
