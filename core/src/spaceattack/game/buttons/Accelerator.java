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

    private final IVectorFactory factory;
    private IProgressButton button;
    private IGameplayInput processor;
    private float acceleratorYPercent;
    private float acceleratorXPercent;
    private IVector center;
    private int currentPointer;

    public Accelerator() {

        factory = Factories.getVectorFactory();
    }

    @Override
    public IActor getActor() {

        return button;
    }

    public void setProgressButton(final IProgressButton progressButton) {

        button = progressButton;
        center = factory.create(0, 0);
    }

    public void setInputProcessor(final IGameplayInput processor) {

        this.processor = processor;
    }

    @Override
    public void act(final float delta) {

        boolean isCurrentPointerStillTouched = false;
        for (int pointer = 0; pointer < 20; pointer++) {
            IVector touch = processor.getTouch(pointer);
            if ((currentPointer == pointer || currentPointer < 0) && touch != null) {
                isCurrentPointerStillTouched = true;
                IVector screenTouch = button.screenToStageCoordinates(touch);
                if (isButtonPressed(screenTouch) || button.wasNotReleased()) {
                    acceleratorYPercent = ((screenTouch.getY() - button.getY()) * 100 / button.getHeight()) * 2 - 100;
                    acceleratorXPercent = ((screenTouch.getX() - button.getX()) * 100 / button.getWidth()) * 2 - 100;
                    normalize();
                    currentPointer = pointer;
                }
            }
        }
        if (!isCurrentPointerStillTouched) {
            acceleratorYPercent = 0;
            acceleratorXPercent = 0;
            button.release();
            currentPointer = -1;
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

    private boolean isButtonPressed(final IVector screenTouch) {

        boolean touched = screenTouch.getX() >= button.getX() && screenTouch.getX() <= button.getX() + button.getWidth()
                && screenTouch.getY() >= button.getY() && screenTouch.getY() <= button.getY() + button.getHeight();

        if (touched) {
            button.keep();
        }

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
    public void setActor(final IActor actor) {

        // do nothing
    }

    @Override
    public void draw(final IBatch batch, final float alpha) {

        // do nothing
    }
}
