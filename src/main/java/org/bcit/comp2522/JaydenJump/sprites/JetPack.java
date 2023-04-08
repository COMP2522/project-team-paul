package org.bcit.comp2522.JaydenJump.sprites;

import org.bcit.comp2522.JaydenJump.Game;
import org.bcit.comp2522.JaydenJump.interfaces.Audible;
import org.bcit.comp2522.JaydenJump.gameUI.MenuManager;
import processing.core.PImage;
import javax.sound.sampled.*;

/**
 * The JetPack class is a type of PowerUp that allows the Player to fly up
 * screen for a certain period of time.
 *
 * @version 1.0
 *
 * @author Shawn Birring
 *
 * @since 2023-03-23
 */

public class JetPack extends PowerUp implements Audible {
  /** The amount of time the JetPack lasts. */
  private int duration;

  /** The amount the y velocity of the player is increased. */
  private int boostVelocity;

  /** Audio clip used for playing JetPack PowerUp. */
  private Clip clip;

  /**
   * Creates an Instance of a JetPack in the game.
   *
   * @param xpos The x position of JetPack
   *
   * @param ypos The y position of JetPack
   *
   * @param vx The x velocity of JetPack
   *
   * @param vy The y velocity of JetPack
   *
   * @param isActive The boolean state that determines whether JetPack is active or not
   *
   * @param duration The amount of time the JetPack lasts for
   *
   * @param boostVelocity The amount the y direction of Player is affected by JetPack
   */
  public JetPack(float xpos,
                 float ypos,
                 float vx,
                 float vy,
                 boolean isActive,
                 int duration,
                 int boostVelocity,
                 PImage image) {
    super(xpos, ypos, vx, vy, isActive, image);
    this.duration = duration;
    this.boostVelocity = boostVelocity;
    this.clip = loadSound("jetpack.wav");
  }

  /**
   * Activates the JetPack.
   */
  @Override
  public void activate() {
    if (isActive()) {
      playSound();
      super.getSketch().text("JETPACK", getSketch().CENTER, getSketch().CENTER);
      getPlayer().setVy(getBoostVelocity());
      if (duration > 0) {
        duration--;
      } else {
        deactivate();
      }
    }
  }
  //if jetpack is active player can not collide with anything?

  /**
   * Deactivates the JetPack.
   */
  @Override
  public void deactivate() {
    setActive(false);
  }

  /**
   * Returns the duration of the JetPack.
   *
   * @return The duration of the JetPack
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Sets the duration of the JetPack.
   *
   * @param duration The duration of the JetPack
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * Returns the boost velocity of the JetPack.
   *
   * @return The boost velocity of the JetPack
   */
  public int getBoostVelocity() {
    return boostVelocity;
  }

  /**
   * Sets the boost velocity of the JetPack.
   *
   * @param boostVelocity The boost velocity of the JetPack
   */
  public void setBoostVelocity(int boostVelocity) {
    this.boostVelocity = boostVelocity;
  }

  /** Plays sound clip of JetPack if MenuManager's sound is on */
  @Override
  public void playSound() {
    if (MenuManager.sound) {
      clip.setFramePosition(0);
      clip.start();
    }
  }

}
