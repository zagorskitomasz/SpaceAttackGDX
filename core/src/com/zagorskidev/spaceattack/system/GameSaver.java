package com.zagorskidev.spaceattack.system;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.zagorskidev.spaceattack.consts.Consts;

public enum GameSaver
{
	INSTANCE;

	private FileHandle file;

	private Lock lock;

	private GameSaver()
	{
		lock = new ReentrantLock();
	}

	public void save(GameProgress progress)
	{
		try
		{
			lock.lock();

			loadFromFilesystem();

			Json json = new Json();
			String content = json.toJson(progress);
			writeToFile(content);
		}
		finally
		{
			lock.unlock();
		}
	}

	void loadFromFilesystem()
	{
		file = Gdx.files.local(Consts.SAVE_FILE);
	}

	public void writeToFile(String fileContent)
	{
		file.writeString(fileContent, false);
	}
}