package com.zagorskidev.spaceattack;

import com.badlogic.gdx.ApplicationAdapter;

import spaceattack.game.main.GameFactory;
import spaceattack.game.main.SpaceAttackGame;

public class SpaceAttackGDX extends ApplicationAdapter
{
	private SpaceAttackGame game;

	@Override
	public void create()
	{
		game = GameFactory.INSTANCE.create();
		game.create();
	}

	@Override
	public void render()
	{
		game.render();
	}

	@Override
	public void resize(int width,int height)
	{
		game.resize(width, height);
	}
}
