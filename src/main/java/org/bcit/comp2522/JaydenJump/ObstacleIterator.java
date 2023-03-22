package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;
import java.util.Iterator;

public class ObstacleIterator implements Iterator<Obstacle> {

  private ArrayList<Obstacle> obstacles;

  public ObstacleIterator(ArrayList<Obstacle> obstacles) {
    this.obstacles = obstacles;
  }

  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  public Obstacle next() {
    return null;
  }

  @Override
  public void remove() {}
}
