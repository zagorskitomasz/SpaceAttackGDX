package com.zagorskidev.spaceattack;

import com.badlogic.gdx.ApplicationAdapter;

import spaceattack.ext.actor.ExtActorFactory;
import spaceattack.ext.animation.ExtAnimationFactory;
import spaceattack.ext.button.ExtTextButtonsFactory;
import spaceattack.ext.button.image.ExtImageButtonFactory;
import spaceattack.ext.sound.ExtMusicFactory;
import spaceattack.ext.sound.ExtSoundFactory;
import spaceattack.ext.stage.ExtStageFactory;
import spaceattack.ext.texture.ExtTextureFactory;
import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.GameFactory;
import spaceattack.game.IGame;
import spaceattack.game.factories.Factories;

public class SpaceAttackGDX extends ApplicationAdapter
{
	private IGame game;

	@Override
	public void create()
	{
		initFactories();
		game = GameFactory.INSTANCE.create();
		game.create();
	}

	private void initFactories()
	{
		Factories.setSoundFactory(ExtSoundFactory.INSTANCE);
		Factories.setTextureFactory(ExtTextureFactory.INSTANCE);
		Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);
		Factories.setStageFactory(ExtStageFactory.INSTANCE);
		Factories.setActorFactory(ExtActorFactory.INSTANCE);
		Factories.setVectorFactory(ExtVectorFactory.INSTANCE);
		Factories.setTextButtonFactory(ExtTextButtonsFactory.INSTANCE);
		Factories.setImageButtonFactory(ExtImageButtonFactory.INSTANCE);
		Factories.setAnimationFactory(ExtAnimationFactory.INSTANCE);
		Factories.setMusicFactory(ExtMusicFactory.INSTANCE);
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
