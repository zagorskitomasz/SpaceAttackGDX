package spaceattack.game.system.notifiers;

public interface INotifier<T> {

    public void registerObserver(IObserver<T> observer);

    public void unregisterObserver(IObserver<T> observer);
}
