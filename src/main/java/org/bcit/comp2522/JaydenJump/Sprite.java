package org.bcit.comp2522.JaydenJump;

public abstract class Sprite implements Comparable<Object>, Drawable, Collideable{
  private float xpos;
  private float ypos;
  private float vx;
  private float vy;

  public Sprite(float xpos, float ypos, float vx, float vy) {
    this.xpos = xpos;
    this.ypos = ypos;
    this.vx = vx;
    this.vy = vy;
  }

  @Override
  public int compareTo(Object o) {
    return 0;
  }

  @Override
  public void draw() {

  }

  @Override
  public void update() {

  }

  @Override
  public boolean collides(Object o) {
    return false;
  }
}
