package org.bcit.comp2522.JaydenJump;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoadTest {

  private Load load;

  @BeforeEach
  void setUp() {
    load = new Load();
    Player.setLives(3);
    Player.setScore(0);
    Player.setUnlocked(1);
  }

  @Test
  void save_load() {
    String testSaveID = "testSaveID";

    Player.setLives(5);
    Player.setScore(10);
    Player.setUnlocked(2);
    load.save(testSaveID);

    Player.setLives(1);
    Player.setScore(0);
    Player.setUnlocked(1);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    load.load(testSaveID);

    assertEquals(5, Player.getLives());
    assertEquals(10, Player.getScore());
    assertEquals(2, Player.getUnlocked());

    // Test if the save is overwritten
    Player.setLives(7);
    Player.setScore(70);
    Player.setUnlocked(6);
    load.save(testSaveID);

    Player.setLives(1);
    Player.setScore(0);
    Player.setUnlocked(1);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    load.load(testSaveID);

    assertEquals(7, Player.getLives());
    assertEquals(70, Player.getScore());
    assertEquals(6, Player.getUnlocked());
  }
}