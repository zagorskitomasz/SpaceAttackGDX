package com.zagorskidev.spaceattack.weapons;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.zagorskidev.spaceattack.stages.GameplayStage;
import com.zagorskidev.spaceattack.weapons.missiles.AbstractMissile;

public class MissileLauncherTest
{
	@Mock
	private GameplayStage stage;

	@Mock
	private AbstractMissile missile;

	@Mock
	private Array<Actor> actors;

	private IMissileLauncher launcher;

	@Before
	public void setUp()
	{
		initMocks(this);
		launcher = new MissileLauncher(stage);
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
}
