package com.zagorskidev.spaceattack.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
		missile.getSound().play();
	}

	void playSound(String soundPath)
	{
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(soundPath));
		sound.play();
	}
}
