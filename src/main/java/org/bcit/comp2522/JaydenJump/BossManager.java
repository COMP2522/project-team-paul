package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class that manages a list of Boss objects.
 *
 * @author Ravdeep, Aulakh
 * @version 1.0
 */
public class BossManager {

  /**
   * the sketch for the boss manager.
   */
  private PApplet sketch;


  /**
   * the image for the boss.
   */
  private PImage image;

  /**
   * the width of the boss.
   */
  private int width;

  /**
   * the height of the boss.
   */
  private int height;

  /**
   * the player.
   */
  private Player player;

  /**
   * array list to store the boss.
   */
  private ArrayList<Boss> bosses = new ArrayList<>();


  /**
   * max amount of bosses on screen at once.
   */
  private int maxBosses;

  /**
   * amount of bosses currently in the game.
   */
  private int bossCounter = 0;

  /**
   * if the bose is alive or not.
   */
  private boolean isAlive;


  /**
   * constructor for the boss manager class.
   *
   * @param image the image of the boss
   * @param width the width of the boss
   * @param height the height of the boss
   * @param sketch the sketch for the boss
   * @param player the player instance
   * @param maxBosses the max amount of bosses
   */
  public BossManager(PImage image, int width, int height, PApplet sketch,
                     Player player, int maxBosses) {
    this.image = image;
    this.width = width;
    this.height = height;
    this.sketch = sketch;
    this.player = player;
    this.maxBosses = maxBosses;
    this.isAlive = false;
  }

  /**
   * update method for the boss manager class.
   */
  public void update() {
    if (bossCounter < maxBosses && !isAlive) {
      int xpos = sketch.width / 2;
      int ypos = 70;
      int vx = 5;
      int vy = 0;
      int health = 3;
      Boss boss = new Boss(xpos, ypos, vx, vy, width, height, image, sketch, player, health);
      bosses.add(boss);
      bossCounter++;
    }

    for (Iterator<Boss> iterator = bosses.iterator(); iterator.hasNext();) {
      Boss boss = iterator.next();
      boss.draw();
      boss.update();
      if (boss.collides(player)) {
        Game.setLives(Game.getLives() - 1);
        player.reset(player.getXpos(), 400, 0, 0);
        if (Game.getLives() == 0) {
          Game.endGame();
        }
      }
      if (boss.collides(player.getProjectile())) {
        player.setShooting(false);
        boss.setHealth(boss.getHealth() - player.getProjectile().getDamage());

      }
      if (boss.getHealth() == 0) {
        isAlive = true;
        iterator.remove();
        bossCounter--;
      }
    }
  }


  /**
   * draw method for the boss manager class.
   */
  public void draw() {
    for (Boss boss : bosses) {
      boss.draw();
    }
  }

  /**
   * setter for if the boss is alive or not.
   *
   * @param isAlive set if the boss is alive or not
   */
  public void setIsAlive(boolean isAlive) {
    this.isAlive = isAlive;
  }
}