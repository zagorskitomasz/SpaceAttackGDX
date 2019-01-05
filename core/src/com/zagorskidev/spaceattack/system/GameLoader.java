package com.zagorskidev.spaceattack.system;

import java.io.InputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.zagorskidev.spaceattack.consts.Paths;

public enum GameLoader
{
	INSTANCE;

	private FileHandle file;
	private InputStream fileContent;

	private Lock lock;

	private GameLoader()
	{
		lock = new ReentrantLock();
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
		fileContent = readData();

		Json json = new Json();
		return json.fromJson(GameProgress.class, fileContent);
	}

	void loadFromFilesystem()
	{
		file = Gdx.files.local(Paths.SAVE);
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

	InputStream readData()
	{
		if (file == null)
			loadFromFilesystem();

		return file.read();
	}
}
