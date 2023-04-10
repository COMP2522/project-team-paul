package org.bcit.comp2522.JaydenJump;

import org.bcit.comp2522.JaydenJump.spriteManagers.PlatformManager;
import org.bcit.comp2522.JaydenJump.sprites.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

class PlatformManagerTest {

  private PlatformManager platformManager;

  @BeforeEach
  void setUp() {
    Level level = Level.getInstance(1);
    platformManager = PlatformManager.getInstance(level);
  }

  @Test
  void getInstanceReturnsSameInstance() {
    Level anotherLevel = Level.getInstance(2);
    PlatformManager anotherPlatformManager = PlatformManager.getInstance(anotherLevel);

    assertSame(platformManager, anotherPlatformManager);
  }

  @Test
  void isOverlappingReturnsCorrectResult() {
    Platform platform1 = new Platform(50, 50, Color.BLUE, 0, 0, false);
    Platform platform2 = new Platform(60, 60, Color.BLUE, 0, 0, false);
    Platform platform3 = new Platform(200, 200, Color.BLUE, 0, 0, false);

    assertTrue(platformManager.isOverlapping(platform1, platform2));
    assertFalse(platformManager.isOverlapping(platform1, platform3));
    assertFalse(platformManager.isOverlapping(platform2, platform3));
  }
}
