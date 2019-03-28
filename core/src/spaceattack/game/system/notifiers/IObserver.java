package spaceattack.game.system.notifiers;

@FunctionalInterface
public interface IObserver<T>
{
	public void notify(T state);
}
