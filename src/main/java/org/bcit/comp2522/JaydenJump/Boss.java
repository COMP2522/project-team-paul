package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Iterator;


public class Boss extends Sprite {

  private PApplet sketch;

  private PImage image;

  private int width;

  private int height;

  private Player player;

  private ArrayList<Boss> bosses = new ArrayList<>();

  private boolean movingRight;

  private int health;

  private Projectile projectile;

  private ArrayList<Projectile> projectiles = new ArrayList<>();
  private int maxProjectiles = 1;
  private int projectileCounter = 0;

  public Boss(int xpos, int ypos, int vx, int vy, int width, int height, PImage image, PApplet sketch, Player player, int health) {
    super(xpos, ypos, vx, vy, sketch);
    this.sketch = sketch;
    this.image = image;
    this.width = width;
    this.height = height;
    this.player = player;
    this.movingRight = true;
    bosses.add(this);
    this.health = health;
    this.projectile = new Projectile(getXpos(), getYpos(), 0, 20, 1, player);
  }

  public void draw() {

    sketch.image(image, getXpos(), getYpos(), width, height);

  }

  public void update() {
    if (movingRight) {
      setXpos(getXpos() + getVx());
    } else {
      setXpos(getXpos() - getVx());
    }

    if (getXpos() < 0 || getXpos() > sketch.width - width) {

      movingRight = !movingRight;
    }

    if (projectileCounter < maxProjectiles) {
      Projectile projectile = new Projectile(getXpos(), getYpos(), 0, 10, 1, player);
      projectiles.add(projectile);
      projectileCounter++;
    }



    for (Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext(); ) {
      Projectile projectile = iterator.next();
      projectile.draw();
      projectile.update();
      if (player.collides(projectile)) {
        Game.setLives(Game.getLives() - 1);
        if(Game.getLives() == 0){
          Game.endGame();
      }
      if (projectile.getYpos() > sketch.height || projectile.collides(player)) {
        iterator.remove();
        projectileCounter--;
      }
    }

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
      if(player.isShooting()){
        return (getXpos() + width >= projectile.getXpos() && getXpos() <= projectile.getXpos() + 10)
                && getYpos() + height >= projectile.getYpos() && getYpos() <= projectile.getYpos() + 10;
      }
    }
    return false;
  }

  public void shootProjectile() {

      projectile.setXpos(getXpos());
      projectile.setYpos(getYpos());
      projectile.setVy(2);

  }

  public ArrayList<Boss> getBosses() {
    return bosses;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public Projectile getProjectile() {
    return projectile;
  }
}
