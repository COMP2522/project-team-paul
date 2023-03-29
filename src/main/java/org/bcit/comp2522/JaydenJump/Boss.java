package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;


public class Boss extends Sprite {

  private PApplet sketch;

  private PImage image;

  private int width;

  private int height;

  private Player player;

  private boolean movingRight;

  public Boss(int xpos, int ypos, int vx, int vy, int width, int height, PImage image, PApplet sketch, Player player) {
    super(xpos, ypos, vx, vy, sketch);
    this.sketch = sketch;
    this.image = image;
    this.width = width;
    this.height = height;
    this.player = player;
    this.movingRight = true;
  }

  public void draw() {

      sketch.image(image, getXpos(), getYpos(), width, height);

  }

  public void update(){
    if (movingRight) {
      setXpos(getXpos() + getVx());
    } else {
      setXpos(getXpos() - getVx());
    }

    if (getXpos() < 0 || getXpos() > sketch.width - width) {

      movingRight = !movingRight;
    }

  }

  @Override
  public boolean collides(Object o) {
    if (o instanceof Player) {
      Player player = (Player) o;
      return (getXpos() + width >= player.getXpos() && getXpos() <= player.getXpos() + player.getImgSize()
              && getYpos() + height >= player.getYpos() && getYpos() <= player.getYpos() + player.getImgSize());
    } else if (o instanceof Projectile) {
      Projectile projectile = (Projectile) o;
      return (getXpos() + width >= projectile.getXpos() && getXpos() <= projectile.getXpos() + 10)
              && getYpos() + height >= projectile.getYpos() && getYpos() <= projectile.getYpos() + 10;
    }
    return false;
  }



}
