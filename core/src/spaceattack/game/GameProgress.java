package spaceattack.game;

import java.util.LinkedList;
import java.util.List;

import spaceattack.consts.Experience;
import spaceattack.game.rpg.Attributes;
import spaceattack.game.system.notifiers.INotifier;
import spaceattack.game.system.notifiers.IObserver;

public class GameProgress implements INotifier<GameProgress> {

    private Integer mission;
    private Integer level;
    private Long experience;
    private String playerName;
    private Attributes attributes;

    private transient int slot;
    private transient List<IObserver<GameProgress>> observers;

    public GameProgress() {

        mission = 1;
        level = 1;
        experience = 0l;
        attributes = new Attributes();

        observers = new LinkedList<>();
    }

    public Integer getMission() {

        return mission;
    }

    public void setMission(final Integer mission) {

        this.mission = mission;
        notifyObservers();
    }

    public Integer getLevel() {

        return level;
    }

    public void setLevel(final Integer level) {

        this.level = level;
        notifyObservers();
    }

    public Long getExperience() {

        return experience;
    }

    public void setExperience(final Long experience) {

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

        return newProgress;
    }

    public void missionCompleted(final int currentMission) {

        if (currentMission + 1 > getMission()) {
            setMission(currentMission + 1);
        }
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((experience == null) ? 0 : experience.hashCode());
        result = prime * result + ((level == null) ? 0 : level.hashCode());
        result = prime * result + ((mission == null) ? 0 : mission.hashCode());
        result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
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
        if (experience == null) {
            if (other.experience != null) {
                return false;
            }
        }
        else
            if (!experience.equals(other.experience)) {
                return false;
            }
        if (level == null) {
            if (other.level != null) {
                return false;
            }
        }
        else
            if (!level.equals(other.level)) {
                return false;
            }
        if (mission == null) {
            if (other.mission != null) {
                return false;
            }
        }
        else
            if (!mission.equals(other.mission)) {
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
        return true;
    }
}
