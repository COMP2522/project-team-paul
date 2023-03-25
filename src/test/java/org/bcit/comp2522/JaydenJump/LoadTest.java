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
    Player.setScore(100);
    Player.setUnlocked(2);
    load.save(testSaveID);

    Player.setLives(1);
    Player.setScore(0);
    Player.setUnlocked(1);

    load.load(testSaveID);

    assertEquals(5, Player.getLives());
    assertEquals(100, Player.getScore());
    assertEquals(2, Player.getUnlocked());
  }
}