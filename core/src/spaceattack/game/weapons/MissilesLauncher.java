package spaceattack.game.weapons;

import spaceattack.game.actors.interfaces.Launchable;
import spaceattack.game.stages.IGameStage;

public class MissilesLauncher
{
	private IGameStage stage;

	public MissilesLauncher(IGameStage stage)
	{
		this.stage = stage;
	}

	public void launch(Launchable launchable)
	{
		stage.addActorBeforeGUI(launchable);
		launchable.setActors(stage.getActors());
		launchable.playSound();
	}
}
