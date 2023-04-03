package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * class for the boss of the game.
 *
 * @author Ravdeep, Aulakh
 * @version 1.0
 */
public class Boss extends Sprite {

  /**
   * the sketch for the boss.
   */
  private PApplet sketch;

  /**
   * the image for the boss.
   */
  private PImage image;

  /**
   * the width for the boss.
   */
  private int width;

  /**
   * the height for the boss.
   */
  private int height;

  /**
   * a player to check for collision.
   */
  private Player player;


  /**
   * boolean to see which direction boss is moving.
   */
  private boolean movingRight;

  /**
   * the health of the boss.
   */
  private int health;

  /**
   * projectile the boss will shoot.
   */
  private Projectile projectile;

  /**
   * arraylist holding the projectiles the boss shots.
   */
  private ArrayList<Projectile> projectiles = new ArrayList<>();

  /**
   * max projectiles boss can shoot at once.
   */
  private int maxProjectiles = 1;

  /**
   * how many projectiles boss has shot.
   */
  private int projectileCounter = 0;

  /**
   * check if projectile has already hit boss once.
   */
  private boolean hasBeenHit = false;


  /**
   * constructor for the boss class.
   *
   * @param xpos x position of the boss
   * @param ypos y position of the boss
   * @param vx x velocity of the boss
   * @param vy y velocity of the boss
   * @param width the width of the boss
   * @param height the height of the boss
   * @param image the image for the boss
   * @param sketch the sketch for the boss
   * @param player the player for boss to collide with
   * @param health the health for the boss
   */
  public Boss(int xpos, int ypos, int vx, int vy, int width, int height,
              PImage image, PApplet sketch, Player player, int health) {
    super(xpos, ypos, vx, vy, sketch);
    this.sketch = sketch;
    this.image = image;
    this.width = width;
    this.height = height;
    this.player = player;
    this.movingRight = true;
    this.health = health;
    this.projectile = new Projectile(getXpos(), getYpos(), 0, 20, 1, player);
  }

  /**
   * draw method for the boss.
   */
  public void draw() {

    sketch.image(image, getXpos(), getYpos(), width, height);

  }

  /**
   * update method for the boss.
   */
  public void update() {

    if (movingRight) {
      setXpos(getXpos() + getVx());
    } else {
      setXpos(getXpos() - getVx());
    }

    if (getXpos() < 0 || getXpos() > sketch.width - width) {
      movingRight = !movingRight;
    }

    if (projectileCounter < maxProjectiles) {
      Projectile projectile = new Projectile(getXpos()
              + (width / 2), getYpos() + height, 0, 8, 1, player);
      projectiles.add(projectile);
      projectileCounter++;
    }

    for (Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext(); ) {
      Projectile projectile = iterator.next();
      projectile.draw();
      projectile.update();
      if (projectile.collides(player)) {
        Game.setLives(Game.getLives() - 1);
        if (Game.getLives() == 0) {
          Game.endGame();
        }
      }
      if (projectile.getYpos() > sketch.height || projectile.collides(player)) {
        iterator.remove();
        projectileCounter--;
      }
    }

  }


  /**
   * collision method for the boss.
   *
   * @param o The object to check for collision
   *
   * @return if the boss collided with them or not
   */
  @Override
  public boolean collides(Object o) {
    if (o instanceof Player) {
      Player player = (Player) o;
      return (getXpos() + width >= player.getXpos() && getXpos()
              <= player.getXpos() + player.getPlayerSize()
              && getYpos() + height >= player.getYpos() && getYpos()
              <= player.getYpos() + player.getPlayerSize());
    } else if (o instanceof Projectile) {
      Projectile projectile = (Projectile) o;
      if (player.isShooting()) {
        return (getXpos() + width >= projectile.getXpos() && getXpos() <= projectile.getXpos() + projectile.getHeight())
                && getYpos() + height >= projectile.getYpos() && getYpos()
                <= projectile.getYpos() + projectile.getWidth();
      }
    }
    return false;
  }


  /**
   * getter for the boss health.
   *
   * @return the boss health
   */
  public int getHealth() {
    return health;
  }

  /**
   * setter for the boss health.
   *
   * @param health the value you want to set the health too
   */
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * getter for the has been hit.
   *
   * @return the value of has been hit
   */
  public boolean hasBeenHit() {
    return hasBeenHit;
  }

  /**
   * setter for has been hit.
   *
   * @param hit the value you want to set the has been hit too
   */
  public void setHasBeenHit(boolean hit) {
    hasBeenHit = hit;
  }

}
