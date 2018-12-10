package com.zagorskidev.spaceattack.stages;

import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ships.player.PlayerShipFactory;

public class GameplayStage extends AbstractStage
{
	protected IShip createPlayersShip()
	{
		return PlayerShipFactory.INSTANCE.create(this);
	}
}
