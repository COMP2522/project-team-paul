package org.bcit.comp2522.JaydenJump.sprites;

import org.bcit.comp2522.JaydenJump.Level;
import org.bcit.comp2522.JaydenJump.interfaces.Audible;
import org.bcit.comp2522.JaydenJump.gameUI.MenuManager;
import processing.core.PImage;
import javax.sound.sampled.Clip;

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

public class Tire extends PowerUp implements Audible {

  /** Audio clip used for playing Tire PowerUp. */
  private Clip clip;

  /**
   * The level of the game.
   */
  private Level level;

  /**
   * Creates an instance of the Tire class in the game.
   *
   * @param level of the game
   *
   * @param xpos The x position of the Tire
   *
   * @param ypos The y position of the Tire
   *
   * @param vx The x velocity of the Tire
   *
   * @param vy The y Velocity of the Tire
   */
  public Tire(Level level, float xpos,
              float ypos,
              float vx,
              float vy,
              PImage image) {
    super(level, xpos, ypos, vx, vy, image);
    this.clip = loadSound("tire.wav");
    this.level = level;
  }

  /**
   * Boosts the height of the player.
   */
  private void boostPlayer() {
    if (isActive()) {
      getPlayer().setVy(getPlayer().getVy() - level.getTireBoostHeight());
    }
  }

  /**
   * Activates the Tire.
   */
  @Override
  public void activate() {
    playSound();
    setActive(true);
    boostPlayer();
    super.getSketch().text("TIRE BOOST", getSketch().CENTER, getSketch().CENTER);
  }


  /**
   * Deactivates the Tire.
   */
  @Override
  public void deactivate() {
    setActive(false);
  }

  /** Plays sound clip of Tire if MenuManager's sound is on */
  @Override
  public void playSound() {
    if (MenuManager.sound) {
      clip.setFramePosition(0);
      clip.start();
    }
  }
}
