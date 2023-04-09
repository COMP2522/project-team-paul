package org.bcit.comp2522.JaydenJump.sprites;

import javax.sound.sampled.*;
import org.bcit.comp2522.JaydenJump.Game;
import org.bcit.comp2522.JaydenJump.Level;
import org.bcit.comp2522.JaydenJump.gameUI.MenuManager;
import org.bcit.comp2522.JaydenJump.interfaces.Audible;
import processing.core.PImage;


/**
 * The Coin class is a type of sprite that can be consumed by the
 * Player in the game. Interacting with the coin results in the coin
 * playing a sound and increasing the player score by a certain amount.
 *
 * @version 1.0
 *
 * @author Hyuk Park
 *
 * @since 03/20/2023
 */
public class Coin extends Sprite implements Audible {

  /**
   * The sound clip of the Coin class.
   */
  private static Clip clip;

  /**
   * The current frame of the coin images.
   */
  private int frame = 0;

  /**
   * An array of the coin images that holds different "frames" of the image.
   * Cycling through the images gives it a animated look in the game.
   */
  private PImage[] animationFrames;

  /**
   * The player in the game.
   */
  private final Player player;

  /**
   * The amount the score is increased if the player interacts with the coin.
   */
  private static final int SCORE_INCREASE = 25;

  /**
   * The level of the game.
   */
  private Level level;

  /**
   * The game window.
   */
  private Game game;

  /**
   * Constructs a Coin Sprite.
   *
   * @param level of the game
   *
   * @param xpos x position of the Coin
   *
   * @param ypos y position of the Coin
   *
   * @param vx x velocity of the Coin
   *
   * @param vy y velocity of the Coin
   *
   * @param animationFrames collection of coin images
   *
   * @param game window
   */
  public Coin(Level level, float xpos, float ypos, float vx,
              float vy, PImage[] animationFrames, Game game) {
    super(xpos, ypos, vx, vy);
    this.game = game;
    this.player = Player.getInstance();
    this.animationFrames = animationFrames;
    this.clip = loadSound("coin_sound.wav");
    this.level = level;
  }

  /**
   * Draws the Coin image at the current interval.
   */
  @Override
  public void draw() {
    super.getSketch().image(animationFrames[frame % animationFrames.length],
        getXpos(), getYpos(), level.getCoinSize(), level.getCoinSize());
    animate();
  }

  /**
   * Animates the Coin sprite and cycles through the different frames of the coin
   * that are stored inside the PImage array.
   */
  public void animate() {
    if (super.getSketch().frameCount % 6 == 0) {
      this.frame++;
    }
  }

  /**
   * Updates the Coin's current x and y position.
   */
  @Override
  public void update() {
    super.setXpos(super.getXpos() + super.getVx());
    super.setYpos(super.getYpos() + super.getVy());
  }

  /**
   * Returns true if the player collides with the Coin sprite.
   *
   * @param o The object to check for collision
   *
   * @return true if collision occurs
   */
  @Override
  public boolean collides(Object o) {
    if (o instanceof Player) {
      if (player.getXpos() + player.getPlayerSize() >= super.getXpos()
          && player.getXpos() <= super.getXpos() + level.getCoinSize()
          && player.getYpos() + player.getPlayerSize() >= super.getYpos()
          && player.getYpos() <= super.getYpos() + level.getCoinSize()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Increases the game score and invokes the method to play
   * the sound clip of the coin.
   */
  public void addToScore() {
    game.increaseScore(SCORE_INCREASE);
    playSound();
  }

  /** Plays sound clip of coin if MenuManager's sound is on. */
  @Override
  public void playSound() {
    if (MenuManager.sound) {
      clip.setFramePosition(0);
      clip.start();
    }
  }

  /**
   * Returns true if the Coin sprite is on the screen.
   *
   * @return true if Coin is on screen
   */
  public boolean isOnScreen() {
    return getXpos() >= 0 && getXpos() + level.getCoinSize() <= super.getSketch().width
        && getYpos() >= 0 && getYpos() + level.getCoinSize() <= super.getSketch().height;
  }
}
