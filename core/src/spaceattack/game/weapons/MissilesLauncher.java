package spaceattack.game.weapons;

import spaceattack.game.actors.interfaces.Launchable;
import spaceattack.game.stages.IGameStage;

public class MissilesLauncher {

    private final IGameStage stage;

    public MissilesLauncher(final IGameStage stage) {

        this.stage = stage;
    }

    public void launch(final Launchable launchable) {

        stage.addActorJustBeforeGUI(launchable);
        launchable.setActors(stage.getActors());
        launchable.launched();
    }
}
