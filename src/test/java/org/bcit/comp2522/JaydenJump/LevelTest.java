package org.bcit.comp2522.JaydenJump;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

  private Level level;
  private PImage backgroundImage;

  @BeforeEach
  void setUp() {
    backgroundImage = new PImage();
    level = new Level(3);
  }
  @Test
  void startTime_endTime() {
    level.startTime();
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    level.stopTime();
    assertEquals(57, level.getTime(), 1);

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    level.startTime();
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    level.stopTime();
    assertEquals(54, level.getTime(), 1);
  }

  @Test
  void addTimeWhileRunning(){
    level.startTime();
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    level.setTime(level.getTime() + 3);
    level.stopTime();
    assertEquals(60, level.getTime(), 1);

    level.startTime();
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    level.stopTime();
    level.setTime(level.getTime() + 30);
    assertEquals(87, level.getTime(), 1);
  }

  @Test
  void getWeather() {
  }

  @Test
  void getTime_setTime() {
    assertEquals(60, level.getTime());

    level.setTime(45);
    assertEquals(45, level.getTime());

    level.setTime(5);
    assertEquals(5, level.getTime());
  }

  @Test
  void getBackground_setBackground() {
    assertSame(backgroundImage, level.getBackground());
    PImage newBackground = new PImage();
    level.setBackground(newBackground);
    assertSame(newBackground, level.getBackground());
  }

  @Test
  void getLevelNumber_setLevelNumber() {
    assertEquals(1, level.getLevelNumber());

    level.setLevelNumber(2);
    assertEquals(2, level.getLevelNumber());

    level.setLevelNumber(3);
    assertEquals(3, level.getLevelNumber());
  }

  @Test
  void getSpeed_setSpeed() {
    assertEquals(5, level.getSpeed());

    level.setSpeed(7);
    assertEquals(7, level.getSpeed());

    level.setSpeed(11);
    assertEquals(11, level.getSpeed());
  }

  @Test
  void getScore_setScore() {
    assertEquals(0, level.getScore());

    level.setScore(10);
    assertEquals(10, level.getScore());

    level.setScore(15);
    assertEquals(15, level.getScore());
  }
}