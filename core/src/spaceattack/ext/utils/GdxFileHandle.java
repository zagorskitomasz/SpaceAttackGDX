package spaceattack.ext.utils;

import java.io.InputStream;

import com.badlogic.gdx.files.FileHandle;

import spaceattack.game.system.IFileHandle;

class GdxFileHandle implements IFileHandle
{
	private FileHandle handle;

	public GdxFileHandle(FileHandle handle)
	{
		this.handle = handle;
	}

	@Override
	public InputStream read()
	{
		return handle.read();
	}

	@Override
	public boolean exists()
	{
		return handle.exists();
	}

	@Override
	public void writeString(String fileContent,boolean append)
	{
		handle.writeString(fileContent, append);
	}
}
