package org.bcit.comp2522.JaydenJump;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

  private Level level;

  @BeforeEach
  void setUp() {
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
    assertEquals(3, level.getTime(), 1);

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
    assertEquals(6, level.getTime(), 1);
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
    assertEquals(6, level.getTime(), 1);

    level.startTime();
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    level.stopTime();
    level.setTime(level.getTime() + 30);
    assertEquals(39, level.getTime(), 1);
  }

  @Test
  void getTime_setTime() {
    assertEquals(0, level.getTime());

    level.setTime(45);
    assertEquals(45, level.getTime());

    level.setTime(5);
    assertEquals(5, level.getTime());
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