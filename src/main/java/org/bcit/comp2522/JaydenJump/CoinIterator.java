package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class CoinIterator implements Iterator {

  private ArrayList<Coin> coins;

  public CoinIterator(ArrayList<Coin> coins) {
    this.coins = coins;
  }

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public Object next() {
    return null;
  }

  @Override
  public void remove() {
    Iterator.super.remove();
  }

  @Override
  public void forEachRemaining(Consumer action) {
    Iterator.super.forEachRemaining(action);
  }
}
