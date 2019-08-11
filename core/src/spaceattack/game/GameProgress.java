package spaceattack.game;

import java.util.LinkedList;
import java.util.List;

import spaceattack.consts.Experience;
import spaceattack.game.rpg.Attribute;
import spaceattack.game.rpg.Attributes;
import spaceattack.game.system.notifiers.INotifier;
import spaceattack.game.system.notifiers.IObserver;
import spaceattack.game.weapons.SpecialWeapons;

public class GameProgress implements INotifier<GameProgress> {

    private int mission;
    private int level;
    private long experience;
    private String playerName;
    private Attributes attributes;
    private String specialWeapon;
    private boolean newWeaponAvailable;

    private transient int slot;
    private transient List<IObserver<GameProgress>> observers;

    public GameProgress() {

        mission = 1;
        level = 1;
        experience = 0l;
        newWeaponAvailable = true;
        specialWeapon = SpecialWeapons.ROCKETS.name();
        attributes = new Attributes();

        observers = new LinkedList<>();
    }

    public int getMission() {

        return mission;
    }

    public void setMission(final int mission) {

        this.mission = mission;
        notifyObservers();
    }

    public int getLevel() {

        return level;
    }

    public void setLevel(final int level) {

        this.level = level;
        notifyObservers();
    }

    public long getExperience() {

        return experience;
    }

    public void setExperience(final long experience) {

        this.experience = experience;
    }

    public String getPlayerName() {

        return playerName;
    }

    public void setPlayerName(final String playerName) {

        this.playerName = playerName;
    }

    public int getSlot() {

        return slot;
    }

    public void setSlot(final int slot) {

        this.slot = slot;
    }

    public String getSpecialWeapon() {

        return specialWeapon;
    }

    public void setSpecialWeapon(final String specialWeapon) {

        this.specialWeapon = specialWeapon;
    }

    public boolean isNewWeaponAvailable() {

        return newWeaponAvailable;
    }

    public void setNewWeaponAvailable(final boolean newWeaponAvailable) {

        this.newWeaponAvailable = newWeaponAvailable;
    }

    public Attributes getAttributes() {

        return attributes;
    }

    public void setAttributes(final Attributes attributes) {

        this.attributes = attributes;
    }

    public void addExperience(final long amount) {

        experience += amount;

        if (experience >= Experience.INSTANCE.expForLevel(level + 1)) {
            setLevel(level + 1);
            attributes.addFreePoints(Attribute.POINTS_PER_LEVEL);
        }
    }

    @Override
    public void registerObserver(final IObserver<GameProgress> observer) {

        observers.add(observer);
    }

    @Override
    public void unregisterObserver(final IObserver<GameProgress> observer) {

        observers.remove(observer);
    }

    public void notifyObservers() {

        for (IObserver<GameProgress> observer : observers) {
            observer.notify(this);
        }
    }

    @Override
    public GameProgress clone() {

        GameProgress newProgress = new GameProgress();

        newProgress.experience = this.experience;
        newProgress.level = this.level;
        newProgress.mission = this.mission;
        newProgress.playerName = this.playerName;
        newProgress.specialWeapon = this.specialWeapon;
        newProgress.newWeaponAvailable = this.newWeaponAvailable;
        newProgress.attributes = this.attributes.clone();

        return newProgress;
    }

    public void missionCompleted(final int currentMission) {

        if (currentMission + 1 > getMission()) {
            setMission(currentMission + 1);
            if (mission % 3 == 0) {
                newWeaponAvailable = true;
            }
        }
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
        result = prime * result + (int) (experience ^ (experience >>> 32));
        result = prime * result + level;
        result = prime * result + mission;
        result = prime * result + (newWeaponAvailable ? 1231 : 1237);
        result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
        result = prime * result + ((specialWeapon == null) ? 0 : specialWeapon.hashCode());
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
        GameProgress other = (GameProgress) obj;
        if (attributes == null) {
            if (other.attributes != null) {
                return false;
            }
        }
        else
            if (!attributes.equals(other.attributes)) {
                return false;
            }
        if (experience != other.experience) {
            return false;
        }
        if (level != other.level) {
            return false;
        }
        if (mission != other.mission) {
            return false;
        }
        if (newWeaponAvailable != other.newWeaponAvailable) {
            return false;
        }
        if (playerName == null) {
            if (other.playerName != null) {
                return false;
            }
        }
        else
            if (!playerName.equals(other.playerName)) {
                return false;
            }
        if (specialWeapon == null) {
            if (other.specialWeapon != null) {
                return false;
            }
        }
        else
            if (!specialWeapon.equals(other.specialWeapon)) {
                return false;
            }
        return true;
    }
}
