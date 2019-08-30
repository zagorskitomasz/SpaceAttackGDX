package spaceattack.game;

import spaceattack.game.stages.GameStageFactory;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.stages.Stages;
import spaceattack.game.system.FrameController;
import spaceattack.game.system.graphics.Animations;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.IUtils;

class Game implements IGame {

    private final boolean isTest;

    private IUtils extUtils;
    private GameStageFactory stageBuilder;
    private FrameController frameController;

    private IGameStage stage;

    public Game(final boolean isTest) {

        this.isTest = isTest;
    }

    public void setExtUtils(final IUtils extUtils) {

        this.extUtils = extUtils;
    }

    public void setStageBuilder(final GameStageFactory stageBuilder) {

        this.stageBuilder = stageBuilder;
    }

    public void setFrameController(final FrameController frameController) {

        this.frameController = frameController;
    }

    @Override
    public void create() {

        extUtils.init();
        loadMedia();
        StageResult defaultResult = prepareInitialResult();
        stage = stageBuilder.getStage(defaultResult);
    }

    private void loadMedia() {

        if (isTest) {
            Textures.loadForTest();
            Sounds.loadForTest();
            Animations.loadForTest();
        }
        else {
            Sounds.load();
            Textures.load();
        }
    }

    private StageResult prepareInitialResult() {

        return new StageResult();
    }

    @Override
    public void render() {

        extUtils.clearScreen();

        if (checkFrame()) {
            stage.act(extUtils.getDeltaTime());
        }

        stage.draw();

        checkStageSwitch();
    }

    private boolean checkFrame() {

        return frameController.check();
    }

    private void checkStageSwitch() {

        if (stage.isCompleted()) {
            StageResult result = stage.getResult();
            stage = stageBuilder.getStage(result);
        }
    }

    @Override
    public void resize(final int width, final int height) {

        if (stage == null) {
            return;
        }

        stage.updateViewport(width, height, true);
    }

    Stages getCurrentStage() {

        return stage.getType();
    }
}
