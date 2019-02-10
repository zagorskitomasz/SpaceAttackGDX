package spaceattack.game.weapons.missiles;

import spaceattack.game.actors.interfaces.Ignitable;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.FrameController;
import spaceattack.game.system.graphics.Animations;

public enum BurnerBuilder
{
	INSTANCE;

	public Burner build(Ignitable ignitable)
	{
		Burner burner = new Burner();

		burner.setBurningAnimation(Animations.FIRE.getAnimation());
		burner.setBurningController(new FrameController(Factories.getUtilsFactory().create(), 1));
		burner.setIgnitable(ignitable);

		return burner;
	}
}
