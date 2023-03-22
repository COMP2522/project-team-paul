package org.bcit.comp2522.JaydenJump;

import processing.core.PImage;
import java.util.Timer;
import java.util.TimerTask;

public class Level {

  private int time;

  private long start;

  private long end;

  private PImage background;

  private int levelNumber;

  private int speed;

  private int score;

  private Timer timer;

  private String weather;

  public Level(int time, PImage background, int levelNumber, int speed, int score) {
    this.time = time;
    this.background = background;
    this.levelNumber = levelNumber;
    this.speed = speed;
    this.score = score;
    this.timer = new Timer();
  }

  public void startTime() {
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        time--;
        if (time <= 0) {
          stopTime();
        }
      }
    };

    timer.scheduleAtFixedRate(task, 0, 1000);
  }

  public void stopTime() {
    timer.cancel();
  }

  public void getWeather() {

  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public PImage getBackground() {
    return background;
  }

  public void setBackground(PImage background) {
    this.background = background;
  }

  public int getLevelNumber() {
    return levelNumber;
  }

  public void setLevelNumber(int levelNumber) {
    this.levelNumber = levelNumber;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}
