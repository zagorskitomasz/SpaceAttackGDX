package spaceattack.game.system.sound;

import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.factories.Factories;
import spaceattack.game.system.Acts;

public class MusicPlayerTest 
{
	@Mock
	private IMusicFactory factory;
	
	@Mock
	private IMusic menuMusic;
	
	@Mock
	private IMusic actMusic;
	
	private MusicPlayer player;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		
		doReturn(menuMusic).when(factory).create(MenuMusic.MENU.getPath());
		doReturn(actMusic).when(factory).create(Act1Music.SONG_1.getPath());
		doReturn(actMusic).when(factory).create(Act1Music.SONG_2.getPath());
		doReturn(actMusic).when(factory).create(Act1Music.SONG_3.getPath());
		doReturn(actMusic).when(factory).create(Act1Music.SONG_4.getPath());
		doReturn(actMusic).when(factory).create(Act1Music.SONG_5.getPath());
		
		Factories.setMusicFactory(factory);
		player = MusicPlayer.INSTANCE;
	}
	
	/**
	 * One big test due to global singleton enum instance
	 */
	@Test
	public void playingMusic() throws InterruptedException 
	{
		doReturn(menuMusic).when(factory).create(MenuMusic.MENU.getPath());
		
		player.playMenu();
		Thread.sleep(1400);
		
		verify(menuMusic).play();
		verify(menuMusic,times(41)).setVolume(anyFloat());
		doReturn(true).when(menuMusic).isPlaying();
		
		player.playAct(Acts.I);
		Thread.sleep(1400);
		
		verify(menuMusic).pause();
		verify(actMusic).play();
		doReturn(false).when(menuMusic).isPlaying();
		doReturn(true).when(actMusic).isPlaying();
		
		player.playMenu();
		Thread.sleep(1400);
		
		verify(actMusic).pause();
		verify(menuMusic,times(2)).play();
	}
}
