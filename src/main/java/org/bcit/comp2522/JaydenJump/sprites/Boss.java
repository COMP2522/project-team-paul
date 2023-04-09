package org.bcit.comp2522.JaydenJump.sprites;

import java.util.ArrayList;
import java.util.Iterator;
import org.bcit.comp2522.JaydenJump.Game;
import processing.core.PImage;

/**
 * class for the boss of the game.
 *
 * @author Ravdeep, Aulakh
 * @version 1.0
 */
public class Boss extends Sprite {

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
   * game instance.
   */
  private Game game;


  /**
   * constructor for the boss class.
   *
   * @param xpos x position of the boss
   * @param ypos y position of the boss
   * @param vx x velocity of the boss
   * @param vy y velocity of the boss
   * @param width the width of the boss
   * @param height the height of the boss
   * @param health the health for the boss
   */
  public Boss(int xpos, int ypos, int vx, int vy, int width, int height,
               int health, Game game) {
    super(xpos, ypos, vx, vy);
    this.image = super.getSketch().loadImage("./Images/Boss.png");
    this.width = width;
    this.height = height;
    this.player = Player.getInstance();
    this.movingRight = true;
    this.health = health;
    this.projectile = new Projectile(getXpos(), getYpos(), 0, 20, 1, player);
    this.game = game;
  }

  /**
   * draw method for the boss.
   */
  public void draw() {

    super.getSketch().image(image, getXpos(), getYpos(), width, height);

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

    if (getXpos() < 0 || getXpos() > super.getSketch().width - width) {
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
        game.setLives(game.getLives() - 1);
        if (game.getLives() == 0) {
          game.endGame();
        }
      }
      if (projectile.getYpos() > super.getSketch().height || projectile.collides(player)) {
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
        return (getXpos() + width >= projectile.getXpos() && getXpos() <= projectile.getXpos()
                + projectile.getHeight())
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


}
