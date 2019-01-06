package spaceattack.game.system;

import spaceattack.game.factories.Factories;

public enum GameSaverFactory
{
	INSTANCE;

	public GameSaver create()
	{
		GameSaver saver = new GameSaver();
		saver.setUtils(Factories.getUtilsFactory().create());

		return saver;
	}
}
