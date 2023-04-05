package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Doodle guy or the player class.
 *
 * @author Ravdeep, Aulakh and Maximillian Yong
 * @version 1.1
 */
public class Player extends Sprite {


  /**
   * Check to see which way the player is facing.
   */
  private static boolean isFacingRight;

  /**
   * instance for the player.
   */
  private static Player instance = null;

  /**
   * for the image for the player.
   */
  private PImage[] image;

  /**
   * gravity for the jumps.
   */
  private float gravity;

  /**
   * speed for moving left and right.
   */
  private final float moveMentspeed;

  /**
   * size of the player usesd as width and height.
   * since the player is a square.
   */
  private final int playerSize;

  /**
   * Check to see if the player is shooting.
   */
  private boolean isShooting = false;

  /**
   * the projectile the player will shoot.
   */
  private static Projectile projectile;

  /**
   * the score of the player.
   */
  private static int score = 0;

  /**
   * the lives of the player.
   */
  private static int lives = 3;

  /**
   * the unlocked levels of the player.
   */
  private static int unlocked = 1;

  /**
   * Size of the player image.
   */
  private static final int IMAGE_SIZE = 80;


  /**
   * constructor for the player class.
   * @param sketch the sketch for the player
   * @param image the image to set the player to
   * @param moveMentspeed the speed of the player
   * @param gravity the gravity on the player
   */
  private Player(PApplet sketch, PImage[] image, float moveMentspeed, float gravity) {
    super(sketch.width / 2, 0f, 0f, 0f, sketch);
    this.image = image;
    this.playerSize = IMAGE_SIZE;
    this.moveMentspeed = moveMentspeed;
    this.gravity = gravity;
    this.projectile = new Projectile(getXpos(), getYpos(), 0, -2, 1, this);
  }

  /**
   * since this is singleton design this is to get a instance of the player.
   *
   * @param sketch the sketch
   * @param image the image for the player
   * @return instance of the player
   */
  public static Player getInstance(PApplet sketch, PImage[] image, float moveMentspeed,
                                   float gravity) {
    if (instance == null) {
      instance = new Player(sketch, image, moveMentspeed, gravity);
      isFacingRight = true;
    }
    return instance;
  }

  /**
   * draw the player onto the screen.
   * also does some projectile stuff and
   * will flip player if facing left or right.
   */
  @Override
  public void draw() {

    if (image != null) {
      if (!isShooting) {
        if (!isFacingRight) {
          super.getSketch().pushMatrix();
          super.getSketch().translate(getXpos() + playerSize / 2, getYpos() + playerSize / 2);
          super.getSketch().scale(-1, 1);
          super.getSketch().image(image[0], -playerSize / 2, -playerSize / 2, playerSize, playerSize);
          super.getSketch().popMatrix();
        } else {
          super.getSketch().pushMatrix();
          super.getSketch().translate(getXpos() + playerSize / 2, getYpos() + playerSize / 2);
          super.getSketch().image(image[0], -playerSize / 2, -playerSize / 2, playerSize, playerSize);
          super.getSketch().popMatrix();
        }
      } else {
        super.getSketch().pushMatrix();
        super.getSketch().translate(getXpos() + playerSize / 2, getYpos() + playerSize / 2);
        super.getSketch().image(image[1], -playerSize / 2, -playerSize / 2, playerSize, playerSize);
        super.getSketch().popMatrix();
        projectile.draw();
      }
    } else {
      System.err.println("Image is null. Please check the image loading process.");
    }

    if (isShooting) {
      projectile.update();
      if (projectile.getYpos() < 0) {
        stopShooting();
      }
    }
  }

  /**
   * update the players position after he jumps and moves side to side.
   */
  @Override
  public void update() {

    setVy(getVy() + gravity);

    setXpos(getXpos() + getVx());
    setYpos(getYpos() + getVy());

    setXpos(super.getSketch().constrain(getXpos(),
        playerSize / 10, super.getSketch().width - playerSize / 2));
    setYpos(super.getSketch().constrain(getYpos(),
        playerSize / 2, super.getSketch().height - playerSize / 2));
  }

