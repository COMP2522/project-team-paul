package org.bcit.comp2522.JaydenJump.sprites;

import org.bcit.comp2522.JaydenJump.Level;
import org.bcit.comp2522.JaydenJump.gameUI.MenuManager;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Doodle guy or the player class.
 *
 * @author Ravdeep, Aulakh and Maximillian Yong, Shawn Birring
 * @version 1.1
 */
public class Player extends Sprite {

  /**
   * The speed of the projectile.
   */
  private static final float PROJECTILE_SPEED = 10;

  /**
   * The minimum y position for the player.
   */
  private static final float MIN_YPOS = 10f;
  /**
   * Check to see which way the player is facing.
   */
  private boolean isFacingRight;

  /**
   * instance for the player.
   */
  private static Player instance;

  /**
   * for the image for the player.
   */
  private final PImage[] image;

  /**
   * gravity for the jumps.
   */
  private final float gravity;

  /**
   * speed for moving left and right.
   */
  private final float movementSpeed;

  /**
   * Check to see if the player is shooting.
   */
  private boolean isShooting;

  /**
   * the projectile the player will shoot.
   */
  private final Projectile projectile;

  /**
   * Size of the player image.
   */
  private static final int IMAGE_SIZE = 80;

  /**
   * store the level number in case we need to reconstruct a player for a new level.
   */
  private final int levelNumber;

  /**
   * constructor for the player class.
   *
   * @param level stats for the player
   */
  private Player(Level level) {
    super(MenuManager.getInstance().width / 2f, 0f, 0f, 0f);
    this.image         = loadPlayerImages();
    this.levelNumber   = level.getLevelNumber();
    this.movementSpeed = level.getPlayerSpeed();
    this.gravity       = level.getGravity();
    this.projectile    = new Projectile(getXpos(), getYpos(), 0, -2, 1, this);
    isFacingRight      = true;
  }

  /**
   * loads in the two images need for the player.
   *
   * @return the two images for the player
   */
  private PImage[] loadPlayerImages() {
    PImage[] playerImages = new PImage[2];
    playerImages[0]       = super.getSketch().loadImage("./Images/Player.png");
    playerImages[1]       = super.getSketch().loadImage("./Images/PlayerShoot.png");
    return playerImages;
  }

  /**
   * since this is singleton design this is to get an instance of the player.
   *
   * @param level the level for the player, including values for movement speed and gravity.
   */
  public static Player getInstance(Level level) {
    if (instance == null || instance.levelNumber != level.getLevelNumber()) {
      instance = new Player(level);
    }
    return instance;
  }

  /**
   * getInstance method to get the instance of the player.
   *
   * @return instance of the player
   */
  public static Player getInstance() {
    if (instance == null) {
      throw new IllegalStateException("Player has not been initialized");
    }
    return instance;
  }

  /**
   * draw the player onto the screen.
   * will also draw the projectile if the player is shooting.
   * and will update the photo if the player is shooting/facing left or right.
   */
  @Override
  public void draw() {
    float halfSize = IMAGE_SIZE / 2f;
    PApplet sketch = super.getSketch();
    sketch.pushMatrix();
    sketch.translate(getXpos() + halfSize, getYpos() + halfSize);
    if (!isShooting) {
      if (!isFacingRight) {
        sketch.scale(-1, 1);
      }
      sketch.image(image[0], -halfSize, -halfSize, IMAGE_SIZE, IMAGE_SIZE);
    } else {
      sketch.image(image[1], -halfSize, -halfSize, IMAGE_SIZE, IMAGE_SIZE);
      sketch.popMatrix();
      projectile.update();
      if (projectile.getYpos() < 0) {
        stopShooting();
      }
      projectile.draw();
      return;
    }
    sketch.popMatrix();
  }


  /**
   * update the players position after he jumps and moves side to side.
   */
  @Override
  public void update() {

    setVy(getVy() + gravity);

    setXpos(getXpos() + getVx());
    setYpos(getYpos() + getVy());

    float halfSize = IMAGE_SIZE / 2f;
    // constrain the player to the screen width and height
    setXpos(PApplet.constrain(getXpos(),
        IMAGE_SIZE / MIN_YPOS, super.getSketch().width - halfSize));
    setYpos(PApplet.constrain(getYpos(),
            halfSize, super.getSketch().height - halfSize));
  }

  /**
   * check to see if the player is colliding with a platform.
   *
   * @param o -> platform The platform to check for collision
   *
   * @return true if the player is colliding with a platform else false
   */
  @Override
  public boolean collides(Object o) {
    if (o instanceof Platform platform) {

      float halfSize = IMAGE_SIZE / 2f;
      float halfPlatformWidth = Platform.getPlatformWidth() / 2f;
      float halfPlatformHeight = Platform.getPlatformHeight() / 2f;


      float playerLeft = getXpos() - halfSize;
      float playerRight = getXpos() + halfSize;
      float playerTop = getYpos() - halfSize;
      float playerBottom = getYpos() + halfSize;


      float platformLeft = platform.getXpos() - halfPlatformWidth;
      float platformRight = platform.getXpos() + halfPlatformWidth;
      float platformTop = platform.getYpos() - halfPlatformHeight;
      float platformBottom = platform.getYpos() + halfPlatformHeight;


      boolean horizontallyOverlapping = playerLeft < platformRight && playerRight > platformLeft;
      boolean verticallyOverlapping = playerTop < platformBottom && playerBottom > platformTop;
      boolean abovePlatform = playerBottom < platformTop + halfPlatformHeight;

      return horizontallyOverlapping && verticallyOverlapping && abovePlatform;
    }

    return false;
  }

  /**
   * move the player to the left.
   */
  public void moveLeft() {
    setVx(-movementSpeed);
    isFacingRight = false;
  }

  /**
   * move the player to the right.
   */
  public void moveRight() {
    setVx(movementSpeed);
    isFacingRight = true;
  }


  /**
   * Method to shoot projectiles.
   */
  public void shootProjectile() {
    if (!isShooting) {
      isShooting = true;
      projectile.setXpos(getXpos());
      projectile.setYpos(getYpos());
      projectile.setVy(-PROJECTILE_SPEED);
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
   */
  public void reset() {
    setXpos(super.getSketch().width / 2f);
    setYpos(0);
    setVx(0);
    setVy(0);
  }

  /**
   * Method to reset the player when they collide with the boss.
   */
  public void bossReset() {
    setXpos(super.getSketch().width / 2f);
    setYpos(400);
    setVy(0);
    setVx(0);
  }

  /**
   * Method to get the image size.
   *
   * @return image size
   */
  public int getPlayerSize() {
    return IMAGE_SIZE;
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
  public Projectile getProjectile() {
    return projectile;
  }
}