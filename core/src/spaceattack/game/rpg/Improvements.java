package spaceattack.game.rpg;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Improvements {

    private transient Lock lock;

    private HashMap<String, Integer> improvements;
    private int freePoints;

    public Improvements() {

        improvements = new HashMap<>();

        Improvement.stream() //
                .forEach(improv -> improvements.put(improv.name(), Improvement.MIN_VALUE));

        lock = new ReentrantLock();
    }

    public int get(final Improvement improvement) {

        try {
            lock.lock();
            return improvements.get(improvement.name());
        }
        finally {
            lock.unlock();
        }
    }

    public boolean increase(final Improvement improvement) {

        try {
            lock.lock();

            if (freePoints <= 0 || improvements.get(improvement.name()) >= Improvement.MAX_VALUE) {
                return false;
            }

            improvements.put(improvement.name(), improvements.get(improvement.name()) + 1);
            freePoints--;

            return true;
        }
        finally {
            lock.unlock();
        }
    }

    public boolean decrease(final Improvement improvement) {

        try {
            lock.lock();

            if (improvements.get(improvement.name()) - 1 < Improvement.MIN_VALUE) {
                return false;
            }

            improvements.put(improvement.name(), improvements.get(improvement.name()) - 1);
            freePoints++;

            return true;
        }
        finally {
            lock.unlock();
        }
    }

    public HashMap<String, Integer> getImprovements() {

        return improvements;
    }

    public void setImprovements(final HashMap<String, Integer> improvements) {

        this.improvements = improvements;
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
        result = prime * result + ((improvements == null) ? 0 : improvements.hashCode());
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
        Improvements other = (Improvements) obj;
        if (improvements == null) {
            if (other.improvements != null) {
                return false;
            }
        }
        else
            if (!improvements.equals(other.improvements)) {
                return false;
            }
        if (freePoints != other.freePoints) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Improvements clone() {

        Improvements newAttributes = new Improvements();
        newAttributes.freePoints = this.freePoints;
        newAttributes.improvements = (HashMap<String, Integer>) this.improvements.clone();

        return newAttributes;
    }
}
