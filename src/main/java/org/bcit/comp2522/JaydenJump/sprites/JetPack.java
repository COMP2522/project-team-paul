package org.bcit.comp2522.JaydenJump.sprites;

import javax.sound.sampled.*;
import org.bcit.comp2522.JaydenJump.Level;
import org.bcit.comp2522.JaydenJump.gameUI.MenuManager;
import org.bcit.comp2522.JaydenJump.interfaces.Audible;
import processing.core.PImage;


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

  /** Audio clip used for playing JetPack PowerUp. */
  private Clip clip;

  private Level level;

  private int duration;


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
   */
  public JetPack(Level level, float xpos, float ypos, float vx, float vy, PImage image) {
    super(level, xpos, ypos, vx, vy, image);
    this.level = level;
    this.duration = level.getJetPackDuration();
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
      getPlayer().setVy(level.getJetPackBoostVelocity());
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

  /** Plays sound clip of JetPack if MenuManager's sound is on. */
  @Override
  public void playSound() {
    if (MenuManager.sound) {
      clip.setFramePosition(0);
      clip.start();
    }
  }

}
