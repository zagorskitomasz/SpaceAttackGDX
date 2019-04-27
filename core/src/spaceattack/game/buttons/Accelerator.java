package spaceattack.game.buttons;

import spaceattack.game.actors.IActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.batch.IBatch;
import spaceattack.game.factories.Factories;
import spaceattack.game.input.IGameplayInput;
import spaceattack.game.utils.NumbersUtils;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;

public class Accelerator implements IGameActor, IAccelerator {

    private IVectorFactory factory;
    private IProgressButton button;
    private IGameplayInput processor;
    private float acceleratorYPercent;
    private float acceleratorXPercent;
    private IVector center;

    public Accelerator() {

        factory = Factories.getVectorFactory();
    }

    @Override
    public IActor getActor() {

        return button;
    }

    public void setProgressButton(IProgressButton progressButton) {

        button = progressButton;
        center = factory.create(0, 0);
    }

    public void setInputProcessor(IGameplayInput processor) {

        this.processor = processor;
    }

    @Override
    public void act(float delta) {

        IVector touch = processor.getTouch();
        if (touch == null) {
            acceleratorYPercent = 0;
            acceleratorXPercent = 0;
            button.release();
        }
        else {
            IVector screenTouch = button.screenToStageCoordinates(touch);
            if (isButtonPressed(screenTouch) || button.wasNotReleased()) {
                acceleratorYPercent = ((screenTouch.getY() - button.getY()) * 100 / button.getHeight()) * 2 - 100;
                acceleratorXPercent = ((screenTouch.getX() - button.getX()) * 100 / button.getWidth()) * 2 - 100;
                normalize();
            }
            else {
                acceleratorYPercent = 0;
                acceleratorXPercent = 0;
            }
        }
        float buttonX = (acceleratorXPercent * button.getWidth()) / 200 + button.getX() + button.getWidth() / 2;
        float buttonY = (acceleratorYPercent * button.getHeight()) / 200 + button.getY() + button.getHeight() / 2;
        button.setJoystickPosition(buttonX, buttonY);
    }

    private void normalize() {

        double vectorLength = NumbersUtils.distance(center, factory.create(acceleratorXPercent, acceleratorYPercent));

        if (vectorLength > 100) {
            double factor = vectorLength / 100;
            acceleratorXPercent /= factor;
            acceleratorYPercent /= factor;
        }
    }

    private boolean isButtonPressed(IVector screenTouch) {

        boolean touched = screenTouch.getX() >= button.getX() && screenTouch.getX() <= button.getX() + button.getWidth()
                && screenTouch.getY() >= button.getY() && screenTouch.getY() <= button.getY() + button.getHeight();

        if (touched)
            button.keep();

        return touched;
    }

    @Override
    public float getVerticalAcceleration() {

        return acceleratorYPercent;
    }

    @Override
    public float getHorizontalAcceleration() {

        return acceleratorXPercent;
    }

    @Override
    public void setActor(IActor actor) {

        // do nothing
    }

    @Override
    public void draw(IBatch batch, float alpha) {

        // do nothing
    }
}
