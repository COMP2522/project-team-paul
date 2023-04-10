package org.bcit.comp2522.JaydenJump.sprites;

import javax.sound.sampled.Clip;
import org.bcit.comp2522.JaydenJump.Game;
import org.bcit.comp2522.JaydenJump.Level;
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

  /**
   * The game window.
   */
  private Game game;

  /**
   * The level of the game.
   */
  private Level level;

  /**
   * Creates an instance of ExtraLife PowerUp in the game.
   *
   * @param level of the game
   *
   * @param xpos The x position of ExtraLife
   *
   * @param ypos The y position of ExtraLife
   *
   * @param vx The x velocity of ExtraLife
   *
   * @param vy The y velocity of ExtraLife
   */
  public ExtraLife(Level level, float xpos, float ypos, float vx, float vy, PImage image,
                   Game game) {
    super(level, xpos, ypos, vx, vy, image);
    this.game = game;
    this.clip = loadSound("extraLife.wav");
    this.level = level;
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