package org.bcit.comp2522.JaydenJump.spriteManagers;

import java.util.ArrayList;
import java.util.Iterator;

import org.bcit.comp2522.JaydenJump.Game;
import org.bcit.comp2522.JaydenJump.Level;
import org.bcit.comp2522.JaydenJump.gameUI.MenuManager;
import org.bcit.comp2522.JaydenJump.sprites.Coin;
import org.bcit.comp2522.JaydenJump.sprites.Player;
import processing.core.PApplet;
import processing.core.PImage;


/**
 * The Coin Manager class manages all the coins that appear in the game.
 * It handles all functions related to the coin in the Game.java file.
 *
 * @version 1.0
 *
 * @author Hyuk Park
 *
 * @since 03/20/2023
 */

public class CoinManager {
  /**
   * Single instance of the CoinManager class in game.
   */
  private static CoinManager instance;

  /**
   * An ArrayList of coins that are manager throughout the game.
   */
  private final ArrayList<Coin> coins;

  /**
   * The game window that is used to draw the coins.
   */
  private final PApplet sketch;

  /**
   * Array of coin images that animate the sprite.
   */
  private PImage[] image;

  /**
   * Level of the game.
   */
  private Level level;

  /**
   * Instance of the player class in the game.
   */
  Player player;

  /**
   * Starting amount of coins that spawn at the beginning of each game.
   */
  private static final int COINSTARTING = 1;

  /**
   * Game instance.
   */
  private Game game;

  /**
   * Constructs a single instance of the CoinManager class in the game.
   *
   * @param level of the game
   *
   * @param coinImg of the Coin sprite
   *
   * @param game window
   */
  private CoinManager(Level level, PImage[] coinImg, Game game) {
    this.sketch = MenuManager.getInstance();
    this.player = Player.getInstance();
    this.image = coinImg;
    coins = new ArrayList<>();
    this.game = game;
    this.level = level;
  }

  /**
   * Creates and uses only one instance of a CoinManager in the game.
   *
   * @param level of the game
   *
   * @param coinImg the images of the coin
   *
   * @param game the game window
   *
   * @return only one instance of the CoinManager in the game
   */
  public static CoinManager getInstance(Level level, PImage[] coinImg, Game game) {
    if (instance == null) {
      instance = new CoinManager(level, coinImg, game);
    }
    return instance;
  }

  /**
   * Returns the ArrayList of coins that are managed in the game.
   *
   * @return ArrayList of coins in the game
   *
   */
  public ArrayList<Coin> getCoins() {
    return coins;
  }

  /**
   * Spawns a certain amount of coins at the start of each game.
   */
  public void generateStartCoins() {
    float y = generateRandomPosition();
    for (int i = 0; i < COINSTARTING; i++) {
      float x = generateRandomPosition();
      coins.add(new Coin(level, x, y, level.getxSpeedCoinPowerUp(), level.getySpeedCoinPowerUp(),
          image, game));
      y += level.getSpawnHeight();
    }
  }

  /**
   * Spawns coins throughout the game on the game window.
   */
  public void generateCoins() {
    float y = 0;
    while (coins.size() < level.getMaxCoins()) {
      float x = generateRandomPosition();
      Coin newCoin = new Coin(level, x, y, level.getxSpeedCoinPowerUp(),
          level.getySpeedCoinPowerUp(), image, game);
      coins.add(newCoin);
      y += level.getSpawnHeight();
    }
  }

  /**
   * Generates a random position for the x and y coordinates of a coin
   * that spawns in the game.
   * @return random float for the x and y coordinate
   */
  public float generateRandomPosition() {
    return sketch.random(sketch.width - level.getCoinSize());
  }

  /**
   * Makes sure that coins that are no longer on the screen are removed from the
   * ArrayList of coins. Coins positions are also updated throughout the game
   * to make it look like the coins are moving down the game window.
   */
  public void updateAndDrawCoins() {
    for (int i = coins.size() - 1; i >= 0; i--) {
      Coin coin = coins.get(i);
      if (!coin.isOnScreen()) {
        coins.remove(i);
      }
    }

    for (Coin coin : coins) {
      coin.update();
      coin.draw();
    }
  }

  /**
   * Checks if coins collide with the Player. Coins that
   * do collide are removed from the game and a certain
   * amount of points are added to the Player's score.
   */
  public void checkCollision() {
    Iterator<Coin> coinIterator = coins.iterator();
    while (coinIterator.hasNext()) {
      Coin coin = coinIterator.next();
      if (coin.collides(this.player)) {
        coin.addToScore();
        coinIterator.remove();
      }
    }
  }
}
