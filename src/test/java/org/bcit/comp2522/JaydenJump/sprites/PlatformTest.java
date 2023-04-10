package org.bcit.comp2522.JaydenJump.sprites;

import org.bcit.comp2522.JaydenJump.gameUI.MenuManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

class PlatformTest {

  private Platform platform;

  @BeforeEach
  void setUp() {
    platform = new Platform(50, 50, Color.BLUE, 0, 0, false);
  }

  @Test
  void constructorSetsCorrectValues() {
    assertEquals(50, platform.getXpos());
    assertEquals(50, platform.getYpos());
    assertEquals(0, platform.getVx());
    assertEquals(0, platform.getVy());
    assertFalse(platform.isBreakable());
  }

  @Test
  void platformVelocity() {
    platform.setVx(5);
    platform.setVy(3);
    assertEquals(5, platform.getVx());
    assertEquals(3, platform.getVy());
  }

  @Test
  public void platformPosition() {
    platform.setXpos(10);
    platform.setYpos(20);
    assertEquals(10, platform.getXpos());
    assertEquals(20, platform.getYpos());
  }
  @Test
  void platformWidthAndHeightAreCorrect() {
    assertEquals(100, Platform.getPlatformWidth());
    assertEquals(20, Platform.getPlatformHeight());
  }

  @Test
  void isBreakableIsCorrect() {
    Platform breakablePlatform = new Platform(50, 50, Color.BLUE, 0, 0, true);
    assertTrue(breakablePlatform.isBreakable());
    assertFalse(platform.isBreakable());
  }

  @Test
  void gettingSketch() {
    assertNotNull(platform.getSketch());
    assertEquals(platform.getSketch(), MenuManager.getInstance());
  }

  @Test
  void isNotOnScreen() {
    Platform platform = new Platform(-50, -50, Color.BLUE, 0, 0, false);
    assertFalse(platform.isOnScreen());
  }
}
