package spaceattack.game.system;

import java.io.InputStream;

public interface IFileHandle
{
	public InputStream read();

	public boolean exists();

	public void writeString(String fileContent,boolean append);
}
