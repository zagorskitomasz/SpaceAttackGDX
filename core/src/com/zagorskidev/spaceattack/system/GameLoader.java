package com.zagorskidev.spaceattack.system;

import java.io.InputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.zagorskidev.spaceattack.Consts;
import com.zagorskidev.spaceattack.stages.IStage;

public enum GameLoader
{
	INSTANCE;

	private FileHandle file;
	private InputStream fileContent;

	public GameProgress load(IStage stage)
	{
		loadFromFilesystem();

		if (isFileExists())
			return parse();

		return new GameProgress();
	}

	private GameProgress parse()
	{
		fileContent = readData();

		Json json = new Json();
		return json.fromJson(GameProgress.class, fileContent);
	}

	void loadFromFilesystem()
	{
		file = Gdx.files.local(Consts.SAVE_FILE);
	}

	boolean isFileExists()
	{
		if (file == null)
			loadFromFilesystem();

		return file.exists();
	}

	InputStream readData()
	{
		if (file == null)
			loadFromFilesystem();

		return file.read();
	}
}
