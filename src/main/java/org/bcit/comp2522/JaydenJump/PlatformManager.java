package org.bcit.comp2522.JaydenJump;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import processing.core.PApplet;

/**
 * PlatformManager class.
 *
 * @author Shawn, Birring
 * @version 1.0
 */
public class PlatformManager {

  private static PlatformManager instance;

  private final ArrayList<Platform> platforms;
  private final int maxPlatforms;
  private final PApplet sketch;
  private final int platformSpeed;

  private final int movableSpeed;
  private static final Color green = new Color(0, 255, 0);
  private static final Color red = new Color(255, 0, 0);
  private static final int startingPlatforms = 6;

  private PlatformManager(int maxPlatforms, PApplet sketch, int platformSpeed, int movableSpeed) {
    this.maxPlatforms = maxPlatforms;
    this.sketch = sketch;
    this.platformSpeed = platformSpeed;
    platforms = new ArrayList<>();
    this.movableSpeed = movableSpeed;
  }

  /**
   * Gets the instance of the platform manager.
   *
   * @param maxPlatforms the maximum number of platforms
   *
   * @param parent the parent sketch
   *
   * @param platformSpeed the speed of the platforms
   *
   * @param moveableSpeed the speed of the moveable platforms
   *
   * @return the instance of the platform manager
   */
  public static PlatformManager getInstance(int maxPlatforms, PApplet parent,
                                            int platformSpeed, int moveableSpeed) {
    if (instance == null) {
      instance = new PlatformManager(maxPlatforms, parent, platformSpeed, moveableSpeed);
    }
    return instance;
  }

  /**
   * Gets the platforms.
   *
   * @return the platforms
   */
  public ArrayList<Platform> getPlatforms() {
    return platforms;
  }

  /**
   * Generates the starting platforms.
   */
  public void generateStartPlatforms() {
    float y = 0;
    for (int i = 0; i < startingPlatforms; i++) {
      float x = sketch.random(sketch.width - 100);
      platforms.add(new Platform(sketch, x, y, green, 0, platformSpeed, false));
      y += 150;
    }
  }

  /**
   * Generates the platforms.
   */
  public void generatePlatforms() {
    while (platforms.size() < maxPlatforms) {
      float x = sketch.random(sketch.width - Platform.getWidth());
      boolean isBreakable = sketch.random(1.0f) < 0.1;
      Color platformColor = isBreakable ? red : green;
      float lastPlatformY = platforms.get(platforms.size() - 1).getYpos();
      float newY = lastPlatformY - 100 - Platform.getHeight();

      float vx = 0;
      if (sketch.random(1.0f) < 0.1) {
        float randomDirection = sketch.random(1.0f);
        if (randomDirection < 0.5) {
          vx = -movableSpeed; // Move left
        } else {
          vx = movableSpeed; // Move right
        }
      }

      Platform newPlatform = new Platform(sketch, x, newY, platformColor,
              vx, platformSpeed, isBreakable);

      // Check for overlap with existing platforms
      boolean isOverlapping = false;
      for (Platform platform : platforms) {
        if (isOverlapping(newPlatform, platform)) {
          isOverlapping = true;
          break;
        }
      }

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
    return p1.getXpos() + p1.getWidth() > p2.getXpos()
            && p2.getXpos() + p2.getWidth() > p1.getXpos()
            && p1.getYpos() + p1.getHeight() > p2.getYpos()
            && p2.getYpos() + p2.getHeight() > p1.getYpos();
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
   *
   * @param player the player
   *
   * @param minDoubleJumpHeight the minimum height for double jumping
   *
   * @param jumpHeight the height of the jump
   */
  public void checkCollision(Player player, int minDoubleJumpHeight, int jumpHeight) {
    Iterator<Platform> platformIterator = platforms.iterator();
    while (platformIterator.hasNext()) {
      Platform platform = platformIterator.next();
      if (player.collides(platform) && player.getVy() > minDoubleJumpHeight) {
        if (platform.isBreakable()) {
          platformIterator.remove();
        }
        player.setVy(-jumpHeight);
        break;
      }
    }
  }
}
