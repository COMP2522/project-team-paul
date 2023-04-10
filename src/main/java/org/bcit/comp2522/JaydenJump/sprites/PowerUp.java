package org.bcit.comp2522.JaydenJump.sprites;

import org.bcit.comp2522.JaydenJump.Level;
import processing.core.PImage;


/**
 * The PowerUp class represents a type of consumable item that can be used by the player class.
 * This class extends the Sprite class.
 *
 * @version 1.0
 *
 * @author Hyuk Park
 *
 * @since 2023-03-21
 */

public abstract class PowerUp extends Sprite {

  /** The level of the game. */
  private Level level;

  /** State that determines whether the PowerUp is active or not. */
  private boolean isActive;

  /** The image associated with the PowerUp. */
  private PImage image;

  /** The player that will receive the power-up. */
  private final Player player;

  /**
   * Constructs the PowerUp class.
   *
   * @param level of the game
   *
   * @param xpos the x position of PowerUp
   *
   * @param ypos the y position of PowerUp
   *
   * @param vx the x velocity of PowerUp
   *
   * @param vy the y velocity of PowerUp
   *
   */
  public PowerUp(Level level, float xpos, float ypos, float vx, float vy, PImage image) {
    super(xpos, ypos, vx, vy);
    this.isActive = true;
    this.player = Player.getInstance();
    this.image = image;
    this.level = level;
    final int imageSize = level.getPowerUpSize() / 2;
  }

  /**
   * Draws the PowerUp to the screen.
   */
  @Override
  public void draw() {
    super.getSketch().image(
            image,
            getXpos() - level.getPowerUpSize() / 2,
            getYpos() - level.getPowerUpSize() / 2,
            level.getPowerUpSize(), level.getPowerUpSize());
  }

  /**
   * Returns true if the PowerUp is within bounds of the Game Window.
   *
   * @return true if on screen
   *
   */
  public boolean isOnScreen() {
    return getXpos() >= 0 && getXpos() + level.getPowerUpSize() <= super.getSketch().width
        && getYpos() >= 0 && getYpos() + level.getPowerUpSize() <= super.getSketch().height;
  }

  /**
   * Moves the PowerUp across the screen.
   */
  @Override
  public void update() {
    super.setXpos(super.getXpos() + super.getVx());
    super.setYpos(super.getYpos() + super.getVy());
  }

  /**
   * Checks if anything collides with the PowerUp.
   *
   * @param o The object to check for collision
   *
   * @return true if PowerUp collides with an object
   */
  @Override
  public boolean collides(Object o) {
    if (o instanceof Player) {

      if (player.getXpos() + player.getPlayerSize() >= super.getXpos()
          && player.getXpos() <= super.getXpos() + level.getPowerUpSize()
          && player.getYpos() + player.getPlayerSize() >= super.getYpos()
          && player.getYpos() <= super.getYpos() + level.getPowerUpSize()) {
        return true;
      }
    }
    return false;
  }

  /** Activates the PowerUp and affects the player class. */
  public void activate() {}

  /** Deactivates the PowerUp. */
  public void deactivate() {}

  /** Returns the state of the PowerUp. */
  public boolean isActive() {
    return isActive;
  }

  /** Sets the state of the PowerUp. */
  public void setActive(boolean active) {
    isActive = active;
  }

  /** Returns the image associated with the PowerUp. */
  public PImage getImage() {
    return image;
  }

  /** Sets the image associated with the PowerUp. */
  public void setImage(PImage image) {
    this.image = image;
  }

  /** Returns the player that will receive the power-up. */
  public Player getPlayer() {
    return player;
  }


}
