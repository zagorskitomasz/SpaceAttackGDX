package spaceattack.game.system;

import java.io.InputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import spaceattack.consts.Paths;
import spaceattack.game.GameProgress;
import spaceattack.game.utils.IUtils;

public class GameLoader
{
	private IUtils utils;
	private IFileHandle file;

	private Lock lock;

	GameLoader()
	{
		lock = new ReentrantLock();
	}

	void setUtils(IUtils utils)
	{
		this.utils = utils;
	}

	public GameProgress load()
	{
		try
		{
			lock.lock();

			loadFromFilesystem();

			if (fileExists())
				return parse();

			return new GameProgress();
		}
		finally
		{
			lock.unlock();
		}
	}

	private GameProgress parse()
	{
		InputStream fileContent = readData();

		return utils.streamToObject(GameProgress.class, fileContent);
	}

	private void loadFromFilesystem()
	{
		file = utils.loadFile(Paths.SAVE);
	}

	public boolean fileExists()
	{
		try
		{
			lock.lock();

			if (file == null)
				loadFromFilesystem();

			return file.exists();
		}
		finally
		{
			lock.unlock();
		}
	}

	private InputStream readData()
	{
		if (file == null)
			loadFromFilesystem();

		return file.read();
	}
}
