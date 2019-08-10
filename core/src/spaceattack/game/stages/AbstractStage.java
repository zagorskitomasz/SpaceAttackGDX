package spaceattack.game.stages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.actors.IActorsContainer;
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

    private final Map<IButton, Predicate<IButton>> buttonsToEnable;
    private final Map<IButton, Predicate<IButton>> buttonsToHide;
    private final Map<IButton, Function<IButton, String>> changeTextButtons;

    public AbstractStage() {

        buttonsToEnable = new HashMap<>();
        buttonsToHide = new HashMap<>();
        changeTextButtons = new HashMap<>();
    }

    public void setStage(final IStage stage) {

        this.stage = stage;
    }

    public void setGameSaver(final GameSaver saver) {

        gameSaver = saver;
    }

    public void setGameLoader(final GameLoader loader) {

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
    public void setResult(final StageResult result) {

        this.result = result;

        if (result.getGameProgress() != null
                && !(result.getGameProgress().equals(progressBackup) && gameLoader.fileExists())) {
            gameSaver.save(result.getGameProgress());
        }
    }

    @Override
    public Stages getType() {

        return type;
    }

    @Override
    public void setType(final Stages type) {

        this.type = type;
    }

    @Override
    public GameProgress getGameProgress() {

        return gameProgress;
    }

    @Override
    public void setGameProgress(final GameProgress gameProgress) {

        this.gameProgress = gameProgress;

        if (progressBackup == null) {
            progressBackup = gameProgress.clone();
        }
    }

    @Override
    public void addActorBeforeGUI(final IGameActor newActor) {

        stage.addActorAtBegining(newActor);
    }

    @Override
    public void addActorJustBeforeGUI(final IGameActor newActor) {

        stage.addActorJustBeforeGui(newActor);
    }

    @Override
    public void addActor(final IGameActor actor) {

        stage.addActor(actor);
    }

    @Override
    public void addActorsContainer(final IActorsContainer container) {

        container.getActors().forEach(actor -> stage.addActor(actor));
    }

    public GameProgress getProgressBackup() {

        return progressBackup;
    }

    @Override
    public void act(final float delta) {

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
    public void updateViewport(final int width, final int height, final boolean centerCamera) {

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
    public void addButtonsEnabledPredicate(final IButton button, final Predicate<IButton> predicate) {

        buttonsToEnable.put(button, predicate);
    }

    @Override
    public void addButtonsVisiblePredicate(final IButton button, final Predicate<IButton> predicate) {

        buttonsToHide.put(button, predicate);
    }

    @Override
    public void addButtonsTextFunction(final IButton button, final Function<IButton, String> predicate) {

        changeTextButtons.put(button, predicate);
    }

    @Override
    public void addBackground(final StaticImage background) {

        stage.addBackground(background);
    }
}
