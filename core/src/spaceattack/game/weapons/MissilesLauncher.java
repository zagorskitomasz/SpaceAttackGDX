package spaceattack.game.weapons;

import spaceattack.game.stages.IGameStage;
import spaceattack.game.weapons.missiles.Missile;

public class MissilesLauncher
{
	private IGameStage stage;

	public MissilesLauncher(IGameStage stage)
	{
		this.stage = stage;
	}

	public void launch(Missile missile)
	{
		stage.addActorBeforeGUI(missile);
		missile.setActors(stage.getActors());
		missile.playSound();
	}
}
