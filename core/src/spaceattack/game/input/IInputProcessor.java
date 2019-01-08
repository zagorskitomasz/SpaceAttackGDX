package spaceattack.game.input;

public interface IInputProcessor
{
	public default boolean keyDown(int keycode)
	{
		return true;
	}

	public default boolean keyUp(int keycode)
	{
		return true;
	}

	public default boolean keyTyped(char character)
	{
		return true;
	}

	public default boolean touchDown(int screenX,int screenY,int pointer,int button)
	{
		return true;
	}

	public default boolean touchUp(int screenX,int screenY,int pointer,int button)
	{
		return true;
	}

	public default boolean touchDragged(int screenX,int screenY,int pointer)
	{
		return true;
	}

	public default boolean mouseMoved(int screenX,int screenY)
	{
		return true;
	}

	public default boolean scrolled(int amount)
	{
		return true;
	}
}
