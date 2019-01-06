package com.zagorskidev.spaceattack.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import spaceattack.consts.Paths;
import spaceattack.game.stages.AbstractStage;

public abstract class UIStage extends AbstractStage
{
	public Skin getSkin()
	{
		return new Skin(Gdx.files.internal(Paths.UI_STYLE));
	}

	@Override
	public InputProcessor getInputProcessor()
	{
		return this;
	}
}
