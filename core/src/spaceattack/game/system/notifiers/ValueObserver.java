package spaceattack.game.system.notifiers;

import java.util.function.Consumer;

import spaceattack.game.system.notifiers.IObserver;

public class ValueObserver implements IObserver<Float>
{
	private Consumer<Float> action;

	public ValueObserver(Consumer<Float> action)
	{
		this.action = action;
	}

	@Override
	public void notify(Float state)
	{
		action.accept(state);
	}
}
