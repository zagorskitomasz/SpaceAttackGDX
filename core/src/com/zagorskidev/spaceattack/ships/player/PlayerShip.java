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

	@Override
	public void loadGraphics(String texturePath)
	{
		textures = new HashMap<>();
		textures.put(IShip.Turn.FRONT, createTexture(texturePath));
		textures.put(IShip.Turn.LEFT, createTexture(texturePath.replaceAll(".png", "L.png")));
		textures.put(IShip.Turn.RIGHT, createTexture(texturePath.replaceAll(".png", "R.png")));

		currentTexture = textures.get(IShip.Turn.FRONT);
	}

	@Override
	protected void fly()
	{
		currentTexture = textures.get(engine.fly());
	}

	@Override
	public void notify(GameProgress state)
	{
		setLevel(state.getLevel());
	}
}
