package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class EnemyIterator implements Iterator {

  private ArrayList<Enemy> enemies;

  public EnemyIterator(ArrayList<Enemy> enemies) {
    this.enemies = enemies;
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
