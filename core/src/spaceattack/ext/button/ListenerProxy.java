package spaceattack.ext.button;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import spaceattack.game.buttons.IListener;

public class ListenerProxy extends ClickListener
{
	private IListener listener;

	public ListenerProxy(IListener listener)
	{
		this.listener = listener;
	}

	@Override
	public void clicked(InputEvent event,float x,float y)
	{
		listener.clicked();
	}
}
