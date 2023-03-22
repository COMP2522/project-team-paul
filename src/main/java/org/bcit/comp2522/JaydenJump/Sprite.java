package org.bcit.comp2522.JaydenJump;

public abstract class Sprite implements Comparable<Object>, Drawable, Collideable{
  private float xpos;
  private float ypos;
  private float vx;

  public float getXpos() {
    return xpos;
  }

  public void setXpos(float xpos) {
    this.xpos = xpos;
  }

  public float getYpos() {
    return ypos;
  }

  public void setYpos(float ypos) {
    this.ypos = ypos;
  }

  public float getVx() {
    return vx;
  }

  public void setVx(float vx) {
    this.vx = vx;
  }

  public float getVy() {
    return vy;
  }

  public void setVy(float vy) {
    this.vy = vy;
  }

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
