package spaceattack.game.rpg;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Attributes {

    private transient Lock lock;

    private HashMap<String, Integer> attributes;
    private int freePoints;

    public Attributes() {

        attributes = new HashMap<>();

        Attribute.stream() //
                .forEach(attrib -> attributes.put(attrib.name(), Attribute.MIN_VALUE));

        lock = new ReentrantLock();
    }

    public int get(final Attribute attribute) {

        try {
            lock.lock();
            return attributes.get(attribute.name());
        }
        finally {
            lock.unlock();
        }
    }

    public boolean increase(final Attribute attribute) {

        try {
            lock.lock();

            if (freePoints <= 0) {
                return false;
            }

            attributes.put(attribute.name(), attributes.get(attribute.name()) + 1);
            freePoints--;

            return true;
        }
        finally {
            lock.unlock();
        }
    }

    public boolean decrease(final Attribute attribute) {

        try {
            lock.lock();

            if (attributes.get(attribute.name()) - 1 < Attribute.MIN_VALUE) {
                return false;
            }

            attributes.put(attribute.name(), attributes.get(attribute.name()) - 1);
            freePoints++;

            return true;
        }
        finally {
            lock.unlock();
        }
    }

    public HashMap<String, Integer> getAttributes() {

        return attributes;
    }

    public void setAttributes(final HashMap<String, Integer> attributes) {

        this.attributes = attributes;
    }

    public int getFreePoints() {

        return freePoints;
    }

    public void setFreePoints(final int freePoints) {

        this.freePoints = freePoints;
    }

    public void addFreePoints(final int additionalPoints) {

        freePoints += additionalPoints;
    }
}
