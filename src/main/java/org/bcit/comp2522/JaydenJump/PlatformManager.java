package org.bcit.comp2522.JaydenJump;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import processing.core.PApplet;

/**
 * PlatformManager class.
 * This class will be managing all the platforms for the game
 * Since we only need one instance of this class, we will be using the singleton
 *
 * @author Shawn Birring
 * @version 1.0
 */
public class PlatformManager {

  /**
   * Instance of the platform manager.
   */
  private static PlatformManager instance;

  /**
   * The platform's arraylist.
   */
  private final ArrayList<Platform> platforms;

  /**
   * The maximum number of platforms on the screen at a time.
   */
  private final int maxPlatforms;

  /**
   * The parent sketch, where we will draw things to.
   * (this is a reference to the MenuManager class, make it a singleton,
   * so that we don't hae to pass it in as a parameter)
   */
  private final PApplet sketch;

  /**
   * The speed of the platforms (moving down).
   */
  private final int platformSpeed;

  /**
   * The height the player gets from jumping off a platform.
   */
  private final int playerJumpHeight;


  /**
   * The speed of the movable platforms.
   */
  private final int movableSpeed;

  /**
   * The color green for non-breakable platforms.
   */
  private static final Color green = new Color(0, 255, 0);

  /**
   * The color red for breakable platforms.
   */
  private static final Color red = new Color(255, 0, 0);

  /**
   * The number of starting platforms to be generated at the beginning of the game.
   */
  private static final int STARTING_PLATFORMS = 6;

  /**
   * The player. (just make this a getInstance call,
   * instead of passing in as parameter and storing as an instance variable)
   */
  private final Player player;

  /**
   * How much a player has to be falling for it to be considered a jump.
   */
  private final int jumpThroughHeight;

  /**
   * Private constructor for the platform manager.
   *
   * @param maxPlatforms the maximum number of platforms
   *
   * @param sketch the parent sketch, where to draw things to
   *
   * @param platformSpeed the speed of the platforms moving down
   *
   * @param movableSpeed the speed of the movable platforms
   */
  private PlatformManager(int maxPlatforms, PApplet sketch,
                          int platformSpeed, int movableSpeed,
                          int jumpThroughHeight, int playerJumpHeight,
                          Player player) {
    this.maxPlatforms = maxPlatforms;
    this.sketch = sketch;
    this.platformSpeed = platformSpeed;
    platforms = new ArrayList<>();
    this.movableSpeed = movableSpeed;
    this.jumpThroughHeight = jumpThroughHeight;
    this.playerJumpHeight = playerJumpHeight;
    this.player = player;
  }

  /**
   * Gets the instance of the platform manager.
   *
   * @param maxPlatforms the maximum number of platforms
   *
   * @param window the parent sketch
   *
   * @param platformSpeed the speed of the platforms
   *
   * @param movableSpeed the speed of the movable platforms
   *
   * @return the instance of the platform manager
   */
  public static PlatformManager getInstance(int maxPlatforms, PApplet window,
                                            int platformSpeed, int movableSpeed,
                                            int jumpThroughHeight, int playerJumpHeight,
                                            Player player) {
    if (instance == null) {
      instance = new PlatformManager(maxPlatforms, window, platformSpeed,
              movableSpeed, jumpThroughHeight, playerJumpHeight, player);
    }
    return instance;
  }

  /**
   * Generates the starting platforms.
   */
  public void generateStartPlatforms() {
    float y = 0;
    for (int i = 0; i < STARTING_PLATFORMS; i++) {
      float x = sketch.random(sketch.width - Platform.getPlatformWidth());
      platforms.add(new Platform(x, y, green, 0, platformSpeed, false));
      y += 150;
    }
  }

  /**
   * Generates the platforms.
   * This method will be called every frame.
   */
  public void generatePlatforms() {

    // Remove platforms that are off the screen and add new ones
    while (platforms.size() < maxPlatforms) {
      float x = sketch.random(sketch.width - Platform.getPlatformWidth());
      boolean isBreakable = sketch.random(1.0f) < 0.1;
      Color platformColor = isBreakable ? red : green;
      float lastPlatformY = platforms.get(platforms.size() - 1).getYpos();
      float newY = lastPlatformY - Platform.getPlatformWidth() - Platform.getPlatformHeight();

      // Randomly generate a movable platform
      float vx = 0;
      if (sketch.random(1.0f) < 0.1) {
        float randomDirection = sketch.random(1.0f);
        if (randomDirection < 0.5) {
          vx = -movableSpeed; // Move left
        } else {
          vx = movableSpeed; // Move right
        }
      }

      // Create the new platform
      Platform newPlatform = new Platform(x, newY, platformColor,
              vx, platformSpeed, isBreakable);

      // Check for overlap with existing platforms
      boolean isOverlapping = false;
      for (Platform platform : platforms) {
        if (isOverlapping(newPlatform, platform)) {
          isOverlapping = true;
          break;
        }
      }

      // If no overlap, add the platform
      if (!isOverlapping) {
        platforms.add(newPlatform);
      }
    }
  }

  /**
   * Checks if two platforms are overlapping.
   *
   * @param p1 the first platform
   *
   * @param p2 the second platform
   *
   * @return true if the platforms are overlapping, false otherwise
   */
  public boolean isOverlapping(Platform p1, Platform p2) {
    return p1.getXpos() + Platform.getPlatformWidth() > p2.getXpos()
            && p2.getXpos() + Platform.getPlatformWidth() > p1.getXpos()
            && p1.getYpos() + Platform.getPlatformHeight() > p2.getYpos()
            && p2.getYpos() + Platform.getPlatformHeight() > p1.getYpos();
  }

  /**
   * Updates and draws the platforms.
   */
  public void updateAndDrawPlatforms() {
    for (int i = platforms.size() - 1; i >= 0; i--) {
      Platform platform = platforms.get(i);
      if (!platform.isOnScreen()) {
        platforms.remove(i);
      }
    }

    for (Platform platform : platforms) {
      platform.update();
      platform.draw();
    }
  }

  /**
   * Checks for collisions between the player and the platforms.
   * If the player collides with a breakable platform, it is removed.
   * If the player collides with a platform, the player's y velocity is set to -jumpHeight.
   */
  public void checkCollision() {
    Iterator<Platform> platformIterator = platforms.iterator();
    while (platformIterator.hasNext()) {
      Platform platform = platformIterator.next();
      if (this.player.collides(platform) && this.player.getVy() > jumpThroughHeight) {
        if (platform.isBreakable()) {
          platformIterator.remove();
        }
        player.setVy(-playerJumpHeight);
        break;
      }
    }
  }

  /**
   * Gets the platforms array list.
   *
   * @return the platforms
   */
  public ArrayList<Platform> getPlatforms() {
    return platforms;
  }
}