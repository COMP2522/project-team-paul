package org.bcit.comp2522.JaydenJump;

public class Player extends Sprite{

  private int score;
  private int lives;

  private Player instance;

  private Player(int xpos, int ypos, int vx, int vy, int score, int lives) {
    super(xpos, ypos, vx, vy);
    this.score = score;
    this.lives = lives;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getLives() {
    return lives;
  }

  public void setLives(int lives) {
    this.lives = lives;
  }

  public Player getInstance(int xpos, int ypos, int vx, int vy, int score, int lives) {
    if (instance == null) {
      instance = new Player(xpos, ypos, vx, vy, score, lives);
    }
    return instance;
  }

  public Player getInstance() {
    if (instance == null) {
      throw new NullPointerException("Player instance is null, use getInstance(int xpos, int ypos, int vx, int vy, int score, int lives) to create a new instance");
    }
    return instance;
  }


  public void jump() {}
  public void move() {}
  public void collectCoin(Coin coin) {}

  public void usePowerUp(PowerUp powerUp) {}





}
