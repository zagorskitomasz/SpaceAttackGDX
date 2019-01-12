package com.zagorskidev.spaceattack.stages.impl;

import com.zagorskidev.spaceattack.graphics.Textures;
import com.zagorskidev.spaceattack.stages.GameplayStageLegacy;

public class Mission1Stage extends GameplayStageLegacy
{
	public Mission1Stage()
	{
		init();
	}

	private void init()
	{
		prepareStaticContent();
		addPlayersShip();
	}

	private void prepareStaticContent()
	{
		addActor(createImage(Textures.M1_BACKGROUND.getTexture(), 0, 0));
	}

	private void addPlayersShip()
	{
		addActorBeforeGUI(createPlayersShip().getActor());
	}
}
