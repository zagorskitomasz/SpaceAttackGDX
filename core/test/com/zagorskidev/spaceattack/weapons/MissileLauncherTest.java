package com.zagorskidev.spaceattack.weapons;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.zagorskidev.spaceattack.consts.Paths;
import com.zagorskidev.spaceattack.stages.GameplayStage;
import com.zagorskidev.spaceattack.weapons.missiles.Missile;

public class MissileLauncherTest
{
	@Mock
	private GameplayStage stage;

	@Mock
	private Missile missile;

	@Mock
	private Array<Actor> actors;

	private MissileLauncher launcher;

	@Before
	public void setUp()
	{
		initMocks(this);
		launcher = spy(new MissileLauncher(stage));
		doReturn(Paths.Sounds.RED_LASER).when(missile).getSound();
		doNothing().when(launcher).playSound(anyString());
	}

	@Test
	public void addingActorToStage()
	{
		launcher.launch(missile);
		verify(stage).addActorBeforeGUI(missile);
	}

	@Test
	public void addingActorsListReferenceToMissile()
	{
		doReturn(actors).when(stage).getActors();

		launcher.launch(missile);
		verify(missile).setActors(actors);
	}

	@Test
	public void playingSoundAfterShot()
	{
		doNothing().when(launcher).playSound(anyString());
		launcher.launch(missile);
		verify(launcher).playSound(Paths.Sounds.RED_LASER);
	}
}
