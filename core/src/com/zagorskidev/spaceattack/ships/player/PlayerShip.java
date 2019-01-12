package com.zagorskidev.spaceattack.ships.player;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ships.Ship;

import spaceattack.consts.Sizes;
import spaceattack.game.GameProgress;
import spaceattack.game.actors.IActor;
import spaceattack.game.actors.interfaces.RequiredOnStage;
import spaceattack.game.utils.vector.IVector;

public class PlayerShip extends Ship implements RequiredOnStage
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

	/**
	 * to remove after refactoring
	 */
	@Override
	public void notify(GameProgress state)
	{
		setLevel(state.getLevel());
	}

	@Override
	public void draw(Batch batch,float alpha)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setDestination(IVector iVector)
	{
		// TODO Auto-generated method stub

	}

	float x,y;

	@Override
	public float getX()
	{
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public float getY()
	{
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void setX(float x)
	{
		this.x = x;
	}

	@Override
	public void setY(float y)
	{
		this.y = y;
	}

	@Override
	public IActor getActor()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
