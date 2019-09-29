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

        attributes.put(Attribute.ARMORY.name(), Attribute.MIN_VALUE + 5);
        attributes.put(Attribute.SHIELDS.name(), Attribute.MIN_VALUE);
        attributes.put(Attribute.BATTERY.name(), Attribute.MIN_VALUE + 5);
        attributes.put(Attribute.ENGINE.name(), Attribute.MIN_VALUE);

        lock = new ReentrantLock();
    }

    public Attributes(final int armory, final int shields, final int battery, final int engine) {

        this();
        attributes.put(Attribute.ARMORY.name(), armory);
        attributes.put(Attribute.SHIELDS.name(), shields);
        attributes.put(Attribute.BATTERY.name(), battery);
        attributes.put(Attribute.ENGINE.name(), engine);
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

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
        result = prime * result + freePoints;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Attributes other = (Attributes) obj;
        if (attributes == null) {
            if (other.attributes != null) {
                return false;
            }
        }
        else
            if (!attributes.equals(other.attributes)) {
                return false;
            }
        if (freePoints != other.freePoints) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Attributes clone() {

        Attributes newAttributes = new Attributes();
        newAttributes.freePoints = this.freePoints;
        newAttributes.attributes = (HashMap<String, Integer>) this.attributes.clone();

        return newAttributes;
    }
}
