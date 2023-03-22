package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;
import java.util.Iterator;

public class PlatformIterator implements Iterator<Platform> {

  private ArrayList<Platform> platforms;

    public PlatformIterator(ArrayList<Platform> platforms) {
        this.platforms = platforms;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Platform next() {
        return null;
    }

    @Override
    public void remove() {}
}
