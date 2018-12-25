package com.zagorskidev.spaceattack.ships.player;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.zagorskidev.spaceattack.graphics.Sizes;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ships.Ship;
import com.zagorskidev.spaceattack.system.GameProgress;

public class PlayerShip extends Ship
{
	private Map<IShip.Turn, Texture> textures;

	PlayerShip()
	{
		super();
		setPosition(Sizes.gameWidth() * 0.5f, Sizes.gameHeight() * 0.3f);
	}

	void loadComplexGraphics(Texture front,Texture right,Texture left)
	{
		textures = new HashMap<>();
		textures.put(IShip.Turn.FRONT, front);
		textures.put(IShip.Turn.LEFT, left);
		textures.put(IShip.Turn.RIGHT, right);

		currentTexture = textures.get(IShip.Turn.FRONT);
	}

	@Override
	protected void fly()
	{
		Turn turn = engine.fly();

		if (textures != null)
			currentTexture = textures.get(turn);
	}

	@Override
	public void notify(GameProgress state)
	{
		setLevel(state.getLevel());
	}
}
