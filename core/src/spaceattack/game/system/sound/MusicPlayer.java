package spaceattack.game.system.sound;

import java.util.HashMap;
import java.util.Map;

import spaceattack.game.system.Acts;

public enum MusicPlayer
{
	INSTANCE;
	
	private PlayableMusic menuMusic;
	private Map<Acts, PlayableMusic> actMusic;
	
	private MusicPlayer() 
	{
		menuMusic = new PlayableMusic(MenuMusic.values(), null, this::updateMenuMusic);
		
		actMusic = new HashMap<>();
		actMusic.put(Acts.I, new PlayableMusic(Acts.I, Act1Music.values(), null, this::updateActMusic));
	}
	
	public void updateMenuMusic(PlayableMusic song)
	{
		menuMusic = song;
	}
	
	public void updateActMusic(Acts act, PlayableMusic song)
	{
		actMusic.put(act, song);
	}

	public void playMenu() 
	{
		if(menuMusic.isPlaying())
			return;
		
		actMusic.values().forEach(music -> music.fadeOut());
		menuMusic.fadeOut();
		menuMusic.fadeIn();
	}

	public void playAct(Acts act) 
	{
		actMusic.values().forEach(music -> music.fadeOut());
		menuMusic.fadeOut();
		actMusic.get(act).fadeIn();
	}
}
