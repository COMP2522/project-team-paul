package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;

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
   * @param width The width of the Tire
   *
   * @param height The height of the Tire
   *
   * @param isActive The boolean state that determines if the Tire is active or not
   *
   * @param image The image of the Tire
   */
  public Tire(int xpos, int ypos, int vx, int vy, int width, int height,
              boolean isActive, PImage image, PApplet sketch, Player player) {
    super(xpos, ypos, vx, vy, width, height, isActive, image, sketch, player);
  }

  /** The amount that affects the jump height of the Player. */
  private int boostHeight;

  /**
   * Retrieves the boost height of Tire.
   *
   * @return boostHeight of Tire
   */
  public int getBoostHeight() {
    return boostHeight;
  }

  /**
   * Reassigns boost height of Tire to a different value.
   *
   * @param boostHeight of Tire that changes the current boostHeight
   */
  public void setBoostHeight(int boostHeight) {
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

  @Override
  public void activate() {
    setActive(true);
    boostPlayer();
  }

  @Override
  public void deactivate() {
    setActive(false);
  }
}
