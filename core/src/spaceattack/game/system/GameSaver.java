package spaceattack.game.system;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import spaceattack.consts.Paths;
import spaceattack.game.GameProgress;
import spaceattack.game.utils.IUtils;

public class GameSaver
{
	private IUtils utils;
	private IFileHandle file;

	private Lock lock;

	GameSaver()
	{
		lock = new ReentrantLock();
	}

	void setUtils(IUtils utils)
	{
		this.utils = utils;
	}

	public void save(GameProgress progress)
	{
		try
		{
			lock.lock();

			loadFromFilesystem();

			String content = utils.objectToString(progress, GameProgress.class);
			writeToFile(content);
		}
		finally
		{
			lock.unlock();
		}
	}

	private void loadFromFilesystem()
	{
		file = utils.loadFile(Paths.SAVE);
	}

	public void writeToFile(String fileContent)
	{
		file.writeString(fileContent, false);
	}
}
