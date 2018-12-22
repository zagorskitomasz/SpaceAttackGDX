package com.zagorskidev.spaceattack.stages.impl;

import com.zagorskidev.spaceattack.consts.Paths;
import com.zagorskidev.spaceattack.stages.GameplayStage;

public class Mission1Stage extends GameplayStage
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
		addActor(createImage(Paths.Graphics.UI.M1_BACKGROUND, 0, 0));
	}

	private void addPlayersShip()
	{
		addActorBeforeGUI(createPlayersShip().getActor());
	}
}
