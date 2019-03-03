package spaceattack.game.buttons;

import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;

public enum AcceleratorFactory {
	INSTANCE;

	public Accelerator create() 
	{
		IProgressButton progressButton = Factories.getImageButtonsFactory().create(Textures.SPEED_BAR.getTexture(), Textures.LEVER.getTexture());
		progressButton.setPosition(0, 0);

		Accelerator accelerator = new Accelerator();
		accelerator.setProgressButton(progressButton);
		progressButton.setGameActor(accelerator);

		return accelerator;
	}
}
