package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Iterator;

public class CoinManager {
  private static CoinManager instance;

  private final ArrayList<Coin> coins;

  private final int maxCoins;

  private final PApplet sketch;

  private PImage[] image;

  private final int coinSpeed;

  Player player;

  private static final int COINSTARTING = 1;

  private CoinManager(int maxCoins, PApplet sketch, int coinSpeed, Player player, PImage[] coinImg) {
    this.maxCoins = maxCoins;
    this.sketch = sketch;
    this.coinSpeed = coinSpeed;
    this.player = player;
    this.image = coinImg;
    coins = new ArrayList<>();
  }

  public static CoinManager getInstance(int maxCoins, PApplet sketch, int coinSpeed, Player player, PImage[] coinImg) {
    if (instance == null) {
      instance = new CoinManager(maxCoins, sketch, coinSpeed, player, coinImg);
    }
    return instance;
  }

  public ArrayList<Coin> getCoins() {
    return coins;
  }

  public void generateStartCoins() {
    float y = sketch.random(sketch.height - Coin.getCoinSize());
    for (int i = 0; i < COINSTARTING; i++) {
      float x = sketch.random(sketch.width - Coin.getCoinSize());
      coins.add(new Coin(x, y, 0, coinSpeed, sketch, player, image));
      y += 150;
    }
  }

  public void generateCoins() {
    float y = 0;
    while (coins.size() < maxCoins) {
      float x = sketch.random(sketch.width - Coin.getCoinSize());
      Coin newCoin = new Coin(x, y, 0, coinSpeed, sketch, player, image);
      coins.add(newCoin);
      y += 150;
    }
  }

  public void updateAndDrawCoins() {
    for (int i = coins.size() - 1; i >= 0; i--) {
      Coin coin = coins.get(i);
      if (!coin.isOnScreen()) {
        coins.remove(i);
      }
    }


    for (Coin coin: coins) {
      coin.update();
      coin.draw();
    }
  }

  public void checkCollision(Player player) {
    Iterator<Coin> coinIterator = coins.iterator();
    while (coinIterator.hasNext()) {
      Coin coin = coinIterator.next();
      if (coin.collides(player)) {
        coin.addToScore();
        coinIterator.remove();
      }
    }
  }

}
