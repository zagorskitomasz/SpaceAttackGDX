package spaceattack.ext.utils;

import com.badlogic.gdx.InputProcessor;

import spaceattack.game.input.IInputProcessor;

class GdxInput implements InputProcessor {

    private IInputProcessor inputProcessor;

    public GdxInput(IInputProcessor inputProcessor) {

        this.inputProcessor = inputProcessor;
    }

    @Override
    public boolean keyDown(int keycode) {

        return inputProcessor.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {

        return inputProcessor.keyUp(keycode);
    }

    @Override
    public boolean keyTyped(char character) {

        return inputProcessor.keyTyped(character);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return inputProcessor.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return inputProcessor.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return inputProcessor.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        return inputProcessor.mouseMoved(screenX, screenY);
    }

    @Override
    public boolean scrolled(int amount) {

        return inputProcessor.scrolled(amount);
    }

}
