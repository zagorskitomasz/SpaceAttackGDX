package com.zagorskidev.spaceattack.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.zagorskidev.spaceattack.Consts;

public abstract class UIStage extends AbstractStage
{
	public Skin getSkin()
	{
		return new Skin(Gdx.files.internal(Consts.UI_STYLE_FILE));
	}
}
