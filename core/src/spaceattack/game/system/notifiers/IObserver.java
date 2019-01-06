package spaceattack.game.system.notifiers;

public interface IObserver<T>
{
	public void notify(T state);
}
