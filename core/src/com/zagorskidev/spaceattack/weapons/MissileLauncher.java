package com.zagorskidev.spaceattack.weapons;

import com.zagorskidev.spaceattack.stages.GameplayStage;
import com.zagorskidev.spaceattack.weapons.missiles.Missile;

public class MissileLauncher implements IMissileLauncher
{
	private GameplayStage stage;

	public MissileLauncher(GameplayStage stage)
	{
		this.stage = stage;
	}

	@Override
	public void launch(Missile missile)
	{
		stage.addActorBeforeGUI(missile);
		missile.setActors(stage.getActors());
	}
}
