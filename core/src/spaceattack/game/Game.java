package spaceattack.game;

import spaceattack.game.stages.GameStageFactory;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.stages.Stages;
import spaceattack.game.system.FrameController;
import spaceattack.game.system.GameLoader;
import spaceattack.game.system.graphics.Animations;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.IUtils;

class Game implements IGame {

    private boolean isTest;

    private IUtils extUtils;
    private GameLoader gameLoader;
    private GameStageFactory stageBuilder;
    private FrameController frameController;

    private IGameStage stage;

    public Game(boolean isTest) {

        this.isTest = isTest;
    }

    public void setExtUtils(IUtils extUtils) {

        this.extUtils = extUtils;
    }

    public void setGameLoader(GameLoader gameLoader) {

        this.gameLoader = gameLoader;
    }

    public void setStageBuilder(GameStageFactory stageBuilder) {

        this.stageBuilder = stageBuilder;
    }

    public void setFrameController(FrameController frameController) {

        this.frameController = frameController;
    }

    @Override
    public void create() {

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

        StageResult defaultResult = new StageResult();
        GameProgress progress = gameLoader.load();
        defaultResult.setGameProgress(progress);

        return defaultResult;
    }

    @Override
    public void render() {

        extUtils.clearScreen();

        if (checkFrame())
            stage.act(extUtils.getDeltaTime());

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
    public void resize(int width, int height) {

        if (stage == null)
            return;

        stage.updateViewport(width, height, true);
    }

    Stages getCurrentStage() {

        return stage.getType();
    }
}
