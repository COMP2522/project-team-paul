package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Iterator;

//some issue with window object

public class JaydenJump extends PApplet {
  private Player player;
  private ArrayList<Platform> platforms;
//  private Window window;
  private ArrayList<Sprite> sprites;
  private ArrayList<Coin> coins;
  private ArrayList<Obstacle> obstacles;
  private ArrayList<Enemy> enemies;

  public void settings() {}

  public void setUp() {}

  public static void main(String[] args) {
    PApplet.main("org.bcit.comp2522.JaydenJump.JaydenJump");
  }

  public void draw() {}

  public Iterator<Platform> platformIterator() {
    return platforms.iterator();
  }

  public Iterator<Coin> coinIterator() {
    return coins.iterator();
  }

  public Iterator<Enemy> enemyIterator() {
    return enemies.iterator();
  }

  public Iterator<Obstacle> obstacleIterator() {
    return obstacles.iterator();
  }

  public void saveGame() {}

  public void loadLevel() {}

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public ArrayList<Platform> getPlatforms() {
    return platforms;
  }

  public void setPlatforms(ArrayList<Platform> platforms) {
    this.platforms = platforms;
  }

  public ArrayList<Sprite> getSprites() {
    return sprites;
  }

  public void setSprites(ArrayList<Sprite> sprites) {
    this.sprites = sprites;
  }

  public ArrayList<Coin> getCoins() {
    return coins;
  }

  public void setCoins(ArrayList<Coin> coins) {
    this.coins = coins;
  }

  public ArrayList<Obstacle> getObstacles() {
    return obstacles;
  }

  public void setObstacles(ArrayList<Obstacle> obstacles) {
    this.obstacles = obstacles;
  }

  public ArrayList<Enemy> getEnemies() {
    return enemies;
  }

  public void setEnemies(ArrayList<Enemy> enemies) {
    this.enemies = enemies;
  }
}
