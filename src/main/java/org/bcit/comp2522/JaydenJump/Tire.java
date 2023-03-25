package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;

/**
 * The Tire class is a type of PowerUp that the Player can interact
 * with to be able momentarily to jump higher.
 *
 * @version 1.0
 *
 * @author Shawn Birring
 *
 * @since 2023-03-22
 */

public class Tire extends PowerUp {

  /** The amount that affects the y velocity of the Player. */
  private int boostHeight;

  /**
   * Creates an instance of the Tire class in the game.
   *
   * @param xpos The x position of the Tire
   *
   * @param ypos The y position of the Tire
   *
   * @param vx The x velocity of the Tire
   *
   * @param vy The y Velocity of the Tire
   *
   * @param isActive The boolean state that determines if the Tire is active or not
   */
  public Tire(float xpos,
              float ypos,
              float vx,
              float vy,
              int boostHeight,
              boolean isActive,
              PApplet sketch,
              Player player) {
    super(xpos, ypos, vx, vy, isActive, sketch, player);
    this.boostHeight = boostHeight;
  }

  /**
   * Boosts the height of the player.
   */
  private void boostPlayer() {
    if (isActive()) {
      getPlayer().setVy(getPlayer().getVy() - getBoostHeight());
    }
  }

  /**
   * Activates the Tire.
   */
  @Override
  public void activate() {
    setActive(true);
    boostPlayer();
  }

  /**
   * Deactivates the Tire.
   */
  @Override
  public void deactivate() {
    setActive(false);
  }

  /**
   * Returns the boost height of the Tire.
   *
   * @return boostHeight The boost height of the Tire
   */
  public int getBoostHeight() {
    return boostHeight;
  }

  /**
   * Sets the boost height of the Tire.
   *
   * @param boostHeight The boost height of the Tire
   */
  public void setBoostHeight(int boostHeight) {
    this.boostHeight = boostHeight;
  }


}
