package org.bcit.comp2522.JaydenJump.sprites;

import javax.sound.sampled.Clip;

import org.bcit.comp2522.JaydenJump.Game;
import org.bcit.comp2522.JaydenJump.gameUI.MenuManager;
import org.bcit.comp2522.JaydenJump.interfaces.Audible;
import processing.core.PImage;


/**
 * A type of PowerUp that gives the player an extra life in the game.
 *
 * @version 1.0
 *
 * @author Shawn Birring
 *
 * @since 2023-03-22
 */

public class ExtraLife extends PowerUp implements Audible {

  /** Audio clip used for playing ExtraLife PowerUp. */
  private Clip clip;

  /**
   * Maxmimum amount of ExtraLife sprites that are spawned in each cycle.
   */
  private static final int MAXIMUMLIVES = 3;

  private Game game;

  /**
   * Creates an instance of ExtraLife PowerUp in the game.
   *
   * @param xpos The x position of ExtraLife
   *
   * @param ypos The y position of ExtraLife
   *
   * @param vx The x velocity of ExtraLife
   *
   * @param vy The y velocity of ExtraLife
   *
   * @param isActive The boolean state that determines whether ExtraLife is active or not
   */
  public ExtraLife(float xpos,
                   float ypos,
                   float vx,
                   float vy,
                   boolean isActive,
                   PImage image, Game game) {
    super(xpos, ypos, vx, vy, isActive, image);
    this.game = game;
    this.clip = loadSound("extraLife.wav");
  }

  /**
   * Increases the life of the Player by one.
   */
  public void increaseLife() {
    if (game.getLives() < MAXIMUMLIVES) {
      game.setLives(game.getLives() + 1);
    }
  }

  /**
   * Activates the ExtraLife PowerUp.
   */
  @Override
  public void activate() {
    if (isActive()) {
      playSound();
      increaseLife();
      super.getSketch().text("1 life", getSketch().CENTER, getSketch().CENTER);
    }
  }

  /**
   * Deactivates the ExtraLife PowerUp.
   */
  @Override
  public void deactivate() {
    if (isActive()) {
      setActive(false);
    }
  }

  /** Plays sound clip of ExtraLife if MenuManager's sound is on. */
  @Override
  public void playSound() {
    if (MenuManager.sound) {
      clip.setFramePosition(0);
      clip.start();
    }
  }
}