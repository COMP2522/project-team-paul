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
   * the health of the boss.
   */
  private int bossHealth;

  /**
   * The single instance of BossManager.
   */
  private static BossManager instance;


  /**
   * constructor for the boss manager class.
   *
   * @param image the image of the boss
   * @param sketch the sketch for the boss
   * @param level the max amount of bosses
   */
  private BossManager(PImage image, PApplet sketch,
                      Level level) {
    this.image = image;
    this.width = 150;
    this.height = 150;
    this.sketch = sketch;
    this.player = Player.getInstance();
    this.maxBosses = level.getMaxBosses();
    this.isAlive = false;
    this.bossHealth = 3;
  }

  /**
   * Get the single instance of BossManager.
   *
   * @param image  the image of the boss
   * @param sketch the sketch for the boss
   * @param level  the max amount of bosses
   * @return the single instance of BossManager
   */
  public static BossManager getInstance(PImage image, PApplet sketch,
                                         Level level) {
    if (instance == null) {
      instance = new BossManager(image, sketch, level);
    }
    return instance;
  }

  /**
   * getInstance method to get the instance of the boss manager.
   *
   * @return instance of the boss manager
   */
  public static BossManager getInstance() {
    if (instance == null) throw new IllegalStateException("Boss Manager has not been initialized");
    return instance;
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
      int health = bossHealth;
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
        player.bossReset();
        if (Game.getLives() == 0) {
          setIsAlive(false);
          boss.setHealth(3);
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

  /**
   * setter for the boss health.
   *
   * @param bossHealth what you want to set the boss health to
   */
  public void setBossHealth(int bossHealth) {
    this.bossHealth = bossHealth;
  }
}