package spaceattack.ext.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import spaceattack.game.system.sound.ISound;

class GdxSound implements ISound,Sound
{
	private Sound sound;

	public GdxSound(String path)
	{
		sound = Gdx.audio.newSound(Gdx.files.internal(path));
	}

	@Override
	public long play()
	{
		return sound.play();
	}

	@Override
	public long play(float volume)
	{
		return sound.play(volume);
	}

	@Override
	public long play(float volume,float pitch,float pan)
	{
		return sound.play(volume, pitch, pan);
	}

	@Override
	public long loop()
	{
		return sound.loop();
	}

	@Override
	public long loop(float volume)
	{
		return sound.loop(volume);
	}

	@Override
	public long loop(float volume,float pitch,float pan)
	{
		return sound.loop(volume, pitch, pan);
	}

	@Override
	public void stop()
	{
		sound.stop();
	}

	@Override
	public void pause()
	{
		sound.pause();
	}

	@Override
	public void resume()
	{
		sound.resume();
	}

	@Override
	public void dispose()
	{
		sound.dispose();
	}

	@Override
	public void stop(long soundId)
	{
		sound.stop(soundId);
	}

	@Override
	public void pause(long soundId)
	{
		sound.pause(soundId);
	}

	@Override
	public void resume(long soundId)
	{
		sound.resume(soundId);
	}

	@Override
	public void setLooping(long soundId,boolean looping)
	{
		sound.setLooping(soundId, looping);
	}

	@Override
	public void setPitch(long soundId,float pitch)
	{
		sound.setPitch(soundId, pitch);
	}

	@Override
	public void setVolume(long soundId,float volume)
	{
		sound.setVolume(soundId, volume);
	}

	@Override
	public void setPan(long soundId,float pan,float volume)
	{
		sound.setPan(soundId, pan, volume);
	}
}
