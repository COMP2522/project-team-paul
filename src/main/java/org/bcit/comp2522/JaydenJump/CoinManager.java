package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;
import java.util.Iterator;
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
   * Maximum amount of coins that are permitted to exist in each cycle.
   */
  private final int maxCoins;

  /**
   * The game window that is used to draw the coins.
   */
  private final PApplet sketch;

  /**
   * Array of coin images that animate the sprite.
   */
  private PImage[] image;

  /**
   * The coin's speed as it moves down the game window.
   */
  private final int coinSpeed;

  /**
   * Instance of the player class in the game.
   */
  Player player;

  /**
   * Starting amount of coins that spawn at the beginning of each game.
   */
  private static final int COINSTARTING = 1;

  /**
   * Constructs a coin.
   *
   * @param maxCoins allowed in game
   *
   * @param coinSpeed given to each coin
   *
   * @param coinImg array that animate the sprite
   *
   */
  private CoinManager(int maxCoins, int coinSpeed, PImage[] coinImg) {
    this.maxCoins = maxCoins;
    this.sketch = MenuManager.getInstance();
    this.coinSpeed = coinSpeed;
    this.player = Player.getInstance();
    this.image = coinImg;
    coins = new ArrayList<>();
  }

  /**
   * Creates and uses only one instance of a CoinManager class in the game.
   *
   * @param maxCoins allowed in game
   *
   * @param coinSpeed of coin in game
   *
   * @param coinImg that animates the coin sprite
   *
   * @return CoinManager object
   *
   */
  public static CoinManager getInstance(int maxCoins,
                                        int coinSpeed, PImage[] coinImg) {
    if (instance == null) {
      instance = new CoinManager(maxCoins, coinSpeed, coinImg);
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
    float y = sketch.random(sketch.height - Coin.getCoinSize());
    for (int i = 0; i < COINSTARTING; i++) {
      float x = sketch.random(sketch.width - Coin.getCoinSize());
      coins.add(new Coin(x, y, 0, coinSpeed, image));
      y += 150;
    }
  }

  /**
   * Spawns coins throughout the game on the game window.
   */
  public void generateCoins() {
    float y = 0;
    while (coins.size() < maxCoins) {
      float x = sketch.random(sketch.width - Coin.getCoinSize());
      Coin newCoin = new Coin(x, y, 0, coinSpeed, image);
      coins.add(newCoin);
      y += 150;
    }
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
