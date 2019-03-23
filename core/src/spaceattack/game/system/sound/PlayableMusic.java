package spaceattack.game.system.sound;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.badlogic.gdx.math.MathUtils;

import spaceattack.game.factories.Factories;
import spaceattack.game.system.Acts;

class PlayableMusic 
{
	private static final int MAX_VOLUME = 70;
	
	private IMusic music;
	
	public PlayableMusic(Song[] playlist, Song nowPlaying, Consumer<PlayableMusic> updater) 
	{
		Song song = chooseCurrentSong(playlist, nowPlaying);
		
		music = Factories.getMusicFactory().create(song.getPath());
		updater.accept(this);
		
		music.setLooping(false);
		music.setOnCompletionListener(music -> {
			
			music.dispose();
			PlayableMusic nextMusic = new PlayableMusic(playlist, song, updater);
			nextMusic.play();
		});
	}
	
	public PlayableMusic(Acts act, Song[] playlist, Song nowPlaying, BiConsumer<Acts, PlayableMusic> updater) 
	{
		Song song = chooseCurrentSong(playlist, nowPlaying);
		
		music = Factories.getMusicFactory().create(song.getPath());
		updater.accept(act, this);
		
		music.setLooping(false);
		music.setOnCompletionListener(music -> {
			
			music.dispose();
			PlayableMusic nextMusic = new PlayableMusic(act, playlist, song, updater);
			nextMusic.play();
		});
	}

	private void play() 
	{
		music.setVolume(MAX_VOLUME);
		music.play();
	}

	private Song chooseCurrentSong(Song[] playlist, Song nowPlaying) 
	{
		if(playlist.length == 1)
			return playlist[0];
		else
			return chooseSong(playlist, nowPlaying);
	}

	private Song chooseSong(Song[] playlist, Song nowPlaying) 
	{
		Song nextSong = null;
		do
		{
			int index = MathUtils.random(playlist.length - 1);
			nextSong = playlist[index];
		}
		while(nowPlaying != null && nowPlaying.equals(nextSong));
		
		return nextSong;
	}

	public void fadeIn() 
	{
		new Thread(() -> {
			
			music.setVolume(0);
			music.play();
			
			for(float i = 1; i <= MAX_VOLUME; i++)
			{
				music.setVolume(i / 100);
				try 
				{
					Thread.sleep(20);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
			
		}).start();
	}

	public void fadeOut()
	{
		if(music == null || !music.isPlaying())
			return;
		
		new Thread(() -> {
			for(float i = MAX_VOLUME; i >= 0; i--)
			{
				music.setVolume(i / 100);
				try 
				{
					Thread.sleep(20);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
			music.pause();
			
		}).start();
	}

	public boolean isPlaying() 
	{
		return music.isPlaying();
	}
}