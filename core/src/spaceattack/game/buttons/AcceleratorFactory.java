package spaceattack.game.buttons;

import spaceattack.game.engines.IEngine;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;

public enum AcceleratorFactory {
	INSTANCE;

	public Accelerator create(IEngine engine) 
	{
		IProgressButton progressButton = Factories.getImageButtonsFactory().create(Textures.SPEED_BAR.getTexture(), Textures.LEVER.getTexture());

		Accelerator accelerator = new Accelerator();
		accelerator.setProgressButton(progressButton);
		accelerator.setPosition(0, 0);
		progressButton.setGameActor(accelerator);

		return accelerator;
	}
}
