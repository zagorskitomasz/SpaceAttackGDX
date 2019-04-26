package spaceattack.game.buttons;

import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Textures;

public enum AcceleratorFactory {
    INSTANCE;

    public Accelerator create() {

        IProgressButton progressButton = Factories.getImageButtonsFactory().create(Textures.JOYSTICK_BG.getTexture(),
                Textures.JOYSTICK_HEAD.getTexture());
        progressButton.setPosition(60, 80);

        Accelerator accelerator = new Accelerator();
        accelerator.setProgressButton(progressButton);
        progressButton.setGameActor(accelerator);

        return accelerator;
    }
}
