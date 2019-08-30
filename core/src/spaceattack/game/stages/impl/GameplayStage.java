package spaceattack.game.stages.impl;

import java.util.LinkedList;
import java.util.List;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.TimeLabel;
import spaceattack.game.actors.interfaces.Killable;
import spaceattack.game.actors.interfaces.RequiredOnStage;
import spaceattack.game.input.IInputProcessor;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.ships.player.PlayerShip;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.stages.AbstractStage;
import spaceattack.game.stages.Stages;
import spaceattack.game.system.Acts;
import spaceattack.game.system.notifiers.INotifier;
import spaceattack.game.system.notifiers.IObserver;
import spaceattack.game.weapons.MissilesLauncher;

public class GameplayStage extends AbstractStage implements IObserver<GameProgress>, INotifier<GameplayStage> {

    private List<IObserver<GameplayStage>> observers;
    private int currentMission;
    private Acts act;

    private boolean gameOver;
    private boolean won;

    private TimeLabel levelUpLabel;
    private TimeLabel completedLabel;
    private TimeLabel failedLabel;

    private TimeLabel finalizeLabel = null;

    private IPool expPool;

    private MissilesLauncher missilesLauncher;

    void setExpPool(final IPool pool) {

        expPool = pool;
    }

    void setLevelUpLabel(final TimeLabel label) {

        levelUpLabel = label;
    }

    void setCompletedLabel(final TimeLabel label) {

        completedLabel = label;
    }

    void setFailedLabel(final TimeLabel label) {

        failedLabel = label;
    }

    @Override
    public IInputProcessor getInputProcessor() {

        return null;
    }

    @Override
    public void notify(final GameProgress state) {

        if (state.getLevel() > getProgressBackup().getLevel()) {
            showLevelUpLabel();
        }
    }

    void showLevelUpLabel() {

        if (!getActors().contains(levelUpLabel)) {
            addActor(levelUpLabel);
        }

        levelUpLabel.show();
    }

    @Override
    public void act(final float delta) {

        if (gameOver && finalizeLabel == null) {
            showFinalizeLabel();
        }

        if (finalizeLabel != null && !finalizeLabel.isVisible()) {
            finalizeStage();
        }

        super.act(delta);

        List<IGameActor> actorsToKill = new LinkedList<>();
        getActors().forEach(actor -> {
            if (actor instanceof Killable && ((Killable) actor).isToKill()) {
                actorsToKill.add(actor);
                if (actor instanceof RequiredOnStage) {
                    finishMission(actor);
                }
                if (actor instanceof IEnemyShip && ((IEnemyShip) actor).exploded()) {
                    getGameProgress().addExperience((long) ((IShip) actor).getHpPool().getMaxAmount());
                }
            }
        });
        actorsToKill.forEach(actor -> stage.removeActor(actor));
    }

    private void finishMission(final IGameActor actor) {

        if (actor instanceof PlayerShip) {
            lose();
        }
        else {
            win();
        }
    }

    private void win() {

        if (!gameOver) {
            gameOver = true;
            won = true;
        }
    }

    void lose() {

        gameOver = true;

        if (expPool != null) {
            expPool.destroy();
        }
    }

    private void showFinalizeLabel() {

        if (won) {
            finalizeLabel = completedLabel;
        }
        else {
            finalizeLabel = failedLabel;
        }

        addActor(finalizeLabel);
        finalizeLabel.show();
    }

    public void finalizeStage() {

        StageResult result = new StageResult();
        result.setNextStage(chooseStage());

        if (won) {
            getGameProgress().missionCompleted(currentMission);
            result.setGameProgress(getGameProgress());
        }
        else {
            result.setGameProgress(getProgressBackup());
        }
        setResult(result);
    }

    private Stages chooseStage() {

        if (won && newStuffAvailable()) {
            return Stages.MAIN_MENU;
        }
        return Stages.MISSIONS;
    }

    protected boolean newStuffAvailable() {

        return freeAttributesAvailable() || freeImprovementsAvailable() || newWeaponAvailable();
    }

    private boolean newWeaponAvailable() {

        return getGameProgress() != null && getGameProgress().isNewWeaponAvailable();
    }

    private boolean freeImprovementsAvailable() {

        return getGameProgress() != null && getGameProgress().getImprovements() != null
                && getGameProgress().getImprovements().getFreePoints() > 0;
    }

    protected boolean freeAttributesAvailable() {

        return getGameProgress() != null && getGameProgress().getAttributes() != null
                && getGameProgress().getAttributes().getFreePoints() > 0;
    }

    boolean isGameOver() {

        return gameOver;
    }

    void setGameOver(final boolean gameOver) {

        this.gameOver = gameOver;
    }

    void setWon(final boolean won) {

        this.won = won;
    }

    public void setMissileLauncher(final MissilesLauncher missilesLauncher) {

        this.missilesLauncher = missilesLauncher;
    }

    public MissilesLauncher getMissilesLauncher() {

        return missilesLauncher;
    }

    public void setCurrentMission(final int mission) {

        currentMission = mission;
        notifyObservers();
    }

    public int getCurrentMission() {

        return currentMission;
    }

    @Override
    public void registerObserver(final IObserver<GameplayStage> observer) {

        if (observers == null) {
            observers = new LinkedList<>();
        }

        observers.add(observer);
    }

    @Override
    public void unregisterObserver(final IObserver<GameplayStage> observer) {

        if (observers != null) {
            observers.remove(observer);
        }
    }

    private void notifyObservers() {

        if (observers != null) {
            observers.forEach(observer -> observer.notify(this));
        }
    }

    public void setAct(final Acts act) {

        this.act = act;
    }

    public Acts getAct() {

        return act;
    }
}
