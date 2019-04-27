package spaceattack.game.stages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.buttons.IButton;
import spaceattack.game.system.GameLoader;
import spaceattack.game.system.GameSaver;
import spaceattack.game.system.graphics.StaticImage;

public abstract class AbstractStage implements IGameStage {

    protected IStage stage;

    protected GameSaver gameSaver;
    protected GameLoader gameLoader;

    private Stages type;
    private StageResult result;
    private GameProgress gameProgress;
    private GameProgress progressBackup;

    private Map<IButton, Predicate<IButton>> buttonsToEnable;
    private Map<IButton, Predicate<IButton>> buttonsToHide;
    private Map<IButton, Function<IButton, String>> changeTextButtons;

    public AbstractStage() {

        buttonsToEnable = new HashMap<>();
        buttonsToHide = new HashMap<>();
        changeTextButtons = new HashMap<>();
    }

    public void setStage(IStage stage) {

        this.stage = stage;
    }

    public void setGameSaver(GameSaver saver) {

        gameSaver = saver;
    }

    public void setGameLoader(GameLoader loader) {

        gameLoader = loader;
    }

    @Override
    public boolean isCompleted() {

        return result != null;
    }

    @Override
    public StageResult getResult() {

        return result;
    }

    @Override
    public void setResult(StageResult result) {

        this.result = result;

        if (result.getGameProgress() != null
                && !(result.getGameProgress().equals(progressBackup) && gameLoader.fileExists()))
            gameSaver.save(result.getGameProgress());
    }

    @Override
    public Stages getType() {

        return type;
    }

    @Override
    public void setType(Stages type) {

        this.type = type;
    }

    @Override
    public GameProgress getGameProgress() {

        return gameProgress;
    }

    @Override
    public void setGameProgress(GameProgress gameProgress) {

        this.gameProgress = gameProgress;

        if (progressBackup == null)
            progressBackup = gameProgress.clone();
    }

    @Override
    public void addActorBeforeGUI(IGameActor newActor) {

        stage.addActorAtBegining(newActor);
    }

    @Override
    public void addActor(IGameActor actor) {

        stage.addActor(actor);
    }

    public GameProgress getProgressBackup() {

        return progressBackup;
    }

    @Override
    public void act(float delta) {

        stage.act(delta);
    }

    @Override
    public void draw() {

        stage.draw();
    }

    @Override
    public List<IGameActor> getActors() {

        return stage.getGameActors();
    }

    @Override
    public void updateViewport(int width, int height, boolean centerCamera) {

        stage.updateViewport(width, height, centerCamera);
    }

    @Override
    public IStage getStage() {

        return stage;
    }

    @Override
    public void updateControls() {

        buttonsToEnable.forEach((button, predicate) -> button.setEnabled(predicate.test(button)));
        buttonsToHide.forEach((button, predicate) -> button.setVisible(predicate.test(button)));
        changeTextButtons.forEach((button, function) -> button.setText(function.apply(button)));
    }

    @Override
    public void addButtonsEnabledPredicate(IButton button, Predicate<IButton> predicate) {

        buttonsToEnable.put(button, predicate);
    }

    @Override
    public void addButtonsVisiblePredicate(IButton button, Predicate<IButton> predicate) {

        buttonsToHide.put(button, predicate);
    }

    @Override
    public void addButtonsTextFunction(IButton button, Function<IButton, String> predicate) {

        changeTextButtons.put(button, predicate);
    }

    @Override
    public void addBackground(StaticImage background) {

        stage.addBackground(background);
    }
}