  /**
   * check to see if the player is colliding with a platform.
   *
   * @param o The object to check for collision
   *
   * @return true if the player is colliding with a platform else false
   */
  @Override
  public boolean collides(Object o) {
    if (o instanceof Platform) {
      Platform platform = (Platform) o;

      float playerLeft = getXpos() - playerSize / 2;
      float playerRight = getXpos() + playerSize / 2;
      float playerTop = getYpos() - playerSize / 2;
      float playerBottom = getYpos() + playerSize / 2;

      float platformLeft = platform.getXpos() - Platform.getPlatformWidth() / 2;
      float platformRight = platform.getXpos() + Platform.getPlatformWidth() / 2;
      float platformTop = platform.getYpos() - Platform.getPlatformHeight() / 2;
      float platformBottom = platform.getYpos() + Platform.getPlatformHeight() / 2;

      boolean horizontallyOverlapping = playerLeft < platformRight && playerRight > platformLeft;
      boolean verticallyOverlapping = playerTop < platformBottom && playerBottom > platformTop;
      boolean abovePlatform = playerBottom < platformTop + Platform.getPlatformHeight() / 2;

      return horizontallyOverlapping && verticallyOverlapping && abovePlatform;
    }

    return false;
  }

  /**
   * move the player to the left.
   */
  public void moveLeft() {
    setVx(-moveMentspeed);
    isFacingRight = false;
  }

  /**
   * move the player to the right.
   */
  public void moveRight() {
    setVx(moveMentspeed);
    isFacingRight = true;
  }

  public void stopMoving() {
    setVx(0);
  }


  /**
   * Method to shoot projectiles.
   */
  public void shootProjectile() {
    if (!isShooting) {
      isShooting = true;
      projectile.setXpos(getXpos());
      projectile.setYpos(getYpos());
      projectile.setVy(-10);
    }
  }

  /**
   * Method to stop shooting projectiles.
   */
  public void stopShooting() {
    isShooting = false;
  }

  /**
   * Method to reset the player.
   *
   * @param x postion
   *
   * @param y position
   *
   * @param vx velocity
   *
   * @param vy velocity
   */
  public void reset(float x, float y, float vx, float vy) {
    setXpos(x);
    setYpos(y);
    setVx(vx);
    setVy(vy);
  }

  /**
   * Method to get the gravity.
   *
   * @return gravity
   */
  public float getGravity() {
    return gravity;
  }

  /**
   * Method to set the gravity.
   *
   * @param gravity used to set the gravity
   */
  public void setGravity(float gravity) {
    this.gravity = gravity;
  }

  /**
   * Method to get the moveMentspeed.
   *
   * @return moveMentspeed
   */
  public float getMoveMentspeed() {
    return moveMentspeed;
  }

  /**
   * Method to get the image size.
   *
   * @return image size
   */
  public int getPlayerSize() {
    return playerSize;
  }

  /**
   * method to check if the player is shooting.
   *
   * @return true if the player is shooting else false
   */
  public boolean isShooting() {
    return isShooting;
  }

  /**
   * Method to set if the player is shooting.
   *
   * @param shooting used to set if the player is shooting
   */
  public void setShooting(boolean shooting) {
    isShooting = shooting;
  }

  /**
   * Method to get the projectile.
   *
   * @return projectile the projectile
   */
  public static Projectile getProjectile() {
    return projectile;
  }

  /**
   * Method to set the projectile.
   *
   * @param projectile used to set the projectile
   *
   */
  public void setProjectile(Projectile projectile) {
    this.projectile = projectile;
  }

  /**
   * setter for the image.
   *
   * @param image the value you want to set the image too
   */
  public void setImage(PImage[] image) {
    this.image = image;
  }

  /**
   * Gets the score.
   *
   * @return score
   */
  public static int getScore() {
    return score;
  }

  /**
   * Sets the score.
   *
   * @param score the player's score
   */
  public static void setScore(int score) {
    Player.score = score;
  }

  /**
   * Gets the lives.
   *
   * @return lives
   */
  public static int getLives() {
    return lives;
  }

  /**
   * Sets the lives.
   *
   * @param lives the player's lives
   */
  public static void setLives(int lives) {
    Player.lives = lives;
  }

  /**
   * Gets the unlocked levels.
   *
   * @return unlocked
   */
  public static int getUnlocked() {
    return unlocked;
  }

  /**
   * Sets the unlocked levels.
   *
   * @param unlocked the player's unlocked levels
   */
  public static void setUnlocked(int unlocked) {
    Player.unlocked = unlocked;
  }


}